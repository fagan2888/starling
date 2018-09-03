/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb.security.hibernate.option;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
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

import com.opengamma.financial.security.option.BarrierDirection;
import com.opengamma.financial.security.option.BarrierType;
import com.opengamma.financial.security.option.FXBarrierOptionSecurity;
import com.opengamma.financial.security.option.MonitoringType;
import com.opengamma.financial.security.option.SamplingFrequency;
import com.opengamma.masterdb.security.hibernate.CurrencyBean;
import com.opengamma.masterdb.security.hibernate.ExpiryBean;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import com.opengamma.masterdb.security.hibernate.ZonedDateTimeBean;

/**
 * A Hibernate bean representation of {@link FXBarrierOptionSecurity}.
 */
@BeanDefinition
public class FXBarrierOptionSecurityBean extends SecurityBean {
  @PropertyDefinition
  private double _putAmount;
  @PropertyDefinition
  private double _callAmount;
  @PropertyDefinition
  private ExpiryBean _expiry;
  @PropertyDefinition
  private CurrencyBean _putCurrency;
  @PropertyDefinition
  private CurrencyBean _callCurrency;
  @PropertyDefinition
  private ZonedDateTimeBean _settlementDate;
  @PropertyDefinition
  private BarrierType _barrierType;
  @PropertyDefinition
  private BarrierDirection _barrierDirection;
  @PropertyDefinition
  private MonitoringType _monitoringType;
  @PropertyDefinition
  private SamplingFrequency _samplingFrequency;
  @PropertyDefinition
  private double _barrierLevel;
  @PropertyDefinition
  private boolean _longShort;

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof FXBarrierOptionSecurityBean)) {
      return false;
    }
    final FXBarrierOptionSecurityBean option = (FXBarrierOptionSecurityBean) other;

    return new EqualsBuilder()
      .append(getId(), option.getId())
      .append(getExpiry(), option.getExpiry())
      .append(getPutCurrency(), option.getPutCurrency())
      .append(getCallCurrency(), option.getCallCurrency())
      .append(getCallAmount(), option.getCallAmount())
      .append(getPutAmount(), option.getPutAmount())
      .append(getSettlementDate(), option.getSettlementDate())
      .append(getBarrierType(), option.getBarrierType())
      .append(getBarrierDirection(), option.getBarrierDirection())
      .append(getMonitoringType(), option.getMonitoringType())
      .append(getSamplingFrequency(), option.getSamplingFrequency())
      .append(getBarrierLevel(), option.getBarrierLevel())
      .append(isLongShort(), option.isLongShort())
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(getExpiry())
      .append(getPutCurrency())
      .append(getCallCurrency())
      .append(getSettlementDate())
      .append(getPutAmount())
      .append(getCallAmount())
      .append(getBarrierType())
      .append(getBarrierDirection())
      .append(getMonitoringType())
      .append(getSamplingFrequency())
      .append(getBarrierLevel())
      .append(isLongShort())
      .toHashCode();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FXBarrierOptionSecurityBean}.
   * @return the meta-bean, not null
   */
  public static FXBarrierOptionSecurityBean.Meta meta() {
    return FXBarrierOptionSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FXBarrierOptionSecurityBean.Meta.INSTANCE);
  }

  @Override
  public FXBarrierOptionSecurityBean.Meta metaBean() {
    return FXBarrierOptionSecurityBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the putAmount.
   * @return the value of the property
   */
  public double getPutAmount() {
    return _putAmount;
  }

  /**
   * Sets the putAmount.
   * @param putAmount  the new value of the property
   */
  public void setPutAmount(double putAmount) {
    this._putAmount = putAmount;
  }

  /**
   * Gets the the {@code putAmount} property.
   * @return the property, not null
   */
  public final Property<Double> putAmount() {
    return metaBean().putAmount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the callAmount.
   * @return the value of the property
   */
  public double getCallAmount() {
    return _callAmount;
  }

  /**
   * Sets the callAmount.
   * @param callAmount  the new value of the property
   */
  public void setCallAmount(double callAmount) {
    this._callAmount = callAmount;
  }

  /**
   * Gets the the {@code callAmount} property.
   * @return the property, not null
   */
  public final Property<Double> callAmount() {
    return metaBean().callAmount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry.
   * @return the value of the property
   */
  public ExpiryBean getExpiry() {
    return _expiry;
  }

  /**
   * Sets the expiry.
   * @param expiry  the new value of the property
   */
  public void setExpiry(ExpiryBean expiry) {
    this._expiry = expiry;
  }

  /**
   * Gets the the {@code expiry} property.
   * @return the property, not null
   */
  public final Property<ExpiryBean> expiry() {
    return metaBean().expiry().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the putCurrency.
   * @return the value of the property
   */
  public CurrencyBean getPutCurrency() {
    return _putCurrency;
  }

  /**
   * Sets the putCurrency.
   * @param putCurrency  the new value of the property
   */
  public void setPutCurrency(CurrencyBean putCurrency) {
    this._putCurrency = putCurrency;
  }

  /**
   * Gets the the {@code putCurrency} property.
   * @return the property, not null
   */
  public final Property<CurrencyBean> putCurrency() {
    return metaBean().putCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the callCurrency.
   * @return the value of the property
   */
  public CurrencyBean getCallCurrency() {
    return _callCurrency;
  }

  /**
   * Sets the callCurrency.
   * @param callCurrency  the new value of the property
   */
  public void setCallCurrency(CurrencyBean callCurrency) {
    this._callCurrency = callCurrency;
  }

  /**
   * Gets the the {@code callCurrency} property.
   * @return the property, not null
   */
  public final Property<CurrencyBean> callCurrency() {
    return metaBean().callCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlementDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getSettlementDate() {
    return _settlementDate;
  }

  /**
   * Sets the settlementDate.
   * @param settlementDate  the new value of the property
   */
  public void setSettlementDate(ZonedDateTimeBean settlementDate) {
    this._settlementDate = settlementDate;
  }

  /**
   * Gets the the {@code settlementDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> settlementDate() {
    return metaBean().settlementDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the barrierType.
   * @return the value of the property
   */
  public BarrierType getBarrierType() {
    return _barrierType;
  }

  /**
   * Sets the barrierType.
   * @param barrierType  the new value of the property
   */
  public void setBarrierType(BarrierType barrierType) {
    this._barrierType = barrierType;
  }

  /**
   * Gets the the {@code barrierType} property.
   * @return the property, not null
   */
  public final Property<BarrierType> barrierType() {
    return metaBean().barrierType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the barrierDirection.
   * @return the value of the property
   */
  public BarrierDirection getBarrierDirection() {
    return _barrierDirection;
  }

  /**
   * Sets the barrierDirection.
   * @param barrierDirection  the new value of the property
   */
  public void setBarrierDirection(BarrierDirection barrierDirection) {
    this._barrierDirection = barrierDirection;
  }

  /**
   * Gets the the {@code barrierDirection} property.
   * @return the property, not null
   */
  public final Property<BarrierDirection> barrierDirection() {
    return metaBean().barrierDirection().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the monitoringType.
   * @return the value of the property
   */
  public MonitoringType getMonitoringType() {
    return _monitoringType;
  }

  /**
   * Sets the monitoringType.
   * @param monitoringType  the new value of the property
   */
  public void setMonitoringType(MonitoringType monitoringType) {
    this._monitoringType = monitoringType;
  }

  /**
   * Gets the the {@code monitoringType} property.
   * @return the property, not null
   */
  public final Property<MonitoringType> monitoringType() {
    return metaBean().monitoringType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the samplingFrequency.
   * @return the value of the property
   */
  public SamplingFrequency getSamplingFrequency() {
    return _samplingFrequency;
  }

  /**
   * Sets the samplingFrequency.
   * @param samplingFrequency  the new value of the property
   */
  public void setSamplingFrequency(SamplingFrequency samplingFrequency) {
    this._samplingFrequency = samplingFrequency;
  }

  /**
   * Gets the the {@code samplingFrequency} property.
   * @return the property, not null
   */
  public final Property<SamplingFrequency> samplingFrequency() {
    return metaBean().samplingFrequency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the barrierLevel.
   * @return the value of the property
   */
  public double getBarrierLevel() {
    return _barrierLevel;
  }

  /**
   * Sets the barrierLevel.
   * @param barrierLevel  the new value of the property
   */
  public void setBarrierLevel(double barrierLevel) {
    this._barrierLevel = barrierLevel;
  }

  /**
   * Gets the the {@code barrierLevel} property.
   * @return the property, not null
   */
  public final Property<Double> barrierLevel() {
    return metaBean().barrierLevel().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the longShort.
   * @return the value of the property
   */
  public boolean isLongShort() {
    return _longShort;
  }

  /**
   * Sets the longShort.
   * @param longShort  the new value of the property
   */
  public void setLongShort(boolean longShort) {
    this._longShort = longShort;
  }

  /**
   * Gets the the {@code longShort} property.
   * @return the property, not null
   */
  public final Property<Boolean> longShort() {
    return metaBean().longShort().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FXBarrierOptionSecurityBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FXBarrierOptionSecurityBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code putAmount} property.
     */
    private final MetaProperty<Double> _putAmount = DirectMetaProperty.ofReadWrite(
        this, "putAmount", FXBarrierOptionSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code callAmount} property.
     */
    private final MetaProperty<Double> _callAmount = DirectMetaProperty.ofReadWrite(
        this, "callAmount", FXBarrierOptionSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code expiry} property.
     */
    private final MetaProperty<ExpiryBean> _expiry = DirectMetaProperty.ofReadWrite(
        this, "expiry", FXBarrierOptionSecurityBean.class, ExpiryBean.class);
    /**
     * The meta-property for the {@code putCurrency} property.
     */
    private final MetaProperty<CurrencyBean> _putCurrency = DirectMetaProperty.ofReadWrite(
        this, "putCurrency", FXBarrierOptionSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code callCurrency} property.
     */
    private final MetaProperty<CurrencyBean> _callCurrency = DirectMetaProperty.ofReadWrite(
        this, "callCurrency", FXBarrierOptionSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code settlementDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _settlementDate = DirectMetaProperty.ofReadWrite(
        this, "settlementDate", FXBarrierOptionSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code barrierType} property.
     */
    private final MetaProperty<BarrierType> _barrierType = DirectMetaProperty.ofReadWrite(
        this, "barrierType", FXBarrierOptionSecurityBean.class, BarrierType.class);
    /**
     * The meta-property for the {@code barrierDirection} property.
     */
    private final MetaProperty<BarrierDirection> _barrierDirection = DirectMetaProperty.ofReadWrite(
        this, "barrierDirection", FXBarrierOptionSecurityBean.class, BarrierDirection.class);
    /**
     * The meta-property for the {@code monitoringType} property.
     */
    private final MetaProperty<MonitoringType> _monitoringType = DirectMetaProperty.ofReadWrite(
        this, "monitoringType", FXBarrierOptionSecurityBean.class, MonitoringType.class);
    /**
     * The meta-property for the {@code samplingFrequency} property.
     */
    private final MetaProperty<SamplingFrequency> _samplingFrequency = DirectMetaProperty.ofReadWrite(
        this, "samplingFrequency", FXBarrierOptionSecurityBean.class, SamplingFrequency.class);
    /**
     * The meta-property for the {@code barrierLevel} property.
     */
    private final MetaProperty<Double> _barrierLevel = DirectMetaProperty.ofReadWrite(
        this, "barrierLevel", FXBarrierOptionSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code longShort} property.
     */
    private final MetaProperty<Boolean> _longShort = DirectMetaProperty.ofReadWrite(
        this, "longShort", FXBarrierOptionSecurityBean.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "putAmount",
        "callAmount",
        "expiry",
        "putCurrency",
        "callCurrency",
        "settlementDate",
        "barrierType",
        "barrierDirection",
        "monitoringType",
        "samplingFrequency",
        "barrierLevel",
        "longShort");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -984864697:  // putAmount
          return _putAmount;
        case 1066661974:  // callAmount
          return _callAmount;
        case -1289159373:  // expiry
          return _expiry;
        case 516393024:  // putCurrency
          return _putCurrency;
        case 643534991:  // callCurrency
          return _callCurrency;
        case -295948169:  // settlementDate
          return _settlementDate;
        case 1029043089:  // barrierType
          return _barrierType;
        case 502579592:  // barrierDirection
          return _barrierDirection;
        case -1483652190:  // monitoringType
          return _monitoringType;
        case 1178782005:  // samplingFrequency
          return _samplingFrequency;
        case 1827586573:  // barrierLevel
          return _barrierLevel;
        case 116685664:  // longShort
          return _longShort;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FXBarrierOptionSecurityBean> builder() {
      return new DirectBeanBuilder<FXBarrierOptionSecurityBean>(new FXBarrierOptionSecurityBean());
    }

    @Override
    public Class<? extends FXBarrierOptionSecurityBean> beanType() {
      return FXBarrierOptionSecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code putAmount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> putAmount() {
      return _putAmount;
    }

    /**
     * The meta-property for the {@code callAmount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> callAmount() {
      return _callAmount;
    }

    /**
     * The meta-property for the {@code expiry} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExpiryBean> expiry() {
      return _expiry;
    }

    /**
     * The meta-property for the {@code putCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> putCurrency() {
      return _putCurrency;
    }

    /**
     * The meta-property for the {@code callCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> callCurrency() {
      return _callCurrency;
    }

    /**
     * The meta-property for the {@code settlementDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> settlementDate() {
      return _settlementDate;
    }

    /**
     * The meta-property for the {@code barrierType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BarrierType> barrierType() {
      return _barrierType;
    }

    /**
     * The meta-property for the {@code barrierDirection} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BarrierDirection> barrierDirection() {
      return _barrierDirection;
    }

    /**
     * The meta-property for the {@code monitoringType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MonitoringType> monitoringType() {
      return _monitoringType;
    }

    /**
     * The meta-property for the {@code samplingFrequency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SamplingFrequency> samplingFrequency() {
      return _samplingFrequency;
    }

    /**
     * The meta-property for the {@code barrierLevel} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> barrierLevel() {
      return _barrierLevel;
    }

    /**
     * The meta-property for the {@code longShort} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> longShort() {
      return _longShort;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -984864697:  // putAmount
          return ((FXBarrierOptionSecurityBean) bean).getPutAmount();
        case 1066661974:  // callAmount
          return ((FXBarrierOptionSecurityBean) bean).getCallAmount();
        case -1289159373:  // expiry
          return ((FXBarrierOptionSecurityBean) bean).getExpiry();
        case 516393024:  // putCurrency
          return ((FXBarrierOptionSecurityBean) bean).getPutCurrency();
        case 643534991:  // callCurrency
          return ((FXBarrierOptionSecurityBean) bean).getCallCurrency();
        case -295948169:  // settlementDate
          return ((FXBarrierOptionSecurityBean) bean).getSettlementDate();
        case 1029043089:  // barrierType
          return ((FXBarrierOptionSecurityBean) bean).getBarrierType();
        case 502579592:  // barrierDirection
          return ((FXBarrierOptionSecurityBean) bean).getBarrierDirection();
        case -1483652190:  // monitoringType
          return ((FXBarrierOptionSecurityBean) bean).getMonitoringType();
        case 1178782005:  // samplingFrequency
          return ((FXBarrierOptionSecurityBean) bean).getSamplingFrequency();
        case 1827586573:  // barrierLevel
          return ((FXBarrierOptionSecurityBean) bean).getBarrierLevel();
        case 116685664:  // longShort
          return ((FXBarrierOptionSecurityBean) bean).isLongShort();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -984864697:  // putAmount
          ((FXBarrierOptionSecurityBean) bean).setPutAmount((Double) newValue);
          return;
        case 1066661974:  // callAmount
          ((FXBarrierOptionSecurityBean) bean).setCallAmount((Double) newValue);
          return;
        case -1289159373:  // expiry
          ((FXBarrierOptionSecurityBean) bean).setExpiry((ExpiryBean) newValue);
          return;
        case 516393024:  // putCurrency
          ((FXBarrierOptionSecurityBean) bean).setPutCurrency((CurrencyBean) newValue);
          return;
        case 643534991:  // callCurrency
          ((FXBarrierOptionSecurityBean) bean).setCallCurrency((CurrencyBean) newValue);
          return;
        case -295948169:  // settlementDate
          ((FXBarrierOptionSecurityBean) bean).setSettlementDate((ZonedDateTimeBean) newValue);
          return;
        case 1029043089:  // barrierType
          ((FXBarrierOptionSecurityBean) bean).setBarrierType((BarrierType) newValue);
          return;
        case 502579592:  // barrierDirection
          ((FXBarrierOptionSecurityBean) bean).setBarrierDirection((BarrierDirection) newValue);
          return;
        case -1483652190:  // monitoringType
          ((FXBarrierOptionSecurityBean) bean).setMonitoringType((MonitoringType) newValue);
          return;
        case 1178782005:  // samplingFrequency
          ((FXBarrierOptionSecurityBean) bean).setSamplingFrequency((SamplingFrequency) newValue);
          return;
        case 1827586573:  // barrierLevel
          ((FXBarrierOptionSecurityBean) bean).setBarrierLevel((Double) newValue);
          return;
        case 116685664:  // longShort
          ((FXBarrierOptionSecurityBean) bean).setLongShort((Boolean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
