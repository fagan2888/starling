/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.future.method;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InterestRateCurveSensitivity;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.interestrate.future.derivative.InterestRateFutureOptionMarginSecurity;
import com.opengamma.analytics.financial.interestrate.method.PricingMethod;
import com.opengamma.util.money.CurrencyAmount;

/**
 * Method for the pricing of interest rate future options with margin process. Abstract class with methods valid for all pricing methods.
 * 
 * @deprecated {@link YieldCurveBundle} is deprecated
 */
@Deprecated
public abstract class InterestRateFutureOptionMarginSecurityMethod implements PricingMethod {

  /**
   * Computes the option security price from future price.
   * 
   * @param security
   *          The future option security.
   * @param curves
   *          The yield curve bundle.
   * @param priceFuture
   *          The price of the underlying future.
   * @return The security price.
   */
  public abstract double optionPriceFromFuturePrice(InterestRateFutureOptionMarginSecurity security, YieldCurveBundle curves,
      double priceFuture);

  /**
   * Computes the option security price. The future price is computed without convexity adjustment.
   * 
   * @param security
   *          The future option security.
   * @param curves
   *          The yield curve bundle.
   * @return The security price.
   */
  public abstract double optionPrice(InterestRateFutureOptionMarginSecurity security, YieldCurveBundle curves);

  /**
   * Computes the option security price curve sensitivity. The future price is computed without convexity adjustment. It is supposed that
   * for a given strike the volatility does not change with the curves.
   * 
   * @param security
   *          The future option security.
   * @param curves
   *          The yield curve bundle.
   * @return The security price curve sensitivity.
   */
  public abstract InterestRateCurveSensitivity priceCurveSensitivity(InterestRateFutureOptionMarginSecurity security,
      YieldCurveBundle curves);

  @Override
  public CurrencyAmount presentValue(final InstrumentDerivative instrument, final YieldCurveBundle curves) {
    throw new UnsupportedOperationException("The InterestRateFutureOptionMarginSecurity don't have a present value, only a price.");
  }

}
