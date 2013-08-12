/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.infrastructure;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.util.ResourceUtils;
import net.sf.ehcache.CacheManager;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Component Factory for an Ehcache CacheManager using the standard OG ehcache config found on the classpath.
 *
 * The cache is shared by default, but this can be overidden.
 * The config is plucked from the classpath by default, but can be explicitly specified.
 *
 * The shutdown method is registered for lifecycleStop.
 */
@BeanDefinition
public class CacheManagerComponentFactory extends AbstractComponentFactory {

  static final String DEFAULT_EHCACHE_CONFIG = "classpath:common/default-ehcache.xml";

  @PropertyDefinition(validate = "notNull")
  private String _classifier;

  @PropertyDefinition
  private boolean _shared = true;

  @PropertyDefinition(validate = "notNull")
  private String _configLocation = DEFAULT_EHCACHE_CONFIG;

  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {

    final ComponentInfo info = new ComponentInfo(CacheManager.class, getClassifier());
    final CacheManager component = initCacheManager();
    repo.registerComponent(info, component);
    repo.registerLifecycleStop(component, "shutdown");

  }

  protected final CacheManager initCacheManager() throws IOException {

    EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
    factoryBean.setShared(isShared());
    factoryBean.setConfigLocation(ResourceUtils.createResource(getConfigLocation()));
    factoryBean.afterPropertiesSet();

    return factoryBean.getObject();

  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CacheManagerComponentFactory}.
   * @return the meta-bean, not null
   */
  public static CacheManagerComponentFactory.Meta meta() {
    return CacheManagerComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CacheManagerComponentFactory.Meta.INSTANCE);
  }

  @Override
  public CacheManagerComponentFactory.Meta metaBean() {
    return CacheManagerComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -903566235:  // shared
        return isShared();
      case -1277483753:  // configLocation
        return getConfigLocation();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -903566235:  // shared
        setShared((Boolean) newValue);
        return;
      case -1277483753:  // configLocation
        setConfigLocation((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_configLocation, "configLocation");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CacheManagerComponentFactory other = (CacheManagerComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(isShared(), other.isShared()) &&
          JodaBeanUtils.equal(getConfigLocation(), other.getConfigLocation()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(isShared());
    hash += hash * 31 + JodaBeanUtils.hashCode(getConfigLocation());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the shared.
   * @return the value of the property
   */
  public boolean isShared() {
    return _shared;
  }

  /**
   * Sets the shared.
   * @param shared  the new value of the property
   */
  public void setShared(boolean shared) {
    this._shared = shared;
  }

  /**
   * Gets the the {@code shared} property.
   * @return the property, not null
   */
  public final Property<Boolean> shared() {
    return metaBean().shared().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the configLocation.
   * @return the value of the property, not null
   */
  public String getConfigLocation() {
    return _configLocation;
  }

  /**
   * Sets the configLocation.
   * @param configLocation  the new value of the property, not null
   */
  public void setConfigLocation(String configLocation) {
    JodaBeanUtils.notNull(configLocation, "configLocation");
    this._configLocation = configLocation;
  }

  /**
   * Gets the the {@code configLocation} property.
   * @return the property, not null
   */
  public final Property<String> configLocation() {
    return metaBean().configLocation().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CacheManagerComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", CacheManagerComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code shared} property.
     */
    private final MetaProperty<Boolean> _shared = DirectMetaProperty.ofReadWrite(
        this, "shared", CacheManagerComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code configLocation} property.
     */
    private final MetaProperty<String> _configLocation = DirectMetaProperty.ofReadWrite(
        this, "configLocation", CacheManagerComponentFactory.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "shared",
        "configLocation");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -903566235:  // shared
          return _shared;
        case -1277483753:  // configLocation
          return _configLocation;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CacheManagerComponentFactory> builder() {
      return new DirectBeanBuilder<CacheManagerComponentFactory>(new CacheManagerComponentFactory());
    }

    @Override
    public Class<? extends CacheManagerComponentFactory> beanType() {
      return CacheManagerComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code shared} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> shared() {
      return _shared;
    }

    /**
     * The meta-property for the {@code configLocation} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> configLocation() {
      return _configLocation;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
