/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.isda.credit;

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

import com.opengamma.analytics.financial.credit.isdastandardmodel.CDSQuoteConvention;
import com.opengamma.analytics.financial.credit.isdastandardmodel.QuotedSpread;

/**
 * A flat spread quote. In OpenGamma this is also known as a quoted spread.
 */
@BeanDefinition
public class FlatSpreadQuote implements CdsQuote, ImmutableBean {

  /**
   * The coupon of the spread, denoted as a fractional amount.
   */
  @PropertyDefinition
  private final double _coupon;

  /**
   * The quoted spread, denoted as a fractional amount.
   */
  @PropertyDefinition
  private final double _quotedSpread;

  /**
   * Creates a flat spread quote using the passed coupon and quoted spread.
   * @param coupon the coupon to use, a fractional amount.
   * @param quotedSpread the quoted spread, a fractional amount.
   * @return a flat spread quote
   */
  public static FlatSpreadQuote from(final double coupon, final double quotedSpread) {
    return builder().coupon(coupon).quotedSpread(quotedSpread).build();
  }

  @Override
  public CDSQuoteConvention toQuoteConvention() {
    return new QuotedSpread(getCoupon(), getQuotedSpread());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FlatSpreadQuote}.
   * @return the meta-bean, not null
   */
  public static FlatSpreadQuote.Meta meta() {
    return FlatSpreadQuote.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FlatSpreadQuote.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static FlatSpreadQuote.Builder builder() {
    return new FlatSpreadQuote.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected FlatSpreadQuote(FlatSpreadQuote.Builder builder) {
    this._coupon = builder._coupon;
    this._quotedSpread = builder._quotedSpread;
  }

  @Override
  public FlatSpreadQuote.Meta metaBean() {
    return FlatSpreadQuote.Meta.INSTANCE;
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
   * Gets the coupon of the spread, denoted as a fractional amount.
   * @return the value of the property
   */
  public double getCoupon() {
    return _coupon;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the quoted spread, denoted as a fractional amount.
   * @return the value of the property
   */
  public double getQuotedSpread() {
    return _quotedSpread;
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
      FlatSpreadQuote other = (FlatSpreadQuote) obj;
      return JodaBeanUtils.equal(_coupon, other._coupon) &&
          JodaBeanUtils.equal(_quotedSpread, other._quotedSpread);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_coupon);
    hash = hash * 31 + JodaBeanUtils.hashCode(_quotedSpread);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("FlatSpreadQuote{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("coupon").append('=').append(JodaBeanUtils.toString(_coupon)).append(',').append(' ');
    buf.append("quotedSpread").append('=').append(JodaBeanUtils.toString(_quotedSpread)).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FlatSpreadQuote}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code coupon} property.
     */
    private final MetaProperty<Double> _coupon = DirectMetaProperty.ofImmutable(
        this, "coupon", FlatSpreadQuote.class, Double.TYPE);
    /**
     * The meta-property for the {@code quotedSpread} property.
     */
    private final MetaProperty<Double> _quotedSpread = DirectMetaProperty.ofImmutable(
        this, "quotedSpread", FlatSpreadQuote.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "coupon",
        "quotedSpread");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1354573786:  // coupon
          return _coupon;
        case -963526405:  // quotedSpread
          return _quotedSpread;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public FlatSpreadQuote.Builder builder() {
      return new FlatSpreadQuote.Builder();
    }

    @Override
    public Class<? extends FlatSpreadQuote> beanType() {
      return FlatSpreadQuote.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code coupon} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> coupon() {
      return _coupon;
    }

    /**
     * The meta-property for the {@code quotedSpread} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> quotedSpread() {
      return _quotedSpread;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1354573786:  // coupon
          return ((FlatSpreadQuote) bean).getCoupon();
        case -963526405:  // quotedSpread
          return ((FlatSpreadQuote) bean).getQuotedSpread();
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
   * The bean-builder for {@code FlatSpreadQuote}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<FlatSpreadQuote> {

    private double _coupon;
    private double _quotedSpread;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(FlatSpreadQuote beanToCopy) {
      this._coupon = beanToCopy.getCoupon();
      this._quotedSpread = beanToCopy.getQuotedSpread();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1354573786:  // coupon
          return _coupon;
        case -963526405:  // quotedSpread
          return _quotedSpread;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1354573786:  // coupon
          this._coupon = (Double) newValue;
          break;
        case -963526405:  // quotedSpread
          this._quotedSpread = (Double) newValue;
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
    public FlatSpreadQuote build() {
      return new FlatSpreadQuote(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the coupon of the spread, denoted as a fractional amount.
     * @param coupon  the new value
     * @return this, for chaining, not null
     */
    public Builder coupon(double coupon) {
      this._coupon = coupon;
      return this;
    }

    /**
     * Sets the quoted spread, denoted as a fractional amount.
     * @param quotedSpread  the new value
     * @return this, for chaining, not null
     */
    public Builder quotedSpread(double quotedSpread) {
      this._quotedSpread = quotedSpread;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("FlatSpreadQuote.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("coupon").append('=').append(JodaBeanUtils.toString(_coupon)).append(',').append(' ');
      buf.append("quotedSpread").append('=').append(JodaBeanUtils.toString(_quotedSpread)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
