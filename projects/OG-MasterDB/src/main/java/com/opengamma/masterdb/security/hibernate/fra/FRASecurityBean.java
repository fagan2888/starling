/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.fra;

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

import com.opengamma.financial.security.fra.FRASecurity;
import com.opengamma.masterdb.security.hibernate.CurrencyBean;
import com.opengamma.masterdb.security.hibernate.ExternalIdBean;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import com.opengamma.masterdb.security.hibernate.ZonedDateTimeBean;

/**
 * A Hibernate bean representation of {@link FRASecurity}.
 */
@BeanDefinition
public class FRASecurityBean extends SecurityBean {
  @PropertyDefinition
  private CurrencyBean _currency;
  @PropertyDefinition
  private ExternalIdBean _region;
  @PropertyDefinition
  private ZonedDateTimeBean _startDate;
  @PropertyDefinition
  private ZonedDateTimeBean _endDate;
  @PropertyDefinition
  private double _rate;
  @PropertyDefinition
  private double _amount;
  @PropertyDefinition
  private ExternalIdBean _underlying;
  @PropertyDefinition
  private ZonedDateTimeBean _fixingDate;
  
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FRASecurityBean}.
   * @return the meta-bean, not null
   */
  public static FRASecurityBean.Meta meta() {
    return FRASecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FRASecurityBean.Meta.INSTANCE);
  }

  @Override
  public FRASecurityBean.Meta metaBean() {
    return FRASecurityBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property
   */
  public CurrencyBean getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property
   */
  public void setCurrency(CurrencyBean currency) {
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<CurrencyBean> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the region.
   * @return the value of the property
   */
  public ExternalIdBean getRegion() {
    return _region;
  }

  /**
   * Sets the region.
   * @param region  the new value of the property
   */
  public void setRegion(ExternalIdBean region) {
    this._region = region;
  }

  /**
   * Gets the the {@code region} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> region() {
    return metaBean().region().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the startDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getStartDate() {
    return _startDate;
  }

  /**
   * Sets the startDate.
   * @param startDate  the new value of the property
   */
  public void setStartDate(ZonedDateTimeBean startDate) {
    this._startDate = startDate;
  }

  /**
   * Gets the the {@code startDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> startDate() {
    return metaBean().startDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the endDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getEndDate() {
    return _endDate;
  }

  /**
   * Sets the endDate.
   * @param endDate  the new value of the property
   */
  public void setEndDate(ZonedDateTimeBean endDate) {
    this._endDate = endDate;
  }

  /**
   * Gets the the {@code endDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> endDate() {
    return metaBean().endDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the rate.
   * @return the value of the property
   */
  public double getRate() {
    return _rate;
  }

  /**
   * Sets the rate.
   * @param rate  the new value of the property
   */
  public void setRate(double rate) {
    this._rate = rate;
  }

  /**
   * Gets the the {@code rate} property.
   * @return the property, not null
   */
  public final Property<Double> rate() {
    return metaBean().rate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the amount.
   * @return the value of the property
   */
  public double getAmount() {
    return _amount;
  }

  /**
   * Sets the amount.
   * @param amount  the new value of the property
   */
  public void setAmount(double amount) {
    this._amount = amount;
  }

  /**
   * Gets the the {@code amount} property.
   * @return the property, not null
   */
  public final Property<Double> amount() {
    return metaBean().amount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying.
   * @return the value of the property
   */
  public ExternalIdBean getUnderlying() {
    return _underlying;
  }

  /**
   * Sets the underlying.
   * @param underlying  the new value of the property
   */
  public void setUnderlying(ExternalIdBean underlying) {
    this._underlying = underlying;
  }

  /**
   * Gets the the {@code underlying} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> underlying() {
    return metaBean().underlying().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the fixingDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getFixingDate() {
    return _fixingDate;
  }

  /**
   * Sets the fixingDate.
   * @param fixingDate  the new value of the property
   */
  public void setFixingDate(ZonedDateTimeBean fixingDate) {
    this._fixingDate = fixingDate;
  }

  /**
   * Gets the the {@code fixingDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> fixingDate() {
    return metaBean().fixingDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FRASecurityBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FRASecurityBean other = (FRASecurityBean) obj;
      return JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getRegion(), other.getRegion()) &&
          JodaBeanUtils.equal(getStartDate(), other.getStartDate()) &&
          JodaBeanUtils.equal(getEndDate(), other.getEndDate()) &&
          JodaBeanUtils.equal(getRate(), other.getRate()) &&
          JodaBeanUtils.equal(getAmount(), other.getAmount()) &&
          JodaBeanUtils.equal(getUnderlying(), other.getUnderlying()) &&
          JodaBeanUtils.equal(getFixingDate(), other.getFixingDate()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRegion());
    hash = hash * 31 + JodaBeanUtils.hashCode(getStartDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getEndDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getAmount());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUnderlying());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFixingDate());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("FRASecurityBean{");
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
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency())).append(',').append(' ');
    buf.append("region").append('=').append(JodaBeanUtils.toString(getRegion())).append(',').append(' ');
    buf.append("startDate").append('=').append(JodaBeanUtils.toString(getStartDate())).append(',').append(' ');
    buf.append("endDate").append('=').append(JodaBeanUtils.toString(getEndDate())).append(',').append(' ');
    buf.append("rate").append('=').append(JodaBeanUtils.toString(getRate())).append(',').append(' ');
    buf.append("amount").append('=').append(JodaBeanUtils.toString(getAmount())).append(',').append(' ');
    buf.append("underlying").append('=').append(JodaBeanUtils.toString(getUnderlying())).append(',').append(' ');
    buf.append("fixingDate").append('=').append(JodaBeanUtils.toString(getFixingDate())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FRASecurityBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<CurrencyBean> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", FRASecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code region} property.
     */
    private final MetaProperty<ExternalIdBean> _region = DirectMetaProperty.ofReadWrite(
        this, "region", FRASecurityBean.class, ExternalIdBean.class);
    /**
     * The meta-property for the {@code startDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _startDate = DirectMetaProperty.ofReadWrite(
        this, "startDate", FRASecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code endDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _endDate = DirectMetaProperty.ofReadWrite(
        this, "endDate", FRASecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code rate} property.
     */
    private final MetaProperty<Double> _rate = DirectMetaProperty.ofReadWrite(
        this, "rate", FRASecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code amount} property.
     */
    private final MetaProperty<Double> _amount = DirectMetaProperty.ofReadWrite(
        this, "amount", FRASecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<ExternalIdBean> _underlying = DirectMetaProperty.ofReadWrite(
        this, "underlying", FRASecurityBean.class, ExternalIdBean.class);
    /**
     * The meta-property for the {@code fixingDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _fixingDate = DirectMetaProperty.ofReadWrite(
        this, "fixingDate", FRASecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "currency",
        "region",
        "startDate",
        "endDate",
        "rate",
        "amount",
        "underlying",
        "fixingDate");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return _currency;
        case -934795532:  // region
          return _region;
        case -2129778896:  // startDate
          return _startDate;
        case -1607727319:  // endDate
          return _endDate;
        case 3493088:  // rate
          return _rate;
        case -1413853096:  // amount
          return _amount;
        case -1770633379:  // underlying
          return _underlying;
        case 1255202043:  // fixingDate
          return _fixingDate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FRASecurityBean> builder() {
      return new DirectBeanBuilder<FRASecurityBean>(new FRASecurityBean());
    }

    @Override
    public Class<? extends FRASecurityBean> beanType() {
      return FRASecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code region} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> region() {
      return _region;
    }

    /**
     * The meta-property for the {@code startDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> startDate() {
      return _startDate;
    }

    /**
     * The meta-property for the {@code endDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> endDate() {
      return _endDate;
    }

    /**
     * The meta-property for the {@code rate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> rate() {
      return _rate;
    }

    /**
     * The meta-property for the {@code amount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> amount() {
      return _amount;
    }

    /**
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> underlying() {
      return _underlying;
    }

    /**
     * The meta-property for the {@code fixingDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> fixingDate() {
      return _fixingDate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return ((FRASecurityBean) bean).getCurrency();
        case -934795532:  // region
          return ((FRASecurityBean) bean).getRegion();
        case -2129778896:  // startDate
          return ((FRASecurityBean) bean).getStartDate();
        case -1607727319:  // endDate
          return ((FRASecurityBean) bean).getEndDate();
        case 3493088:  // rate
          return ((FRASecurityBean) bean).getRate();
        case -1413853096:  // amount
          return ((FRASecurityBean) bean).getAmount();
        case -1770633379:  // underlying
          return ((FRASecurityBean) bean).getUnderlying();
        case 1255202043:  // fixingDate
          return ((FRASecurityBean) bean).getFixingDate();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          ((FRASecurityBean) bean).setCurrency((CurrencyBean) newValue);
          return;
        case -934795532:  // region
          ((FRASecurityBean) bean).setRegion((ExternalIdBean) newValue);
          return;
        case -2129778896:  // startDate
          ((FRASecurityBean) bean).setStartDate((ZonedDateTimeBean) newValue);
          return;
        case -1607727319:  // endDate
          ((FRASecurityBean) bean).setEndDate((ZonedDateTimeBean) newValue);
          return;
        case 3493088:  // rate
          ((FRASecurityBean) bean).setRate((Double) newValue);
          return;
        case -1413853096:  // amount
          ((FRASecurityBean) bean).setAmount((Double) newValue);
          return;
        case -1770633379:  // underlying
          ((FRASecurityBean) bean).setUnderlying((ExternalIdBean) newValue);
          return;
        case 1255202043:  // fixingDate
          ((FRASecurityBean) bean).setFixingDate((ZonedDateTimeBean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
