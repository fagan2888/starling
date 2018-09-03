/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.config;

import java.util.Map;

import javax.ws.rs.core.UriInfo;

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

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.opengamma.id.UniqueId;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.web.WebPerRequestData;
import com.opengamma.web.json.JSONBuilder;

/**
 * Data class for web-based configuration management.
 */
@BeanDefinition
public class WebConfigData extends WebPerRequestData {

  /**
   * The config master.
   */
  @PropertyDefinition
  private ConfigMaster _configMaster;
  /**
   * The type of data being stored.
   */
  @PropertyDefinition
  private Class<?> _type;
  /**
   * The config id from the input URI.
   */
  @PropertyDefinition
  private String _uriConfigId;
  /**
   * The version id from the URI.
   */
  @PropertyDefinition
  private String _uriVersionId;
  /**
   * The config.
   * The {@code Object} type is necessary to handle generics in a practical way.
   */
  @PropertyDefinition
  private ConfigDocument _config;
  /**
   * The versioned config.
   * The {@code Object} type is necessary to handle generics in a practical way.
   */
  @PropertyDefinition
  private ConfigDocument _versioned;

  /**
   * The valid map of types.
   */
  @PropertyDefinition(set = "setClearPutAll")
  private final BiMap<String, Class<?>> _typeMap = HashBiMap.create();

  /**
   * The valid map of templates.
   */
  @PropertyDefinition
  private final Map<Class<?>, JSONBuilder<?>> _jsonBuilderMap = Maps.newHashMap();

  /**
   * Creates an instance.
   */
  public WebConfigData() {
  }

  /**
   * Creates an instance.
   * @param uriInfo  the URI information
   */
  public WebConfigData(final UriInfo uriInfo) {
    setUriInfo(uriInfo);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the best available config id.
   * @param overrideId  the override id, null derives the result from the data
   * @return the id, may be null
   */
  public String getBestConfigUriId(final UniqueId overrideId) {
    if (overrideId != null) {
      return overrideId.toLatest().toString();
    }
    return getConfig() != null ? getConfig().getUniqueId().toLatest().toString() : getUriConfigId();
  }


  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code WebConfigData}.
   * @return the meta-bean, not null
   */
  public static WebConfigData.Meta meta() {
    return WebConfigData.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(WebConfigData.Meta.INSTANCE);
  }

