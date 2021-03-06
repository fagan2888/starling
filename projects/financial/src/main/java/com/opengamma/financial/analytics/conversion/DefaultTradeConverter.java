/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.conversion;

import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.core.position.Trade;
import com.opengamma.core.security.Security;
import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.financial.security.FinancialSecurityVisitorSameValueAdapter;
import com.opengamma.financial.security.future.AgricultureFutureSecurity;
import com.opengamma.financial.security.future.DeliverableSwapFutureSecurity;
import com.opengamma.financial.security.future.EnergyFutureSecurity;
import com.opengamma.financial.security.future.EquityFutureSecurity;
import com.opengamma.financial.security.future.EquityIndexDividendFutureSecurity;
import com.opengamma.financial.security.future.FederalFundsFutureSecurity;
import com.opengamma.financial.security.future.IndexFutureSecurity;
import com.opengamma.financial.security.future.InterestRateFutureSecurity;
import com.opengamma.financial.security.future.MetalFutureSecurity;
import com.opengamma.util.ArgumentChecker;

/**
 * Converts {@link Trade} to the appropriate {@link InstrumentDefinition}.
 */
public class DefaultTradeConverter implements TradeConverter {
  /** Converter for futures excluding Federal funds futures */
  private final FutureTradeConverter _futureTradeConverter;
  /** Converter for Federal funds futures */
  private final FederalFundsFutureTradeConverter _federalFundsFutureTradeConverter;
  /** Converter for STIR futures */
  private final InterestRateFutureTradeConverter _interestRateFutureTradeConverter;
  /** Converter for deliverable swap futures */
  private final DeliverableSwapFutureTradeConverter _deliverableSwapFutureTradeConverter;
  /** Converter for all other securities */
  private final FinancialSecurityVisitor<InstrumentDefinition<?>> _securityConverter;

  /**
   * Note that this constructor explicitly sets the future trade converter and Federal funds future converter to null.
   *
   * @param securityConverter
   *          The security converter, not null
   */
  public DefaultTradeConverter(final FinancialSecurityVisitor<InstrumentDefinition<?>> securityConverter) {
    ArgumentChecker.notNull(securityConverter, "securityConverter");
    _securityConverter = securityConverter;
    _futureTradeConverter = null;
    _federalFundsFutureTradeConverter = null;
    _interestRateFutureTradeConverter = null;
    _deliverableSwapFutureTradeConverter = null;
  }

  /**
   * Note that this constructor explicitly sets the Federal funds future converter to null.
   *
   * @param futureTradeConverter
   *          The futures trade converter, not null
   * @param securityConverter
   *          The future security converter, not null
   */
  public DefaultTradeConverter(final FutureTradeConverter futureTradeConverter, final FinancialSecurityVisitor<InstrumentDefinition<?>> securityConverter) {
    ArgumentChecker.notNull(futureTradeConverter, "future trade converter");
    ArgumentChecker.notNull(securityConverter, "security converter");
    _futureTradeConverter = futureTradeConverter;
    _federalFundsFutureTradeConverter = null;
    _securityConverter = securityConverter;
    _interestRateFutureTradeConverter = null;
    _deliverableSwapFutureTradeConverter = null;
  }

  /**
   * @param futureTradeConverter
   *          The futures trade converter, not null
   * @param federalFundsFutureTradeConverter
   *          The Federal funds future trade converter, not null
   * @param irFutureTradeConverter
   *          TODO
   * @param deliverableSwapFutureTradeConverter
   *          TODO
   * @param securityConverter
   *          The security converter, not null
   */
  public DefaultTradeConverter(final FutureTradeConverter futureTradeConverter,
      final FederalFundsFutureTradeConverter federalFundsFutureTradeConverter,
      final InterestRateFutureTradeConverter irFutureTradeConverter,
      final DeliverableSwapFutureTradeConverter deliverableSwapFutureTradeConverter,
      final FinancialSecurityVisitor<InstrumentDefinition<?>> securityConverter) {
    ArgumentChecker.notNull(futureTradeConverter, "future trade converter");
    ArgumentChecker.notNull(federalFundsFutureTradeConverter, "Federal funds future trade converter");
    ArgumentChecker.notNull(securityConverter, "security converter");
    _futureTradeConverter = futureTradeConverter;
    _federalFundsFutureTradeConverter = federalFundsFutureTradeConverter;
    _interestRateFutureTradeConverter = irFutureTradeConverter;
    _deliverableSwapFutureTradeConverter = deliverableSwapFutureTradeConverter;
    _securityConverter = securityConverter;
  }

  /**
   * Converts a {@link Trade} to a {@link InstrumentDefinition}.
   *
   * @param trade
   *          The trade, not null
   * @return The instrument definition
   * @throws IllegalArgumentException
   *           if the underlying security is not a {@link FinancialSecurity}
   */
  @Override
  public InstrumentDefinition<?> convert(final Trade trade) {
    ArgumentChecker.notNull(trade, "trade");
    final Security security = trade.getSecurity();
    ArgumentChecker.isTrue(security instanceof FinancialSecurity, "Security must be a FinancialSecurity");
    return ((FinancialSecurity) security).accept(new TradeVisitor()).convert(trade);
  }

  private final class TradeVisitor extends FinancialSecurityVisitorSameValueAdapter<TradeConverter> {

    TradeVisitor() {
      super(new TradeConverter() {
        @Override
        public InstrumentDefinition<?> convert(final Trade trade) {
          return ((FinancialSecurity) trade.getSecurity()).accept(_securityConverter);
        }
      });
    }

    @Override
    public TradeConverter visitAgricultureFutureSecurity(final AgricultureFutureSecurity security) {
      return _futureTradeConverter;
    }

    @Override
    public TradeConverter visitDeliverableSwapFutureSecurity(final DeliverableSwapFutureSecurity security) {
      return _deliverableSwapFutureTradeConverter;
    }

    @Override
    public TradeConverter visitEnergyFutureSecurity(final EnergyFutureSecurity security) {
      return _futureTradeConverter;
    }

    @Override
    public TradeConverter visitEquityFutureSecurity(final EquityFutureSecurity security) {
      return _futureTradeConverter;
    }

    @Override
    public TradeConverter visitEquityIndexDividendFutureSecurity(final EquityIndexDividendFutureSecurity security) {
      return _futureTradeConverter;
    }

    @Override
    public TradeConverter visitFederalFundsFutureSecurity(final FederalFundsFutureSecurity security) {
      return _federalFundsFutureTradeConverter;
    }

    @Override
    public TradeConverter visitIndexFutureSecurity(final IndexFutureSecurity security) {
      return _futureTradeConverter;
    }

    @Override
    public TradeConverter visitInterestRateFutureSecurity(final InterestRateFutureSecurity security) {
      return _interestRateFutureTradeConverter;
    }

    @Override
    public TradeConverter visitMetalFutureSecurity(final MetalFutureSecurity security) {
      return _futureTradeConverter;
    }
  }

}
