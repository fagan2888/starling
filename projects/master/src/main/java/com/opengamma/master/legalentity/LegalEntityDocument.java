/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.legalentity;

import java.io.Serializable;
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

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractDocument;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;

/**
 * A document used to pass into and out of the legal entity master.
 */
@PublicSPI
@BeanDefinition
public class LegalEntityDocument extends AbstractDocument implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The legal entity object held by the document.
   */
  @PropertyDefinition(validate = "notNull")
  private ManageableLegalEntity _legalEntity;
  /**
   * The legal entity unique identifier.
   * This field is managed by the master but must be set for updates.
   */
  @PropertyDefinition(overrideGet = true, overrideSet = true)
  private UniqueId _uniqueId;

  /**
   * Creates an instance.
   */
  public LegalEntityDocument() {
  }

  /**
   * Creates an instance from a legal entity.
   *
   * @param legalEntity the legal entity, not null
   */
  public LegalEntityDocument(final ManageableLegalEntity legalEntity) {
    ArgumentChecker.notNull(legalEntity, "legalEntity");
    setUniqueId(legalEntity.getUniqueId());
    setLegalEntity(legalEntity);
  }

  //-------------------------------------------------------------------------
  @Override
  public ManageableLegalEntity getValue() {
    return getLegalEntity();
  }

  /**
   * Gets the name of the legal entity.
   * <p>
   * This is derived from the legal entity itself.
   *
   * @return the name, null if no name
   */
  public String getName() {
    return getLegalEntity() != null ? getLegalEntity().getName() : null;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegalEntityDocument}.
   * @return the meta-bean, not null
   */
  public static LegalEntityDocument.Meta meta() {
    return LegalEntityDocument.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegalEntityDocument.Meta.INSTANCE);
  }

  @Override
  public LegalEntityDocument.Meta metaBean() {
    return LegalEntityDocument.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the legal entity object held by the document.
   * @return the value of the property, not null
   */
  public ManageableLegalEntity getLegalEntity() {
    return _legalEntity;
  }

  /**
   * Sets the legal entity object held by the document.
   * @param legalEntity  the new value of the property, not null
   */
  public void setLegalEntity(ManageableLegalEntity legalEntity) {
    JodaBeanUtils.notNull(legalEntity, "legalEntity");
    this._legalEntity = legalEntity;
  }

  /**
   * Gets the the {@code legalEntity} property.
   * @return the property, not null
   */
  public final Property<ManageableLegalEntity> legalEntity() {
    return metaBean().legalEntity().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the legal entity unique identifier.
   * This field is managed by the master but must be set for updates.
   * @return the value of the property
   */
  @Override
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the legal entity unique identifier.
   * This field is managed by the master but must be set for updates.
   * @param uniqueId  the new value of the property
   */
  @Override
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This field is managed by the master but must be set for updates.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public LegalEntityDocument clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      LegalEntityDocument other = (LegalEntityDocument) obj;
      return JodaBeanUtils.equal(getLegalEntity(), other.getLegalEntity()) &&
          JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getLegalEntity());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("LegalEntityDocument{");
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
    buf.append("legalEntity").append('=').append(JodaBeanUtils.toString(getLegalEntity())).append(',').append(' ');
    buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(getUniqueId())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LegalEntityDocument}.
   */
  public static class Meta extends AbstractDocument.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code legalEntity} property.
     */
    private final MetaProperty<ManageableLegalEntity> _legalEntity = DirectMetaProperty.ofReadWrite(
        this, "legalEntity", LegalEntityDocument.class, ManageableLegalEntity.class);
    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", LegalEntityDocument.class, UniqueId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "legalEntity",
        "uniqueId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 41124860:  // legalEntity
          return _legalEntity;
        case -294460212:  // uniqueId
          return _uniqueId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends LegalEntityDocument> builder() {
      return new DirectBeanBuilder<LegalEntityDocument>(new LegalEntityDocument());
    }

    @Override
    public Class<? extends LegalEntityDocument> beanType() {
      return LegalEntityDocument.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code legalEntity} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ManageableLegalEntity> legalEntity() {
      return _legalEntity;
    }

    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 41124860:  // legalEntity
          return ((LegalEntityDocument) bean).getLegalEntity();
        case -294460212:  // uniqueId
          return ((LegalEntityDocument) bean).getUniqueId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 41124860:  // legalEntity
          ((LegalEntityDocument) bean).setLegalEntity((ManageableLegalEntity) newValue);
          return;
        case -294460212:  // uniqueId
          ((LegalEntityDocument) bean).setUniqueId((UniqueId) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((LegalEntityDocument) bean)._legalEntity, "legalEntity");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
