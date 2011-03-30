/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view.client;

import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.engine.view.ComputationResultListener;
import com.opengamma.engine.view.DeltaComputationResultListener;
import com.opengamma.engine.view.ViewComputationResultModel;
import com.opengamma.engine.view.ViewDeltaResultModel;
import com.opengamma.engine.view.ViewProcessListener;
import com.opengamma.engine.view.ViewProcessorImpl;
import com.opengamma.engine.view.calc.ViewCycleReference;
import com.opengamma.engine.view.calc.ViewCycleRetainer;
import com.opengamma.engine.view.client.merging.RateLimitingMergingViewProcessListener;
import com.opengamma.engine.view.compilation.ViewCompilationListener;
import com.opengamma.engine.view.compilation.ViewEvaluationModel;
import com.opengamma.engine.view.execution.ViewExecutionOptions;
import com.opengamma.engine.view.permission.ViewPermissionProvider;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.livedata.UserPrincipal;
import com.opengamma.util.ArgumentChecker;

/**
 * Default implementation of {@link ViewClient}.
 */
public class ViewClientImpl implements ViewClient {
  
  private static final Logger s_logger = LoggerFactory.getLogger(ViewClientImpl.class);
  
  private final ReentrantLock _clientLock = new ReentrantLock();
  
  private final UniqueIdentifier _id;
  private final ViewProcessorImpl _viewProcessor;
  private final UserPrincipal _user;
  private final ViewCycleRetainer _latestCycleRetainer;
  
  private boolean _isViewCycleAccessSupported;
  private ViewClientState _state = ViewClientState.STARTED;
  private boolean _isAttached;
  
  // Per-process state
  private boolean _isBatchController;
  private volatile ViewPermissionProvider _permissionProvider;
  private volatile boolean _canAccessCompilationOutput;
  private volatile boolean _canAccessComputationResults;
  private volatile CountDownLatch _completionLatch = new CountDownLatch(0);
  private final AtomicReference<ViewComputationResultModel> _latestResult = new AtomicReference<ViewComputationResultModel>();
  
  private final ViewProcessListener _mergedViewProcessListener;
  private final RateLimitingMergingViewProcessListener _mergingViewProcessListener;
  
  private ViewCompilationListener _userCompilationListener;
  private ComputationResultListener _userResultListener;
  private DeltaComputationResultListener _userDeltaListener;
  
  /**
   * Constructs an instance.
   *
   * @param id  the unique identifier assigned to this view client
   * @param viewProcessor  the parent view processor to which this client belongs
   * @param user  the user who owns this client
   * @param timer  the timer to use for scheduled tasks
   */
  public ViewClientImpl(UniqueIdentifier id, ViewProcessorImpl viewProcessor, UserPrincipal user, Timer timer) {
    ArgumentChecker.notNull(id, "id");
    ArgumentChecker.notNull(viewProcessor, "viewProcessor");
    ArgumentChecker.notNull(user, "user");
    ArgumentChecker.notNull(timer, "timer");
    
    _id = id;
    _viewProcessor = viewProcessor;
    _user = user;
    _latestCycleRetainer = new ViewCycleRetainer(viewProcessor.getViewCycleManager());
    
    _mergedViewProcessListener = new ViewProcessListener() {
      
      @Override
      public boolean isDeltaResultRequired() {
        return _userDeltaListener != null;
      }

      @Override
      public void compiled(ViewEvaluationModel viewEvaluationModel) {
        _canAccessCompilationOutput = _permissionProvider.canAccessCompilationOutput(getUser(), viewEvaluationModel);
        _canAccessComputationResults = _permissionProvider.canAccessComputationResults(getUser(), viewEvaluationModel);
        
        // TODO [PLAT-1144] -- so we know whether or not the user is permissioned for various things, but what do we
        // pass to downstream listeners? Some special perm denied message in place of results on each computation
        // cycle?
        
        ViewCompilationListener compilationListener = _userCompilationListener;
        if (compilationListener != null) {
          compilationListener.viewCompiled(viewEvaluationModel);
        }
      }

      @Override
      public void result(ViewComputationResultModel fullResult, ViewDeltaResultModel deltaResult) {
        updateLatestResult(fullResult);
        ComputationResultListener listener = _userResultListener;
        if (listener != null) {
          listener.computationResultAvailable(fullResult);
        }
        if (deltaResult != null) {
          DeltaComputationResultListener deltaListener = _userDeltaListener;
          if (deltaListener != null) {
            deltaListener.deltaResultAvailable(deltaResult);
          }
        }
      }
      
      @Override
      public void processCompleted() {
        ViewClientImpl.this.processCompleted();
      }

      @Override
      public void shutdown() {
        ViewClientImpl.this.detachFromViewProcess();
      }
      
    };
    
    _mergingViewProcessListener = new RateLimitingMergingViewProcessListener(_mergedViewProcessListener, getViewProcessor().getViewCycleManager(), timer);
    _mergingViewProcessListener.setPaused(true);
  }

