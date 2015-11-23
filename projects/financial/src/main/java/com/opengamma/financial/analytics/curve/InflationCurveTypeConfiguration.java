/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;

/**
 *
 */
@BeanDefinition
public class InflationCurveTypeConfiguration extends CurveTypeConfiguration {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The reference.
   */
  @PropertyDefinition(validate = "notNull")
  private String _reference;

  /**
   * The price index convention.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _priceIndex;

  /**
   * For the builder.
   */
  /* package */ InflationCurveTypeConfiguration() {
    super();
  };

  /**
   * @param reference The code for this curve (e.g. a currency), not null
   * @param priceIndex The price index for this curve, not null
   */
  public InflationCurveTypeConfiguration(final String reference, final ExternalId priceIndex) {
    super();
    setReference(reference);
    setPriceIndex(priceIndex);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code InflationCurveTypeConfiguration}.
   * @return the meta-bean, not null
   */
  public static InflationCurveTypeConfiguration.Meta meta() {
    return InflationCurveTypeConfiguration.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(InflationCurveTypeConfiguration.Meta.INSTANCE);
  }

  @Override
  public InflationCurveTypeConfiguration.Meta metaBean() {
    return InflationCurveTypeConfiguration.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference.
   * @return the value of the property, not null
   */
  public String getReference() {
    return _reference;
  }

  /**
   * Sets the reference.
   * @param reference  the new value of the property, not null
   */
  public void setReference(String reference) {
    JodaBeanUtils.notNull(reference, "reference");
    this._reference = reference;
  }

  /**
   * Gets the the {@code reference} property.
   * @return the property, not null
   */
  public final Property<String> reference() {
    return metaBean().reference().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the price index convention.
   * @return the value of the property, not null
   */
  public ExternalId getPriceIndex() {
    return _priceIndex;
  }

  /**
   * Sets the price index convention.
   * @param priceIndex  the new value of the property, not null
   */
  public void setPriceIndex(ExternalId priceIndex) {
    JodaBeanUtils.notNull(priceIndex, "priceIndex");
    this._priceIndex = priceIndex;
  }

  /**
   * Gets the the {@code priceIndex} property.
   * @return the property, not null
   */
  public final Property<ExternalId> priceIndex() {
    return metaBean().priceIndex().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public InflationCurveTypeConfiguration clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      InflationCurveTypeConfiguration other = (InflationCurveTypeConfiguration) obj;
      return JodaBeanUtils.equal(getReference(), other.getReference()) &&
          JodaBeanUtils.equal(getPriceIndex(), other.getPriceIndex()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getReference());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPriceIndex());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("InflationCurveTypeConfiguration{");
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
    buf.append("reference").append('=').append(JodaBeanUtils.toString(getReference())).append(',').append(' ');
    buf.append("priceIndex").append('=').append(JodaBeanUtils.toString(getPriceIndex())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code InflationCurveTypeConfiguration}.
   */
  public static class Meta extends CurveTypeConfiguration.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code reference} property.
     */
    private final MetaProperty<String> _reference = DirectMetaProperty.ofReadWrite(
        this, "reference", InflationCurveTypeConfiguration.class, String.class);
    /**
     * The meta-property for the {@code priceIndex} property.
     */
    private final MetaProperty<ExternalId> _priceIndex = DirectMetaProperty.ofReadWrite(
        this, "priceIndex", InflationCurveTypeConfiguration.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "reference",
        "priceIndex");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -925155509:  // reference
          return _reference;
        case -1483674359:  // priceIndex
          return _priceIndex;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends InflationCurveTypeConfiguration> builder() {
      return new DirectBeanBuilder<InflationCurveTypeConfiguration>(new InflationCurveTypeConfiguration());
    }

    @Override
    public Class<? extends InflationCurveTypeConfiguration> beanType() {
      return InflationCurveTypeConfiguration.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code reference} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> reference() {
      return _reference;
    }

    /**
     * The meta-property for the {@code priceIndex} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> priceIndex() {
      return _priceIndex;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -925155509:  // reference
          return ((InflationCurveTypeConfiguration) bean).getReference();
        case -1483674359:  // priceIndex
          return ((InflationCurveTypeConfiguration) bean).getPriceIndex();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -925155509:  // reference
          ((InflationCurveTypeConfiguration) bean).setReference((String) newValue);
          return;
        case -1483674359:  // priceIndex
          ((InflationCurveTypeConfiguration) bean).setPriceIndex((ExternalId) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((InflationCurveTypeConfiguration) bean)._reference, "reference");
      JodaBeanUtils.notNull(((InflationCurveTypeConfiguration) bean)._priceIndex, "priceIndex");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}