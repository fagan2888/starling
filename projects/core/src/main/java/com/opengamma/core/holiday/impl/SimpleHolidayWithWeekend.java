/**
 * Copyright (C) 2014 - Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.opengamma.core.holiday.impl;

import java.util.Map;

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
import org.threeten.bp.LocalDate;

import com.opengamma.core.holiday.WeekendType;
import com.opengamma.core.holiday.WeekendTypeProvider;

/**
 * A simple implementation of {@link com.opengamma.core.holiday.Holiday} that contains explicit
 * information about weekends. This class should be used in preference to {@link SimpleHoliday},
 * as it is incorrect for some countries to assume that weekend days are Saturday and Sunday.
 * <p>
 * This is the simplest possible implementation of the {@link com.opengamma.core.holiday.Holiday} interface.
 * <p>
 * This class is mutable and not thread-safe.
 * It is intended to be used in the engine via the read-only {@code com.opengamma.core.holiday.Holiday} interface.
 */
@BeanDefinition
public class SimpleHolidayWithWeekend extends SimpleHoliday implements WeekendTypeProvider {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The weekend type.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private WeekendType _weekendType;

  /**
   * Creates an empty instance.
   */
  public SimpleHolidayWithWeekend() {
    super();
    setWeekendType(WeekendType.SATURDAY_SUNDAY);
  }

  /**
   * Creates an instance populated with a set of dates and the weekend type.
   *
   * @param dates  the dates to add, not null
   * @param weekendType  the weekend type, not null
   */
  public SimpleHolidayWithWeekend(final Iterable<LocalDate> dates, final WeekendType weekendType) {
    super(dates);
    setWeekendType(weekendType);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SimpleHolidayWithWeekend}.
   * @return the meta-bean, not null
   */
  public static SimpleHolidayWithWeekend.Meta meta() {
    return SimpleHolidayWithWeekend.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SimpleHolidayWithWeekend.Meta.INSTANCE);
  }

  @Override
  public SimpleHolidayWithWeekend.Meta metaBean() {
    return SimpleHolidayWithWeekend.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the weekend type.
   * @return the value of the property, not null
   */
  @Override
  public WeekendType getWeekendType() {
    return _weekendType;
  }

  /**
   * Sets the weekend type.
   * @param weekendType  the new value of the property, not null
   */
  public void setWeekendType(WeekendType weekendType) {
    JodaBeanUtils.notNull(weekendType, "weekendType");
    this._weekendType = weekendType;
  }

  /**
   * Gets the the {@code weekendType} property.
   * @return the property, not null
   */
  public final Property<WeekendType> weekendType() {
    return metaBean().weekendType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public SimpleHolidayWithWeekend clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SimpleHolidayWithWeekend other = (SimpleHolidayWithWeekend) obj;
      return JodaBeanUtils.equal(getWeekendType(), other.getWeekendType()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getWeekendType());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("SimpleHolidayWithWeekend{");
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
    buf.append("weekendType").append('=').append(JodaBeanUtils.toString(getWeekendType())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SimpleHolidayWithWeekend}.
   */
  public static class Meta extends SimpleHoliday.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code weekendType} property.
     */
    private final MetaProperty<WeekendType> _weekendType = DirectMetaProperty.ofReadWrite(
        this, "weekendType", SimpleHolidayWithWeekend.class, WeekendType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "weekendType");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 563735617:  // weekendType
          return _weekendType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SimpleHolidayWithWeekend> builder() {
      return new DirectBeanBuilder<SimpleHolidayWithWeekend>(new SimpleHolidayWithWeekend());
    }

    @Override
    public Class<? extends SimpleHolidayWithWeekend> beanType() {
      return SimpleHolidayWithWeekend.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code weekendType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<WeekendType> weekendType() {
      return _weekendType;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 563735617:  // weekendType
          return ((SimpleHolidayWithWeekend) bean).getWeekendType();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 563735617:  // weekendType
          ((SimpleHolidayWithWeekend) bean).setWeekendType((WeekendType) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((SimpleHolidayWithWeekend) bean)._weekendType, "weekendType");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
