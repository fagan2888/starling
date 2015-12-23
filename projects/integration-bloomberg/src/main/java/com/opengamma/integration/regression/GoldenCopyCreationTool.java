/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.opengamma.component.tool.AbstractTool;
import com.opengamma.integration.tool.IntegrationToolContext;
import com.opengamma.scripts.Scriptable;

/**
 *
 */
@Scriptable
public class GoldenCopyCreationTool extends AbstractTool<IntegrationToolContext> {

  /**
   * The version name to use against calculation results in the golden copy.
   */
  public static final String GOLDEN_COPY_VERSION_NAME = "Golden Copy";

  /** Logger */
  private static final Logger s_logger = LoggerFactory.getLogger(GoldenCopyCreationTool.class);
  /**
   * Unsupported character sequence.
   */
  private static final ImmutableSet<CharSequence> UNSUPPORTED_CHAR_SEQUENCES = ImmutableSet.<CharSequence>of("/");

  //-------------------------------------------------------------------------
  /**
   * Main method to run the tool.
   *
   * @param args  the standard tool arguments, not null
   */
  public static void main(final String[] args) {
    new GoldenCopyCreationTool().invokeAndTerminate(args);
  }

  //-------------------------------------------------------------------------
  @Override
  protected void doRun() throws Exception {
    final CommandLine commandLine = getCommandLine();
    final String regressionDirectory = commandLine.getOptionValue("db-dump-output-dir");
    final GoldenCopyCreator goldenCopyCreator = new GoldenCopyCreator(getToolContext());
    final String[] viewSnapshotPairs = commandLine.getArgs();
    validateAsFilesystemNames(viewSnapshotPairs);
    Preconditions.checkArgument(viewSnapshotPairs.length % 2 == 0,
                                "Should be an even number of view/snapshot pairs. Found %s",
                                Arrays.toString(viewSnapshotPairs));

    for (int i = 0; i < viewSnapshotPairs.length; i += 2) {
      final String viewName = viewSnapshotPairs[i];
      final String snapshotName = viewSnapshotPairs[i + 1];
      s_logger.info("Executing {} against snapshot {}", viewName, snapshotName);
      final GoldenCopy goldenCopy = goldenCopyCreator.run(viewName, snapshotName, GOLDEN_COPY_VERSION_NAME);
      s_logger.info("Persisting golden copy for {} against snapshot {}", viewName, snapshotName);
      new GoldenCopyPersistenceHelper(new File(regressionDirectory)).save(goldenCopy);
      s_logger.info("Persisted golden copy for {} against snapshot {}", viewName, snapshotName);
    }
    final File dumpFile = new File(regressionDirectory, GoldenCopyDumpCreator.DB_DUMP_ZIP);
    final RegressionIO io = ZipFileRegressionIO.createWriter(dumpFile, new FudgeXMLFormat());
    final IntegrationToolContext tc = getToolContext();

    final GoldenCopyDumpCreator goldenCopyDumpCreator = new GoldenCopyDumpCreator(io, tc);

    s_logger.info("Persisting db dump with tracked data");
    goldenCopyDumpCreator.execute();
  }

  @Override
  protected Options createOptions(final boolean mandatoryConfig) {
    final Options options = super.createOptions(mandatoryConfig);
    options.addOption(createDbDumpOutputDirectory());
    return options;
  }

  private static Option createDbDumpOutputDirectory() {
    final String description = "Where to write the golden copy(ies) and the corresponding dump.";
    final String argName = "outputdir";
    final String longOpt = "db-dump-output-dir";
    final Option option = new Option("o", longOpt, true, description);
    option.setRequired(true);
    option.setArgName(argName);
    return option;
  }

  private void validateAsFilesystemNames(final String[] names) {
    for (final String name : names) {
      for (final CharSequence unsupportedCharSequence : UNSUPPORTED_CHAR_SEQUENCES) {
        Preconditions.checkArgument(!name.contains(unsupportedCharSequence), "Unsupported char sequence '%s' found in string '%s'", unsupportedCharSequence, name);
      }
    }
  }

}
