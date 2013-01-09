/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.bloombergexample.generator;

import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.generator.AbstractPortfolioGeneratorTool;
import com.opengamma.financial.generator.SecurityGenerator;
import com.opengamma.id.ExternalId;
import com.opengamma.util.functional.Function2;
import com.opengamma.util.money.Currency;

/**
 * Portfolio generator for Bloomberg exmaples.
 */
public class BloombergExamplePortfolioGeneratorTool extends AbstractPortfolioGeneratorTool {

  @Override
  protected void configureChain(final SecurityGenerator<?> securityGenerator) {
    super.configureChain(securityGenerator);
    securityGenerator.setCurrencyCurveName("Discounting");
    securityGenerator.setPreferredScheme(ExternalSchemes.BLOOMBERG_TICKER);
    securityGenerator.setSpotRateIdentifier(new Function2<Currency, Currency, ExternalId>() {
      @Override
      public ExternalId execute(final Currency a, final Currency b) {
        return ExternalSchemes.bloombergTickerSecurityId(a.getCode() + b.getCode() + " Curncy");
      }
    });
  }

}

