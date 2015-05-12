/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.batch.rest;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.Instant;

import com.opengamma.id.UniqueId;
import com.opengamma.util.paging.PagingRequest;

/**
 * Request for searching for batches.
 * <p>
 * Documents will be returned that match the search criteria.
 * This class provides the ability to page the results.
 * <p>
 * This class is mutable and not thread-safe.
 */
@BeanDefinition
public class BatchRunSearchRequest extends DirectBean {

  /**
   * The request for paging.
   * By default all matching items will be returned.
   */
  @PropertyDefinition
  private PagingRequest _pagingRequest = PagingRequest.FIRST_PAGE;

  /**
   * The unique id of the market data snapshot used by the batch
   */
  @PropertyDefinition
  private UniqueId _marketDataUid;

  /**
   * The unique id of the view definition used by the batch
   */
  @PropertyDefinition
  private UniqueId _viewDefinitionUid;

  /**
   * The version correction used by the batch
   */
  @PropertyDefinition
  private com.opengamma.id.VersionCorrection _versionCorrection;

  /**
   * The valuation time used by the batch
   */
  @PropertyDefinition
  private Instant _valuationTime;

  /**
   * Creates an instance.
   */
  public BatchRunSearchRequest() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BatchRunSearchRequest}.
   * @return the meta-bean, not null
   */
  public static BatchRunSearchRequest.Meta meta() {
    return BatchRunSearchRequest.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(BatchRunSearchRequest.Meta.INSTANCE);
  }

  @Override
  public BatchRunSearchRequest.Meta metaBean() {
    return BatchRunSearchRequest.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the request for paging.
   * By default all matching items will be returned.
   * @return the value of the property
   */
  public PagingRequest getPagingRequest() {
    return _pagingRequest;
  }

  /**
   * Sets the request for paging.
   * By default all matching items will be returned.
   * @param pagingRequest  the new value of the property
   */
  public void setPagingRequest(PagingRequest pagingRequest) {
    this._pagingRequest = pagingRequest;
  }

  /**
   * Gets the the {@code pagingRequest} property.
   * By default all matching items will be returned.
   * @return the property, not null
   */
  public final Property<PagingRequest> pagingRequest() {
    return metaBean().pagingRequest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique id of the market data snapshot used by the batch
   * @return the value of the property
   */
  public UniqueId getMarketDataUid() {
    return _marketDataUid;
  }

  /**
   * Sets the unique id of the market data snapshot used by the batch
   * @param marketDataUid  the new value of the property
   */
  public void setMarketDataUid(UniqueId marketDataUid) {
    this._marketDataUid = marketDataUid;
  }

  /**
   * Gets the the {@code marketDataUid} property.
   * @return the property, not null
   */
  public final Property<UniqueId> marketDataUid() {
    return metaBean().marketDataUid().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique id of the view definition used by the batch
   * @return the value of the property
   */
  public UniqueId getViewDefinitionUid() {
    return _viewDefinitionUid;
  }

  /**
   * Sets the unique id of the view definition used by the batch
   * @param viewDefinitionUid  the new value of the property
   */
  public void setViewDefinitionUid(UniqueId viewDefinitionUid) {
    this._viewDefinitionUid = viewDefinitionUid;
  }

  /**
   * Gets the the {@code viewDefinitionUid} property.
   * @return the property, not null
   */
  public final Property<UniqueId> viewDefinitionUid() {
    return metaBean().viewDefinitionUid().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the version correction used by the batch
   * @return the value of the property
   */
  public com.opengamma.id.VersionCorrection getVersionCorrection() {
    return _versionCorrection;
  }

  /**
   * Sets the version correction used by the batch
   * @param versionCorrection  the new value of the property
   */
  public void setVersionCorrection(com.opengamma.id.VersionCorrection versionCorrection) {
    this._versionCorrection = versionCorrection;
  }

  /**
   * Gets the the {@code versionCorrection} property.
   * @return the property, not null
   */
  public final Property<com.opengamma.id.VersionCorrection> versionCorrection() {
    return metaBean().versionCorrection().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuation time used by the batch
   * @return the value of the property
   */
  public Instant getValuationTime() {
    return _valuationTime;
  }

  /**
   * Sets the valuation time used by the batch
   * @param valuationTime  the new value of the property
   */
  public void setValuationTime(Instant valuationTime) {
    this._valuationTime = valuationTime;
  }

  /**
   * Gets the the {@code valuationTime} property.
   * @return the property, not null
   */
  public final Property<Instant> valuationTime() {
    return metaBean().valuationTime().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public BatchRunSearchRequest clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BatchRunSearchRequest other = (BatchRunSearchRequest) obj;
      return JodaBeanUtils.equal(getPagingRequest(), other.getPagingRequest()) &&
          JodaBeanUtils.equal(getMarketDataUid(), other.getMarketDataUid()) &&
          JodaBeanUtils.equal(getViewDefinitionUid(), other.getViewDefinitionUid()) &&
          JodaBeanUtils.equal(getVersionCorrection(), other.getVersionCorrection()) &&
          JodaBeanUtils.equal(getValuationTime(), other.getValuationTime());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getPagingRequest());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMarketDataUid());
    hash = hash * 31 + JodaBeanUtils.hashCode(getViewDefinitionUid());
    hash = hash * 31 + JodaBeanUtils.hashCode(getVersionCorrection());
    hash = hash * 31 + JodaBeanUtils.hashCode(getValuationTime());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("BatchRunSearchRequest{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("pagingRequest").append('=').append(JodaBeanUtils.toString(getPagingRequest())).append(',').append(' ');
    buf.append("marketDataUid").append('=').append(JodaBeanUtils.toString(getMarketDataUid())).append(',').append(' ');
    buf.append("viewDefinitionUid").append('=').append(JodaBeanUtils.toString(getViewDefinitionUid())).append(',').append(' ');
    buf.append("versionCorrection").append('=').append(JodaBeanUtils.toString(getVersionCorrection())).append(',').append(' ');
    buf.append("valuationTime").append('=').append(JodaBeanUtils.toString(getValuationTime())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BatchRunSearchRequest}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code pagingRequest} property.
     */
    private final MetaProperty<PagingRequest> _pagingRequest = DirectMetaProperty.ofReadWrite(
        this, "pagingRequest", BatchRunSearchRequest.class, PagingRequest.class);
    /**
     * The meta-property for the {@code marketDataUid} property.
     */
    private final MetaProperty<UniqueId> _marketDataUid = DirectMetaProperty.ofReadWrite(
        this, "marketDataUid", BatchRunSearchRequest.class, UniqueId.class);
    /**
     * The meta-property for the {@code viewDefinitionUid} property.
     */
    private final MetaProperty<UniqueId> _viewDefinitionUid = DirectMetaProperty.ofReadWrite(
        this, "viewDefinitionUid", BatchRunSearchRequest.class, UniqueId.class);
    /**
     * The meta-property for the {@code versionCorrection} property.
     */
    private final MetaProperty<com.opengamma.id.VersionCorrection> _versionCorrection = DirectMetaProperty.ofReadWrite(
        this, "versionCorrection", BatchRunSearchRequest.class, com.opengamma.id.VersionCorrection.class);
    /**
     * The meta-property for the {@code valuationTime} property.
     */
    private final MetaProperty<Instant> _valuationTime = DirectMetaProperty.ofReadWrite(
        this, "valuationTime", BatchRunSearchRequest.class, Instant.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "pagingRequest",
        "marketDataUid",
        "viewDefinitionUid",
        "versionCorrection",
        "valuationTime");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2092032669:  // pagingRequest
          return _pagingRequest;
        case 719932522:  // marketDataUid
          return _marketDataUid;
        case 276749144:  // viewDefinitionUid
          return _viewDefinitionUid;
        case -2031293866:  // versionCorrection
          return _versionCorrection;
        case 113591406:  // valuationTime
          return _valuationTime;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BatchRunSearchRequest> builder() {
      return new DirectBeanBuilder<BatchRunSearchRequest>(new BatchRunSearchRequest());
    }

    @Override
    public Class<? extends BatchRunSearchRequest> beanType() {
      return BatchRunSearchRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code pagingRequest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PagingRequest> pagingRequest() {
      return _pagingRequest;
    }

    /**
     * The meta-property for the {@code marketDataUid} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> marketDataUid() {
      return _marketDataUid;
    }

    /**
     * The meta-property for the {@code viewDefinitionUid} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> viewDefinitionUid() {
      return _viewDefinitionUid;
    }

    /**
     * The meta-property for the {@code versionCorrection} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<com.opengamma.id.VersionCorrection> versionCorrection() {
      return _versionCorrection;
    }

    /**
     * The meta-property for the {@code valuationTime} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> valuationTime() {
      return _valuationTime;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -2092032669:  // pagingRequest
          return ((BatchRunSearchRequest) bean).getPagingRequest();
        case 719932522:  // marketDataUid
          return ((BatchRunSearchRequest) bean).getMarketDataUid();
        case 276749144:  // viewDefinitionUid
          return ((BatchRunSearchRequest) bean).getViewDefinitionUid();
        case -2031293866:  // versionCorrection
          return ((BatchRunSearchRequest) bean).getVersionCorrection();
        case 113591406:  // valuationTime
          return ((BatchRunSearchRequest) bean).getValuationTime();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -2092032669:  // pagingRequest
          ((BatchRunSearchRequest) bean).setPagingRequest((PagingRequest) newValue);
          return;
        case 719932522:  // marketDataUid
          ((BatchRunSearchRequest) bean).setMarketDataUid((UniqueId) newValue);
          return;
        case 276749144:  // viewDefinitionUid
          ((BatchRunSearchRequest) bean).setViewDefinitionUid((UniqueId) newValue);
          return;
        case -2031293866:  // versionCorrection
          ((BatchRunSearchRequest) bean).setVersionCorrection((com.opengamma.id.VersionCorrection) newValue);
          return;
        case 113591406:  // valuationTime
          ((BatchRunSearchRequest) bean).setValuationTime((Instant) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
