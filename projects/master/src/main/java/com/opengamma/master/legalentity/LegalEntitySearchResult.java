/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.legalentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.AbstractSearchResult;
import com.opengamma.util.PublicSPI;

/**
 * Result from searching for legal entities.
 * <p>
 * The returned documents will match the search criteria.
 * See {@link LegalEntitySearchRequest} for more details.
 */
@PublicSPI
@BeanDefinition
public class LegalEntitySearchResult extends AbstractSearchResult<LegalEntityDocument> {

  /**
   * Creates an instance.
   */
  public LegalEntitySearchResult() {
  }

  /**
   * Creates an instance from a collection of documents.
   *
   * @param coll the collection of documents to add, not null
   */
  public LegalEntitySearchResult(final Collection<LegalEntityDocument> coll) {
    super(coll);
  }

  /**
   * Creates an instance specifying the version-correction searched for.
   *
   * @param versionCorrection the version-correction of the data, not null
   */
  public LegalEntitySearchResult(final VersionCorrection versionCorrection) {
    setVersionCorrection(versionCorrection);
  }

  //-------------------------------------------------------------------------

  /**
   * Gets the returned legal entities from within the documents.
   *
   * @return the legal entities, not null
   */
  public List<ManageableLegalEntity> getLegalEntities() {
    final List<ManageableLegalEntity> result = new ArrayList<>();
    if (getDocuments() != null) {
      for (final LegalEntityDocument doc : getDocuments()) {
        result.add(doc.getLegalEntity());
      }
    }
    return result;
  }

  /**
   * Gets the first legal entity, or null if no documents.
   *
   * @return the first legal entity, null if none
   */
  public ManageableLegalEntity getFirstLegalEntity() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getLegalEntity() : null;
  }

  /**
   * Gets the single result expected from a query.
   * <p>
   * This throws an exception if more than 1 result is actually available. Thus,
   * this method implies an assumption about uniqueness of the queried legal
   * entity.
   *
   * @return the matching legal entity, not null
   * @throws IllegalStateException
   *           if no legal entity was found
   */
  public ManageableLegalEntity getSingleLegalEntity() {
    if (getDocuments().size() != 1) {
      throw new OpenGammaRuntimeException("Expecting zero or single resulting match, and was " + getDocuments().size());
    }
    return getDocuments().get(0).getLegalEntity();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegalEntitySearchResult}.
   * @return the meta-bean, not null
   */
  public static LegalEntitySearchResult.Meta meta() {
    return LegalEntitySearchResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegalEntitySearchResult.Meta.INSTANCE);
  }

  @Override
  public LegalEntitySearchResult.Meta metaBean() {
    return LegalEntitySearchResult.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public LegalEntitySearchResult clone() {
    return JodaBeanUtils.cloneAlways(this);
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
    buf.append("LegalEntitySearchResult{");
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
   * The meta-bean for {@code LegalEntitySearchResult}.
   */
  public static class Meta extends AbstractSearchResult.Meta<LegalEntityDocument> {
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
    public BeanBuilder<? extends LegalEntitySearchResult> builder() {
      return new DirectBeanBuilder<LegalEntitySearchResult>(new LegalEntitySearchResult());
    }

    @Override
    public Class<? extends LegalEntitySearchResult> beanType() {
      return LegalEntitySearchResult.class;
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
