/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fudgemsg.FudgeField;
import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;
import org.fudgemsg.types.IndicatorType;
import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.marketdatasnapshot.ValueSnapshot;
import com.opengamma.core.marketdatasnapshot.VolatilityCubeSnapshot;
import com.opengamma.util.tuple.Triple;

/**
 *
 */
@BeanDefinition
public class ManageableVolatilityCubeSnapshot implements Bean, VolatilityCubeSnapshot {

  /**
   * The values in the snapshot.
   */
  @PropertyDefinition(validate = "notNull")
  private Map<Triple<Object, Object, Object>, ValueSnapshot> _values;

  /**
   * Creates a Fudge representation of the snapshot:
   * <pre>
   *   message {
   *     message { // map
   *       repeated Pair key = 1;
   *       repeated ValueSnapshot value = 2;
   *     } values;
   *   }
   * </pre>
   *
   * @param serializer Fudge serialization context, not null
   * @return the message representation of this snapshot
   */
  public FudgeMsg toFudgeMsg(final FudgeSerializer serializer) {
    final MutableFudgeMsg ret = serializer.newMessage();
    // TODO: this should not be adding it's own class header; the caller should be doing that, or this be registered as a generic builder for VolatilityCubeSnapshot and that class name be added
    FudgeSerializer.addClassHeader(ret, ManageableVolatilityCubeSnapshot.class);
    final MutableFudgeMsg valuesMsg = serializer.newMessage();
    if (_values != null) {
      for (final Entry<Triple<Object, Object, Object>, ValueSnapshot> entry : _values.entrySet()) {
        serializer.addToMessage(valuesMsg, null, 1, entry.getKey());
        if (entry.getValue() == null) {
          valuesMsg.add(2, IndicatorType.INSTANCE);
        } else {
          serializer.addToMessage(valuesMsg, null, 2, entry.getValue());
        }
      }
    }
    ret.add("values", valuesMsg);
    return ret;
  }

  // TODO: externalize the message representation to a Fudge builder

  /**
   * Creates a snapshot object from a Fudge message representation. See {@link #toFudgeMsg}
   * for the message format.
   *
   * @param deserializer the Fudge deserialization context, not null
   * @param msg message containing the snapshot representation, not null
   * @return a snapshot object
   */
  @SuppressWarnings("unchecked")
  public static ManageableVolatilityCubeSnapshot fromFudgeMsg(final FudgeDeserializer deserializer, final FudgeMsg msg) {
    final HashMap<Triple<Object, Object, Object>, ValueSnapshot> values = new HashMap<>();
    Triple<Object, Object, Object> key = null;
    for (final FudgeField fudgeField : msg.getMessage("values")) {
      final Integer ordinal = fudgeField.getOrdinal();
      if (ordinal == null) {
        continue;
      }
      final int intValue = ordinal.intValue();
      if (intValue == 1) {
        key = deserializer.fieldValueToObject(Triple.class, fudgeField);
      } else if (intValue == 2) {
        final ValueSnapshot value = deserializer.fieldValueToObject(ValueSnapshot.class, fudgeField);
        values.put(key, value);
        key = null;
      }
    }
    final ManageableVolatilityCubeSnapshot ret = new ManageableVolatilityCubeSnapshot();
    ret.setValues(values);
    return ret;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ManageableVolatilityCubeSnapshot}.
   * @return the meta-bean, not null
   */
  public static ManageableVolatilityCubeSnapshot.Meta meta() {
    return ManageableVolatilityCubeSnapshot.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ManageableVolatilityCubeSnapshot.Meta.INSTANCE);
  }

  @Override
  public ManageableVolatilityCubeSnapshot.Meta metaBean() {
    return ManageableVolatilityCubeSnapshot.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the values in the snapshot.
   * @return the value of the property, not null
   */
  public Map<Triple<Object, Object, Object>, ValueSnapshot> getValues() {
    return _values;
  }

  /**
   * Sets the values in the snapshot.
   * @param values  the new value of the property, not null
   */
  public void setValues(Map<Triple<Object, Object, Object>, ValueSnapshot> values) {
    JodaBeanUtils.notNull(values, "values");
    this._values = values;
  }

  /**
   * Gets the the {@code values} property.
   * @return the property, not null
   */
  public final Property<Map<Triple<Object, Object, Object>, ValueSnapshot>> values() {
    return metaBean().values().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ManageableVolatilityCubeSnapshot clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ManageableVolatilityCubeSnapshot other = (ManageableVolatilityCubeSnapshot) obj;
      return JodaBeanUtils.equal(getValues(), other.getValues());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getValues());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ManageableVolatilityCubeSnapshot{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("values").append('=').append(JodaBeanUtils.toString(getValues())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ManageableVolatilityCubeSnapshot}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code values} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<Triple<Object, Object, Object>, ValueSnapshot>> _values = DirectMetaProperty.ofReadWrite(
        this, "values", ManageableVolatilityCubeSnapshot.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "values");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          return _values;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ManageableVolatilityCubeSnapshot> builder() {
      return new DirectBeanBuilder<ManageableVolatilityCubeSnapshot>(new ManageableVolatilityCubeSnapshot());
    }

    @Override
    public Class<? extends ManageableVolatilityCubeSnapshot> beanType() {
      return ManageableVolatilityCubeSnapshot.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code values} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<Triple<Object, Object, Object>, ValueSnapshot>> values() {
      return _values;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          return ((ManageableVolatilityCubeSnapshot) bean).getValues();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          ((ManageableVolatilityCubeSnapshot) bean).setValues((Map<Triple<Object, Object, Object>, ValueSnapshot>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ManageableVolatilityCubeSnapshot) bean)._values, "values");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
