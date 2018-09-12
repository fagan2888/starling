/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

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

import com.opengamma.core.convention.ConventionType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * Conventions for overnight swap legs.
 */
@BeanDefinition
public class OISLegConvention extends FinancialConvention {

  /**
   * Type of the convention.
   */
  public static final ConventionType TYPE = ConventionType.of("OvernightIndexSwapLeg");

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The overnight index convention.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _overnightIndexConvention;
  /**
   * The payment tenor.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _paymentTenor;
  /**
   * The payment lag in days.
   */
  @PropertyDefinition
  private int _paymentLag;
  /**
   * The business day convention.
   */
  @PropertyDefinition(validate = "notNull")
  private BusinessDayConvention _businessDayConvention;
  /**
   * The settlement days.
   */
  @PropertyDefinition
  private int _settlementDays;
  /**
   * Whether the is schedule end-of-month.
   */
  @PropertyDefinition
  private boolean _isEOM;
  /**
   * The stub type.
   */
  @PropertyDefinition(validate = "notNull")
  private StubType _stubType;
  /**
   * Whether the notional exchanged.
   */
  @PropertyDefinition
  private boolean _isExchangeNotional;

  /**
   * Creates an instance.
   */
  protected OISLegConvention() {
    super();
  }

  /**
   * Creates an instance.
   *
   * @param name  the convention name, not null
   * @param externalIdBundle  the external identifiers for this convention, not null
   * @param overnightIndexConvention  the id of the overnight index, not null
   * @param paymentTenor  the payment tenor, not null
   * @param businessDayConvention  the business-day convention, not null
   * @param settlementDays  the number of settlement days
   * @param isEOM  true if dates follow the end-of-month rule
   * @param stubType  the stub type, not null
   * @param isExchangeNotional  true if notional is to be exchanged
   * @param paymentLag  the payment lag
   */
  public OISLegConvention(
      final String name, final ExternalIdBundle externalIdBundle, final ExternalId overnightIndexConvention,
      final Tenor paymentTenor, final BusinessDayConvention businessDayConvention, final int settlementDays,
      final boolean isEOM, final StubType stubType, final boolean isExchangeNotional, final int paymentLag) {
    super(name, externalIdBundle);
    setOvernightIndexConvention(overnightIndexConvention);
    setPaymentTenor(paymentTenor);
    setBusinessDayConvention(businessDayConvention);
    setSettlementDays(settlementDays);
    setIsEOM(isEOM);
    setStubType(stubType);
    setIsExchangeNotional(isExchangeNotional);
    setPaymentLag(paymentLag);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the type identifying this convention.
   *
   * @return the {@link #TYPE} constant, not null
   */
  @Override
  public ConventionType getConventionType() {
    return TYPE;
  }

  /**
   * Accepts a visitor to manage traversal of the hierarchy.
   *
   * @param <T>  the result type of the visitor
   * @param visitor  the visitor, not null
   * @return the result
   */
  @Override
  public <T> T accept(final FinancialConventionVisitor<T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitOISLegConvention(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code OISLegConvention}.
   * @return the meta-bean, not null
   */
  public static OISLegConvention.Meta meta() {
    return OISLegConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(OISLegConvention.Meta.INSTANCE);
  }

  @Override
  public OISLegConvention.Meta metaBean() {
    return OISLegConvention.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the overnight index convention.
   * @return the value of the property, not null
   */
  public ExternalId getOvernightIndexConvention() {
    return _overnightIndexConvention;
  }

  /**
   * Sets the overnight index convention.
   * @param overnightIndexConvention  the new value of the property, not null
   */
  public void setOvernightIndexConvention(ExternalId overnightIndexConvention) {
    JodaBeanUtils.notNull(overnightIndexConvention, "overnightIndexConvention");
    this._overnightIndexConvention = overnightIndexConvention;
  }

  /**
   * Gets the the {@code overnightIndexConvention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> overnightIndexConvention() {
    return metaBean().overnightIndexConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payment tenor.
   * @return the value of the property, not null
   */
  public Tenor getPaymentTenor() {
    return _paymentTenor;
  }

  /**
   * Sets the payment tenor.
   * @param paymentTenor  the new value of the property, not null
   */
  public void setPaymentTenor(Tenor paymentTenor) {
    JodaBeanUtils.notNull(paymentTenor, "paymentTenor");
    this._paymentTenor = paymentTenor;
  }

  /**
   * Gets the the {@code paymentTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> paymentTenor() {
    return metaBean().paymentTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payment lag in days.
   * @return the value of the property
   */
  public int getPaymentLag() {
    return _paymentLag;
  }

  /**
   * Sets the payment lag in days.
   * @param paymentLag  the new value of the property
   */
  public void setPaymentLag(int paymentLag) {
    this._paymentLag = paymentLag;
  }

  /**
   * Gets the the {@code paymentLag} property.
   * @return the property, not null
   */
  public final Property<Integer> paymentLag() {
    return metaBean().paymentLag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the business day convention.
   * @return the value of the property, not null
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Sets the business day convention.
   * @param businessDayConvention  the new value of the property, not null
   */
  public void setBusinessDayConvention(BusinessDayConvention businessDayConvention) {
    JodaBeanUtils.notNull(businessDayConvention, "businessDayConvention");
    this._businessDayConvention = businessDayConvention;
  }

  /**
   * Gets the the {@code businessDayConvention} property.
   * @return the property, not null
   */
  public final Property<BusinessDayConvention> businessDayConvention() {
    return metaBean().businessDayConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement days.
   * @return the value of the property
   */
  public int getSettlementDays() {
    return _settlementDays;
  }

  /**
   * Sets the settlement days.
   * @param settlementDays  the new value of the property
   */
  public void setSettlementDays(int settlementDays) {
    this._settlementDays = settlementDays;
  }

  /**
   * Gets the the {@code settlementDays} property.
   * @return the property, not null
   */
  public final Property<Integer> settlementDays() {
    return metaBean().settlementDays().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether the is schedule end-of-month.
   * @return the value of the property
   */
  public boolean isIsEOM() {
    return _isEOM;
  }

  /**
   * Sets whether the is schedule end-of-month.
   * @param isEOM  the new value of the property
   */
  public void setIsEOM(boolean isEOM) {
    this._isEOM = isEOM;
  }

  /**
   * Gets the the {@code isEOM} property.
   * @return the property, not null
   */
  public final Property<Boolean> isEOM() {
    return metaBean().isEOM().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stub type.
   * @return the value of the property, not null
   */
  public StubType getStubType() {
    return _stubType;
  }

  /**
   * Sets the stub type.
   * @param stubType  the new value of the property, not null
   */
  public void setStubType(StubType stubType) {
    JodaBeanUtils.notNull(stubType, "stubType");
    this._stubType = stubType;
  }

  /**
   * Gets the the {@code stubType} property.
   * @return the property, not null
   */
  public final Property<StubType> stubType() {
    return metaBean().stubType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether the notional exchanged.
   * @return the value of the property
   */
  public boolean isIsExchangeNotional() {
    return _isExchangeNotional;
  }

  /**
   * Sets whether the notional exchanged.
   * @param isExchangeNotional  the new value of the property
   */
  public void setIsExchangeNotional(boolean isExchangeNotional) {
    this._isExchangeNotional = isExchangeNotional;
  }

  /**
   * Gets the the {@code isExchangeNotional} property.
   * @return the property, not null
   */
  public final Property<Boolean> isExchangeNotional() {
    return metaBean().isExchangeNotional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public OISLegConvention clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      OISLegConvention other = (OISLegConvention) obj;
      return JodaBeanUtils.equal(getOvernightIndexConvention(), other.getOvernightIndexConvention()) &&
          JodaBeanUtils.equal(getPaymentTenor(), other.getPaymentTenor()) &&
          (getPaymentLag() == other.getPaymentLag()) &&
          JodaBeanUtils.equal(getBusinessDayConvention(), other.getBusinessDayConvention()) &&
          (getSettlementDays() == other.getSettlementDays()) &&
          (isIsEOM() == other.isIsEOM()) &&
          JodaBeanUtils.equal(getStubType(), other.getStubType()) &&
          (isIsExchangeNotional() == other.isIsExchangeNotional()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getOvernightIndexConvention());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPaymentTenor());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPaymentLag());
    hash = hash * 31 + JodaBeanUtils.hashCode(getBusinessDayConvention());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSettlementDays());
    hash = hash * 31 + JodaBeanUtils.hashCode(isIsEOM());
    hash = hash * 31 + JodaBeanUtils.hashCode(getStubType());
    hash = hash * 31 + JodaBeanUtils.hashCode(isIsExchangeNotional());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("OISLegConvention{");
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
    buf.append("overnightIndexConvention").append('=').append(JodaBeanUtils.toString(getOvernightIndexConvention())).append(',').append(' ');
    buf.append("paymentTenor").append('=').append(JodaBeanUtils.toString(getPaymentTenor())).append(',').append(' ');
    buf.append("paymentLag").append('=').append(JodaBeanUtils.toString(getPaymentLag())).append(',').append(' ');
    buf.append("businessDayConvention").append('=').append(JodaBeanUtils.toString(getBusinessDayConvention())).append(',').append(' ');
    buf.append("settlementDays").append('=').append(JodaBeanUtils.toString(getSettlementDays())).append(',').append(' ');
    buf.append("isEOM").append('=').append(JodaBeanUtils.toString(isIsEOM())).append(',').append(' ');
    buf.append("stubType").append('=').append(JodaBeanUtils.toString(getStubType())).append(',').append(' ');
    buf.append("isExchangeNotional").append('=').append(JodaBeanUtils.toString(isIsExchangeNotional())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code OISLegConvention}.
   */
  public static class Meta extends FinancialConvention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code overnightIndexConvention} property.
     */
    private final MetaProperty<ExternalId> _overnightIndexConvention = DirectMetaProperty.ofReadWrite(
        this, "overnightIndexConvention", OISLegConvention.class, ExternalId.class);
    /**
     * The meta-property for the {@code paymentTenor} property.
     */
    private final MetaProperty<Tenor> _paymentTenor = DirectMetaProperty.ofReadWrite(
        this, "paymentTenor", OISLegConvention.class, Tenor.class);
    /**
     * The meta-property for the {@code paymentLag} property.
     */
    private final MetaProperty<Integer> _paymentLag = DirectMetaProperty.ofReadWrite(
        this, "paymentLag", OISLegConvention.class, Integer.TYPE);
    /**
     * The meta-property for the {@code businessDayConvention} property.
     */
    private final MetaProperty<BusinessDayConvention> _businessDayConvention = DirectMetaProperty.ofReadWrite(
        this, "businessDayConvention", OISLegConvention.class, BusinessDayConvention.class);
    /**
     * The meta-property for the {@code settlementDays} property.
     */
    private final MetaProperty<Integer> _settlementDays = DirectMetaProperty.ofReadWrite(
        this, "settlementDays", OISLegConvention.class, Integer.TYPE);
    /**
     * The meta-property for the {@code isEOM} property.
     */
    private final MetaProperty<Boolean> _isEOM = DirectMetaProperty.ofReadWrite(
        this, "isEOM", OISLegConvention.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code stubType} property.
     */
    private final MetaProperty<StubType> _stubType = DirectMetaProperty.ofReadWrite(
        this, "stubType", OISLegConvention.class, StubType.class);
    /**
     * The meta-property for the {@code isExchangeNotional} property.
     */
    private final MetaProperty<Boolean> _isExchangeNotional = DirectMetaProperty.ofReadWrite(
        this, "isExchangeNotional", OISLegConvention.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "overnightIndexConvention",
        "paymentTenor",
        "paymentLag",
        "businessDayConvention",
        "settlementDays",
        "isEOM",
        "stubType",
        "isExchangeNotional");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1218695809:  // overnightIndexConvention
          return _overnightIndexConvention;
        case -507548582:  // paymentTenor
          return _paymentTenor;
        case 1612870060:  // paymentLag
          return _paymentLag;
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case -295948000:  // settlementDays
          return _settlementDays;
        case 100464505:  // isEOM
          return _isEOM;
        case 1873675528:  // stubType
          return _stubType;
        case 348962765:  // isExchangeNotional
          return _isExchangeNotional;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends OISLegConvention> builder() {
      return new DirectBeanBuilder<OISLegConvention>(new OISLegConvention());
    }

    @Override
    public Class<? extends OISLegConvention> beanType() {
      return OISLegConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code overnightIndexConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> overnightIndexConvention() {
      return _overnightIndexConvention;
    }

    /**
     * The meta-property for the {@code paymentTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> paymentTenor() {
      return _paymentTenor;
    }

    /**
     * The meta-property for the {@code paymentLag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> paymentLag() {
      return _paymentLag;
    }

    /**
     * The meta-property for the {@code businessDayConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BusinessDayConvention> businessDayConvention() {
      return _businessDayConvention;
    }

    /**
     * The meta-property for the {@code settlementDays} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> settlementDays() {
      return _settlementDays;
    }

    /**
     * The meta-property for the {@code isEOM} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isEOM() {
      return _isEOM;
    }

    /**
     * The meta-property for the {@code stubType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StubType> stubType() {
      return _stubType;
    }

    /**
     * The meta-property for the {@code isExchangeNotional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isExchangeNotional() {
      return _isExchangeNotional;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1218695809:  // overnightIndexConvention
          return ((OISLegConvention) bean).getOvernightIndexConvention();
        case -507548582:  // paymentTenor
          return ((OISLegConvention) bean).getPaymentTenor();
        case 1612870060:  // paymentLag
          return ((OISLegConvention) bean).getPaymentLag();
        case -1002835891:  // businessDayConvention
          return ((OISLegConvention) bean).getBusinessDayConvention();
        case -295948000:  // settlementDays
          return ((OISLegConvention) bean).getSettlementDays();
        case 100464505:  // isEOM
          return ((OISLegConvention) bean).isIsEOM();
        case 1873675528:  // stubType
          return ((OISLegConvention) bean).getStubType();
        case 348962765:  // isExchangeNotional
          return ((OISLegConvention) bean).isIsExchangeNotional();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1218695809:  // overnightIndexConvention
          ((OISLegConvention) bean).setOvernightIndexConvention((ExternalId) newValue);
          return;
        case -507548582:  // paymentTenor
          ((OISLegConvention) bean).setPaymentTenor((Tenor) newValue);
          return;
        case 1612870060:  // paymentLag
          ((OISLegConvention) bean).setPaymentLag((Integer) newValue);
          return;
        case -1002835891:  // businessDayConvention
          ((OISLegConvention) bean).setBusinessDayConvention((BusinessDayConvention) newValue);
          return;
        case -295948000:  // settlementDays
          ((OISLegConvention) bean).setSettlementDays((Integer) newValue);
          return;
        case 100464505:  // isEOM
          ((OISLegConvention) bean).setIsEOM((Boolean) newValue);
          return;
        case 1873675528:  // stubType
          ((OISLegConvention) bean).setStubType((StubType) newValue);
          return;
        case 348962765:  // isExchangeNotional
          ((OISLegConvention) bean).setIsExchangeNotional((Boolean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((OISLegConvention) bean)._overnightIndexConvention, "overnightIndexConvention");
      JodaBeanUtils.notNull(((OISLegConvention) bean)._paymentTenor, "paymentTenor");
      JodaBeanUtils.notNull(((OISLegConvention) bean)._businessDayConvention, "businessDayConvention");
      JodaBeanUtils.notNull(((OISLegConvention) bean)._stubType, "stubType");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
