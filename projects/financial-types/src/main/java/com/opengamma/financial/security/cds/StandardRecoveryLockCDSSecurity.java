/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.cds;

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

import com.opengamma.analytics.financial.credit.DebtSeniority;
import com.opengamma.analytics.financial.credit.RestructuringClause;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.financial.security.swap.InterestRateNotional;
import com.opengamma.id.ExternalId;
import com.opengamma.master.security.SecurityDescription;

/**
 * @deprecated Use {@link com.opengamma.financial.security.credit.StandardCDSSecurity}.
 */
@Deprecated
@BeanDefinition
@SecurityDescription(type = StandardRecoveryLockCDSSecurity.SECURITY_TYPE, description = "Standard recovery lock cds")
public class StandardRecoveryLockCDSSecurity extends StandardCDSSecurity {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The security type
   */
  public static final String SECURITY_TYPE = "STANDARD_RECOVERY_LOCK_CDS";

  /**
   * The recovery rate.
   */
  @PropertyDefinition(validate = "notNull")
  private double _recoveryRate;

  StandardRecoveryLockCDSSecurity() { // for fudge
    super(SECURITY_TYPE);
  }

  public StandardRecoveryLockCDSSecurity(final boolean isBuy, final ExternalId protectionSeller, final ExternalId protectionBuyer,
      final ExternalId referenceEntity, // CSIGNORE
      final DebtSeniority debtSeniority, final RestructuringClause restructuringClause, final ExternalId regionId, final ZonedDateTime startDate,
      final ZonedDateTime effectiveDate, final ZonedDateTime maturityDate, final StubType stubType, final Frequency couponFrequency, final DayCount dayCount,
      final BusinessDayConvention businessDayConvention, final boolean immAdjustMaturityDate, final boolean adjustEffectiveDate,
      final boolean adjustMaturityDate, final InterestRateNotional notional, final double recoveryRate, final boolean includeAccruedPremium,
      final boolean protectionStart, final double quotedSpread, final InterestRateNotional upfrontAmount) {
    super(isBuy, protectionSeller, protectionBuyer, referenceEntity, debtSeniority, restructuringClause, regionId, startDate, effectiveDate, maturityDate,
        stubType, couponFrequency, dayCount, businessDayConvention, immAdjustMaturityDate, adjustEffectiveDate, adjustMaturityDate, notional,
        includeAccruedPremium, protectionStart, quotedSpread, upfrontAmount, SECURITY_TYPE);
    _recoveryRate = recoveryRate;
  }

  @Override
  public final <T> T accept(final FinancialSecurityVisitor<T> visitor) {
    return visitor.visitStandardRecoveryLockCDSSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code StandardRecoveryLockCDSSecurity}.
   * @return the meta-bean, not null
   */
  public static StandardRecoveryLockCDSSecurity.Meta meta() {
    return StandardRecoveryLockCDSSecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(StandardRecoveryLockCDSSecurity.Meta.INSTANCE);
  }

  @Override
  public StandardRecoveryLockCDSSecurity.Meta metaBean() {
    return StandardRecoveryLockCDSSecurity.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the recovery rate.
   * @return the value of the property, not null
   */
  public double getRecoveryRate() {
    return _recoveryRate;
  }

  /**
   * Sets the recovery rate.
   * @param recoveryRate  the new value of the property, not null
   */
  public void setRecoveryRate(double recoveryRate) {
    JodaBeanUtils.notNull(recoveryRate, "recoveryRate");
    this._recoveryRate = recoveryRate;
  }

  /**
   * Gets the the {@code recoveryRate} property.
   * @return the property, not null
   */
  public final Property<Double> recoveryRate() {
    return metaBean().recoveryRate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public StandardRecoveryLockCDSSecurity clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      StandardRecoveryLockCDSSecurity other = (StandardRecoveryLockCDSSecurity) obj;
      return JodaBeanUtils.equal(getRecoveryRate(), other.getRecoveryRate()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getRecoveryRate());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("StandardRecoveryLockCDSSecurity{");
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
    buf.append("recoveryRate").append('=').append(JodaBeanUtils.toString(getRecoveryRate())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code StandardRecoveryLockCDSSecurity}.
   */
  public static class Meta extends StandardCDSSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code recoveryRate} property.
     */
    private final MetaProperty<Double> _recoveryRate = DirectMetaProperty.ofReadWrite(
        this, "recoveryRate", StandardRecoveryLockCDSSecurity.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "recoveryRate");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 2002873877:  // recoveryRate
          return _recoveryRate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends StandardRecoveryLockCDSSecurity> builder() {
      return new DirectBeanBuilder<StandardRecoveryLockCDSSecurity>(new StandardRecoveryLockCDSSecurity());
    }

    @Override
    public Class<? extends StandardRecoveryLockCDSSecurity> beanType() {
      return StandardRecoveryLockCDSSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code recoveryRate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> recoveryRate() {
      return _recoveryRate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 2002873877:  // recoveryRate
          return ((StandardRecoveryLockCDSSecurity) bean).getRecoveryRate();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 2002873877:  // recoveryRate
          ((StandardRecoveryLockCDSSecurity) bean).setRecoveryRate((Double) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((StandardRecoveryLockCDSSecurity) bean)._recoveryRate, "recoveryRate");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
