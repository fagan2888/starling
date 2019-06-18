/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.deposit;

/**
 * An enum representing the types of compounding that can be done on a deposit.
 */
public enum CompoundingType {
  /** Simple. */
  SIMPLE,
  /** Periodic. */
  PERIODIC,
  /** Continuous. */
  CONTINUOUS
}
