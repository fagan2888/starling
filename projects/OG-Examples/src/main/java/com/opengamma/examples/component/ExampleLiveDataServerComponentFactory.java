/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.examples.component;

import java.util.Collection;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.springframework.core.io.Resource;

import com.google.common.collect.ImmutableList;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.livedata.AbstractStandardLiveDataServerComponentFactory;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.examples.livedata.ExampleJmsTopicNameResolver;
import com.opengamma.examples.livedata.ExampleLiveDataServer;
import com.opengamma.examples.livedata.ExampleLiveDataServerMBean;
import com.opengamma.examples.livedata.NormalizationRules;
import com.opengamma.examples.livedata.SyntheticIdResolver;
import com.opengamma.livedata.normalization.NormalizationRuleSet;
import com.opengamma.livedata.normalization.StandardRuleResolver;
import com.opengamma.livedata.normalization.StandardRules;
import com.opengamma.livedata.resolver.DefaultDistributionSpecificationResolver;
import com.opengamma.livedata.server.StandardLiveDataServer;
import com.opengamma.livedata.server.distribution.JmsSenderFactory;
import com.opengamma.provider.livedata.LiveDataMetaData;
import com.opengamma.provider.livedata.LiveDataServerTypes;

/**
 * Component factory for producing simulated live data.
 */
@BeanDefinition
public class ExampleLiveDataServerComponentFactory extends AbstractStandardLiveDataServerComponentFactory {

  /**
   * Name to use.
   */
  private static final String SIMULATED_LIVE_SOURCE_NAME = "Simulated live market data";

  /**
   * The JMS connector.
   */
  @PropertyDefinition(validate = "notNull")
  private Resource _simulatedData;

  //-------------------------------------------------------------------------
  @Override
  protected StandardLiveDataServer initServer(ComponentRepository repo) {
    ExampleLiveDataServer server = new ExampleLiveDataServer(getSimulatedData());
    
    Collection<NormalizationRuleSet> rules = ImmutableList.of(StandardRules.getNoNormalization(), NormalizationRules.getMarketValueNormalization());
    DefaultDistributionSpecificationResolver distSpecResolver = new DefaultDistributionSpecificationResolver(
        new SyntheticIdResolver(),
        new StandardRuleResolver(rules),
        new ExampleJmsTopicNameResolver());
    server.setDistributionSpecificationResolver(distSpecResolver);
    
    JmsSenderFactory senderFactory = new JmsSenderFactory(getJmsConnector());
    server.setMarketDataSenderFactory(senderFactory);
    
    repo.registerMBean(new ExampleLiveDataServerMBean(server));
    return server;
  }

  @Override
  protected LiveDataMetaData createMetaData(ComponentRepository repo) {
    return new LiveDataMetaData(ImmutableList.of(ExternalSchemes.OG_SYNTHETIC_TICKER), LiveDataServerTypes.STANDARD, SIMULATED_LIVE_SOURCE_NAME);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExampleLiveDataServerComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ExampleLiveDataServerComponentFactory.Meta meta() {
    return ExampleLiveDataServerComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(ExampleLiveDataServerComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ExampleLiveDataServerComponentFactory.Meta metaBean() {
    return ExampleLiveDataServerComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -349682038:  // simulatedData
        return getSimulatedData();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -349682038:  // simulatedData
        setSimulatedData((Resource) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_simulatedData, "simulatedData");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExampleLiveDataServerComponentFactory other = (ExampleLiveDataServerComponentFactory) obj;
      return JodaBeanUtils.equal(getSimulatedData(), other.getSimulatedData()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getSimulatedData());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS connector.
   * @return the value of the property, not null
   */
  public Resource getSimulatedData() {
    return _simulatedData;
  }

  /**
   * Sets the JMS connector.
   * @param simulatedData  the new value of the property, not null
   */
  public void setSimulatedData(Resource simulatedData) {
    JodaBeanUtils.notNull(simulatedData, "simulatedData");
    this._simulatedData = simulatedData;
  }

  /**
   * Gets the the {@code simulatedData} property.
   * @return the property, not null
   */
  public final Property<Resource> simulatedData() {
    return metaBean().simulatedData().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExampleLiveDataServerComponentFactory}.
   */
  public static class Meta extends AbstractStandardLiveDataServerComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code simulatedData} property.
     */
    private final MetaProperty<Resource> _simulatedData = DirectMetaProperty.ofReadWrite(
        this, "simulatedData", ExampleLiveDataServerComponentFactory.class, Resource.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "simulatedData");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -349682038:  // simulatedData
          return _simulatedData;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExampleLiveDataServerComponentFactory> builder() {
      return new DirectBeanBuilder<ExampleLiveDataServerComponentFactory>(new ExampleLiveDataServerComponentFactory());
    }

    @Override
    public Class<? extends ExampleLiveDataServerComponentFactory> beanType() {
      return ExampleLiveDataServerComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code simulatedData} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Resource> simulatedData() {
      return _simulatedData;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}