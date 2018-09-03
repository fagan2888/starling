/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.mcleodmoores.financial.function.trade;

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
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.swap.SwapDefinition;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.financial.security.irs.PayReceiveType;
import com.opengamma.financial.security.swap.FixedInterestRateLeg;
import com.opengamma.financial.security.swap.SwapSecurity;
import com.opengamma.util.ArgumentChecker;

/**
 *
 */
@BeanDefinition
public class SwapDetailsProvider implements ImmutableBean, InstrumentDetailsProvider<SwapDefinition, MulticurveProviderInterface> {

  /**
   * The MulticurveProviderInterface bundle
   */
  @PropertyDefinition(validate = "notNull")
  private final MulticurveProviderInterface _curves;
  /**
   * The valuation time
   */
  @PropertyDefinition(validate = "notNull")
  private final ZonedDateTime _valuationTime;
  /**
   * Boolean, whether the leg is fixed or floating
   */
  @PropertyDefinition(validate = "notNull")
  private final boolean _fixed;
  /**
   * The swap definition
   */
  @PropertyDefinition(validate = "notNull")
  private final SwapDefinition _definition;
  /**
   * The PayReceiveType, whether the leg is pay or receive
   */
  @PropertyDefinition(validate = "notNull")
  private final PayReceiveType _type;

  /**
   * Creates an instance.
   *
   * @param curves  the curves, not null
   * @param valuationTime  the valuation time, not null
   * @param definition  the swap containing the payment definitions, not null
   * @param security  the security, not null
   * @param type  whether the pay or receive leg is to be used, not null
   */
  @ImmutableConstructor
  public SwapDetailsProvider(final MulticurveProviderInterface curves, final ZonedDateTime valuationTime,
                             final SwapDefinition definition, final SwapSecurity security, final PayReceiveType type) {
    _curves = ArgumentChecker.notNull(curves, "curves");
    _valuationTime = ArgumentChecker.notNull(valuationTime, "valuationTime");
    _definition = ArgumentChecker.notNull(definition, "definition");
    _type = ArgumentChecker.notNull(type, "type");
    if (type == PayReceiveType.PAY) {
      _fixed = security.getPayLeg() instanceof FixedInterestRateLeg;
    } else {
      _fixed = security.getReceiveLeg() instanceof FixedInterestRateLeg;
    }
  }

