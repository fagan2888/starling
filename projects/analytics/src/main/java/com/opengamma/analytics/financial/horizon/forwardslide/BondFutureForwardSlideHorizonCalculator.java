/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.horizon.forwardslide;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.horizon.HorizonCalculator;
import com.opengamma.analytics.financial.horizon.rolldown.CurveProviderForwardSlideRolldown;
import com.opengamma.analytics.financial.instrument.future.BondFuturesTransactionDefinition;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitor;
import com.opengamma.analytics.financial.provider.calculator.issuer.PresentValueIssuerCalculator;
import com.opengamma.analytics.financial.provider.description.interestrate.IssuerProviderInterface;
import com.opengamma.analytics.financial.provider.description.interestrate.ParameterIssuerProviderInterface;
import com.opengamma.analytics.util.time.TimeCalculator;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.MultipleCurrencyAmount;

/**
 * Calculates the difference in the present value of a bond future between two dates with rate slide.
 */
public final class BondFutureForwardSlideHorizonCalculator
    extends HorizonCalculator<BondFuturesTransactionDefinition, IssuerProviderInterface, Double> {
  /** The present value calculator */
  private static final InstrumentDerivativeVisitor<ParameterIssuerProviderInterface, MultipleCurrencyAmount> PV_CALCULATOR = PresentValueIssuerCalculator
      .getInstance();
  /** The singleton instance */
  private static final HorizonCalculator<BondFuturesTransactionDefinition, IssuerProviderInterface, Double> INSTANCE = new BondFutureForwardSlideHorizonCalculator();

  /**
   * Gets the singleton instance.
   *
   * @return The instance
   */
  public static HorizonCalculator<BondFuturesTransactionDefinition, IssuerProviderInterface, Double> getInstance() {
    return INSTANCE;
  }

  /**
   * Private constructor
   */
  private BondFutureForwardSlideHorizonCalculator() {
  }

  @Override
  public MultipleCurrencyAmount getTheta(final BondFuturesTransactionDefinition definition, final ZonedDateTime date,
      final IssuerProviderInterface data,
      final int daysForward, final Calendar calendar) {
    throw new UnsupportedOperationException("Must supply a last margin price");
  }

  @Override
  public MultipleCurrencyAmount getTheta(final BondFuturesTransactionDefinition definition, final ZonedDateTime date,
      final IssuerProviderInterface data,
      final int daysForward, final Calendar calendar, final Double lastMarginPrice) {
    ArgumentChecker.notNull(definition, "definition");
    ArgumentChecker.notNull(date, "date");
    ArgumentChecker.notNull(data, "data");
    ArgumentChecker.isTrue(daysForward == 1 || daysForward == -1, "daysForward must be either 1 or -1");
    final InstrumentDerivative instrumentToday = definition.toDerivative(date, lastMarginPrice);
    final ZonedDateTime horizonDate = date.plusDays(daysForward);
    final double shiftTime = TimeCalculator.getTimeBetween(date, horizonDate);
    final InstrumentDerivative instrumentTomorrow = definition.toDerivative(horizonDate, lastMarginPrice);
    final ParameterIssuerProviderInterface dataTomorrow = (ParameterIssuerProviderInterface) CurveProviderForwardSlideRolldown.INSTANCE
        .rollDown(data, shiftTime);
    final MultipleCurrencyAmount pvTomorrow = instrumentTomorrow.accept(PV_CALCULATOR, dataTomorrow);
    final MultipleCurrencyAmount pvToday = instrumentToday.accept(PV_CALCULATOR, data);
    return subtract(pvTomorrow, pvToday);
  }

}
