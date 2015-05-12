/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.legalentity;

import java.io.Serializable;
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
import org.joda.beans.impl.flexi.FlexiBean;

import com.opengamma.util.ArgumentChecker;

/**
 * Describes a sector. There is the ability to add arbitrary classifications
 * to the description.
 */
@BeanDefinition(builderScope = "private")
public final class Sector implements Bean, Serializable {

  /** The serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The name of the sector.
   */
  @PropertyDefinition(validate = "notNull")
  private String _name;

  /**
   * Any additional information that can identify a sector (e.g. GICS codes).
   */
  @PropertyDefinition(validate = "notNull")
  private FlexiBean _classifications;

  /**
   * Creates a sector with empty classifications.
   * @param name The sector name
   * @return The sector
   */
  public static Sector of(final String name) {
    return new Sector(name, new FlexiBean());
  }

  /**
   * Creates a sector.
   * @param name The sector name
   * @param classifications The sector classifications, not null
   * @return The sector
   */
  public static Sector of(final String name, final FlexiBean classifications) {
    return new Sector(name, classifications);
  }

  /**
   * For the builder.
   */
  /* package */ Sector() {
  }

  /**
   * @param name The name of the sector
   * @param classifications The classifications of the sector
   */
  private Sector(final String name, final FlexiBean classifications) {
    ArgumentChecker.notNull(name, "name");
    ArgumentChecker.notNull(classifications, "classifications");
    _name = name;
    _classifications = classifications;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code Sector}.
   * @return the meta-bean, not null
   */
  public static Sector.Meta meta() {
    return Sector.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(Sector.Meta.INSTANCE);
  }

  @Override
  public Sector.Meta metaBean() {
    return Sector.Meta.INSTANCE;
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
   * Gets the name of the sector.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name of the sector.
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
  public Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets any additional information that can identify a sector (e.g. GICS codes).
   * @return the value of the property, not null
   */
  public FlexiBean getClassifications() {
    return _classifications;
  }

  /**
   * Sets any additional information that can identify a sector (e.g. GICS codes).
   * @param classifications  the new value of the property, not null
   */
  public void setClassifications(FlexiBean classifications) {
    JodaBeanUtils.notNull(classifications, "classifications");
    this._classifications = classifications;
  }

  /**
   * Gets the the {@code classifications} property.
   * @return the property, not null
   */
  public Property<FlexiBean> classifications() {
    return metaBean().classifications().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public Sector clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      Sector other = (Sector) obj;
      return JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getClassifications(), other.getClassifications());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getClassifications());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("Sector{");
    buf.append("name").append('=').append(getName()).append(',').append(' ');
    buf.append("classifications").append('=').append(JodaBeanUtils.toString(getClassifications()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code Sector}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", Sector.class, String.class);
    /**
     * The meta-property for the {@code classifications} property.
     */
    private final MetaProperty<FlexiBean> _classifications = DirectMetaProperty.ofReadWrite(
        this, "classifications", Sector.class, FlexiBean.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "classifications");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return _name;
        case -1032042163:  // classifications
          return _classifications;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends Sector> builder() {
      return new DirectBeanBuilder<Sector>(new Sector());
    }

    @Override
    public Class<? extends Sector> beanType() {
      return Sector.class;
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
    public MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code classifications} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FlexiBean> classifications() {
      return _classifications;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((Sector) bean).getName();
        case -1032042163:  // classifications
          return ((Sector) bean).getClassifications();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          ((Sector) bean).setName((String) newValue);
          return;
        case -1032042163:  // classifications
          ((Sector) bean).setClassifications((FlexiBean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((Sector) bean)._name, "name");
      JodaBeanUtils.notNull(((Sector) bean)._classifications, "classifications");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
