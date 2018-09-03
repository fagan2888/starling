/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.irs;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * A rate, either constant or a complex schedule.
 * The schedule can contain both absolute values and step changes.
 */
@BeanDefinition
public final class Rate implements ImmutableBean {

  /**
   * The periods for which custom spreads are required.
   * If a period is looked for but is not present then
   * the value from the previous period will be used.
   */
  @PropertyDefinition
  private final int[] _dates;

  /**
   * The custom rates.
   */
  @PropertyDefinition
  private final double[] _rates;

  /**
   * The adjustment types for each step. Either Delta or absolute.
   */
  @PropertyDefinition
  private final ShiftType[] _types;

  /**
   * A constant rate
   *
   * @param rate the rate
   */
  public Rate(final double rate) {
    _dates = new int[] {0};
    _rates = new double[] {rate};
    _types = new ShiftType[] {ShiftType.OUTRIGHT};
  }

  /**
   * Get the initial rate.
   *
   * @return the initial rate
   */
  public double getInitialRate() {
    return getRate(0);
  }

  /**
   * Get the rate for the given period.
   *
   * @param period the period
   * @return the rate
   */
  public double getRate(final int period) {
    if (period == 0) {
      return _rates[0]; // first amount must be absolute.
    }
    final int index = Arrays.binarySearch(_dates, period);
    if (index >= 0) {
      if (_types[index] == ShiftType.OUTRIGHT) {
        return _rates[index]; // short circuit if we don't need to adjust from previous
      }
      final int previousIndex = Math.max(0, index - 1);
      return _types[index].getRate(getRate(previousIndex), _rates[index]);
    } else {
      // if value not explicitly set for this period, take from previous.
      // TODO: Calculate directly here to avoid recursive call
      return getRate(-(index + 2));
    }
  }

  /**
   * Enum describing how the amount is varied by the steps within a schedule.
   */
  public enum ShiftType {
    /**
     * Apply an adjustment to the previous amount.
     * e.g. decrease by 10% each step.
     */
    DELTA {
      @Override
      double getRate(final double previous, final double step) {
        return previous * step;
      }
    },

    /**
     * Apply an adjustment to the previous amount.
     * e.g. decrease by 1 each step.
     */
    ADDITIVE {
      @Override
      double getRate(final double previous, final double step) {
        return previous + step;
      }
    },

    /**
     * The amount given for the period is the desired value.
     */
    OUTRIGHT {
      @Override
      double getRate(final double previous, final double thisRate) {
        return thisRate;
      }
    };

    abstract double getRate(final double previous, final double step);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code Rate}.
   * @return the meta-bean, not null
   */
  public static Rate.Meta meta() {
    return Rate.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(Rate.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static Rate.Builder builder() {
    return new Rate.Builder();
  }

  private Rate(
      int[] dates,
      double[] rates,
      ShiftType[] types) {
    this._dates = (dates != null ? dates.clone() : null);
    this._rates = (rates != null ? rates.clone() : null);
    this._types = types;
  }

  @Override
  public Rate.Meta metaBean() {
    return Rate.Meta.INSTANCE;
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
   * Gets the periods for which custom spreads are required.
   * If a period is looked for but is not present then
   * the value from the previous period will be used.
   * @return the value of the property
   */
  public int[] getDates() {
    return (_dates != null ? _dates.clone() : null);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the custom rates.
   * @return the value of the property
   */
  public double[] getRates() {
    return (_rates != null ? _rates.clone() : null);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the adjustment types for each step. Either Delta or absolute.
   * @return the value of the property
   */
  public ShiftType[] getTypes() {
    return _types;
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
      Rate other = (Rate) obj;
      return JodaBeanUtils.equal(_dates, other._dates) &&
          JodaBeanUtils.equal(_rates, other._rates) &&
          JodaBeanUtils.equal(_types, other._types);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_dates);
    hash = hash * 31 + JodaBeanUtils.hashCode(_rates);
    hash = hash * 31 + JodaBeanUtils.hashCode(_types);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("Rate{");
    buf.append("dates").append('=').append(_dates).append(',').append(' ');
    buf.append("rates").append('=').append(_rates).append(',').append(' ');
    buf.append("types").append('=').append(JodaBeanUtils.toString(_types));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code Rate}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code dates} property.
     */
    private final MetaProperty<int[]> _dates = DirectMetaProperty.ofImmutable(
        this, "dates", Rate.class, int[].class);
    /**
     * The meta-property for the {@code rates} property.
     */
    private final MetaProperty<double[]> _rates = DirectMetaProperty.ofImmutable(
        this, "rates", Rate.class, double[].class);
    /**
     * The meta-property for the {@code types} property.
     */
    private final MetaProperty<ShiftType[]> _types = DirectMetaProperty.ofImmutable(
        this, "types", Rate.class, ShiftType[].class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "dates",
        "rates",
        "types");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          return _dates;
        case 108285843:  // rates
          return _rates;
        case 110844025:  // types
          return _types;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public Rate.Builder builder() {
      return new Rate.Builder();
    }

    @Override
    public Class<? extends Rate> beanType() {
      return Rate.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code dates} property.
     * @return the meta-property, not null
     */
    public MetaProperty<int[]> dates() {
      return _dates;
    }

    /**
     * The meta-property for the {@code rates} property.
     * @return the meta-property, not null
     */
    public MetaProperty<double[]> rates() {
      return _rates;
    }

    /**
     * The meta-property for the {@code types} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ShiftType[]> types() {
      return _types;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          return ((Rate) bean).getDates();
        case 108285843:  // rates
          return ((Rate) bean).getRates();
        case 110844025:  // types
          return ((Rate) bean).getTypes();
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
   * The bean-builder for {@code Rate}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<Rate> {

    private int[] _dates;
    private double[] _rates;
    private ShiftType[] _types;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(Rate beanToCopy) {
      this._dates = (beanToCopy.getDates() != null ? beanToCopy.getDates().clone() : null);
      this._rates = (beanToCopy.getRates() != null ? beanToCopy.getRates().clone() : null);
      this._types = beanToCopy.getTypes();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          return _dates;
        case 108285843:  // rates
          return _rates;
        case 110844025:  // types
          return _types;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          this._dates = (int[]) newValue;
          break;
        case 108285843:  // rates
          this._rates = (double[]) newValue;
          break;
        case 110844025:  // types
          this._types = (ShiftType[]) newValue;
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
    public Rate build() {
      return new Rate(
          _dates,
          _rates,
          _types);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the periods for which custom spreads are required.
     * If a period is looked for but is not present then
     * the value from the previous period will be used.
     * @param dates  the new value
     * @return this, for chaining, not null
     */
    public Builder dates(int... dates) {
      this._dates = dates;
      return this;
    }

    /**
     * Sets the custom rates.
     * @param rates  the new value
     * @return this, for chaining, not null
     */
    public Builder rates(double... rates) {
      this._rates = rates;
      return this;
    }

    /**
     * Sets the adjustment types for each step. Either Delta or absolute.
     * @param types  the new value
     * @return this, for chaining, not null
     */
    public Builder types(ShiftType... types) {
      this._types = types;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("Rate.Builder{");
      buf.append("dates").append('=').append(JodaBeanUtils.toString(_dates)).append(',').append(' ');
      buf.append("rates").append('=').append(JodaBeanUtils.toString(_rates)).append(',').append(' ');
      buf.append("types").append('=').append(JodaBeanUtils.toString(_types));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
