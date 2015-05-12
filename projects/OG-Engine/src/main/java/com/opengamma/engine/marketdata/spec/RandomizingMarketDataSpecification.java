/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.marketdata.spec;

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

import com.opengamma.util.ArgumentChecker;

/**
 * Specifies a source of market data that decorates an underlying source and ticks at random intervals with random
 * perturbations.
 */
@BeanDefinition
public final class RandomizingMarketDataSpecification implements ImmutableBean, MarketDataSpecification {

  private static final long serialVersionUID = 1L;

  @PropertyDefinition(validate = "notNull")
  private final MarketDataSpecification _underlying;
  
  @PropertyDefinition(validate = "notNull")
  private final Double _updateProbability;
  
  @PropertyDefinition(validate = "notNull")
  private final Integer _maxPercentageChange;
  
  @PropertyDefinition(validate = "notNull")
  private final Long _averageCycleInterval;

  /**
   * Creates an instance
   * 
   * @param underlying Specification of the underlying source of market data
   * @param updateProbability Probability of a value updating during a cycle
   * @param maxPercentageChange The maximum percentage change of any value in a single cycle
   * @param averageCycleInterval The average interval between calculation cycles in milliseconds. The actual interval
   * is a random value +/- 50% of this value.
   * @return the Randomizing market data specification
   */
  public static RandomizingMarketDataSpecification of(MarketDataSpecification underlying,
                                            double updateProbability,
                                            int maxPercentageChange,
                                            long averageCycleInterval) {
    ArgumentChecker.notNull(underlying, "underlying");
    ArgumentChecker.isInRangeInclusive(0, 1, updateProbability);
    ArgumentChecker.notNegative(maxPercentageChange, "maxPercentageChange");
    ArgumentChecker.notNegativeOrZero(averageCycleInterval, "averageCycleInterval");
    
    return new RandomizingMarketDataSpecification(underlying, updateProbability, maxPercentageChange, averageCycleInterval);
  }

