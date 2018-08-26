package com.opengamma.financial.analytics.test.unittest.dealstest;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.interestrate.PresentValueCalculator;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.model.interestrate.curve.DiscountCurve;
import com.opengamma.analytics.math.curve.InterpolatedDoublesCurve;
import com.opengamma.financial.analytics.test.IRCurveParser;
import com.opengamma.financial.analytics.test.IRSwapSecurity;
import com.opengamma.financial.analytics.test.IRSwapTradeParser;
import com.opengamma.util.ResourceUtils;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.TestGroup;

/**
 * Unit tests for CAD cme deals
 */
@Test(groups = TestGroup.UNIT)
public class CADTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CADTest.class);
  private static final String CURRENCY = "CAD";

  private static final String ON_NAME = "CAD_BA_3M_ERS";
  private static final String THREE_MONTH_NAME = "CAD_BA_3M_ERS";
  private static final String SIX_MONTH_NAME = "CAD_BA_3M_ERS";
  final static String DISCOUNTING_CURVE_NAME = "Discounting";
  final static String FORWARD_3M_CURVE_NAME = "Forward 3M";
  final static String FORWARD_6M_CURVE_NAME = "Forward 6M";

  final static Currency CCY = Currency.CAD;

  private static final String PAY_CURRENCY = "LEG1_CCY";

  private static final PresentValueCalculator PVC = PresentValueCalculator.getInstance();

  public void test() throws Exception {

    // Build the clean list of swap
    IRSwapTradeParser tradeParser = new IRSwapTradeParser();
    Resource resource = ResourceUtils.createResource("classpath:com/opengamma/financial/analytics/test/Trades14Oct.csv");
    List<IRSwapSecurity> trades = tradeParser.parseCSVFile(resource.getURL());
    List<IRSwapSecurity> tradesClean = Lists.newArrayList();
    for (IRSwapSecurity irSwapSecurity : trades) {

      String currency = irSwapSecurity.getRawInput().getString(PAY_CURRENCY);
      if (currency.equals(CURRENCY)) {
        tradesClean.add(irSwapSecurity);
      }
    }

    // Build the curve bundle
    final HashMap<String, Currency> ccyMap = new HashMap<>();
    ccyMap.put(DISCOUNTING_CURVE_NAME, CCY);
    ccyMap.put(FORWARD_3M_CURVE_NAME, CCY);
    ccyMap.put(FORWARD_6M_CURVE_NAME, CCY);
    final FXMatrix fx = new FXMatrix(CCY);
    final YieldCurveBundle curvesClean = new YieldCurveBundle(fx, ccyMap);

    IRCurveParser curveParser = new IRCurveParser();
    Resource resourceCurve = ResourceUtils.createResource("classpath:com/opengamma/financial/analytics/test/Base_Curves_20131014_Clean.csv");
    List<InterpolatedDoublesCurve> curves = curveParser.parseCSVFile(resourceCurve.getURL());

    for (InterpolatedDoublesCurve interpolatedDoublesCurve : curves) {

      String name = interpolatedDoublesCurve.getName();
      if (name.equals(ON_NAME)) {
        curvesClean.setCurve(DISCOUNTING_CURVE_NAME, DiscountCurve.from(interpolatedDoublesCurve));
      }
      if (name.equals(THREE_MONTH_NAME)) {
        curvesClean.setCurve(FORWARD_3M_CURVE_NAME, DiscountCurve.from(interpolatedDoublesCurve));
      }
      if (name.equals(SIX_MONTH_NAME)) {
        curvesClean.setCurve(FORWARD_6M_CURVE_NAME, DiscountCurve.from(interpolatedDoublesCurve));
      }
    }

    // Convert the swap security into a swap definition 
    //TODO
    LOGGER.warn("Got {} trades", trades.size());
  }

}