  private SwapDetailsProvider(final MulticurveProviderInterface curves, final ZonedDateTime valuationTime,
      final boolean fixed, final SwapDefinition definition, final PayReceiveType type) {
    _curves = ArgumentChecker.notNull(curves, "curves");
    _valuationTime = ArgumentChecker.notNull(valuationTime, "valuationTime");
    _definition = ArgumentChecker.notNull(definition, "definition");
    _type = ArgumentChecker.notNull(type, "type");
    _fixed = fixed;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SwapDetailsProvider}.
   * @return the meta-bean, not null
   */
  public static SwapDetailsProvider.Meta meta() {
    return SwapDetailsProvider.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SwapDetailsProvider.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static SwapDetailsProvider.Builder builder() {
    return new SwapDetailsProvider.Builder();
  }

  @Override
  public SwapDetailsProvider.Meta metaBean() {
    return SwapDetailsProvider.Meta.INSTANCE;
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
   * Gets the MulticurveProviderInterface bundle
   * @return the value of the property, not null
   */
  public MulticurveProviderInterface getCurves() {
    return _curves;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuation time
   * @return the value of the property, not null
   */
  public ZonedDateTime getValuationTime() {
    return _valuationTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets boolean, whether the leg is fixed or floating
   * @return the value of the property, not null
   */
  public boolean isFixed() {
    return _fixed;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the swap definition
   * @return the value of the property, not null
   */
  public SwapDefinition getDefinition() {
    return _definition;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the PayReceiveType, whether the leg is pay or receive
   * @return the value of the property, not null
   */
  public PayReceiveType getType() {
    return _type;
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
      SwapDetailsProvider other = (SwapDetailsProvider) obj;
      return JodaBeanUtils.equal(_curves, other._curves) &&
          JodaBeanUtils.equal(_valuationTime, other._valuationTime) &&
          (_fixed == other._fixed) &&
          JodaBeanUtils.equal(_definition, other._definition) &&
          JodaBeanUtils.equal(_type, other._type);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_curves);
    hash = hash * 31 + JodaBeanUtils.hashCode(_valuationTime);
    hash = hash * 31 + JodaBeanUtils.hashCode(_fixed);
    hash = hash * 31 + JodaBeanUtils.hashCode(_definition);
    hash = hash * 31 + JodaBeanUtils.hashCode(_type);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("SwapDetailsProvider{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("curves").append('=').append(JodaBeanUtils.toString(_curves)).append(',').append(' ');
    buf.append("valuationTime").append('=').append(JodaBeanUtils.toString(_valuationTime)).append(',').append(' ');
    buf.append("fixed").append('=').append(JodaBeanUtils.toString(_fixed)).append(',').append(' ');
    buf.append("definition").append('=').append(JodaBeanUtils.toString(_definition)).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(_type)).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SwapDetailsProvider}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code curves} property.
     */
    private final MetaProperty<MulticurveProviderInterface> _curves = DirectMetaProperty.ofImmutable(
        this, "curves", SwapDetailsProvider.class, MulticurveProviderInterface.class);
    /**
     * The meta-property for the {@code valuationTime} property.
     */
    private final MetaProperty<ZonedDateTime> _valuationTime = DirectMetaProperty.ofImmutable(
        this, "valuationTime", SwapDetailsProvider.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code fixed} property.
     */
    private final MetaProperty<Boolean> _fixed = DirectMetaProperty.ofImmutable(
        this, "fixed", SwapDetailsProvider.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code definition} property.
     */
    private final MetaProperty<SwapDefinition> _definition = DirectMetaProperty.ofImmutable(
        this, "definition", SwapDetailsProvider.class, SwapDefinition.class);
    /**
     * The meta-property for the {@code type} property.
     */
    private final MetaProperty<PayReceiveType> _type = DirectMetaProperty.ofImmutable(
        this, "type", SwapDetailsProvider.class, PayReceiveType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "curves",
        "valuationTime",
        "fixed",
        "definition",
        "type");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1349116572:  // curves
          return _curves;
        case 113591406:  // valuationTime
          return _valuationTime;
        case 97445748:  // fixed
          return _fixed;
        case -1014418093:  // definition
          return _definition;
        case 3575610:  // type
          return _type;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public SwapDetailsProvider.Builder builder() {
      return new SwapDetailsProvider.Builder();
    }

    @Override
    public Class<? extends SwapDetailsProvider> beanType() {
      return SwapDetailsProvider.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code curves} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MulticurveProviderInterface> curves() {
      return _curves;
    }

    /**
     * The meta-property for the {@code valuationTime} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> valuationTime() {
      return _valuationTime;
    }

    /**
     * The meta-property for the {@code fixed} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> fixed() {
      return _fixed;
    }

    /**
     * The meta-property for the {@code definition} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SwapDefinition> definition() {
      return _definition;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PayReceiveType> type() {
      return _type;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1349116572:  // curves
          return ((SwapDetailsProvider) bean).getCurves();
        case 113591406:  // valuationTime
          return ((SwapDetailsProvider) bean).getValuationTime();
        case 97445748:  // fixed
          return ((SwapDetailsProvider) bean).isFixed();
        case -1014418093:  // definition
          return ((SwapDetailsProvider) bean).getDefinition();
        case 3575610:  // type
          return ((SwapDetailsProvider) bean).getType();
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
   * The bean-builder for {@code SwapDetailsProvider}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<SwapDetailsProvider> {

    private MulticurveProviderInterface _curves;
    private ZonedDateTime _valuationTime;
    private boolean _fixed;
    private SwapDefinition _definition;
    private PayReceiveType _type;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(SwapDetailsProvider beanToCopy) {
      this._curves = beanToCopy.getCurves();
      this._valuationTime = beanToCopy.getValuationTime();
      this._fixed = beanToCopy.isFixed();
      this._definition = beanToCopy.getDefinition();
      this._type = beanToCopy.getType();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1349116572:  // curves
          return _curves;
        case 113591406:  // valuationTime
          return _valuationTime;
        case 97445748:  // fixed
          return _fixed;
        case -1014418093:  // definition
          return _definition;
        case 3575610:  // type
          return _type;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1349116572:  // curves
          this._curves = (MulticurveProviderInterface) newValue;
          break;
        case 113591406:  // valuationTime
          this._valuationTime = (ZonedDateTime) newValue;
          break;
        case 97445748:  // fixed
          this._fixed = (Boolean) newValue;
          break;
        case -1014418093:  // definition
          this._definition = (SwapDefinition) newValue;
          break;
        case 3575610:  // type
          this._type = (PayReceiveType) newValue;
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
    public SwapDetailsProvider build() {
      return new SwapDetailsProvider(
          _curves,
          _valuationTime,
          _fixed,
          _definition,
          _type);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the MulticurveProviderInterface bundle
     * @param curves  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder curves(MulticurveProviderInterface curves) {
      JodaBeanUtils.notNull(curves, "curves");
      this._curves = curves;
      return this;
    }

    /**
     * Sets the valuation time
     * @param valuationTime  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder valuationTime(ZonedDateTime valuationTime) {
      JodaBeanUtils.notNull(valuationTime, "valuationTime");
      this._valuationTime = valuationTime;
      return this;
    }

    /**
     * Sets boolean, whether the leg is fixed or floating
     * @param fixed  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder fixed(boolean fixed) {
      JodaBeanUtils.notNull(fixed, "fixed");
      this._fixed = fixed;
      return this;
    }

    /**
     * Sets the swap definition
     * @param definition  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder definition(SwapDefinition definition) {
      JodaBeanUtils.notNull(definition, "definition");
      this._definition = definition;
      return this;
    }

    /**
     * Sets the PayReceiveType, whether the leg is pay or receive
     * @param type  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder type(PayReceiveType type) {
      JodaBeanUtils.notNull(type, "type");
      this._type = type;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("SwapDetailsProvider.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("curves").append('=').append(JodaBeanUtils.toString(_curves)).append(',').append(' ');
      buf.append("valuationTime").append('=').append(JodaBeanUtils.toString(_valuationTime)).append(',').append(' ');
      buf.append("fixed").append('=').append(JodaBeanUtils.toString(_fixed)).append(',').append(' ');
      buf.append("definition").append('=').append(JodaBeanUtils.toString(_definition)).append(',').append(' ');
      buf.append("type").append('=').append(JodaBeanUtils.toString(_type)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
