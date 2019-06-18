/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.bond;

import java.util.Collections;
import java.util.Set;

import com.opengamma.core.value.MarketDataRequirementNames;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetSpecification;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.security.FinancialSecurity;

/**
 *
 */
public class BondMarketYieldFunction extends BondMarketDataFunction {

  public BondMarketYieldFunction() {
    super(MarketDataRequirementNames.YIELD_YIELD_TO_MATURITY_MID);
  }

  @Override
  protected Set<ComputedValue> getComputedValues(final FunctionExecutionContext context, final double value, final FinancialSecurity security,
      final ComputationTargetSpecification target) {
    final ValueSpecification specification = new ValueSpecification(ValueRequirementNames.MARKET_YTM, target, createValueProperties().get());
    return Collections.singleton(new ComputedValue(specification, value));
  }

  @Override
  public Set<ValueSpecification> getResults(final FunctionCompilationContext context, final ComputationTarget target) {
    return Collections.singleton(new ValueSpecification(ValueRequirementNames.MARKET_YTM, target.toSpecification(), createValueProperties().get()));
  }

  @Override
  public String getShortName() {
    return "BondMarketYieldFunction";
  }

}
