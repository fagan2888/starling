/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.index;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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

import com.opengamma.masterdb.security.hibernate.ExternalIdBean;

/**
 * A Hibernate bean representation of {@link OvernightIndexBean}.
 */
@BeanDefinition
public class OvernightIndexBean extends IndexBean {
  @PropertyDefinition
  private ExternalIdBean _conventionId;

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof OvernightIndexBean)) {
      return false;
    }
    final OvernightIndexBean index = (OvernightIndexBean) other;
    return new EqualsBuilder()
      .append(getId(), index.getId())
      .append(getDescription(), index.getDescription())
      .append(getConventionId(), index.getConventionId())
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(getDescription())
      .append(getConventionId())
      .toHashCode();
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code OvernightIndexBean}.
   * @return the meta-bean, not null
   */
  public static OvernightIndexBean.Meta meta() {
    return OvernightIndexBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(OvernightIndexBean.Meta.INSTANCE);
  }

  @Override
  public OvernightIndexBean.Meta metaBean() {
    return OvernightIndexBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the conventionId.
   * @return the value of the property
   */
  public ExternalIdBean getConventionId() {
    return _conventionId;
  }

  /**
   * Sets the conventionId.
   * @param conventionId  the new value of the property
   */
  public void setConventionId(ExternalIdBean conventionId) {
    this._conventionId = conventionId;
  }

  /**
   * Gets the the {@code conventionId} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> conventionId() {
    return metaBean().conventionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public OvernightIndexBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("OvernightIndexBean{");
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
    buf.append("conventionId").append('=').append(JodaBeanUtils.toString(getConventionId())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code OvernightIndexBean}.
   */
  public static class Meta extends IndexBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code conventionId} property.
     */
    private final MetaProperty<ExternalIdBean> _conventionId = DirectMetaProperty.ofReadWrite(
        this, "conventionId", OvernightIndexBean.class, ExternalIdBean.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "conventionId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1520979052:  // conventionId
          return _conventionId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends OvernightIndexBean> builder() {
      return new DirectBeanBuilder<OvernightIndexBean>(new OvernightIndexBean());
    }

    @Override
    public Class<? extends OvernightIndexBean> beanType() {
      return OvernightIndexBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code conventionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> conventionId() {
      return _conventionId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1520979052:  // conventionId
          return ((OvernightIndexBean) bean).getConventionId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1520979052:  // conventionId
          ((OvernightIndexBean) bean).setConventionId((ExternalIdBean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
