/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.fra;

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
import org.threeten.bp.ZonedDateTime;

import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.id.ExternalId;
import com.opengamma.master.security.SecurityDescription;
import com.opengamma.util.money.Currency;

/**
 * A security defining a vanilla FRA.
 */
@BeanDefinition
@SecurityDescription(type = FRASecurity.SECURITY_TYPE, description = "Fra")
public class FRASecurity extends FinancialSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The security type.
   */
  public static final String SECURITY_TYPE = "FRA";

  /**
   * The currency.
   */
  @PropertyDefinition(validate = "notNull")
  private Currency _currency;
  /**
   * The region.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _regionId;
  /**
   * The start date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _startDate;
  /**
   * The end date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _endDate;
  /**
   * The settlement date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _fixingDate;
  /**
   * The rate.
   */
  @PropertyDefinition
  private double _rate;
  /**
   * The amount.
   */
  @PropertyDefinition
  private double _amount;
  /**
   * The underlying identifier.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _underlyingId;

  /**
   * For the builder.
   */
  FRASecurity() {
    super(SECURITY_TYPE);
  }

  /**
   * Creates an instance.
   *
   * @param currency
   *          the currency, not null.
   * @param region
   *          the region identifier, not null
   * @param startDate
   *          the start date, not null
   * @param endDate
   *          the end date, not null
   * @param rate
   *          the rate
   * @param amount
   *          the amount
   * @param underlyingIdentifier
   *          the underlying identifier, not null
   * @param fixingDate
   *          the fixing date, not null
   */
  public FRASecurity(final Currency currency, final ExternalId region, final ZonedDateTime startDate, final ZonedDateTime endDate, final double rate,
      final double amount, final ExternalId underlyingIdentifier, final ZonedDateTime fixingDate) {
    super(SECURITY_TYPE);
    setCurrency(currency);
    setRegionId(region);
    setStartDate(startDate);
    setEndDate(endDate);
    setRate(rate);
    setAmount(amount);
    setUnderlyingId(underlyingIdentifier);
    setFixingDate(fixingDate);
  }

  // -------------------------------------------------------------------------
  @Override
  public final <T> T accept(final FinancialSecurityVisitor<T> visitor) {
    return visitor.visitFRASecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FRASecurity}.
   * @return the meta-bean, not null
   */
  public static FRASecurity.Meta meta() {
    return FRASecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FRASecurity.Meta.INSTANCE);
  }

  @Override
  public FRASecurity.Meta metaBean() {
    return FRASecurity.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property, not null
   */
  public void setCurrency(Currency currency) {
    JodaBeanUtils.notNull(currency, "currency");
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<Currency> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the region.
   * @return the value of the property, not null
   */
  public ExternalId getRegionId() {
    return _regionId;
  }

  /**
   * Sets the region.
   * @param regionId  the new value of the property, not null
   */
  public void setRegionId(ExternalId regionId) {
    JodaBeanUtils.notNull(regionId, "regionId");
    this._regionId = regionId;
  }

  /**
   * Gets the the {@code regionId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> regionId() {
    return metaBean().regionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getStartDate() {
    return _startDate;
  }

  /**
   * Sets the start date.
   * @param startDate  the new value of the property, not null
   */
  public void setStartDate(ZonedDateTime startDate) {
    JodaBeanUtils.notNull(startDate, "startDate");
    this._startDate = startDate;
  }

  /**
   * Gets the the {@code startDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> startDate() {
    return metaBean().startDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getEndDate() {
    return _endDate;
  }

  /**
   * Sets the end date.
   * @param endDate  the new value of the property, not null
   */
  public void setEndDate(ZonedDateTime endDate) {
    JodaBeanUtils.notNull(endDate, "endDate");
    this._endDate = endDate;
  }

  /**
   * Gets the the {@code endDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> endDate() {
    return metaBean().endDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getFixingDate() {
    return _fixingDate;
  }

  /**
   * Sets the settlement date.
   * @param fixingDate  the new value of the property, not null
   */
  public void setFixingDate(ZonedDateTime fixingDate) {
    JodaBeanUtils.notNull(fixingDate, "fixingDate");
    this._fixingDate = fixingDate;
  }

  /**
   * Gets the the {@code fixingDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> fixingDate() {
    return metaBean().fixingDate().createProperty(this);
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
   * Gets the underlying identifier.
   * @return the value of the property, not null
   */
  public ExternalId getUnderlyingId() {
    return _underlyingId;
  }

  /**
   * Sets the underlying identifier.
   * @param underlyingId  the new value of the property, not null
   */
  public void setUnderlyingId(ExternalId underlyingId) {
    JodaBeanUtils.notNull(underlyingId, "underlyingId");
    this._underlyingId = underlyingId;
  }

  /**
   * Gets the the {@code underlyingId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> underlyingId() {
    return metaBean().underlyingId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FRASecurity clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FRASecurity other = (FRASecurity) obj;
      return JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getRegionId(), other.getRegionId()) &&
          JodaBeanUtils.equal(getStartDate(), other.getStartDate()) &&
          JodaBeanUtils.equal(getEndDate(), other.getEndDate()) &&
          JodaBeanUtils.equal(getFixingDate(), other.getFixingDate()) &&
          JodaBeanUtils.equal(getRate(), other.getRate()) &&
          JodaBeanUtils.equal(getAmount(), other.getAmount()) &&
          JodaBeanUtils.equal(getUnderlyingId(), other.getUnderlyingId()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRegionId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getStartDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getEndDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFixingDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getAmount());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUnderlyingId());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("FRASecurity{");
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
    buf.append("regionId").append('=').append(JodaBeanUtils.toString(getRegionId())).append(',').append(' ');
    buf.append("startDate").append('=').append(JodaBeanUtils.toString(getStartDate())).append(',').append(' ');
    buf.append("endDate").append('=').append(JodaBeanUtils.toString(getEndDate())).append(',').append(' ');
    buf.append("fixingDate").append('=').append(JodaBeanUtils.toString(getFixingDate())).append(',').append(' ');
    buf.append("rate").append('=').append(JodaBeanUtils.toString(getRate())).append(',').append(' ');
    buf.append("amount").append('=').append(JodaBeanUtils.toString(getAmount())).append(',').append(' ');
    buf.append("underlyingId").append('=').append(JodaBeanUtils.toString(getUnderlyingId())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FRASecurity}.
   */
  public static class Meta extends FinancialSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", FRASecurity.class, Currency.class);
    /**
     * The meta-property for the {@code regionId} property.
     */
    private final MetaProperty<ExternalId> _regionId = DirectMetaProperty.ofReadWrite(
        this, "regionId", FRASecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code startDate} property.
     */
    private final MetaProperty<ZonedDateTime> _startDate = DirectMetaProperty.ofReadWrite(
        this, "startDate", FRASecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code endDate} property.
     */
    private final MetaProperty<ZonedDateTime> _endDate = DirectMetaProperty.ofReadWrite(
        this, "endDate", FRASecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code fixingDate} property.
     */
    private final MetaProperty<ZonedDateTime> _fixingDate = DirectMetaProperty.ofReadWrite(
        this, "fixingDate", FRASecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code rate} property.
     */
    private final MetaProperty<Double> _rate = DirectMetaProperty.ofReadWrite(
        this, "rate", FRASecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code amount} property.
     */
    private final MetaProperty<Double> _amount = DirectMetaProperty.ofReadWrite(
        this, "amount", FRASecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code underlyingId} property.
     */
    private final MetaProperty<ExternalId> _underlyingId = DirectMetaProperty.ofReadWrite(
        this, "underlyingId", FRASecurity.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "currency",
        "regionId",
        "startDate",
        "endDate",
        "fixingDate",
        "rate",
        "amount",
        "underlyingId");

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
        case -690339025:  // regionId
          return _regionId;
        case -2129778896:  // startDate
          return _startDate;
        case -1607727319:  // endDate
          return _endDate;
        case 1255202043:  // fixingDate
          return _fixingDate;
        case 3493088:  // rate
          return _rate;
        case -1413853096:  // amount
          return _amount;
        case -771625640:  // underlyingId
          return _underlyingId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FRASecurity> builder() {
      return new DirectBeanBuilder<FRASecurity>(new FRASecurity());
    }

    @Override
    public Class<? extends FRASecurity> beanType() {
      return FRASecurity.class;
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
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code regionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> regionId() {
      return _regionId;
    }

    /**
     * The meta-property for the {@code startDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> startDate() {
      return _startDate;
    }

    /**
     * The meta-property for the {@code endDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> endDate() {
      return _endDate;
    }

    /**
     * The meta-property for the {@code fixingDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> fixingDate() {
      return _fixingDate;
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
     * The meta-property for the {@code underlyingId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> underlyingId() {
      return _underlyingId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return ((FRASecurity) bean).getCurrency();
        case -690339025:  // regionId
          return ((FRASecurity) bean).getRegionId();
        case -2129778896:  // startDate
          return ((FRASecurity) bean).getStartDate();
        case -1607727319:  // endDate
          return ((FRASecurity) bean).getEndDate();
        case 1255202043:  // fixingDate
          return ((FRASecurity) bean).getFixingDate();
        case 3493088:  // rate
          return ((FRASecurity) bean).getRate();
        case -1413853096:  // amount
          return ((FRASecurity) bean).getAmount();
        case -771625640:  // underlyingId
          return ((FRASecurity) bean).getUnderlyingId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          ((FRASecurity) bean).setCurrency((Currency) newValue);
          return;
        case -690339025:  // regionId
          ((FRASecurity) bean).setRegionId((ExternalId) newValue);
          return;
        case -2129778896:  // startDate
          ((FRASecurity) bean).setStartDate((ZonedDateTime) newValue);
          return;
        case -1607727319:  // endDate
          ((FRASecurity) bean).setEndDate((ZonedDateTime) newValue);
          return;
        case 1255202043:  // fixingDate
          ((FRASecurity) bean).setFixingDate((ZonedDateTime) newValue);
          return;
        case 3493088:  // rate
          ((FRASecurity) bean).setRate((Double) newValue);
          return;
        case -1413853096:  // amount
          ((FRASecurity) bean).setAmount((Double) newValue);
          return;
        case -771625640:  // underlyingId
          ((FRASecurity) bean).setUnderlyingId((ExternalId) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((FRASecurity) bean)._currency, "currency");
      JodaBeanUtils.notNull(((FRASecurity) bean)._regionId, "regionId");
      JodaBeanUtils.notNull(((FRASecurity) bean)._startDate, "startDate");
      JodaBeanUtils.notNull(((FRASecurity) bean)._endDate, "endDate");
      JodaBeanUtils.notNull(((FRASecurity) bean)._fixingDate, "fixingDate");
      JodaBeanUtils.notNull(((FRASecurity) bean)._underlyingId, "underlyingId");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
