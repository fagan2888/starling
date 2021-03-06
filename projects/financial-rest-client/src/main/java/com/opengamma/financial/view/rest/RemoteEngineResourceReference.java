/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.view.rest;

import java.net.URI;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.engine.resource.EngineResourceReference;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.rest.FudgeRestClient;

/**
 * Remote implementation of {@link EngineResourceReference}.
 *
 * @param <T>
 *          the type of resource
 */
public abstract class RemoteEngineResourceReference<T extends UniqueIdentifiable> implements EngineResourceReference<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoteEngineResourceReference.class);

  private final URI _baseUri;
  private final FudgeRestClient _client;
  private final ScheduledFuture<?> _scheduledHeartbeat;

  private final AtomicBoolean _isReleased = new AtomicBoolean(false);

  public RemoteEngineResourceReference(final URI baseUri, final ScheduledExecutorService scheduler) {
    this(baseUri, scheduler, FudgeRestClient.create());
  }

  public RemoteEngineResourceReference(final URI baseUri, final ScheduledExecutorService scheduler, final FudgeRestClient client) {
    _baseUri = baseUri;
    _client = client;
    _scheduledHeartbeat = scheduler.scheduleAtFixedRate(new HeartbeaterTask(client, _baseUri), DataEngineResourceManagerUris.REFERENCE_LEASE_MILLIS / 2,
        DataEngineResourceManagerUris.REFERENCE_LEASE_MILLIS / 2, TimeUnit.MILLISECONDS);
  }

  protected FudgeRestClient getClient() {
    return _client;
  }

  private void releaseImpl() {
    LOGGER.debug("Releasing {}", this);
    try {
      getClient().accessFudge(_baseUri).delete();
      LOGGER.debug("Remote for {}", this);
    } finally {
      stopHeartbeating();
    }
  }

  @Override
  protected void finalize() throws Throwable {
    if (!_isReleased.getAndSet(true)) {
      LOGGER.warn("{} has open reference at garbage collection time", this);
      releaseImpl();
    }
    super.finalize();
  }

  @Override
  public T get() {
    if (_isReleased.get()) {
      throw new IllegalStateException("The view cycle reference has been released");
    }
    final URI uri = UriBuilder.fromUri(_baseUri).path(DataEngineResourceReferenceUris.PATH_RESOURCE).build();
    return getRemoteResource(uri);
  }

  protected abstract T getRemoteResource(URI baseUri);

  @Override
  public void release() {
    if (_isReleased.getAndSet(true)) {
      LOGGER.warn("{} already released", this);
      return;
    }
    releaseImpl();
  }

  /**
   * For testing.
   */
  public void stopHeartbeating() {
    LOGGER.debug("Stopping heartbeating of {}", this);
    _scheduledHeartbeat.cancel(true);
  }

  private static final class HeartbeaterTask implements Runnable {

    private final FudgeRestClient _client;
    private final URI _baseUri;

    HeartbeaterTask(final FudgeRestClient client, final URI baseUri) {
      _client = client;
      _baseUri = baseUri;
    }

    @Override
    public void run() {
      try {
        _client.accessFudge(_baseUri).post();
      } catch (final Exception e) {
        LOGGER.warn("Failed to heartbeat view cycle reference", e);
      }
    }

  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + _baseUri + "]";
  }

}
