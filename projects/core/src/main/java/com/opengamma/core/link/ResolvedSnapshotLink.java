/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.link;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.marketdatasnapshot.NamedSnapshot;
import com.opengamma.util.ArgumentChecker;

/**
 * Represents a link to a Snapshot object using an actual instance. When the resolve
 * method is called, the embedded object is then returned.
 *
 * @param <S> type of the snapshot
 */
@BeanDefinition
public final class ResolvedSnapshotLink<S extends NamedSnapshot>
    extends SnapshotLink<S>
    implements ImmutableBean {

  /**
   * The snapshot instance.
   */
  @PropertyDefinition(validate = "notNull")
  private final S _value;

  /**
   * Create the link, embedding the provided object.
   *
   * @param value the snapshot object to be embedded
   */
  @ImmutableConstructor
  ResolvedSnapshotLink(final S value) {
    _value = ArgumentChecker.notNull(value, "snapshot");
  }

  @Override
  public S resolve() {
    return _value;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Class<S> getTargetType() {
    return (Class<S>) _value.getClass();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedSnapshotLink}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static ResolvedSnapshotLink.Meta meta() {
    return ResolvedSnapshotLink.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code ResolvedSnapshotLink}.
   * @param <R>  the bean's generic type
   * @param cls  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R extends NamedSnapshot> ResolvedSnapshotLink.Meta<R> metaResolvedSnapshotLink(Class<R> cls) {
    return ResolvedSnapshotLink.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedSnapshotLink.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @param <S>  the type
   * @return the builder, not null
   */
  public static <S extends NamedSnapshot> ResolvedSnapshotLink.Builder<S> builder() {
    return new ResolvedSnapshotLink.Builder<S>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ResolvedSnapshotLink.Meta<S> metaBean() {
    return ResolvedSnapshotLink.Meta.INSTANCE;
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
   * Gets the snapshot instance.
   * @return the value of the property, not null
   */
  public S getValue() {
    return _value;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder<S> toBuilder() {
    return new Builder<S>(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedSnapshotLink<?> other = (ResolvedSnapshotLink<?>) obj;
      return JodaBeanUtils.equal(_value, other._value);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_value);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ResolvedSnapshotLink{");
    buf.append("value").append('=').append(JodaBeanUtils.toString(_value));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedSnapshotLink}.
   * @param <S>  the type
   */
  public static final class Meta<S extends NamedSnapshot> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code value} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<S> _value = (DirectMetaProperty) DirectMetaProperty.ofImmutable(
        this, "value", ResolvedSnapshotLink.class, Object.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "value");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return _value;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedSnapshotLink.Builder<S> builder() {
      return new ResolvedSnapshotLink.Builder<S>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends ResolvedSnapshotLink<S>> beanType() {
      return (Class) ResolvedSnapshotLink.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code value} property.
     * @return the meta-property, not null
     */
    public MetaProperty<S> value() {
      return _value;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return ((ResolvedSnapshotLink<?>) bean).getValue();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ResolvedSnapshotLink}.
   * @param <S>  the type
   */
  public static final class Builder<S extends NamedSnapshot> extends DirectFieldsBeanBuilder<ResolvedSnapshotLink<S>> {

    private S _value;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ResolvedSnapshotLink<S> beanToCopy) {
      this._value = beanToCopy.getValue();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return _value;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<S> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          this._value = (S) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder<S> set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    /**
     * @deprecated Use Joda-Convert in application code
     */
    @Override
    @Deprecated
    public Builder<S> setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    /**
     * @deprecated Use Joda-Convert in application code
     */
    @Override
    @Deprecated
    public Builder<S> setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    /**
     * @deprecated Loop in application code
     */
    @Override
    @Deprecated
    public Builder<S> setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public ResolvedSnapshotLink<S> build() {
      return new ResolvedSnapshotLink<S>(
          _value);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the snapshot instance.
     * @param value  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<S> value(S value) {
      JodaBeanUtils.notNull(value, "value");
      this._value = value;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("ResolvedSnapshotLink.Builder{");
      buf.append("value").append('=').append(JodaBeanUtils.toString(_value));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
