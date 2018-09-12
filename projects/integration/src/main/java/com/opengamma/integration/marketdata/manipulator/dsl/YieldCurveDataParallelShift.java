/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.marketdata.manipulator.dsl;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.marketdata.manipulator.function.StructureManipulator;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.analytics.ircurve.YieldCurveData;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.ArgumentChecker;

/**
 * {@link StructureManipulator} that shifts all points on a curve up or down by the same amount.
 */
@BeanDefinition
public final class YieldCurveDataParallelShift implements StructureManipulator<YieldCurveData>, ImmutableBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(YieldCurveDataParallelShift.class);

  /** How the shift should be applied */
  @PropertyDefinition(validate = "notNull")
  private final ScenarioShiftType _shiftType;

  /** The shift to apply  */
  @PropertyDefinition
  private final double _shift;

  @ImmutableConstructor
  /* package */ YieldCurveDataParallelShift(final ScenarioShiftType shiftType, final double shift) {
    _shiftType = ArgumentChecker.notNull(shiftType, "shiftType");
    _shift = shift;
  }

  @Override
  public YieldCurveData execute(final YieldCurveData curveData,
                                final ValueSpecification valueSpec,
                                final FunctionExecutionContext executionContext) {
    LOGGER.debug("Shifting curve data {} by {}", curveData.getCurveSpecification().getName(), _shift);
    final Map<ExternalIdBundle, Double> dataPoints = curveData.getDataPoints();
    final Map<ExternalIdBundle, Double> shiftedPoints = Maps.newHashMapWithExpectedSize(dataPoints.size());
    for (final Map.Entry<ExternalIdBundle, Double> entry : dataPoints.entrySet()) {
      switch (_shiftType) {
        case ABSOLUTE:
          shiftedPoints.put(entry.getKey(), entry.getValue() + _shift);
          break;
        case RELATIVE:
          // A relative shift is expressed as an amount to add or subtract, e.g.
          //    10% shift = rate * 1.1
          //   -20% shift = rate * 0.8
          shiftedPoints.put(entry.getKey(), entry.getValue() * (1 + _shift));
          break;
        default:
          throw new IllegalArgumentException("Unexpected shift type " + _shiftType);
      }
    }
    return new YieldCurveData(curveData.getCurveSpecification(), shiftedPoints);
  }

  @Override
  public Class<YieldCurveData> getExpectedType() {
    return YieldCurveData.class;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code YieldCurveDataParallelShift}.
   * @return the meta-bean, not null
   */
  public static YieldCurveDataParallelShift.Meta meta() {
    return YieldCurveDataParallelShift.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(YieldCurveDataParallelShift.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static YieldCurveDataParallelShift.Builder builder() {
    return new YieldCurveDataParallelShift.Builder();
  }

  @Override
  public YieldCurveDataParallelShift.Meta metaBean() {
    return YieldCurveDataParallelShift.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets how the shift should be applied
   * @return the value of the property, not null
   */
  public ScenarioShiftType getShiftType() {
    return _shiftType;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the shift to apply
   * @return the value of the property
   */
  public double getShift() {
    return _shift;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      YieldCurveDataParallelShift other = (YieldCurveDataParallelShift) obj;
      return JodaBeanUtils.equal(_shiftType, other._shiftType) &&
          JodaBeanUtils.equal(_shift, other._shift);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_shiftType);
    hash = hash * 31 + JodaBeanUtils.hashCode(_shift);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("YieldCurveDataParallelShift{");
    buf.append("shiftType").append('=').append(_shiftType).append(',').append(' ');
    buf.append("shift").append('=').append(JodaBeanUtils.toString(_shift));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code YieldCurveDataParallelShift}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code shiftType} property.
     */
    private final MetaProperty<ScenarioShiftType> _shiftType = DirectMetaProperty.ofImmutable(
        this, "shiftType", YieldCurveDataParallelShift.class, ScenarioShiftType.class);
    /**
     * The meta-property for the {@code shift} property.
     */
    private final MetaProperty<Double> _shift = DirectMetaProperty.ofImmutable(
        this, "shift", YieldCurveDataParallelShift.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "shiftType",
        "shift");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 893345500:  // shiftType
          return _shiftType;
        case 109407362:  // shift
          return _shift;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public YieldCurveDataParallelShift.Builder builder() {
      return new YieldCurveDataParallelShift.Builder();
    }

    @Override
    public Class<? extends YieldCurveDataParallelShift> beanType() {
      return YieldCurveDataParallelShift.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code shiftType} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ScenarioShiftType> shiftType() {
      return _shiftType;
    }

    /**
     * The meta-property for the {@code shift} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> shift() {
      return _shift;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 893345500:  // shiftType
          return ((YieldCurveDataParallelShift) bean).getShiftType();
        case 109407362:  // shift
          return ((YieldCurveDataParallelShift) bean).getShift();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code YieldCurveDataParallelShift}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<YieldCurveDataParallelShift> {

    private ScenarioShiftType _shiftType;
    private double _shift;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(YieldCurveDataParallelShift beanToCopy) {
      this._shiftType = beanToCopy.getShiftType();
      this._shift = beanToCopy.getShift();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 893345500:  // shiftType
          return _shiftType;
        case 109407362:  // shift
          return _shift;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 893345500:  // shiftType
          this._shiftType = (ScenarioShiftType) newValue;
          break;
        case 109407362:  // shift
          this._shift = (Double) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    /**
     * @deprecated Use Joda-Convert in application code
     */
    @Override
    @Deprecated
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    /**
     * @deprecated Use Joda-Convert in application code
     */
    @Override
    @Deprecated
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    /**
     * @deprecated Loop in application code
     */
    @Override
    @Deprecated
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public YieldCurveDataParallelShift build() {
      return new YieldCurveDataParallelShift(
          _shiftType,
          _shift);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets how the shift should be applied
     * @param shiftType  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder shiftType(ScenarioShiftType shiftType) {
      JodaBeanUtils.notNull(shiftType, "shiftType");
      this._shiftType = shiftType;
      return this;
    }

    /**
     * Sets the shift to apply
     * @param shift  the new value
     * @return this, for chaining, not null
     */
    public Builder shift(double shift) {
      this._shift = shift;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("YieldCurveDataParallelShift.Builder{");
      buf.append("shiftType").append('=').append(JodaBeanUtils.toString(_shiftType)).append(',').append(' ');
      buf.append("shift").append('=').append(JodaBeanUtils.toString(_shift));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
