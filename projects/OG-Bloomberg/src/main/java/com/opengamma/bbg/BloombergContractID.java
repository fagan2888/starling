/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.bbg;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
@BeanDefinition
public class BloombergContractID extends DirectBean {

  /**
   * The prefix in classic bloomberg ticker
   */
  @PropertyDefinition
  private String _ticker;
  
  /**
   * The yellow key also know as market sector;
   */
  @PropertyDefinition
  private String _marketSector;
  
  
  public BloombergContractID() {
  }
  
  public BloombergContractID(String ticker, String marketSector) {
    ArgumentChecker.notNull(ticker, "ticker");
    ArgumentChecker.notNull(marketSector, "marketSector");
    setTicker(ticker);
    setMarketSector(marketSector);
  }
  
  public ExternalId toFutureExternalId() {
    return null;
  }
  
  public ExternalId toOptionExternalId() {
    return null;
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BloombergContractID}.
   * @return the meta-bean, not null
   */
  public static BloombergContractID.Meta meta() {
    return BloombergContractID.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(BloombergContractID.Meta.INSTANCE);
  }

  @Override
  public BloombergContractID.Meta metaBean() {
    return BloombergContractID.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -873960694:  // ticker
        return getTicker();
      case -98321726:  // marketSector
        return getMarketSector();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -873960694:  // ticker
        setTicker((String) newValue);
        return;
      case -98321726:  // marketSector
        setMarketSector((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BloombergContractID other = (BloombergContractID) obj;
      return JodaBeanUtils.equal(getTicker(), other.getTicker()) &&
          JodaBeanUtils.equal(getMarketSector(), other.getMarketSector());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getTicker());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMarketSector());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the prefix in classic bloomberg ticker
   * @return the value of the property
   */
  public String getTicker() {
    return _ticker;
  }

  /**
   * Sets the prefix in classic bloomberg ticker
   * @param ticker  the new value of the property
   */
  public void setTicker(String ticker) {
    this._ticker = ticker;
  }

  /**
   * Gets the the {@code ticker} property.
   * @return the property, not null
   */
  public final Property<String> ticker() {
    return metaBean().ticker().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the yellow key also know as market sector;
   * @return the value of the property
   */
  public String getMarketSector() {
    return _marketSector;
  }

  /**
   * Sets the yellow key also know as market sector;
   * @param marketSector  the new value of the property
   */
  public void setMarketSector(String marketSector) {
    this._marketSector = marketSector;
  }

  /**
   * Gets the the {@code marketSector} property.
   * @return the property, not null
   */
  public final Property<String> marketSector() {
    return metaBean().marketSector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BloombergContractID}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code ticker} property.
     */
    private final MetaProperty<String> _ticker = DirectMetaProperty.ofReadWrite(
        this, "ticker", BloombergContractID.class, String.class);
    /**
     * The meta-property for the {@code marketSector} property.
     */
    private final MetaProperty<String> _marketSector = DirectMetaProperty.ofReadWrite(
        this, "marketSector", BloombergContractID.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "ticker",
        "marketSector");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -873960694:  // ticker
          return _ticker;
        case -98321726:  // marketSector
          return _marketSector;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BloombergContractID> builder() {
      return new DirectBeanBuilder<BloombergContractID>(new BloombergContractID());
    }

    @Override
    public Class<? extends BloombergContractID> beanType() {
      return BloombergContractID.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code ticker} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> ticker() {
      return _ticker;
    }

    /**
     * The meta-property for the {@code marketSector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> marketSector() {
      return _marketSector;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}