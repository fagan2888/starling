/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.irs;

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
import org.joda.beans.impl.BasicImmutableBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Logic controlling how a notional is to be exchanged.
 */
@BeanDefinition
public final class NotionalExchange implements ImmutableBean {

  /** Exchange the final notional */
  @PropertyDefinition(validate = "notNull")
  private final boolean _exchangeFinalNotional;

  /**
   * Exchange the initial notional
   */
  @PropertyDefinition(validate = "notNull")
  private final boolean _exchangeInitialNotional;

  /**
   * Exchange the interim notional
   */
  @PropertyDefinition(validate = "notNull")
  private final boolean _exchangeInterimNotional;

  /**
   * No exchange (at any point).
   */
  public static final NotionalExchange NO_EXCHANGE = builder().build();

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code NotionalExchange}.
   * @return the meta-bean, not null
   */
  public static NotionalExchange.Meta meta() {
    return NotionalExchange.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(NotionalExchange.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   *
   * @return the builder, not null
   */
  public static NotionalExchange.Builder builder() {
    return new NotionalExchange.Builder();
  }

  private NotionalExchange(
      boolean exchangeFinalNotional,
      boolean exchangeInitialNotional,
      boolean exchangeInterimNotional) {
    JodaBeanUtils.notNull(exchangeFinalNotional, "exchangeFinalNotional");
    JodaBeanUtils.notNull(exchangeInitialNotional, "exchangeInitialNotional");
    JodaBeanUtils.notNull(exchangeInterimNotional, "exchangeInterimNotional");
    this._exchangeFinalNotional = exchangeFinalNotional;
    this._exchangeInitialNotional = exchangeInitialNotional;
    this._exchangeInterimNotional = exchangeInterimNotional;
  }

  @Override
  public NotionalExchange.Meta metaBean() {
    return NotionalExchange.Meta.INSTANCE;
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
   * Gets exchange the final notional
   * @return the value of the property, not null
   */
  public boolean isExchangeFinalNotional() {
    return _exchangeFinalNotional;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets exchange the initial notional
   * @return the value of the property, not null
   */
  public boolean isExchangeInitialNotional() {
    return _exchangeInitialNotional;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets exchange the interim notional
   * @return the value of the property, not null
   */
  public boolean isExchangeInterimNotional() {
    return _exchangeInterimNotional;
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
  public NotionalExchange clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      NotionalExchange other = (NotionalExchange) obj;
      return (isExchangeFinalNotional() == other.isExchangeFinalNotional()) &&
          (isExchangeInitialNotional() == other.isExchangeInitialNotional()) &&
          (isExchangeInterimNotional() == other.isExchangeInterimNotional());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(isExchangeFinalNotional());
    hash += hash * 31 + JodaBeanUtils.hashCode(isExchangeInitialNotional());
    hash += hash * 31 + JodaBeanUtils.hashCode(isExchangeInterimNotional());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("NotionalExchange{");
    buf.append("exchangeFinalNotional").append('=').append(isExchangeFinalNotional()).append(',').append(' ');
    buf.append("exchangeInitialNotional").append('=').append(isExchangeInitialNotional()).append(',').append(' ');
    buf.append("exchangeInterimNotional").append('=').append(JodaBeanUtils.toString(isExchangeInterimNotional()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code NotionalExchange}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code exchangeFinalNotional} property.
     */
    private final MetaProperty<Boolean> _exchangeFinalNotional = DirectMetaProperty.ofImmutable(
        this, "exchangeFinalNotional", NotionalExchange.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code exchangeInitialNotional} property.
     */
    private final MetaProperty<Boolean> _exchangeInitialNotional = DirectMetaProperty.ofImmutable(
        this, "exchangeInitialNotional", NotionalExchange.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code exchangeInterimNotional} property.
     */
    private final MetaProperty<Boolean> _exchangeInterimNotional = DirectMetaProperty.ofImmutable(
        this, "exchangeInterimNotional", NotionalExchange.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "exchangeFinalNotional",
        "exchangeInitialNotional",
        "exchangeInterimNotional");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1976228493:  // exchangeFinalNotional
          return _exchangeFinalNotional;
        case -1304307199:  // exchangeInitialNotional
          return _exchangeInitialNotional;
        case -250302019:  // exchangeInterimNotional
          return _exchangeInterimNotional;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public NotionalExchange.Builder builder() {
      return new NotionalExchange.Builder();
    }

    @Override
    public Class<? extends NotionalExchange> beanType() {
      return NotionalExchange.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code exchangeFinalNotional} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> exchangeFinalNotional() {
      return _exchangeFinalNotional;
    }

    /**
     * The meta-property for the {@code exchangeInitialNotional} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> exchangeInitialNotional() {
      return _exchangeInitialNotional;
    }

    /**
     * The meta-property for the {@code exchangeInterimNotional} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> exchangeInterimNotional() {
      return _exchangeInterimNotional;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1976228493:  // exchangeFinalNotional
          return ((NotionalExchange) bean).isExchangeFinalNotional();
        case -1304307199:  // exchangeInitialNotional
          return ((NotionalExchange) bean).isExchangeInitialNotional();
        case -250302019:  // exchangeInterimNotional
          return ((NotionalExchange) bean).isExchangeInterimNotional();
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
   * The bean-builder for {@code NotionalExchange}.
   */
  public static final class Builder extends BasicImmutableBeanBuilder<NotionalExchange> {

    private boolean _exchangeFinalNotional;
    private boolean _exchangeInitialNotional;
    private boolean _exchangeInterimNotional;

    /**
     * Restricted constructor.
     */
    private Builder() {
      super(NotionalExchange.Meta.INSTANCE);
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(NotionalExchange beanToCopy) {
      super(NotionalExchange.Meta.INSTANCE);
      this._exchangeFinalNotional = beanToCopy.isExchangeFinalNotional();
      this._exchangeInitialNotional = beanToCopy.isExchangeInitialNotional();
      this._exchangeInterimNotional = beanToCopy.isExchangeInterimNotional();
    }

    //-----------------------------------------------------------------------
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1976228493:  // exchangeFinalNotional
          this._exchangeFinalNotional = (Boolean) newValue;
          break;
        case -1304307199:  // exchangeInitialNotional
          this._exchangeInitialNotional = (Boolean) newValue;
          break;
        case -250302019:  // exchangeInterimNotional
          this._exchangeInterimNotional = (Boolean) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public NotionalExchange build() {
      return new NotionalExchange(
          _exchangeFinalNotional,
          _exchangeInitialNotional,
          _exchangeInterimNotional);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code exchangeFinalNotional} property in the builder.
     * @param exchangeFinalNotional  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder exchangeFinalNotional(boolean exchangeFinalNotional) {
      JodaBeanUtils.notNull(exchangeFinalNotional, "exchangeFinalNotional");
      this._exchangeFinalNotional = exchangeFinalNotional;
      return this;
    }

    /**
     * Sets the {@code exchangeInitialNotional} property in the builder.
     * @param exchangeInitialNotional  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder exchangeInitialNotional(boolean exchangeInitialNotional) {
      JodaBeanUtils.notNull(exchangeInitialNotional, "exchangeInitialNotional");
      this._exchangeInitialNotional = exchangeInitialNotional;
      return this;
    }

    /**
     * Sets the {@code exchangeInterimNotional} property in the builder.
     * @param exchangeInterimNotional  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder exchangeInterimNotional(boolean exchangeInterimNotional) {
      JodaBeanUtils.notNull(exchangeInterimNotional, "exchangeInterimNotional");
      this._exchangeInterimNotional = exchangeInterimNotional;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("NotionalExchange.Builder{");
      buf.append("exchangeFinalNotional").append('=').append(_exchangeFinalNotional).append(',').append(' ');
      buf.append("exchangeInitialNotional").append('=').append(_exchangeInitialNotional).append(',').append(' ');
      buf.append("exchangeInterimNotional").append('=').append(_exchangeInterimNotional);
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
