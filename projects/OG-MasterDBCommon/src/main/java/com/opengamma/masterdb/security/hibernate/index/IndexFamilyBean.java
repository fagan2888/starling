/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.index;

import java.util.Map;
import java.util.Set;

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

import com.opengamma.masterdb.security.hibernate.SecurityBean;

/**
 * A Hibernate bean representation of {@link IndexFamilyBean}.
 */
@BeanDefinition
public class IndexFamilyBean extends SecurityBean {
  @PropertyDefinition
  private Set<IndexFamilyEntryBean> _entries;

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof IndexFamilyBean)) {
      return false;
    }
    IndexFamilyBean index = (IndexFamilyBean) other;
    return new EqualsBuilder()
      .append(getId(), index.getId())
      .append(getEntries(), index.getEntries())
      .isEquals();
  }
  
  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(getId())
      .append(getEntries())
      .toHashCode();
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IndexFamilyBean}.
   * @return the meta-bean, not null
   */
  public static IndexFamilyBean.Meta meta() {
    return IndexFamilyBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IndexFamilyBean.Meta.INSTANCE);
  }

  @Override
  public IndexFamilyBean.Meta metaBean() {
    return IndexFamilyBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the entries.
   * @return the value of the property
   */
  public Set<IndexFamilyEntryBean> getEntries() {
    return _entries;
  }

  /**
   * Sets the entries.
   * @param entries  the new value of the property
   */
  public void setEntries(Set<IndexFamilyEntryBean> entries) {
    this._entries = entries;
  }

  /**
   * Gets the the {@code entries} property.
   * @return the property, not null
   */
  public final Property<Set<IndexFamilyEntryBean>> entries() {
    return metaBean().entries().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public IndexFamilyBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("IndexFamilyBean{");
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
    buf.append("entries").append('=').append(JodaBeanUtils.toString(getEntries())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IndexFamilyBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code entries} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<IndexFamilyEntryBean>> _entries = DirectMetaProperty.ofReadWrite(
        this, "entries", IndexFamilyBean.class, (Class) Set.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "entries");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1591573360:  // entries
          return _entries;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IndexFamilyBean> builder() {
      return new DirectBeanBuilder<IndexFamilyBean>(new IndexFamilyBean());
    }

    @Override
    public Class<? extends IndexFamilyBean> beanType() {
      return IndexFamilyBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code entries} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Set<IndexFamilyEntryBean>> entries() {
      return _entries;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1591573360:  // entries
          return ((IndexFamilyBean) bean).getEntries();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1591573360:  // entries
          ((IndexFamilyBean) bean).setEntries((Set<IndexFamilyEntryBean>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}