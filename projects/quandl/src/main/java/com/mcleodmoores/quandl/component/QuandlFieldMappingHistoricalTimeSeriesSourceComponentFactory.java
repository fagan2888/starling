/**
 * Copyright (C) 2014 - present by McLeod Moores Software Limited
 * Based on APLv2 code Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.mcleodmoores.quandl.component;

import java.util.Collection;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.mcleodmoores.quandl.classification.QuandlDataUtils;
import com.mcleodmoores.quandl.historicaltimeseries.QuandlFieldMappingHistoricalTimeSeriesResolver;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.source.HistoricalTimeSeriesSourceComponentFactory;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolver;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesSelector;
import com.opengamma.master.historicaltimeseries.impl.DefaultHistoricalTimeSeriesSelector;
import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesFieldAdjustmentMap;

/**
 * Component factory for the historical time-series source resolved using Quandl.
 */
@BeanDefinition
public class QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory extends HistoricalTimeSeriesSourceComponentFactory {

  //-------------------------------------------------------------------------
  @Override
  protected HistoricalTimeSeriesResolver createResolver(final ComponentRepository repo) {
    final Collection<HistoricalTimeSeriesFieldAdjustmentMap> fieldAdjustmentMaps = QuandlDataUtils.createFieldAdjustmentMap(getCacheManager());
    final HistoricalTimeSeriesSelector selector = new DefaultHistoricalTimeSeriesSelector(getConfigSource());
    return new QuandlFieldMappingHistoricalTimeSeriesResolver(fieldAdjustmentMaps, selector, getHistoricalTimeSeriesMaster());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta meta() {
    return QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta metaBean() {
    return QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory}.
   */
  public static class Meta extends HistoricalTimeSeriesSourceComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory> builder() {
      return new DirectBeanBuilder<QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory>(new QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory());
    }

    @Override
    public Class<? extends QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory> beanType() {
      return QuandlFieldMappingHistoricalTimeSeriesSourceComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}