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

/**
 * Represents the identification data required for a link to an object. The link can
 * then be resolved on demand.
 *
 * @param <I> the identifier for the linked object
 * @param <T> the type of the object being linked to
 */
@BeanDefinition
public final class LinkIdentifier<I, T> implements ImmutableBean {

  /**
   * The identifier for the linked object, not null.
   */
  @PropertyDefinition(validate = "notNull")
  private final I _identifier;

  /**
   * The class of the object being linked to, not null.
   */
  @PropertyDefinition(validate = "notNull")
  private final Class<T> _type;

  /**
   * Create a new link identifier for the combination of type and id.
   *
   * @param <I> the identifier for the linked object
   * @param <T> the type of the object being linked to
   * @param identifier the identifier of the object being linked to
   * @param type the type of the object being linked to
   * @return a new instance
   */
  public static <I, T> LinkIdentifier<I, T> of(I identifier, Class<T> type) {
    return new LinkIdentifier<>(identifier, type);
  }

  /**
   * Create an identifier for the combination of type and id.
   *
   * @param identifier the identifier of the object being linked to
   * @param type the type of the object being linked to
   */
  @ImmutableConstructor
  private LinkIdentifier(I identifier, Class<T> type) {
    _type = type;
    _identifier = identifier;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LinkIdentifier}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static LinkIdentifier.Meta meta() {
    return LinkIdentifier.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code LinkIdentifier}.
   * @param <R>  the first generic type
   * @param <S>  the second generic type
   * @param cls1  the first generic type
   * @param cls2  the second generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R, S> LinkIdentifier.Meta<R, S> metaLinkIdentifier(Class<R> cls1, Class<S> cls2) {
    return LinkIdentifier.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LinkIdentifier.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @param <I>  the type
   * @param <T>  the type
   * @return the builder, not null
   */
  public static <I, T> LinkIdentifier.Builder<I, T> builder() {
    return new LinkIdentifier.Builder<I, T>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public LinkIdentifier.Meta<I, T> metaBean() {
    return LinkIdentifier.Meta.INSTANCE;
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
   * Gets the identifier for the linked object, not null.
   * @return the value of the property, not null
   */
  public I getIdentifier() {
    return _identifier;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the class of the object being linked to, not null.
   * @return the value of the property, not null
   */
  public Class<T> getType() {
    return _type;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder<I, T> toBuilder() {
    return new Builder<I, T>(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      LinkIdentifier<?, ?> other = (LinkIdentifier<?, ?>) obj;
      return JodaBeanUtils.equal(getIdentifier(), other.getIdentifier()) &&
          JodaBeanUtils.equal(getType(), other.getType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getIdentifier());
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("LinkIdentifier{");
    buf.append("identifier").append('=').append(getIdentifier()).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LinkIdentifier}.
   * @param <I>  the type
   * @param <T>  the type
   */
  public static final class Meta<I, T> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code identifier} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<I> _identifier = (DirectMetaProperty) DirectMetaProperty.ofImmutable(
        this, "identifier", LinkIdentifier.class, Object.class);
    /**
     * The meta-property for the {@code type} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<T>> _type = DirectMetaProperty.ofImmutable(
        this, "type", LinkIdentifier.class, (Class) Class.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "identifier",
        "type");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          return _identifier;
        case 3575610:  // type
          return _type;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public LinkIdentifier.Builder<I, T> builder() {
      return new LinkIdentifier.Builder<I, T>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends LinkIdentifier<I, T>> beanType() {
      return (Class) LinkIdentifier.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code identifier} property.
     * @return the meta-property, not null
     */
    public MetaProperty<I> identifier() {
      return _identifier;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Class<T>> type() {
      return _type;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          return ((LinkIdentifier<?, ?>) bean).getIdentifier();
        case 3575610:  // type
          return ((LinkIdentifier<?, ?>) bean).getType();
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
   * The bean-builder for {@code LinkIdentifier}.
   * @param <I>  the type
   * @param <T>  the type
   */
  public static final class Builder<I, T> extends DirectFieldsBeanBuilder<LinkIdentifier<I, T>> {

    private I _identifier;
    private Class<T> _type;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(LinkIdentifier<I, T> beanToCopy) {
      this._identifier = beanToCopy.getIdentifier();
      this._type = beanToCopy.getType();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          return _identifier;
        case 3575610:  // type
          return _type;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<I, T> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          this._identifier = (I) newValue;
          break;
        case 3575610:  // type
          this._type = (Class<T>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder<I, T> set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder<I, T> setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder<I, T> setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder<I, T> setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public LinkIdentifier<I, T> build() {
      return new LinkIdentifier<I, T>(
          _identifier,
          _type);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the identifier for the linked object, not null.
     * @param identifier  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<I, T> identifier(I identifier) {
      JodaBeanUtils.notNull(identifier, "identifier");
      this._identifier = identifier;
      return this;
    }

    /**
     * Sets the class of the object being linked to, not null.
     * @param type  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<I, T> type(Class<T> type) {
      JodaBeanUtils.notNull(type, "type");
      this._type = type;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("LinkIdentifier.Builder{");
      buf.append("identifier").append('=').append(JodaBeanUtils.toString(_identifier)).append(',').append(' ');
      buf.append("type").append('=').append(JodaBeanUtils.toString(_type));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
