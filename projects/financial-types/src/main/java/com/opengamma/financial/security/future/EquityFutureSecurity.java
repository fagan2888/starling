/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.future;

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

import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;

/**
 * A security for equity futures.
 */
@BeanDefinition
public class EquityFutureSecurity extends FutureSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The settlement date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _settlementDate;
  /**
   * The underlying identifier.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _underlyingId;

  /**
   * Creates an empty instance.
   * <p>
   * The security details should be set before use.
   */
  public EquityFutureSecurity() {
    super();
  }

  /**
   * @param expiry
   *          the expiry, not null
   * @param tradingExchange
   *          the name of the trading exchange, not null
   * @param settlementExchange
   *          the settlement exchange, not null
   * @param currency
   *          the currency, not null
   * @param unitAmount
   *          the unit amount, not null
   * @param settlementDate
   *          the settlement date i.e. delivery date, not null
   * @param underlyingIdentifier
   *          the identifier of the underlying equity, not null
   * @param category
   *          the future category, not null
   */
  public EquityFutureSecurity(final Expiry expiry, final String tradingExchange, final String settlementExchange, final Currency currency,
      final double unitAmount, final ZonedDateTime settlementDate, final ExternalId underlyingIdentifier, final String category) {
    super(expiry, tradingExchange, settlementExchange, currency, unitAmount, category);
    setSettlementDate(settlementDate);
    setUnderlyingId(underlyingIdentifier);
  }

  // -------------------------------------------------------------------------
  @Override
  public <T> T accept(final FinancialSecurityVisitor<T> visitor) {
    return visitor.visitEquityFutureSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code EquityFutureSecurity}.
   * @return the meta-bean, not null
   */
  public static EquityFutureSecurity.Meta meta() {
    return EquityFutureSecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(EquityFutureSecurity.Meta.INSTANCE);
  }

  @Override
  public EquityFutureSecurity.Meta metaBean() {
    return EquityFutureSecurity.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getSettlementDate() {
    return _settlementDate;
  }

  /**
   * Sets the settlement date.
   * @param settlementDate  the new value of the property, not null
   */
  public void setSettlementDate(ZonedDateTime settlementDate) {
    JodaBeanUtils.notNull(settlementDate, "settlementDate");
    this._settlementDate = settlementDate;
  }

  /**
   * Gets the the {@code settlementDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> settlementDate() {
    return metaBean().settlementDate().createProperty(this);
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
  public EquityFutureSecurity clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      EquityFutureSecurity other = (EquityFutureSecurity) obj;
      return JodaBeanUtils.equal(getSettlementDate(), other.getSettlementDate()) &&
          JodaBeanUtils.equal(getUnderlyingId(), other.getUnderlyingId()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getSettlementDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUnderlyingId());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("EquityFutureSecurity{");
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
    buf.append("settlementDate").append('=').append(JodaBeanUtils.toString(getSettlementDate())).append(',').append(' ');
    buf.append("underlyingId").append('=').append(JodaBeanUtils.toString(getUnderlyingId())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code EquityFutureSecurity}.
   */
  public static class Meta extends FutureSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code settlementDate} property.
     */
    private final MetaProperty<ZonedDateTime> _settlementDate = DirectMetaProperty.ofReadWrite(
        this, "settlementDate", EquityFutureSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code underlyingId} property.
     */
    private final MetaProperty<ExternalId> _underlyingId = DirectMetaProperty.ofReadWrite(
        this, "underlyingId", EquityFutureSecurity.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "settlementDate",
        "underlyingId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -295948169:  // settlementDate
          return _settlementDate;
        case -771625640:  // underlyingId
          return _underlyingId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends EquityFutureSecurity> builder() {
      return new DirectBeanBuilder<EquityFutureSecurity>(new EquityFutureSecurity());
    }

    @Override
    public Class<? extends EquityFutureSecurity> beanType() {
      return EquityFutureSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code settlementDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> settlementDate() {
      return _settlementDate;
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
        case -295948169:  // settlementDate
          return ((EquityFutureSecurity) bean).getSettlementDate();
        case -771625640:  // underlyingId
          return ((EquityFutureSecurity) bean).getUnderlyingId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -295948169:  // settlementDate
          ((EquityFutureSecurity) bean).setSettlementDate((ZonedDateTime) newValue);
          return;
        case -771625640:  // underlyingId
          ((EquityFutureSecurity) bean).setUnderlyingId((ExternalId) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((EquityFutureSecurity) bean)._settlementDate, "settlementDate");
      JodaBeanUtils.notNull(((EquityFutureSecurity) bean)._underlyingId, "underlyingId");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