  @Override
  public WebConfigData.Meta metaBean() {
    return WebConfigData.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the config master.
   * @return the value of the property
   */
  public ConfigMaster getConfigMaster() {
    return _configMaster;
  }

  /**
   * Sets the config master.
   * @param configMaster  the new value of the property
   */
  public void setConfigMaster(ConfigMaster configMaster) {
    this._configMaster = configMaster;
  }

  /**
   * Gets the the {@code configMaster} property.
   * @return the property, not null
   */
  public final Property<ConfigMaster> configMaster() {
    return metaBean().configMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the type of data being stored.
   * @return the value of the property
   */
  public Class<?> getType() {
    return _type;
  }

  /**
   * Sets the type of data being stored.
   * @param type  the new value of the property
   */
  public void setType(Class<?> type) {
    this._type = type;
  }

  /**
   * Gets the the {@code type} property.
   * @return the property, not null
   */
  public final Property<Class<?>> type() {
    return metaBean().type().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the config id from the input URI.
   * @return the value of the property
   */
  public String getUriConfigId() {
    return _uriConfigId;
  }

  /**
   * Sets the config id from the input URI.
   * @param uriConfigId  the new value of the property
   */
  public void setUriConfigId(String uriConfigId) {
    this._uriConfigId = uriConfigId;
  }

  /**
   * Gets the the {@code uriConfigId} property.
   * @return the property, not null
   */
  public final Property<String> uriConfigId() {
    return metaBean().uriConfigId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the version id from the URI.
   * @return the value of the property
   */
  public String getUriVersionId() {
    return _uriVersionId;
  }

  /**
   * Sets the version id from the URI.
   * @param uriVersionId  the new value of the property
   */
  public void setUriVersionId(String uriVersionId) {
    this._uriVersionId = uriVersionId;
  }

  /**
   * Gets the the {@code uriVersionId} property.
   * @return the property, not null
   */
  public final Property<String> uriVersionId() {
    return metaBean().uriVersionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the config.
   * The {@code Object} type is necessary to handle generics in a practical way.
   * @return the value of the property
   */
  public ConfigDocument getConfig() {
    return _config;
  }

  /**
   * Sets the config.
   * The {@code Object} type is necessary to handle generics in a practical way.
   * @param config  the new value of the property
   */
  public void setConfig(ConfigDocument config) {
    this._config = config;
  }

  /**
   * Gets the the {@code config} property.
   * The {@code Object} type is necessary to handle generics in a practical way.
   * @return the property, not null
   */
  public final Property<ConfigDocument> config() {
    return metaBean().config().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the versioned config.
   * The {@code Object} type is necessary to handle generics in a practical way.
   * @return the value of the property
   */
  public ConfigDocument getVersioned() {
    return _versioned;
  }

  /**
   * Sets the versioned config.
   * The {@code Object} type is necessary to handle generics in a practical way.
   * @param versioned  the new value of the property
   */
  public void setVersioned(ConfigDocument versioned) {
    this._versioned = versioned;
  }

  /**
   * Gets the the {@code versioned} property.
   * The {@code Object} type is necessary to handle generics in a practical way.
   * @return the property, not null
   */
  public final Property<ConfigDocument> versioned() {
    return metaBean().versioned().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valid map of types.
   * @return the value of the property, not null
   */
  public BiMap<String, Class<?>> getTypeMap() {
    return _typeMap;
  }

  /**
   * Sets the valid map of types.
   * @param typeMap  the new value of the property, not null
   */
  public void setTypeMap(BiMap<String, Class<?>> typeMap) {
    JodaBeanUtils.notNull(typeMap, "typeMap");
    this._typeMap.clear();
    this._typeMap.putAll(typeMap);
  }

  /**
   * Gets the the {@code typeMap} property.
   * @return the property, not null
   */
  public final Property<BiMap<String, Class<?>>> typeMap() {
    return metaBean().typeMap().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valid map of templates.
   * @return the value of the property, not null
   */
  public Map<Class<?>, JSONBuilder<?>> getJsonBuilderMap() {
    return _jsonBuilderMap;
  }

  /**
   * Sets the valid map of templates.
   * @param jsonBuilderMap  the new value of the property, not null
   */
  public void setJsonBuilderMap(Map<Class<?>, JSONBuilder<?>> jsonBuilderMap) {
    JodaBeanUtils.notNull(jsonBuilderMap, "jsonBuilderMap");
    this._jsonBuilderMap.clear();
    this._jsonBuilderMap.putAll(jsonBuilderMap);
  }

  /**
   * Gets the the {@code jsonBuilderMap} property.
   * @return the property, not null
   */
  public final Property<Map<Class<?>, JSONBuilder<?>>> jsonBuilderMap() {
    return metaBean().jsonBuilderMap().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public WebConfigData clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      WebConfigData other = (WebConfigData) obj;
      return JodaBeanUtils.equal(getConfigMaster(), other.getConfigMaster()) &&
          JodaBeanUtils.equal(getType(), other.getType()) &&
          JodaBeanUtils.equal(getUriConfigId(), other.getUriConfigId()) &&
          JodaBeanUtils.equal(getUriVersionId(), other.getUriVersionId()) &&
          JodaBeanUtils.equal(getConfig(), other.getConfig()) &&
          JodaBeanUtils.equal(getVersioned(), other.getVersioned()) &&
          JodaBeanUtils.equal(getTypeMap(), other.getTypeMap()) &&
          JodaBeanUtils.equal(getJsonBuilderMap(), other.getJsonBuilderMap()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getConfigMaster());
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUriConfigId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUriVersionId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getConfig());
    hash = hash * 31 + JodaBeanUtils.hashCode(getVersioned());
    hash = hash * 31 + JodaBeanUtils.hashCode(getTypeMap());
    hash = hash * 31 + JodaBeanUtils.hashCode(getJsonBuilderMap());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("WebConfigData{");
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
    buf.append("configMaster").append('=').append(JodaBeanUtils.toString(getConfigMaster())).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType())).append(',').append(' ');
    buf.append("uriConfigId").append('=').append(JodaBeanUtils.toString(getUriConfigId())).append(',').append(' ');
    buf.append("uriVersionId").append('=').append(JodaBeanUtils.toString(getUriVersionId())).append(',').append(' ');
    buf.append("config").append('=').append(JodaBeanUtils.toString(getConfig())).append(',').append(' ');
    buf.append("versioned").append('=').append(JodaBeanUtils.toString(getVersioned())).append(',').append(' ');
    buf.append("typeMap").append('=').append(JodaBeanUtils.toString(getTypeMap())).append(',').append(' ');
    buf.append("jsonBuilderMap").append('=').append(JodaBeanUtils.toString(getJsonBuilderMap())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code WebConfigData}.
   */
  public static class Meta extends WebPerRequestData.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code configMaster} property.
     */
    private final MetaProperty<ConfigMaster> _configMaster = DirectMetaProperty.ofReadWrite(
        this, "configMaster", WebConfigData.class, ConfigMaster.class);
    /**
     * The meta-property for the {@code type} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<?>> _type = DirectMetaProperty.ofReadWrite(
        this, "type", WebConfigData.class, (Class) Class.class);
    /**
     * The meta-property for the {@code uriConfigId} property.
     */
    private final MetaProperty<String> _uriConfigId = DirectMetaProperty.ofReadWrite(
        this, "uriConfigId", WebConfigData.class, String.class);
    /**
     * The meta-property for the {@code uriVersionId} property.
     */
    private final MetaProperty<String> _uriVersionId = DirectMetaProperty.ofReadWrite(
        this, "uriVersionId", WebConfigData.class, String.class);
    /**
     * The meta-property for the {@code config} property.
     */
    private final MetaProperty<ConfigDocument> _config = DirectMetaProperty.ofReadWrite(
        this, "config", WebConfigData.class, ConfigDocument.class);
    /**
     * The meta-property for the {@code versioned} property.
     */
    private final MetaProperty<ConfigDocument> _versioned = DirectMetaProperty.ofReadWrite(
        this, "versioned", WebConfigData.class, ConfigDocument.class);
    /**
     * The meta-property for the {@code typeMap} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<BiMap<String, Class<?>>> _typeMap = DirectMetaProperty.ofReadWrite(
        this, "typeMap", WebConfigData.class, (Class) BiMap.class);
    /**
     * The meta-property for the {@code jsonBuilderMap} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<Class<?>, JSONBuilder<?>>> _jsonBuilderMap = DirectMetaProperty.ofReadWrite(
        this, "jsonBuilderMap", WebConfigData.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "configMaster",
        "type",
        "uriConfigId",
        "uriVersionId",
        "config",
        "versioned",
        "typeMap",
        "jsonBuilderMap");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 10395716:  // configMaster
          return _configMaster;
        case 3575610:  // type
          return _type;
        case -2037268087:  // uriConfigId
          return _uriConfigId;
        case 666567687:  // uriVersionId
          return _uriVersionId;
        case -1354792126:  // config
          return _config;
        case -1407102089:  // versioned
          return _versioned;
        case -853107774:  // typeMap
          return _typeMap;
        case 1444420297:  // jsonBuilderMap
          return _jsonBuilderMap;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends WebConfigData> builder() {
      return new DirectBeanBuilder<WebConfigData>(new WebConfigData());
    }

    @Override
    public Class<? extends WebConfigData> beanType() {
      return WebConfigData.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code configMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ConfigMaster> configMaster() {
      return _configMaster;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Class<?>> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code uriConfigId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> uriConfigId() {
      return _uriConfigId;
    }

    /**
     * The meta-property for the {@code uriVersionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> uriVersionId() {
      return _uriVersionId;
    }

    /**
     * The meta-property for the {@code config} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ConfigDocument> config() {
      return _config;
    }

    /**
     * The meta-property for the {@code versioned} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ConfigDocument> versioned() {
      return _versioned;
    }

    /**
     * The meta-property for the {@code typeMap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BiMap<String, Class<?>>> typeMap() {
      return _typeMap;
    }

    /**
     * The meta-property for the {@code jsonBuilderMap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<Class<?>, JSONBuilder<?>>> jsonBuilderMap() {
      return _jsonBuilderMap;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 10395716:  // configMaster
          return ((WebConfigData) bean).getConfigMaster();
        case 3575610:  // type
          return ((WebConfigData) bean).getType();
        case -2037268087:  // uriConfigId
          return ((WebConfigData) bean).getUriConfigId();
        case 666567687:  // uriVersionId
          return ((WebConfigData) bean).getUriVersionId();
        case -1354792126:  // config
          return ((WebConfigData) bean).getConfig();
        case -1407102089:  // versioned
          return ((WebConfigData) bean).getVersioned();
        case -853107774:  // typeMap
          return ((WebConfigData) bean).getTypeMap();
        case 1444420297:  // jsonBuilderMap
          return ((WebConfigData) bean).getJsonBuilderMap();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 10395716:  // configMaster
          ((WebConfigData) bean).setConfigMaster((ConfigMaster) newValue);
          return;
        case 3575610:  // type
          ((WebConfigData) bean).setType((Class<?>) newValue);
          return;
        case -2037268087:  // uriConfigId
          ((WebConfigData) bean).setUriConfigId((String) newValue);
          return;
        case 666567687:  // uriVersionId
          ((WebConfigData) bean).setUriVersionId((String) newValue);
          return;
        case -1354792126:  // config
          ((WebConfigData) bean).setConfig((ConfigDocument) newValue);
          return;
        case -1407102089:  // versioned
          ((WebConfigData) bean).setVersioned((ConfigDocument) newValue);
          return;
        case -853107774:  // typeMap
          ((WebConfigData) bean).setTypeMap((BiMap<String, Class<?>>) newValue);
          return;
        case 1444420297:  // jsonBuilderMap
          ((WebConfigData) bean).setJsonBuilderMap((Map<Class<?>, JSONBuilder<?>>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((WebConfigData) bean)._typeMap, "typeMap");
      JodaBeanUtils.notNull(((WebConfigData) bean)._jsonBuilderMap, "jsonBuilderMap");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
