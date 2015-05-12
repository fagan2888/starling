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

import com.opengamma.id.ObjectId;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;

/**
 * Request containing options for getting a single batch data set.
 * <p>
 * Each batch data set is potentially large, thus options are available
 * to filter the resulting document.
 * <p>
 * This class is mutable and not thread-safe.
 */
@BeanDefinition
public class BatchGetRequest extends DirectBean {

  /**
   * The unique identifier of the batch.
   * This must not be null for a valid search,
   */
  @PropertyDefinition
  private ObjectId _batchId;
  /**
   * The request for paging the main batch data.
   * By default, the entire data set will be returned.
   */
  @PropertyDefinition
  private PagingRequest _dataPagingRequest = PagingRequest.ALL;
  /**
   * The request for paging the batch errors.
   * By default, no error data will be returned, although the total count will be.
   */
  @PropertyDefinition
  private PagingRequest _errorPagingRequest = PagingRequest.NONE;

  /**
   * Creates an instance.
   */
  public BatchGetRequest() {
  }

  /**
   * Creates an instance specifying an identifier.
   * <p>
   * With no further customisation this will retrieve all the batch data and
   * the total error count.
   * 
   * @param batchId  the batch identifier, not null
   */
  public BatchGetRequest(ObjectId batchId) {
    ArgumentChecker.notNull(batchId, "batchId");
    setBatchId(batchId);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BatchGetRequest}.
   * @return the meta-bean, not null
   */
  public static BatchGetRequest.Meta meta() {
    return BatchGetRequest.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(BatchGetRequest.Meta.INSTANCE);
  }

  @Override
  public BatchGetRequest.Meta metaBean() {
    return BatchGetRequest.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the batch.
   * This must not be null for a valid search,
   * @return the value of the property
   */
  public ObjectId getBatchId() {
    return _batchId;
  }

  /**
   * Sets the unique identifier of the batch.
   * This must not be null for a valid search,
   * @param batchId  the new value of the property
   */
  public void setBatchId(ObjectId batchId) {
    this._batchId = batchId;
  }

  /**
   * Gets the the {@code batchId} property.
   * This must not be null for a valid search,
   * @return the property, not null
   */
  public final Property<ObjectId> batchId() {
    return metaBean().batchId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the request for paging the main batch data.
   * By default, the entire data set will be returned.
   * @return the value of the property
   */
  public PagingRequest getDataPagingRequest() {
    return _dataPagingRequest;
  }

  /**
   * Sets the request for paging the main batch data.
   * By default, the entire data set will be returned.
   * @param dataPagingRequest  the new value of the property
   */
  public void setDataPagingRequest(PagingRequest dataPagingRequest) {
    this._dataPagingRequest = dataPagingRequest;
  }

  /**
   * Gets the the {@code dataPagingRequest} property.
   * By default, the entire data set will be returned.
   * @return the property, not null
   */
  public final Property<PagingRequest> dataPagingRequest() {
    return metaBean().dataPagingRequest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the request for paging the batch errors.
   * By default, no error data will be returned, although the total count will be.
   * @return the value of the property
   */
  public PagingRequest getErrorPagingRequest() {
    return _errorPagingRequest;
  }

  /**
   * Sets the request for paging the batch errors.
   * By default, no error data will be returned, although the total count will be.
   * @param errorPagingRequest  the new value of the property
   */
  public void setErrorPagingRequest(PagingRequest errorPagingRequest) {
    this._errorPagingRequest = errorPagingRequest;
  }

  /**
   * Gets the the {@code errorPagingRequest} property.
   * By default, no error data will be returned, although the total count will be.
   * @return the property, not null
   */
  public final Property<PagingRequest> errorPagingRequest() {
    return metaBean().errorPagingRequest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public BatchGetRequest clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BatchGetRequest other = (BatchGetRequest) obj;
      return JodaBeanUtils.equal(getBatchId(), other.getBatchId()) &&
          JodaBeanUtils.equal(getDataPagingRequest(), other.getDataPagingRequest()) &&
          JodaBeanUtils.equal(getErrorPagingRequest(), other.getErrorPagingRequest());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getBatchId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getDataPagingRequest());
    hash = hash * 31 + JodaBeanUtils.hashCode(getErrorPagingRequest());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("BatchGetRequest{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("batchId").append('=').append(JodaBeanUtils.toString(getBatchId())).append(',').append(' ');
    buf.append("dataPagingRequest").append('=').append(JodaBeanUtils.toString(getDataPagingRequest())).append(',').append(' ');
    buf.append("errorPagingRequest").append('=').append(JodaBeanUtils.toString(getErrorPagingRequest())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BatchGetRequest}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code batchId} property.
     */
    private final MetaProperty<ObjectId> _batchId = DirectMetaProperty.ofReadWrite(
        this, "batchId", BatchGetRequest.class, ObjectId.class);
    /**
     * The meta-property for the {@code dataPagingRequest} property.
     */
    private final MetaProperty<PagingRequest> _dataPagingRequest = DirectMetaProperty.ofReadWrite(
        this, "dataPagingRequest", BatchGetRequest.class, PagingRequest.class);
    /**
     * The meta-property for the {@code errorPagingRequest} property.
     */
    private final MetaProperty<PagingRequest> _errorPagingRequest = DirectMetaProperty.ofReadWrite(
        this, "errorPagingRequest", BatchGetRequest.class, PagingRequest.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "batchId",
        "dataPagingRequest",
        "errorPagingRequest");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -331744779:  // batchId
          return _batchId;
        case -100490791:  // dataPagingRequest
          return _dataPagingRequest;
        case 704383035:  // errorPagingRequest
          return _errorPagingRequest;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BatchGetRequest> builder() {
      return new DirectBeanBuilder<BatchGetRequest>(new BatchGetRequest());
    }

    @Override
    public Class<? extends BatchGetRequest> beanType() {
      return BatchGetRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code batchId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ObjectId> batchId() {
      return _batchId;
    }

    /**
     * The meta-property for the {@code dataPagingRequest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PagingRequest> dataPagingRequest() {
      return _dataPagingRequest;
    }

    /**
     * The meta-property for the {@code errorPagingRequest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PagingRequest> errorPagingRequest() {
      return _errorPagingRequest;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -331744779:  // batchId
          return ((BatchGetRequest) bean).getBatchId();
        case -100490791:  // dataPagingRequest
          return ((BatchGetRequest) bean).getDataPagingRequest();
        case 704383035:  // errorPagingRequest
          return ((BatchGetRequest) bean).getErrorPagingRequest();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -331744779:  // batchId
          ((BatchGetRequest) bean).setBatchId((ObjectId) newValue);
          return;
        case -100490791:  // dataPagingRequest
          ((BatchGetRequest) bean).setDataPagingRequest((PagingRequest) newValue);
          return;
        case 704383035:  // errorPagingRequest
          ((BatchGetRequest) bean).setErrorPagingRequest((PagingRequest) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
