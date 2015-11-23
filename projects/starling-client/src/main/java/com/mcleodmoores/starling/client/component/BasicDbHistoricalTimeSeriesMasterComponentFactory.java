package com.mcleodmoores.starling.client.component;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.component.factory.master.OGSchema;
import com.opengamma.core.change.BasicChangeManager;
import com.opengamma.master.convention.ConventionMaster;
import com.opengamma.master.convention.impl.DataTrackingConventionMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.impl.DataTrackingHistoricalTimeSeriesMaster;
import com.opengamma.masterdb.convention.DbConventionBeanMaster;
import com.opengamma.masterdb.historicaltimeseries.DbHistoricalTimeSeriesMaster;
import com.opengamma.util.db.DbConnector;
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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jim on 12/08/2015.
 */
@BeanDefinition
public class BasicDbHistoricalTimeSeriesMasterComponentFactory extends AbstractComponentFactory {
  private static final String SCHEMA_NAME = "hts";

  /**
   * The classifier.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;

  /**
   * The Database connector.
   */
  @PropertyDefinition(validate = "notNull")
  private DbConnector _dbConnector;

  /**
   * Whether to enforce the schema version.
   */
  @PropertyDefinition
  private boolean _enforceSchemaVersion;

  /**
   * Whether to auto-manage the schema.
   */
  @PropertyDefinition
  private boolean _autoSchemaManagement;

  /**
   * Maximum number of retries for queries.
   */
  @PropertyDefinition
  private Integer _maxRetries;

  /**
   * Tracking mode
   */
  @PropertyDefinition
  private boolean _trackingMode;

  /**
   * Unique ID scheme
   */
  @PropertyDefinition
  private String _uniqueIdScheme;

