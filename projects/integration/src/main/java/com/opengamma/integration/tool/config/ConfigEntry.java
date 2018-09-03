/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.config;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Hold a single configuration database entry for storage retrieval from files
 */
@BeanDefinition
public class ConfigEntry extends DirectBean {
  @PropertyDefinition(validate = "notNull")
  private String _name;

  @PropertyDefinition(validate = "notNull")
  private String _type;

  @PropertyDefinition(validate = "notNull")
  private Object _object;
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ConfigEntry}.
   * @return the meta-bean, not null
   */
  public static ConfigEntry.Meta meta() {
    return ConfigEntry.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ConfigEntry.Meta.INSTANCE);
  }

  @Override
  public ConfigEntry.Meta metaBean() {
    return ConfigEntry.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name.
   * @param name  the new value of the property, not null
   */
  public void setName(String name) {
    JodaBeanUtils.notNull(name, "name");
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the type.
   * @return the value of the property, not null
   */
  public String getType() {
    return _type;
  }

  /**
   * Sets the type.
   * @param type  the new value of the property, not null
   */
  public void setType(String type) {
    JodaBeanUtils.notNull(type, "type");
    this._type = type;
  }

  /**
   * Gets the the {@code type} property.
   * @return the property, not null
   */
  public final Property<String> type() {
    return metaBean().type().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the object.
   * @return the value of the property, not null
   */
  public Object getObject() {
    return _object;
  }

  /**
   * Sets the object.
   * @param object  the new value of the property, not null
   */
  public void setObject(Object object) {
    JodaBeanUtils.notNull(object, "object");
    this._object = object;
  }

  /**
   * Gets the the {@code object} property.
   * @return the property, not null
   */
  public final Property<Object> object() {
    return metaBean().object().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ConfigEntry clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ConfigEntry other = (ConfigEntry) obj;
      return JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getType(), other.getType()) &&
          JodaBeanUtils.equal(getObject(), other.getObject());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getObject());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("ConfigEntry{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType())).append(',').append(' ');
    buf.append("object").append('=').append(JodaBeanUtils.toString(getObject())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ConfigEntry}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", ConfigEntry.class, String.class);
    /**
     * The meta-property for the {@code type} property.
     */
    private final MetaProperty<String> _type = DirectMetaProperty.ofReadWrite(
        this, "type", ConfigEntry.class, String.class);
    /**
     * The meta-property for the {@code object} property.
     */
    private final MetaProperty<Object> _object = DirectMetaProperty.ofReadWrite(
        this, "object", ConfigEntry.class, Object.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "type",
        "object");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return _name;
        case 3575610:  // type
          return _type;
        case -1023368385:  // object
          return _object;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ConfigEntry> builder() {
      return new DirectBeanBuilder<ConfigEntry>(new ConfigEntry());
    }

    @Override
    public Class<? extends ConfigEntry> beanType() {
      return ConfigEntry.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code object} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Object> object() {
      return _object;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((ConfigEntry) bean).getName();
        case 3575610:  // type
          return ((ConfigEntry) bean).getType();
        case -1023368385:  // object
          return ((ConfigEntry) bean).getObject();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          ((ConfigEntry) bean).setName((String) newValue);
          return;
        case 3575610:  // type
          ((ConfigEntry) bean).setType((String) newValue);
          return;
        case -1023368385:  // object
          ((ConfigEntry) bean).setObject((Object) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ConfigEntry) bean)._name, "name");
      JodaBeanUtils.notNull(((ConfigEntry) bean)._type, "type");
      JodaBeanUtils.notNull(((ConfigEntry) bean)._object, "object");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
