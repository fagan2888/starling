/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.livedata.cogda.msg;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;

/**
 *
 */
@BeanDefinition
public abstract class CogdaLiveDataCommandResponseMessage extends CogdaCommandResponseMessage {
  /**
   * The item being requested.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _subscriptionId;
  /**
   * The optional name of the normalization scheme requested.
   */
  @PropertyDefinition
  private String _normalizationScheme;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CogdaLiveDataCommandResponseMessage}.
   * @return the meta-bean, not null
   */
  public static CogdaLiveDataCommandResponseMessage.Meta meta() {
    return CogdaLiveDataCommandResponseMessage.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CogdaLiveDataCommandResponseMessage.Meta.INSTANCE);
  }

  @Override
  public CogdaLiveDataCommandResponseMessage.Meta metaBean() {
    return CogdaLiveDataCommandResponseMessage.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the item being requested.
   * @return the value of the property, not null
   */
  public ExternalId getSubscriptionId() {
    return _subscriptionId;
  }

  /**
   * Sets the item being requested.
   * @param subscriptionId  the new value of the property, not null
   */
  public void setSubscriptionId(ExternalId subscriptionId) {
    JodaBeanUtils.notNull(subscriptionId, "subscriptionId");
    this._subscriptionId = subscriptionId;
  }

  /**
   * Gets the the {@code subscriptionId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> subscriptionId() {
    return metaBean().subscriptionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the optional name of the normalization scheme requested.
   * @return the value of the property
   */
  public String getNormalizationScheme() {
    return _normalizationScheme;
  }

  /**
   * Sets the optional name of the normalization scheme requested.
   * @param normalizationScheme  the new value of the property
   */
  public void setNormalizationScheme(String normalizationScheme) {
    this._normalizationScheme = normalizationScheme;
  }

  /**
   * Gets the the {@code normalizationScheme} property.
   * @return the property, not null
   */
  public final Property<String> normalizationScheme() {
    return metaBean().normalizationScheme().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CogdaLiveDataCommandResponseMessage other = (CogdaLiveDataCommandResponseMessage) obj;
      return JodaBeanUtils.equal(getSubscriptionId(), other.getSubscriptionId()) &&
          JodaBeanUtils.equal(getNormalizationScheme(), other.getNormalizationScheme()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getSubscriptionId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getNormalizationScheme());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("CogdaLiveDataCommandResponseMessage{");
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
    buf.append("subscriptionId").append('=').append(JodaBeanUtils.toString(getSubscriptionId())).append(',').append(' ');
    buf.append("normalizationScheme").append('=').append(JodaBeanUtils.toString(getNormalizationScheme())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CogdaLiveDataCommandResponseMessage}.
   */
  public static class Meta extends CogdaCommandResponseMessage.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code subscriptionId} property.
     */
    private final MetaProperty<ExternalId> _subscriptionId = DirectMetaProperty.ofReadWrite(
        this, "subscriptionId", CogdaLiveDataCommandResponseMessage.class, ExternalId.class);
    /**
     * The meta-property for the {@code normalizationScheme} property.
     */
    private final MetaProperty<String> _normalizationScheme = DirectMetaProperty.ofReadWrite(
        this, "normalizationScheme", CogdaLiveDataCommandResponseMessage.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "subscriptionId",
        "normalizationScheme");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1478790936:  // subscriptionId
          return _subscriptionId;
        case -1440170590:  // normalizationScheme
          return _normalizationScheme;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CogdaLiveDataCommandResponseMessage> builder() {
      throw new UnsupportedOperationException("CogdaLiveDataCommandResponseMessage is an abstract class");
    }

    @Override
    public Class<? extends CogdaLiveDataCommandResponseMessage> beanType() {
      return CogdaLiveDataCommandResponseMessage.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code subscriptionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> subscriptionId() {
      return _subscriptionId;
    }

    /**
     * The meta-property for the {@code normalizationScheme} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> normalizationScheme() {
      return _normalizationScheme;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1478790936:  // subscriptionId
          return ((CogdaLiveDataCommandResponseMessage) bean).getSubscriptionId();
        case -1440170590:  // normalizationScheme
          return ((CogdaLiveDataCommandResponseMessage) bean).getNormalizationScheme();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1478790936:  // subscriptionId
          ((CogdaLiveDataCommandResponseMessage) bean).setSubscriptionId((ExternalId) newValue);
          return;
        case -1440170590:  // normalizationScheme
          ((CogdaLiveDataCommandResponseMessage) bean).setNormalizationScheme((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((CogdaLiveDataCommandResponseMessage) bean)._subscriptionId, "subscriptionId");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