  @Override
  public UniqueIdentifier getUniqueId() {
    return _id;
  }
  
  @Override
  public UserPrincipal getUser() {
    return _user;
  }
  
  @Override
  public ViewProcessorImpl getViewProcessor() {
    return _viewProcessor;
  }
  
  @Override
  public ViewClientState getState() {
    return _state;
  }
  
  //-------------------------------------------------------------------------
  @Override
  public boolean isAttached() {
    return _isAttached;
  }
  
  @Override
  public void attachToViewProcess(String viewDefinitionName, ViewExecutionOptions executionOptions) {
    attachToViewProcess(viewDefinitionName, executionOptions, false);
  }
  
  @Override
  public void attachToViewProcess(String viewDefinitionName, ViewExecutionOptions executionOptions, boolean newBatchProcess) {
    _clientLock.lock();
    try {
      checkNotTerminated();
      
      // The client is detached right now so the merging update listener is paused. Although the following calls may
      // cause initial updates to be pushed through, they will not be seen until the merging update listener is
      // resumed, at which point the new permission provider will be in place. 
      if (newBatchProcess) {
        _permissionProvider = getViewProcessor().attachClientToBatchViewProcess(getUniqueId(), _mergingViewProcessListener, viewDefinitionName, executionOptions);
      } else {
        _permissionProvider = getViewProcessor().attachClientToSharedViewProcess(getUniqueId(), _mergingViewProcessListener, viewDefinitionName, executionOptions);
      }
      attachToViewProcessCore(newBatchProcess);
    } finally {
      _clientLock.unlock();
    }
  }
  
  @Override
  public void attachToViewProcess(UniqueIdentifier processId) {
    _clientLock.lock();
    try {
      checkNotTerminated();
      _permissionProvider = getViewProcessor().attachClientToViewProcess(getUniqueId(), _mergingViewProcessListener, processId);
      attachToViewProcessCore(false);
    } finally {
      _clientLock.unlock();
    }
  }
  
  private void attachToViewProcessCore(boolean isBatchController) {
    _isBatchController = isBatchController;
    _isAttached = true;
    boolean isPaused = getState() == ViewClientState.PAUSED;
    _mergingViewProcessListener.setPaused(isPaused);
    _completionLatch = new CountDownLatch(1);
  }

  @Override
  public void detachFromViewProcess() {
    _clientLock.lock();
    try {
      processCompleted();
      getViewProcessor().detachClientFromViewProcess(getUniqueId());
      getLatestCycleRetainer().replaceRetainedCycle(null);
      _mergingViewProcessListener.setPaused(true);
      _mergingViewProcessListener.reset();
      _isBatchController = false;
      _latestResult.set(null);
      _isAttached = false;
      _permissionProvider = null;
    } finally {
      _clientLock.unlock();
    }
  }

  @Override
  public boolean isBatchController() {
    return _isBatchController;
  }
  
