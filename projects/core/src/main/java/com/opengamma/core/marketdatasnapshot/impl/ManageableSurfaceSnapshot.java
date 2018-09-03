/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot.impl;

import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.marketdatasnapshot.SurfaceSnapshot;
import com.opengamma.core.marketdatasnapshot.ValueSnapshot;
import com.opengamma.util.tuple.Pair;

/**
 * Mutable snapshot of curve data.
 */
@BeanDefinition
public class ManageableSurfaceSnapshot implements Bean, SurfaceSnapshot {

  /**
   * The values.
   */
  @PropertyDefinition(validate = "notNull")
  private Map<Pair<Object, Object>, ValueSnapshot> _values;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ManageableSurfaceSnapshot}.
   * @return the meta-bean, not null
   */
  public static ManageableSurfaceSnapshot.Meta meta() {
    return ManageableSurfaceSnapshot.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ManageableSurfaceSnapshot.Meta.INSTANCE);
  }

  @Override
  public ManageableSurfaceSnapshot.Meta metaBean() {
    return ManageableSurfaceSnapshot.Meta.INSTANCE;
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
   * Gets the values.
   * @return the value of the property, not null
   */
  public Map<Pair<Object, Object>, ValueSnapshot> getValues() {
    return _values;
  }

  /**
   * Sets the values.
   * @param values  the new value of the property, not null
   */
  public void setValues(Map<Pair<Object, Object>, ValueSnapshot> values) {
    JodaBeanUtils.notNull(values, "values");
    this._values = values;
  }

  /**
   * Gets the the {@code values} property.
   * @return the property, not null
   */
  public final Property<Map<Pair<Object, Object>, ValueSnapshot>> values() {
    return metaBean().values().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ManageableSurfaceSnapshot clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ManageableSurfaceSnapshot other = (ManageableSurfaceSnapshot) obj;
      return JodaBeanUtils.equal(getValues(), other.getValues());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getValues());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ManageableSurfaceSnapshot{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("values").append('=').append(JodaBeanUtils.toString(getValues())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ManageableSurfaceSnapshot}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code values} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<Pair<Object, Object>, ValueSnapshot>> _values = DirectMetaProperty.ofReadWrite(
        this, "values", ManageableSurfaceSnapshot.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "values");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          return _values;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ManageableSurfaceSnapshot> builder() {
      return new DirectBeanBuilder<ManageableSurfaceSnapshot>(new ManageableSurfaceSnapshot());
    }

    @Override
    public Class<? extends ManageableSurfaceSnapshot> beanType() {
      return ManageableSurfaceSnapshot.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code values} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<Pair<Object, Object>, ValueSnapshot>> values() {
      return _values;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          return ((ManageableSurfaceSnapshot) bean).getValues();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          ((ManageableSurfaceSnapshot) bean).setValues((Map<Pair<Object, Object>, ValueSnapshot>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ManageableSurfaceSnapshot) bean)._values, "values");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
