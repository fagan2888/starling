/**
 * Copyright (C) 2009 - 2011 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view.execution;

import org.fudgemsg.util.ArgumentChecker;

/**
 * Provides standard view process execution options.
 */
public class ExecutionOptions implements ViewExecutionOptions {

  private ViewCycleExecutionSequence _executionSequence;
  private final boolean _runAsFastAsPossible;
  private final boolean _liveDataTriggerEnabled;
  private final Integer _maxSuccessiveDeltaCycles;
  
  public ExecutionOptions(ViewCycleExecutionSequence evaluationTimeSequence, boolean liveDataTriggerEnabled) {
    this(evaluationTimeSequence, false, liveDataTriggerEnabled, null);
  }
  
  public ExecutionOptions(ViewCycleExecutionSequence executionSequence, boolean runAsFastAsPossible, boolean liveDataTriggerEnabled, Integer maxSuccessiveDeltaCycles) {
    ArgumentChecker.notNull(executionSequence, "executionSequence");
    
    _executionSequence = executionSequence;
    _runAsFastAsPossible = runAsFastAsPossible;
    _liveDataTriggerEnabled = liveDataTriggerEnabled;
    _maxSuccessiveDeltaCycles = maxSuccessiveDeltaCycles;
  }
  
  public static ViewExecutionOptions getRealTime() {
    return new ExecutionOptions(new RealTimeViewCycleExecutionSequence(), true);
  }
  
  public static ViewExecutionOptions getBatch(ViewCycleExecutionSequence cycleExecutionSequence) {
    return new ExecutionOptions(
        cycleExecutionSequence,
        true,
        false,
        null);
  }
  
  @Override
  public ViewCycleExecutionSequence getExecutionSequence() {
    return _executionSequence;
  }

  @Override
  public boolean isRunAsFastAsPossible() {
    return _runAsFastAsPossible;
  }

  @Override
  public boolean isLiveDataTriggerEnabled() {
    return _liveDataTriggerEnabled;
  }

  @Override
  public Integer getMaxSuccessiveDeltaCycles() {
    return _maxSuccessiveDeltaCycles;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _executionSequence.hashCode();
    result = prime * result + (_liveDataTriggerEnabled ? 1231 : 1237);
    result = prime * result + ((_maxSuccessiveDeltaCycles == null) ? 0 : _maxSuccessiveDeltaCycles.hashCode());
    result = prime * result + (_runAsFastAsPossible ? 1231 : 1237);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ExecutionOptions)) {
      return false;
    }
    ExecutionOptions other = (ExecutionOptions) obj;
    if (!_executionSequence.equals(other._executionSequence)) {
      return false;
    }
    if (_liveDataTriggerEnabled != other._liveDataTriggerEnabled) {
      return false;
    }
    if (_maxSuccessiveDeltaCycles == null) {
      if (other._maxSuccessiveDeltaCycles != null) {
        return false;
      }
    } else if (!_maxSuccessiveDeltaCycles.equals(other._maxSuccessiveDeltaCycles)) {
      return false;
    }
    if (_runAsFastAsPossible != other._runAsFastAsPossible) {
      return false;
    }
    return true;
  }

}
