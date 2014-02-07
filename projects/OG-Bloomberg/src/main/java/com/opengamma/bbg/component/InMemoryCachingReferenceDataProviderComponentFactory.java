/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.bbg.component;

import java.util.LinkedHashMap;
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

import com.opengamma.bbg.referencedata.ReferenceDataProvider;
import com.opengamma.bbg.referencedata.impl.InMemoryCachingReferenceDataProvider;
import com.opengamma.bbg.referencedata.impl.NoneFoundReferenceDataProvider;
import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;

/**
 * Wraps an underlying reference data provider with {@link InMemoryCachingReferenceDataProviderComponentFactory}
 */
@BeanDefinition
public class InMemoryCachingReferenceDataProviderComponentFactory extends AbstractComponentFactory {

  @PropertyDefinition
  private String _classifier;
  
  @PropertyDefinition(get = "manual")
  private ReferenceDataProvider _underlying;
  
  
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
    InMemoryCachingReferenceDataProvider provider = new InMemoryCachingReferenceDataProvider(getUnderlying());
    
    ComponentInfo info = new ComponentInfo(ReferenceDataProvider.class, _classifier);
    repo.registerComponent(info, provider);
  }

  public ReferenceDataProvider getUnderlying() {
    if (_underlying == null) {
      return new NoneFoundReferenceDataProvider();
    } else {
      return _underlying;
    }
  }
  
  
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code InMemoryCachingReferenceDataProviderComponentFactory}.
   * @return the meta-bean, not null
   */
  public static InMemoryCachingReferenceDataProviderComponentFactory.Meta meta() {
    return InMemoryCachingReferenceDataProviderComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(InMemoryCachingReferenceDataProviderComponentFactory.Meta.INSTANCE);
  }

  @Override
  public InMemoryCachingReferenceDataProviderComponentFactory.Meta metaBean() {
    return InMemoryCachingReferenceDataProviderComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier.
   * @return the value of the property
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier.
   * @param classifier  the new value of the property
   */
  public void setClassifier(String classifier) {
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
   * Sets the underlying.
   * @param underlying  the new value of the property
   */
  public void setUnderlying(ReferenceDataProvider underlying) {
    this._underlying = underlying;
  }

  /**
   * Gets the the {@code underlying} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> underlying() {
    return metaBean().underlying().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public InMemoryCachingReferenceDataProviderComponentFactory clone() {
    return (InMemoryCachingReferenceDataProviderComponentFactory) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      InMemoryCachingReferenceDataProviderComponentFactory other = (InMemoryCachingReferenceDataProviderComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getUnderlying(), other.getUnderlying()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlying());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("InMemoryCachingReferenceDataProviderComponentFactory{");
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
    buf.append("classifier").append('=').append(JodaBeanUtils.toString(getClassifier())).append(',').append(' ');
    buf.append("underlying").append('=').append(JodaBeanUtils.toString(getUnderlying())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code InMemoryCachingReferenceDataProviderComponentFactory}.
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
        this, "classifier", InMemoryCachingReferenceDataProviderComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<ReferenceDataProvider> _underlying = DirectMetaProperty.ofReadWrite(
        this, "underlying", InMemoryCachingReferenceDataProviderComponentFactory.class, ReferenceDataProvider.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "underlying");

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
        case -1770633379:  // underlying
          return _underlying;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends InMemoryCachingReferenceDataProviderComponentFactory> builder() {
      return new DirectBeanBuilder<InMemoryCachingReferenceDataProviderComponentFactory>(new InMemoryCachingReferenceDataProviderComponentFactory());
    }

    @Override
    public Class<? extends InMemoryCachingReferenceDataProviderComponentFactory> beanType() {
      return InMemoryCachingReferenceDataProviderComponentFactory.class;
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
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> underlying() {
      return _underlying;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return ((InMemoryCachingReferenceDataProviderComponentFactory) bean).getClassifier();
        case -1770633379:  // underlying
          return ((InMemoryCachingReferenceDataProviderComponentFactory) bean).getUnderlying();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          ((InMemoryCachingReferenceDataProviderComponentFactory) bean).setClassifier((String) newValue);
          return;
        case -1770633379:  // underlying
          ((InMemoryCachingReferenceDataProviderComponentFactory) bean).setUnderlying((ReferenceDataProvider) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
