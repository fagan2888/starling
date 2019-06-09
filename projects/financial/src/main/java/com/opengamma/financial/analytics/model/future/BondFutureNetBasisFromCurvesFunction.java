/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.future;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.interestrate.future.calculator.BondFutureNetBasisFromCurvesCalculator;
import com.opengamma.analytics.financial.interestrate.future.derivative.BondFuture;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.financial.analytics.StringLabelledMatrix1D;
import com.opengamma.financial.analytics.model.bond.BondFunction;
import com.opengamma.financial.security.future.BondFutureDeliverable;

/**
 *
 * @deprecated Deprecated
 */
@Deprecated
public class BondFutureNetBasisFromCurvesFunction extends BondFutureFromCurvesFunction {
  private static final BondFutureNetBasisFromCurvesCalculator CALCULATOR = BondFutureNetBasisFromCurvesCalculator.getInstance();

  public BondFutureNetBasisFromCurvesFunction() {
    super(ValueRequirementNames.NET_BASIS, BondFunction.FROM_CURVES_METHOD);
  }

  @Override
  protected Set<ComputedValue> calculate(final com.opengamma.financial.security.future.BondFutureSecurity security, final BondFuture bondFuture,
      final YieldCurveBundle data,
      final ComputationTarget target) {
    final List<BondFutureDeliverable> deliverables = security.getBasket();
    final int n = deliverables.size();
    final double[] netBasis = bondFuture.accept(CALCULATOR, data);
    if (netBasis.length != n) {
      throw new OpenGammaRuntimeException("Do not have a net basis for every deliverable: should never happen");
    }
    final String[] label = new String[n];
    for (int i = 0; i < n; i++) {
      label[i] = deliverables.get(i).getIdentifiers().getValue(ExternalSchemes.BLOOMBERG_BUID);
    }
    final StringLabelledMatrix1D result = new StringLabelledMatrix1D(label, netBasis);
    return Sets.newHashSet(new ComputedValue(getResultSpec(target), result));
  }
}