  /**
   * Creates a specification with an update probability of 0.2, maximum change of 5% and average cycle interval
   * of 1000ms.
   * 
   * @param underlying Specification of the underlying source of market data
   * @return the Randomizing market data specification
   */
  public static RandomizingMarketDataSpecification of(MarketDataSpecification underlying) {
    return of(underlying, 0.2, 5, 1000);
  }


  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RandomizingMarketDataSpecification}.
   * @return the meta-bean, not null
   */
  public static RandomizingMarketDataSpecification.Meta meta() {
    return RandomizingMarketDataSpecification.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(RandomizingMarketDataSpecification.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static RandomizingMarketDataSpecification.Builder builder() {
    return new RandomizingMarketDataSpecification.Builder();
  }

  private RandomizingMarketDataSpecification(
      MarketDataSpecification underlying,
      Double updateProbability,
      Integer maxPercentageChange,
      Long averageCycleInterval) {
    JodaBeanUtils.notNull(underlying, "underlying");
    JodaBeanUtils.notNull(updateProbability, "updateProbability");
    JodaBeanUtils.notNull(maxPercentageChange, "maxPercentageChange");
    JodaBeanUtils.notNull(averageCycleInterval, "averageCycleInterval");
    this._underlying = underlying;
    this._updateProbability = updateProbability;
    this._maxPercentageChange = maxPercentageChange;
    this._averageCycleInterval = averageCycleInterval;
  }

  @Override
  public RandomizingMarketDataSpecification.Meta metaBean() {
    return RandomizingMarketDataSpecification.Meta.INSTANCE;
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
   * Gets the underlying.
   * @return the value of the property, not null
   */
  public MarketDataSpecification getUnderlying() {
    return _underlying;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the updateProbability.
   * @return the value of the property, not null
   */
  public Double getUpdateProbability() {
    return _updateProbability;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maxPercentageChange.
   * @return the value of the property, not null
   */
  public Integer getMaxPercentageChange() {
    return _maxPercentageChange;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the averageCycleInterval.
   * @return the value of the property, not null
   */
  public Long getAverageCycleInterval() {
    return _averageCycleInterval;
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
      RandomizingMarketDataSpecification other = (RandomizingMarketDataSpecification) obj;
      return JodaBeanUtils.equal(getUnderlying(), other.getUnderlying()) &&
          JodaBeanUtils.equal(getUpdateProbability(), other.getUpdateProbability()) &&
          JodaBeanUtils.equal(getMaxPercentageChange(), other.getMaxPercentageChange()) &&
          JodaBeanUtils.equal(getAverageCycleInterval(), other.getAverageCycleInterval());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getUnderlying());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUpdateProbability());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaxPercentageChange());
    hash = hash * 31 + JodaBeanUtils.hashCode(getAverageCycleInterval());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("RandomizingMarketDataSpecification{");
    buf.append("underlying").append('=').append(getUnderlying()).append(',').append(' ');
    buf.append("updateProbability").append('=').append(getUpdateProbability()).append(',').append(' ');
    buf.append("maxPercentageChange").append('=').append(getMaxPercentageChange()).append(',').append(' ');
    buf.append("averageCycleInterval").append('=').append(JodaBeanUtils.toString(getAverageCycleInterval()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RandomizingMarketDataSpecification}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<MarketDataSpecification> _underlying = DirectMetaProperty.ofImmutable(
        this, "underlying", RandomizingMarketDataSpecification.class, MarketDataSpecification.class);
    /**
     * The meta-property for the {@code updateProbability} property.
     */
    private final MetaProperty<Double> _updateProbability = DirectMetaProperty.ofImmutable(
        this, "updateProbability", RandomizingMarketDataSpecification.class, Double.class);
    /**
     * The meta-property for the {@code maxPercentageChange} property.
     */
    private final MetaProperty<Integer> _maxPercentageChange = DirectMetaProperty.ofImmutable(
        this, "maxPercentageChange", RandomizingMarketDataSpecification.class, Integer.class);
    /**
     * The meta-property for the {@code averageCycleInterval} property.
     */
    private final MetaProperty<Long> _averageCycleInterval = DirectMetaProperty.ofImmutable(
        this, "averageCycleInterval", RandomizingMarketDataSpecification.class, Long.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "underlying",
        "updateProbability",
        "maxPercentageChange",
        "averageCycleInterval");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1770633379:  // underlying
          return _underlying;
        case 1864687532:  // updateProbability
          return _updateProbability;
        case -1539131282:  // maxPercentageChange
          return _maxPercentageChange;
        case 497321134:  // averageCycleInterval
          return _averageCycleInterval;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public RandomizingMarketDataSpecification.Builder builder() {
      return new RandomizingMarketDataSpecification.Builder();
    }

    @Override
    public Class<? extends RandomizingMarketDataSpecification> beanType() {
      return RandomizingMarketDataSpecification.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public MetaProperty<MarketDataSpecification> underlying() {
      return _underlying;
    }

    /**
     * The meta-property for the {@code updateProbability} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> updateProbability() {
      return _updateProbability;
    }

    /**
     * The meta-property for the {@code maxPercentageChange} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Integer> maxPercentageChange() {
      return _maxPercentageChange;
    }

    /**
     * The meta-property for the {@code averageCycleInterval} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Long> averageCycleInterval() {
      return _averageCycleInterval;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1770633379:  // underlying
          return ((RandomizingMarketDataSpecification) bean).getUnderlying();
        case 1864687532:  // updateProbability
          return ((RandomizingMarketDataSpecification) bean).getUpdateProbability();
        case -1539131282:  // maxPercentageChange
          return ((RandomizingMarketDataSpecification) bean).getMaxPercentageChange();
        case 497321134:  // averageCycleInterval
          return ((RandomizingMarketDataSpecification) bean).getAverageCycleInterval();
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
   * The bean-builder for {@code RandomizingMarketDataSpecification}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<RandomizingMarketDataSpecification> {

    private MarketDataSpecification _underlying;
    private Double _updateProbability;
    private Integer _maxPercentageChange;
    private Long _averageCycleInterval;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(RandomizingMarketDataSpecification beanToCopy) {
      this._underlying = beanToCopy.getUnderlying();
      this._updateProbability = beanToCopy.getUpdateProbability();
      this._maxPercentageChange = beanToCopy.getMaxPercentageChange();
      this._averageCycleInterval = beanToCopy.getAverageCycleInterval();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1770633379:  // underlying
          return _underlying;
        case 1864687532:  // updateProbability
          return _updateProbability;
        case -1539131282:  // maxPercentageChange
          return _maxPercentageChange;
        case 497321134:  // averageCycleInterval
          return _averageCycleInterval;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1770633379:  // underlying
          this._underlying = (MarketDataSpecification) newValue;
          break;
        case 1864687532:  // updateProbability
          this._updateProbability = (Double) newValue;
          break;
        case -1539131282:  // maxPercentageChange
          this._maxPercentageChange = (Integer) newValue;
          break;
        case 497321134:  // averageCycleInterval
          this._averageCycleInterval = (Long) newValue;
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

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public RandomizingMarketDataSpecification build() {
      return new RandomizingMarketDataSpecification(
          _underlying,
          _updateProbability,
          _maxPercentageChange,
          _averageCycleInterval);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the underlying.
     * @param underlying  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder underlying(MarketDataSpecification underlying) {
      JodaBeanUtils.notNull(underlying, "underlying");
      this._underlying = underlying;
      return this;
    }

    /**
     * Sets the updateProbability.
     * @param updateProbability  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder updateProbability(Double updateProbability) {
      JodaBeanUtils.notNull(updateProbability, "updateProbability");
      this._updateProbability = updateProbability;
      return this;
    }

    /**
     * Sets the maxPercentageChange.
     * @param maxPercentageChange  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder maxPercentageChange(Integer maxPercentageChange) {
      JodaBeanUtils.notNull(maxPercentageChange, "maxPercentageChange");
      this._maxPercentageChange = maxPercentageChange;
      return this;
    }

    /**
     * Sets the averageCycleInterval.
     * @param averageCycleInterval  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder averageCycleInterval(Long averageCycleInterval) {
      JodaBeanUtils.notNull(averageCycleInterval, "averageCycleInterval");
      this._averageCycleInterval = averageCycleInterval;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("RandomizingMarketDataSpecification.Builder{");
      buf.append("underlying").append('=').append(JodaBeanUtils.toString(_underlying)).append(',').append(' ');
      buf.append("updateProbability").append('=').append(JodaBeanUtils.toString(_updateProbability)).append(',').append(' ');
      buf.append("maxPercentageChange").append('=').append(JodaBeanUtils.toString(_maxPercentageChange)).append(',').append(' ');
      buf.append("averageCycleInterval").append('=').append(JodaBeanUtils.toString(_averageCycleInterval));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
