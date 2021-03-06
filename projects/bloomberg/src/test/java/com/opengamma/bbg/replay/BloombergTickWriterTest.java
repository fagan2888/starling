/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.bbg.replay;

import static com.opengamma.bbg.replay.BloombergTick.FIELDS_KEY;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.fudgemsg.FudgeContext;
import org.fudgemsg.FudgeMsg;
import org.fudgemsg.test.FudgeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.threeten.bp.Clock;
import org.threeten.bp.ZonedDateTime;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.bbg.replay.BloombergTicksReplayer.Mode;
import com.opengamma.bbg.test.BloombergTestUtils;
import com.opengamma.util.fudgemsg.OpenGammaFudgeContext;
import com.opengamma.util.test.TestGroup;

/**
 * Test.
 */
@Test(groups = TestGroup.UNIT_SLOW)
public class BloombergTickWriterTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(BloombergTickWriterTest.class);

  private static final FudgeContext FUDGE_CONTEXT = OpenGammaFudgeContext.getInstance();

  private static final int TICKS_GENERATOR_THREAD_SIZE = 1;
  private static final int RUN_DURATION = 5000;
  private static final long REPORT_INTERVAL = RUN_DURATION * 3;
  private static final long WRITER_SPEED_THRESHOLD = 1024000;
  private static final int MAX_QUEUE_SIZE = 1000;

  private final BlockingQueue<FudgeMsg> _allTicksQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
  private BloombergTickWriter _writer;
  private final File _rootDir = new File(SystemUtils.getJavaIoTmpDir(), "tickDataTest");
  private RandomTicksGeneratorJob _ticksGenerator;
  private final Map<String, String> _ticker2buid = ImmutableMap.of("QQQQ US Equity", "EQ0082335400001000");

  @BeforeMethod
  public void setUp(final Method m) throws Exception {
    _writer = new BloombergTickWriter(FUDGE_CONTEXT, _allTicksQueue, _ticker2buid, _rootDir.getAbsolutePath(), StorageMode.MULTI);
    _ticksGenerator = new RandomTicksGeneratorJob(new ArrayList<>(_ticker2buid.keySet()), _allTicksQueue);
    makeRootDir();
  }

  private void makeRootDir() {
    if (!_rootDir.exists()) {
      if (!_rootDir.mkdirs()) {
        throw new OpenGammaRuntimeException("unable to create root dir " + _rootDir);
      }
    }
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _writer = null;
    //clean up
    if (_rootDir.exists()) {
      FileUtils.forceDeleteOnExit(_rootDir);
    }
  }

  //-------------------------------------------------------------------------
  @Test
  public void ticksWriting() throws Exception {
    final ZonedDateTime startTime = ZonedDateTime.now(Clock.systemUTC());

    //run test for 5secs
    final long runTime = 5000;
    final ExecutorService writerExecutor = Executors.newSingleThreadExecutor();
    final Future<?> writerFuture = writerExecutor.submit(_writer);

    //create ticks generators
    final ExecutorService ticksGeneratorExec = Executors.newSingleThreadExecutor();
    final Future<?> ticksGenFuture = ticksGeneratorExec.submit(_ticksGenerator);

    LOGGER.info("Test running for {}ms to generate ticks", runTime);
    Thread.sleep(runTime);

    //terminate ticks generation after 1mins
    _ticksGenerator.terminate();
    sendTerminateMessage();

    //test should fail if ticksGenerator throws an exception
    ticksGenFuture.get();
    ticksGeneratorExec.shutdown();
    ticksGeneratorExec.awaitTermination(1, TimeUnit.SECONDS);

    //test should fail if writer throws an exception
    writerFuture.get();
    writerExecutor.shutdown();
    writerExecutor.awaitTermination(1, TimeUnit.SECONDS);

    final ZonedDateTime endTime = ZonedDateTime.now(Clock.systemUTC());

    //now lets replay generated allTicks.dat
    final Set<String> buids = Sets.newHashSet(_ticker2buid.values());
    final UnitTestTickReceiver receiver = new UnitTestTickReceiver();
    final BloombergTicksReplayer player = new BloombergTicksReplayer(Mode.AS_FAST_AS_POSSIBLE, _rootDir.getAbsolutePath(), receiver, startTime, endTime, buids);
    player.start();
    while (player.isRunning()) {
      Thread.sleep(1000);
    }
    assertTrue(receiver.count() > 0);
  }

  @Test(invocationCount = 5, successPercentage = 19)
  public void performance() throws Exception {
    final ExecutorService writerExecutor = Executors.newSingleThreadExecutor();
    final Future<?> writerFuture = writerExecutor.submit(_writer);

    final double nStartTime = System.currentTimeMillis();

    //create ticks generators
    final List<RandomTicksGeneratorJob> ticksGeneratorsList = new ArrayList<>();
    final List<Thread> ticksGeneratorThreads = new ArrayList<>();
    for (int i = 0; i < TICKS_GENERATOR_THREAD_SIZE; i++) {
      final RandomTicksGeneratorJob ticksGeneratorJob = new RandomTicksGeneratorJob(new ArrayList<>(_ticker2buid.keySet()), _allTicksQueue);
      ticksGeneratorsList.add(ticksGeneratorJob);
      final Thread thread = new Thread(ticksGeneratorJob, "TicksGenerator" + i);
      thread.start();
      ticksGeneratorThreads.add(thread);
    }

    LOGGER.info("Test running for 1min to gather stats");
    Thread.sleep(RUN_DURATION);

    for (final RandomTicksGeneratorJob ticksGeneratorJob : ticksGeneratorsList) {
      ticksGeneratorJob.terminate();
    }

    //wait for all ticksGenerator threads to finish
    for (final Thread thread : ticksGeneratorThreads) {
      thread.join();
    }

    //send terminate message for tickWriter to terminate
    sendTerminateMessage();

    //test should fail if writer throws an exception
    writerFuture.get();
    writerExecutor.shutdown();
    writerExecutor.awaitTermination(1, TimeUnit.SECONDS);

    final double nRunDuration = System.currentTimeMillis() - nStartTime;

    final double nTicks = _writer.getNTicks() / nRunDuration * 1000;
    LOGGER.info("ticks {}/s", nTicks);
    final double nWrites = _writer.getNWrites() / nRunDuration * 1000;
    LOGGER.info("fileOperations {}/s", nWrites);
    final double nBlocks = (double) _writer.getNBlocks() / (double) _writer.getNWrites();
    LOGGER.info("average blocks {}bytes", nBlocks);

    assertTrue("reportInterval > testRunTime", REPORT_INTERVAL > nRunDuration);
    if (nWrites * nBlocks < WRITER_SPEED_THRESHOLD) {
      LOGGER.warn("BloombergTickWriter looks like running really slower than {}b/s", WRITER_SPEED_THRESHOLD);
    }
  }

  private void sendTerminateMessage() throws Exception {
    _allTicksQueue.put(BloombergTickReplayUtils.getTerminateMessage());
  }

  private class UnitTestTickReceiver implements BloombergTickReceiver {
    private final Random _valueGenerator = new Random(RandomTicksGeneratorJob.RANDOM_SEED);
    private int _count;

    @Override
    public void tickReceived(final BloombergTick msg) {
      _count++;
      final FudgeMsg randomStandardTick = BloombergTestUtils.makeRandomStandardTick(_valueGenerator, FUDGE_CONTEXT);
      final FudgeMsg actual = msg.getFields();
      final FudgeMsg expected = randomStandardTick.getMessage(FIELDS_KEY);
      assertAllFieldsMatch(expected, actual);
    }

    int count() {
      return _count;
    }
  }

  private static void assertAllFieldsMatch(final FudgeMsg expectedMsg, final FudgeMsg actualMsg) {
    FudgeUtils.assertAllFieldsMatch(expectedMsg, actualMsg, true);
  }

}
