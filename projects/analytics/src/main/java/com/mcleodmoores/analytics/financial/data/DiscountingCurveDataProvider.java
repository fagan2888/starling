/**
 * Copyright (C) 2017 - present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.analytics.financial.data;

import java.util.List;

import com.opengamma.analytics.financial.provider.sensitivity.multicurve.ForwardSensitivity;
import com.opengamma.id.UniqueIdentifiable;

/**
 *
 */
public interface DiscountingCurveDataProvider extends CurveDataProvider {

  @Override
  DiscountingCurveDataProvider copy();

  double getDiscountFactor(UniqueIdentifiable id, double time);

  double[] parameterForwardSensitivity(String name, List<ForwardSensitivity> pointSensitivity);

}
