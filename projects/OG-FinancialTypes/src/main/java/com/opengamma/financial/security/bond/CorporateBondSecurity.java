/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.bond;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.convention.yield.YieldConvention;
import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;

/**
 * A security for corporate bonds.
 */
@BeanDefinition
public class CorporateBondSecurity extends BondSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  CorporateBondSecurity() { //For builder
    super();
  }

  public CorporateBondSecurity(String issuerName, String issuerType, String issuerDomicile, String market, Currency currency,
      YieldConvention yieldConvention, Expiry lastTradeDate, String couponType, double couponRate, Frequency couponFrequency,
      DayCount dayCountConvention, ZonedDateTime interestAccrualDate, ZonedDateTime settlementDate, ZonedDateTime firstCouponDate,
      Double issuancePrice, double totalAmountIssued, double minimumAmount, double minimumIncrement, double parAmount, double redemptionValue) {
    super(issuerName, issuerType, issuerDomicile, market, currency, yieldConvention, lastTradeDate, couponType, couponRate, couponFrequency,
        dayCountConvention, interestAccrualDate, settlementDate, firstCouponDate, issuancePrice, totalAmountIssued, minimumAmount, minimumIncrement, parAmount, redemptionValue);
  }

  @Override
  public <T> T accept(FinancialSecurityVisitor<T> visitor) {
    return visitor.visitCorporateBondSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CorporateBondSecurity}.
   * @return the meta-bean, not null
   */
  public static CorporateBondSecurity.Meta meta() {
    return CorporateBondSecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CorporateBondSecurity.Meta.INSTANCE);
  }

  @Override
  public CorporateBondSecurity.Meta metaBean() {
    return CorporateBondSecurity.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public CorporateBondSecurity clone() {
    return (CorporateBondSecurity) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("CorporateBondSecurity{");
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
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CorporateBondSecurity}.
   */
  public static class Meta extends BondSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends CorporateBondSecurity> builder() {
      return new DirectBeanBuilder<CorporateBondSecurity>(new CorporateBondSecurity());
    }

    @Override
    public Class<? extends CorporateBondSecurity> beanType() {
      return CorporateBondSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}