  @Override
  public void init(final ComponentRepository repo, final LinkedHashMap<String, String> configuration) {
    ComponentInfo info = new ComponentInfo(HistoricalTimeSeriesMaster.class, getClassifier());
    info.addAttribute(ComponentInfoAttributes.LEVEL, 1);
    DbHistoricalTimeSeriesMaster dbMaster = new DbHistoricalTimeSeriesMaster(getDbConnector());

    if (getUniqueIdScheme() != null) {
      dbMaster.setUniqueIdScheme(getUniqueIdScheme());
    }
    dbMaster.setChangeManager(new BasicChangeManager());

    String resolvedScheme = dbMaster.getUniqueIdScheme();

    if (getMaxRetries() != null) {
      dbMaster.setMaxRetries(getMaxRetries());
    }

    OGSchema ogSchema = OGSchema.on(getDbConnector())
        .enforcingSchemaVersion(isEnforceSchemaVersion())
        .withAutoSchemaManagement(isAutoSchemaManagement())
        .build();

    ogSchema.checkSchema(dbMaster.getSchemaVersion(), SCHEMA_NAME);

    HistoricalTimeSeriesMaster master = dbMaster;
    if (isTrackingMode()) {
      master = new DataTrackingHistoricalTimeSeriesMaster(dbMaster);
    }

    info.addAttribute(ComponentInfoAttributes.UNIQUE_ID_SCHEME, resolvedScheme);
    repo.registerComponent(info, master);
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BasicDbHistoricalTimeSeriesMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static BasicDbHistoricalTimeSeriesMasterComponentFactory.Meta meta() {
    return BasicDbHistoricalTimeSeriesMasterComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(BasicDbHistoricalTimeSeriesMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public BasicDbHistoricalTimeSeriesMasterComponentFactory.Meta metaBean() {
    return BasicDbHistoricalTimeSeriesMasterComponentFactory.Meta.INSTANCE;
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
   * Gets the Database connector.
   * @return the value of the property, not null
   */
  public DbConnector getDbConnector() {
    return _dbConnector;
  }

  /**
   * Sets the Database connector.
   * @param dbConnector  the new value of the property, not null
   */
  public void setDbConnector(DbConnector dbConnector) {
    JodaBeanUtils.notNull(dbConnector, "dbConnector");
    this._dbConnector = dbConnector;
  }

  /**
   * Gets the the {@code dbConnector} property.
   * @return the property, not null
   */
  public final Property<DbConnector> dbConnector() {
    return metaBean().dbConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to enforce the schema version.
   * @return the value of the property
   */
  public boolean isEnforceSchemaVersion() {
    return _enforceSchemaVersion;
  }

  /**
   * Sets whether to enforce the schema version.
   * @param enforceSchemaVersion  the new value of the property
   */
  public void setEnforceSchemaVersion(boolean enforceSchemaVersion) {
    this._enforceSchemaVersion = enforceSchemaVersion;
  }

  /**
   * Gets the the {@code enforceSchemaVersion} property.
   * @return the property, not null
   */
  public final Property<Boolean> enforceSchemaVersion() {
    return metaBean().enforceSchemaVersion().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to auto-manage the schema.
   * @return the value of the property
   */
  public boolean isAutoSchemaManagement() {
    return _autoSchemaManagement;
  }

  /**
   * Sets whether to auto-manage the schema.
   * @param autoSchemaManagement  the new value of the property
   */
  public void setAutoSchemaManagement(boolean autoSchemaManagement) {
    this._autoSchemaManagement = autoSchemaManagement;
  }

  /**
   * Gets the the {@code autoSchemaManagement} property.
   * @return the property, not null
   */
  public final Property<Boolean> autoSchemaManagement() {
    return metaBean().autoSchemaManagement().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets maximum number of retries for queries.
   * @return the value of the property
   */
  public Integer getMaxRetries() {
    return _maxRetries;
  }

  /**
   * Sets maximum number of retries for queries.
   * @param maxRetries  the new value of the property
   */
  public void setMaxRetries(Integer maxRetries) {
    this._maxRetries = maxRetries;
  }

  /**
   * Gets the the {@code maxRetries} property.
   * @return the property, not null
   */
  public final Property<Integer> maxRetries() {
    return metaBean().maxRetries().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets tracking mode
   * @return the value of the property
   */
  public boolean isTrackingMode() {
    return _trackingMode;
  }

  /**
   * Sets tracking mode
   * @param trackingMode  the new value of the property
   */
  public void setTrackingMode(boolean trackingMode) {
    this._trackingMode = trackingMode;
  }

  /**
   * Gets the the {@code trackingMode} property.
   * @return the property, not null
   */
  public final Property<Boolean> trackingMode() {
    return metaBean().trackingMode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets unique ID scheme
   * @return the value of the property
   */
  public String getUniqueIdScheme() {
    return _uniqueIdScheme;
  }

  /**
   * Sets unique ID scheme
   * @param uniqueIdScheme  the new value of the property
   */
  public void setUniqueIdScheme(String uniqueIdScheme) {
    this._uniqueIdScheme = uniqueIdScheme;
  }

  /**
   * Gets the the {@code uniqueIdScheme} property.
   * @return the property, not null
   */
  public final Property<String> uniqueIdScheme() {
    return metaBean().uniqueIdScheme().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public BasicDbHistoricalTimeSeriesMasterComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BasicDbHistoricalTimeSeriesMasterComponentFactory other = (BasicDbHistoricalTimeSeriesMasterComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getDbConnector(), other.getDbConnector()) &&
          (isEnforceSchemaVersion() == other.isEnforceSchemaVersion()) &&
          (isAutoSchemaManagement() == other.isAutoSchemaManagement()) &&
          JodaBeanUtils.equal(getMaxRetries(), other.getMaxRetries()) &&
          (isTrackingMode() == other.isTrackingMode()) &&
          JodaBeanUtils.equal(getUniqueIdScheme(), other.getUniqueIdScheme()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash = hash * 31 + JodaBeanUtils.hashCode(getDbConnector());
    hash = hash * 31 + JodaBeanUtils.hashCode(isEnforceSchemaVersion());
    hash = hash * 31 + JodaBeanUtils.hashCode(isAutoSchemaManagement());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaxRetries());
    hash = hash * 31 + JodaBeanUtils.hashCode(isTrackingMode());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUniqueIdScheme());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("BasicDbHistoricalTimeSeriesMasterComponentFactory{");
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
    buf.append("dbConnector").append('=').append(JodaBeanUtils.toString(getDbConnector())).append(',').append(' ');
    buf.append("enforceSchemaVersion").append('=').append(JodaBeanUtils.toString(isEnforceSchemaVersion())).append(',').append(' ');
    buf.append("autoSchemaManagement").append('=').append(JodaBeanUtils.toString(isAutoSchemaManagement())).append(',').append(' ');
    buf.append("maxRetries").append('=').append(JodaBeanUtils.toString(getMaxRetries())).append(',').append(' ');
    buf.append("trackingMode").append('=').append(JodaBeanUtils.toString(isTrackingMode())).append(',').append(' ');
    buf.append("uniqueIdScheme").append('=').append(JodaBeanUtils.toString(getUniqueIdScheme())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BasicDbHistoricalTimeSeriesMasterComponentFactory}.
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
        this, "classifier", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code dbConnector} property.
     */
    private final MetaProperty<DbConnector> _dbConnector = DirectMetaProperty.ofReadWrite(
        this, "dbConnector", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, DbConnector.class);
    /**
     * The meta-property for the {@code enforceSchemaVersion} property.
     */
    private final MetaProperty<Boolean> _enforceSchemaVersion = DirectMetaProperty.ofReadWrite(
        this, "enforceSchemaVersion", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code autoSchemaManagement} property.
     */
    private final MetaProperty<Boolean> _autoSchemaManagement = DirectMetaProperty.ofReadWrite(
        this, "autoSchemaManagement", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code maxRetries} property.
     */
    private final MetaProperty<Integer> _maxRetries = DirectMetaProperty.ofReadWrite(
        this, "maxRetries", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, Integer.class);
    /**
     * The meta-property for the {@code trackingMode} property.
     */
    private final MetaProperty<Boolean> _trackingMode = DirectMetaProperty.ofReadWrite(
        this, "trackingMode", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code uniqueIdScheme} property.
     */
    private final MetaProperty<String> _uniqueIdScheme = DirectMetaProperty.ofReadWrite(
        this, "uniqueIdScheme", BasicDbHistoricalTimeSeriesMasterComponentFactory.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "dbConnector",
        "enforceSchemaVersion",
        "autoSchemaManagement",
        "maxRetries",
        "trackingMode",
        "uniqueIdScheme");

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
        case 39794031:  // dbConnector
          return _dbConnector;
        case 2128193333:  // enforceSchemaVersion
          return _enforceSchemaVersion;
        case 1236703379:  // autoSchemaManagement
          return _autoSchemaManagement;
        case -2022653118:  // maxRetries
          return _maxRetries;
        case -1884120838:  // trackingMode
          return _trackingMode;
        case -1737146991:  // uniqueIdScheme
          return _uniqueIdScheme;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BasicDbHistoricalTimeSeriesMasterComponentFactory> builder() {
      return new DirectBeanBuilder<BasicDbHistoricalTimeSeriesMasterComponentFactory>(new BasicDbHistoricalTimeSeriesMasterComponentFactory());
    }

    @Override
    public Class<? extends BasicDbHistoricalTimeSeriesMasterComponentFactory> beanType() {
      return BasicDbHistoricalTimeSeriesMasterComponentFactory.class;
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
     * The meta-property for the {@code dbConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbConnector> dbConnector() {
      return _dbConnector;
    }

    /**
     * The meta-property for the {@code enforceSchemaVersion} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> enforceSchemaVersion() {
      return _enforceSchemaVersion;
    }

    /**
     * The meta-property for the {@code autoSchemaManagement} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> autoSchemaManagement() {
      return _autoSchemaManagement;
    }

    /**
     * The meta-property for the {@code maxRetries} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> maxRetries() {
      return _maxRetries;
    }

    /**
     * The meta-property for the {@code trackingMode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> trackingMode() {
      return _trackingMode;
    }

    /**
     * The meta-property for the {@code uniqueIdScheme} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> uniqueIdScheme() {
      return _uniqueIdScheme;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).getClassifier();
        case 39794031:  // dbConnector
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).getDbConnector();
        case 2128193333:  // enforceSchemaVersion
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).isEnforceSchemaVersion();
        case 1236703379:  // autoSchemaManagement
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).isAutoSchemaManagement();
        case -2022653118:  // maxRetries
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).getMaxRetries();
        case -1884120838:  // trackingMode
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).isTrackingMode();
        case -1737146991:  // uniqueIdScheme
          return ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).getUniqueIdScheme();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setClassifier((String) newValue);
          return;
        case 39794031:  // dbConnector
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setDbConnector((DbConnector) newValue);
          return;
        case 2128193333:  // enforceSchemaVersion
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setEnforceSchemaVersion((Boolean) newValue);
          return;
        case 1236703379:  // autoSchemaManagement
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setAutoSchemaManagement((Boolean) newValue);
          return;
        case -2022653118:  // maxRetries
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setMaxRetries((Integer) newValue);
          return;
        case -1884120838:  // trackingMode
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setTrackingMode((Boolean) newValue);
          return;
        case -1737146991:  // uniqueIdScheme
          ((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean).setUniqueIdScheme((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean)._classifier, "classifier");
      JodaBeanUtils.notNull(((BasicDbHistoricalTimeSeriesMasterComponentFactory) bean)._dbConnector, "dbConnector");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}