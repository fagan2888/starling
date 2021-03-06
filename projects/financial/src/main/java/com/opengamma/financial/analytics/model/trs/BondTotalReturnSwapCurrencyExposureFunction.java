/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.trs;

import static com.opengamma.engine.value.ValueRequirementNames.FX_CURRENCY_EXPOSURE;

import java.util.Set;

import org.threeten.bp.Instant;

import com.google.common.collect.Sets;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitor;
import com.opengamma.analytics.financial.provider.calculator.issuer.CurrencyExposureIssuerCalculator;
import com.opengamma.analytics.financial.provider.description.interestrate.ParameterIssuerProviderInterface;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.CompiledFunctionDefinition;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.security.swap.BondTotalReturnSwapSecurity;
import com.opengamma.util.money.MultipleCurrencyAmount;

/**
 *
 */
public class BondTotalReturnSwapCurrencyExposureFunction extends BondTotalReturnSwapFunction {
  private static final InstrumentDerivativeVisitor<ParameterIssuerProviderInterface, MultipleCurrencyAmount> CALCULATOR = CurrencyExposureIssuerCalculator
      .getInstance();

  /**
   *
   */
  public BondTotalReturnSwapCurrencyExposureFunction() {
    super(FX_CURRENCY_EXPOSURE);
  }

  @Override
  public CompiledFunctionDefinition compile(final FunctionCompilationContext context, final Instant atInstant) {
    return new BondTotalReturnSwapCompiledFunction(getTargetToDefinitionConverter(context),
        getDefinitionToDerivativeConverter(context), false) {

      @Override
      protected String getCurrencyOfResult(final BondTotalReturnSwapSecurity security) {
        throw new IllegalStateException("BondTotalReturnSwapCurrencyExposureFunction does not set the Currency property in this method");
      }

      @Override
      protected Set<ComputedValue> getValues(final FunctionExecutionContext executionContext, final FunctionInputs inputs, final ComputationTarget target,
          final Set<ValueRequirement> desiredValues,
          final InstrumentDerivative derivative, final FXMatrix fxMatrix) {

        final Set<ComputedValue> results = Sets.newHashSet();
        for (final ValueRequirement desiredValue : desiredValues) {
          final ParameterIssuerProviderInterface issuerCurves = getMergedWithIssuerProviders(inputs, fxMatrix);

          final MultipleCurrencyAmount exposure = derivative.accept(CALCULATOR, issuerCurves);
          final ComputedValue result = new ComputedValue(ValueSpecification.of(FX_CURRENCY_EXPOSURE, target.toSpecification(), desiredValue.getConstraints()),
              exposure);
          results.add(result);
        }

        return results;
      }

    };
  }

}
