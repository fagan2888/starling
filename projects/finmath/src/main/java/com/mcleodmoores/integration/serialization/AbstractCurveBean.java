/**
 * Copyright (C) 2014-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.integration.serialization;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import net.finmath.marketdata.model.curves.CurveInterface;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Bean for the base Finmath curves class {@link net.finmath.marketdata.model.curves.AbstractCurve}. The Finmath
 * class does not perform checks on the inputs (e.g. for non-null parameters). However, this bean does perform these
 * checks on construction. The reference date string is parsed using {@link SimpleDateFormat} and will throw an
 * exception if the date string cannot be parsed.
 */
@BeanDefinition
public abstract class AbstractCurveBean extends DirectBean implements Serializable {

  /** The serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The curve name.
   */
  @PropertyDefinition(validate = "notNull")
  private String _name;

  /**
   * The reference date string.
   */
  @PropertyDefinition
  private String _referenceDateString;

  /**
   * For the builder.
   */
  /* package */ AbstractCurveBean() {
  }

  /**
   * Constructs an instance.
   * @param name The name of the curve, not null
   * @param referenceDateString The reference date as a string, not null
   */
  public AbstractCurveBean(final String name, final String referenceDateString) {
    setName(name);
    setReferenceDateString(referenceDateString);
  }

  /**
   * Gets the reference date from the reference date string. Throws an {@link IllegalStateException}
   * if the date cannot be parsed.
   * @return The reference date.
   */
  public Calendar getReferenceDate() {
    if (getReferenceDateString() == null) {
      return null;
    }
    try {
      final Date referenceDate = new SimpleDateFormat("yyyy-MM-dd").parse(getReferenceDateString());
      final Calendar calendar = Calendar.getInstance();
      calendar.setTime(referenceDate);
      return calendar;
    } catch (final ParseException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Builds the curve.
   * @return The curve
   */
  public abstract CurveInterface buildCurve();

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AbstractCurveBean}.
   * @return the meta-bean, not null
   */
  public static AbstractCurveBean.Meta meta() {
    return AbstractCurveBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AbstractCurveBean.Meta.INSTANCE);
  }

  @Override
  public AbstractCurveBean.Meta metaBean() {
    return AbstractCurveBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the curve name.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the curve name.
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
   * Gets the reference date string.
   * @return the value of the property
   */
  public String getReferenceDateString() {
    return _referenceDateString;
  }

  /**
   * Sets the reference date string.
   * @param referenceDateString  the new value of the property
   */
  public void setReferenceDateString(String referenceDateString) {
    this._referenceDateString = referenceDateString;
  }

  /**
   * Gets the the {@code referenceDateString} property.
   * @return the property, not null
   */
  public final Property<String> referenceDateString() {
    return metaBean().referenceDateString().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public AbstractCurveBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AbstractCurveBean other = (AbstractCurveBean) obj;
      return JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getReferenceDateString(), other.getReferenceDateString());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getReferenceDateString());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("AbstractCurveBean{");
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
    buf.append("referenceDateString").append('=').append(JodaBeanUtils.toString(getReferenceDateString())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AbstractCurveBean}.
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
        this, "name", AbstractCurveBean.class, String.class);
    /**
     * The meta-property for the {@code referenceDateString} property.
     */
    private final MetaProperty<String> _referenceDateString = DirectMetaProperty.ofReadWrite(
        this, "referenceDateString", AbstractCurveBean.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "referenceDateString");

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
        case 815856394:  // referenceDateString
          return _referenceDateString;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AbstractCurveBean> builder() {
      throw new UnsupportedOperationException("AbstractCurveBean is an abstract class");
    }

    @Override
    public Class<? extends AbstractCurveBean> beanType() {
      return AbstractCurveBean.class;
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
     * The meta-property for the {@code referenceDateString} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> referenceDateString() {
      return _referenceDateString;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((AbstractCurveBean) bean).getName();
        case 815856394:  // referenceDateString
          return ((AbstractCurveBean) bean).getReferenceDateString();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          ((AbstractCurveBean) bean).setName((String) newValue);
          return;
        case 815856394:  // referenceDateString
          ((AbstractCurveBean) bean).setReferenceDateString((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((AbstractCurveBean) bean)._name, "name");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}