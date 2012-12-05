/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.web.spring.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.analytics.model.curve.forward.ForwardCurveValuePropertyNames;
import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.UnorderedCurrencyPair;

/**
 * Class containing default values for Black volatility surface calculations. The default values
 * are target-specific (e.g. unordered currency pairs for FX, a ticker for equities). 
 */
public class TargetSpecificBlackVolatilitySurfaceDefaults {
  private static final Map<UnorderedCurrencyPair, List<String>> FX_BLACK_SURFACE_DEFAULTS;
  private static final Map<ExternalId, List<String>> EQUITY_BLACK_SURFACE_DEFAULTS;
  
  static {
    FX_BLACK_SURFACE_DEFAULTS = new HashMap<UnorderedCurrencyPair, List<String>>(); 
    List<String> eurusd = Arrays.asList("EURUSD", "DiscountingImplied", ForwardCurveValuePropertyNames.PROPERTY_YIELD_CURVE_IMPLIED_METHOD, "TULLETT");
    FX_BLACK_SURFACE_DEFAULTS.put(UnorderedCurrencyPair.of(Currency.EUR, Currency.USD), eurusd);

    List<String> djxIndex = Arrays.asList("DJX Index", "Discounting", ForwardCurveValuePropertyNames.PROPERTY_YIELD_CURVE_IMPLIED_METHOD, "USD", "DefaultTwoCurveUSDConfig", "BBG");
    List<String> spxIndex = Arrays.asList("SPX Index", "Discounting", ForwardCurveValuePropertyNames.PROPERTY_YIELD_CURVE_IMPLIED_METHOD, "USD", "DefaultTwoCurveUSDConfig", "BBG");
    List<String> nkyIndex = Arrays.asList("NKY Index", "Discounting", ForwardCurveValuePropertyNames.PROPERTY_YIELD_CURVE_IMPLIED_METHOD, "USD", "DefaultTwoCurveUSDConfig", "BBG");
    EQUITY_BLACK_SURFACE_DEFAULTS = new HashMap<ExternalId, List<String>>();
    EQUITY_BLACK_SURFACE_DEFAULTS.put(ExternalSchemes.bloombergTickerSecurityId("DJX Index").getExternalId(), djxIndex);
    EQUITY_BLACK_SURFACE_DEFAULTS.put(ExternalSchemes.bloombergTickerSecurityId("SPX Index").getExternalId(), spxIndex);
    EQUITY_BLACK_SURFACE_DEFAULTS.put(ExternalSchemes.bloombergTickerSecurityId("NKY Index").getExternalId(), nkyIndex);
  }
  
  /**
   * Gets all lists of default values for FX Black volatility surfaces
   * @return A collection of lists containing the default values
   */
  public static List<String> getAllFXDefaults() {
    List<String> result = new ArrayList<String>();
    for (List<String> defaults : FX_BLACK_SURFACE_DEFAULTS.values()) {
      result.addAll(defaults);
    }
    return result;
  }
  
  /**
   * Gets all lists of default values for equity Black volatility surfaces
   * @return A collection of lists containing the default values
   */
  public static List<String> getAllEquityDefaults() {
    List<String> result = new ArrayList<String>();
    for (List<String> defaults : EQUITY_BLACK_SURFACE_DEFAULTS.values()) {
      result.addAll(defaults);
    }
    return result;
  }
  
  /**
   * Gets the default values for a FX Black volatility surface for a particular currency pair.
   * @param currencyPair The currency pair
   * @return A list containing the default values
   * @throws IllegalArgumentException if default values for the currency pair are not found 
   */
  public static List<String> getFXDefaults(final UnorderedCurrencyPair currencyPair) {
    List<String> defaults = FX_BLACK_SURFACE_DEFAULTS.get(currencyPair);
    if (defaults != null) {
      return defaults;
    }
    throw new IllegalArgumentException("Could not get Black volatility surface defaults for " + currencyPair);
  }
  
  /**
   * Gets the default values for an equity Black volatility surface for a particular external id (e.g. Bloomberg ticker).
   * @param id The external id
   * @return A list containing the default values
   * @throws IllegalArgumentException if default values for the id are not found
   */
  public static List<String> getEquityDefaults(final ExternalId id) {
    List<String> defaults = EQUITY_BLACK_SURFACE_DEFAULTS.get(id);
    if (defaults != null) {
      return defaults;
    }
    throw new IllegalArgumentException("Could not get Black volatility surface defaults for " + id);    
  }
}