  //-------------------------------------------------------------------------  
  @Override
  public void setCompilationListener(ViewCompilationListener compilationListener) {
    _userCompilationListener = compilationListener;
  }
  
  @Override
  public void setResultListener(ComputationResultListener resultListener) {
    _userResultListener = resultListener;
  }

  @Override
  public void setDeltaResultListener(DeltaComputationResultListener deltaResultListener) {
    _userDeltaListener = deltaResultListener;
  }
  
  @Override
  public void setUpdatePeriod(long periodMillis) {
    _mergingViewProcessListener.setMinimumUpdatePeriodMillis(periodMillis);
  }
  
  //-------------------------------------------------------------------------
  @Override
  public void pause() {
    _clientLock.lock();
    try {
      checkNotTerminated();
      if (isAttached()) {
        _mergingViewProcessListener.setPaused(true);
      }
      _state = ViewClientState.PAUSED;
    } finally {
      _clientLock.unlock();
    }
  }
  
  @Override
  public void resume() {
    _clientLock.lock();
    try {
      checkNotTerminated();
      if (isAttached()) {
        _mergingViewProcessListener.setPaused(false);
      }
      _state = ViewClientState.STARTED;
    } finally {
      _clientLock.unlock();
    }
  }
  
  @Override
  public void waitForCompletion() throws InterruptedException {
    try {
      _completionLatch.await();
    } catch (InterruptedException e) {
      s_logger.debug("Interrupted while waiting for completion of the view process");
      throw e;
    }
  }
  
  @Override
  public boolean isResultAvailable() {
    return _latestResult.get() != null;
  }
  
  @Override
  public ViewComputationResultModel getLatestResult() {
    return _latestResult.get();
  }
  
  @Override
  public boolean isViewCycleAccessSupported() {
    return _isViewCycleAccessSupported;
  }
  
  @Override
  public void setViewCycleAccessSupported(boolean isViewCycleAccessSupported) {
    _clientLock.lock();
    try {
      _isViewCycleAccessSupported = isViewCycleAccessSupported;
      _mergingViewProcessListener.setLatestResultCycleRetained(isViewCycleAccessSupported);
      if (!isViewCycleAccessSupported) {
        getLatestCycleRetainer().replaceRetainedCycle(null);
      }
    } finally {
      _clientLock.unlock();
    }
  }

  @Override
  public ViewCycleReference createLatestCycleReference() {
    if (!isViewCycleAccessSupported()) {
      throw new UnsupportedOperationException("Access to computation cycles is not supported from this client");
    }
    ViewComputationResultModel latestResult = _latestResult.get();
    if (latestResult == null) {
      return null;
    }
    return _viewProcessor.getViewCycleManager().createReference(latestResult.getViewCycleId());
  }
  
  @Override
  public void shutdown() {
    _clientLock.lock();
    try {
      if (_state == ViewClientState.TERMINATED) {
        return;
      }
      detachFromViewProcess();
      getViewProcessor().removeViewClient(getUniqueId());
      _mergingViewProcessListener.terminate();
      _state = ViewClientState.TERMINATED;
    } finally {
      _clientLock.unlock();
    }
  }

  //-------------------------------------------------------------------------
  private void processCompleted() {
    CountDownLatch latch = _completionLatch;
    if (latch != null) {
      latch.countDown();
    }
  }
  
  private void checkNotTerminated() {
    if (getState() == ViewClientState.TERMINATED) {
      throw new IllegalStateException("The client has been terminated. It is not possible to use a terminated client.");
    }
  }
  
  private ViewCycleRetainer getLatestCycleRetainer() {
    return _latestCycleRetainer;
  }
  
  private void updateLatestResult(ViewComputationResultModel result) {
    if (isViewCycleAccessSupported()) {
      getLatestCycleRetainer().replaceRetainedCycle(result.getViewCycleId());
    }
    _latestResult.set(result);
  }
  
}
