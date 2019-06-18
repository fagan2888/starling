/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

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
import org.threeten.bp.Instant;

/**
 *
 */
@BeanDefinition
public final class GoldenCopy implements ImmutableBean {

  @PropertyDefinition
  private final String _snapshotName;

  @PropertyDefinition
  private final String _viewName;

  @PropertyDefinition
  private final Instant _valuationTime;

  @PropertyDefinition
  private final CalculationResults _calculationResults;

  /**
   * Creates a new instance.
   *
   * @param snapshotName
   *          snapshot name
   * @param viewName
   *          viewName
   * @param valuationTime
   *          the valuation time
   * @param calculationResults
   *          calculationResults
   * @return a GoldenCopy
   */
  public static GoldenCopy create(
      final String snapshotName,
      final String viewName,
      final Instant valuationTime,
      final CalculationResults calculationResults) {
    return new GoldenCopy(snapshotName, viewName, valuationTime, calculationResults);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code GoldenCopy}.
   * @return the meta-bean, not null
   */
  public static GoldenCopy.Meta meta() {
    return GoldenCopy.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(GoldenCopy.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static GoldenCopy.Builder builder() {
    return new GoldenCopy.Builder();
  }

  private GoldenCopy(
      String snapshotName,
      String viewName,
      Instant valuationTime,
      CalculationResults calculationResults) {
    this._snapshotName = snapshotName;
    this._viewName = viewName;
    this._valuationTime = valuationTime;
    this._calculationResults = calculationResults;
  }

  @Override
  public GoldenCopy.Meta metaBean() {
    return GoldenCopy.Meta.INSTANCE;
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
   * Gets the snapshotName.
   * @return the value of the property
   */
  public String getSnapshotName() {
    return _snapshotName;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the viewName.
   * @return the value of the property
   */
  public String getViewName() {
    return _viewName;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuationTime.
   * @return the value of the property
   */
  public Instant getValuationTime() {
    return _valuationTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the calculationResults.
   * @return the value of the property
   */
  public CalculationResults getCalculationResults() {
    return _calculationResults;
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
      GoldenCopy other = (GoldenCopy) obj;
      return JodaBeanUtils.equal(_snapshotName, other._snapshotName) &&
          JodaBeanUtils.equal(_viewName, other._viewName) &&
          JodaBeanUtils.equal(_valuationTime, other._valuationTime) &&
          JodaBeanUtils.equal(_calculationResults, other._calculationResults);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_snapshotName);
    hash = hash * 31 + JodaBeanUtils.hashCode(_viewName);
    hash = hash * 31 + JodaBeanUtils.hashCode(_valuationTime);
    hash = hash * 31 + JodaBeanUtils.hashCode(_calculationResults);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("GoldenCopy{");
    buf.append("snapshotName").append('=').append(_snapshotName).append(',').append(' ');
    buf.append("viewName").append('=').append(_viewName).append(',').append(' ');
    buf.append("valuationTime").append('=').append(_valuationTime).append(',').append(' ');
    buf.append("calculationResults").append('=').append(JodaBeanUtils.toString(_calculationResults));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code GoldenCopy}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code snapshotName} property.
     */
    private final MetaProperty<String> _snapshotName = DirectMetaProperty.ofImmutable(
        this, "snapshotName", GoldenCopy.class, String.class);
    /**
     * The meta-property for the {@code viewName} property.
     */
    private final MetaProperty<String> _viewName = DirectMetaProperty.ofImmutable(
        this, "viewName", GoldenCopy.class, String.class);
    /**
     * The meta-property for the {@code valuationTime} property.
     */
    private final MetaProperty<Instant> _valuationTime = DirectMetaProperty.ofImmutable(
        this, "valuationTime", GoldenCopy.class, Instant.class);
    /**
     * The meta-property for the {@code calculationResults} property.
     */
    private final MetaProperty<CalculationResults> _calculationResults = DirectMetaProperty.ofImmutable(
        this, "calculationResults", GoldenCopy.class, CalculationResults.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "snapshotName",
        "viewName",
        "valuationTime",
        "calculationResults");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -931708305:  // snapshotName
          return _snapshotName;
        case 1195658960:  // viewName
          return _viewName;
        case 113591406:  // valuationTime
          return _valuationTime;
        case 2096132333:  // calculationResults
          return _calculationResults;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public GoldenCopy.Builder builder() {
      return new GoldenCopy.Builder();
    }

    @Override
    public Class<? extends GoldenCopy> beanType() {
      return GoldenCopy.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code snapshotName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> snapshotName() {
      return _snapshotName;
    }

    /**
     * The meta-property for the {@code viewName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> viewName() {
      return _viewName;
    }

    /**
     * The meta-property for the {@code valuationTime} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Instant> valuationTime() {
      return _valuationTime;
    }

    /**
     * The meta-property for the {@code calculationResults} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CalculationResults> calculationResults() {
      return _calculationResults;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -931708305:  // snapshotName
          return ((GoldenCopy) bean).getSnapshotName();
        case 1195658960:  // viewName
          return ((GoldenCopy) bean).getViewName();
        case 113591406:  // valuationTime
          return ((GoldenCopy) bean).getValuationTime();
        case 2096132333:  // calculationResults
          return ((GoldenCopy) bean).getCalculationResults();
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
   * The bean-builder for {@code GoldenCopy}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<GoldenCopy> {

    private String _snapshotName;
    private String _viewName;
    private Instant _valuationTime;
    private CalculationResults _calculationResults;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(GoldenCopy beanToCopy) {
      this._snapshotName = beanToCopy.getSnapshotName();
      this._viewName = beanToCopy.getViewName();
      this._valuationTime = beanToCopy.getValuationTime();
      this._calculationResults = beanToCopy.getCalculationResults();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -931708305:  // snapshotName
          return _snapshotName;
        case 1195658960:  // viewName
          return _viewName;
        case 113591406:  // valuationTime
          return _valuationTime;
        case 2096132333:  // calculationResults
          return _calculationResults;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -931708305:  // snapshotName
          this._snapshotName = (String) newValue;
          break;
        case 1195658960:  // viewName
          this._viewName = (String) newValue;
          break;
        case 113591406:  // valuationTime
          this._valuationTime = (Instant) newValue;
          break;
        case 2096132333:  // calculationResults
          this._calculationResults = (CalculationResults) newValue;
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
    public GoldenCopy build() {
      return new GoldenCopy(
          _snapshotName,
          _viewName,
          _valuationTime,
          _calculationResults);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the snapshotName.
     * @param snapshotName  the new value
     * @return this, for chaining, not null
     */
    public Builder snapshotName(String snapshotName) {
      this._snapshotName = snapshotName;
      return this;
    }

    /**
     * Sets the viewName.
     * @param viewName  the new value
     * @return this, for chaining, not null
     */
    public Builder viewName(String viewName) {
      this._viewName = viewName;
      return this;
    }

    /**
     * Sets the valuationTime.
     * @param valuationTime  the new value
     * @return this, for chaining, not null
     */
    public Builder valuationTime(Instant valuationTime) {
      this._valuationTime = valuationTime;
      return this;
    }

    /**
     * Sets the calculationResults.
     * @param calculationResults  the new value
     * @return this, for chaining, not null
     */
    public Builder calculationResults(CalculationResults calculationResults) {
      this._calculationResults = calculationResults;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("GoldenCopy.Builder{");
      buf.append("snapshotName").append('=').append(JodaBeanUtils.toString(_snapshotName)).append(',').append(' ');
      buf.append("viewName").append('=').append(JodaBeanUtils.toString(_viewName)).append(',').append(' ');
      buf.append("valuationTime").append('=').append(JodaBeanUtils.toString(_valuationTime)).append(',').append(' ');
      buf.append("calculationResults").append('=').append(JodaBeanUtils.toString(_calculationResults));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
