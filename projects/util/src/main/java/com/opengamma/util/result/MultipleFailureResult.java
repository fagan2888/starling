/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.result;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.opengamma.util.ArgumentChecker;

/**
 * A result indicating a failure with multiple underlying causes.
 * <p>
 * An example of this might be a function calling several other functions when
 * there is no market data available, causing them all to fail.
 * If all underlying results have the same status this result will use that status.
 * If they have different statues the status of this result will be {@link FailureStatus#MULTIPLE}.
 *
 * @param <T> the type of the underlying result for a successful invocation
 * @deprecated {@link FailureResult} can deal with multiple failures
 */
@Deprecated
@BeanDefinition
public final class MultipleFailureResult<T> extends Result<T> implements ImmutableBean {

  @PropertyDefinition(validate = "notNull", get = "manual")
  private final ImmutableSet<Failure> _failures;

  @PropertyDefinition(validate = "notNull", get = "manual")
  private final FailureStatus _status;

  @PropertyDefinition(validate = "notNull", get = "private")
  private final String _message;

  /**
   * @param failures the failures, must contain at least two elements
   */
  static <U> Result<U> of(final List<Failure> failures) {
    ArgumentChecker.notNull(failures, "failures");

    if (failures.size() < 2) {
      throw new IllegalArgumentException("At least two failures are required");
    }
    ResultStatus status = failures.get(0).getStatus();
    final StringBuilder builder = new StringBuilder();

    for (final Iterator<Failure> itr = failures.iterator(); itr.hasNext();) {
      final Failure failure = itr.next();
      builder.append(failure.getMessage());

      if (itr.hasNext()) {
        builder.append("\n");
      }
      if (!status.equals(failure.getStatus())) {
        status = FailureStatus.MULTIPLE;
      }
    }
    return new MultipleFailureResult<>(failures, (FailureStatus) status, builder.toString()).propagateFailure();
  }

  @ImmutableConstructor
  private MultipleFailureResult(final Collection<Failure> failures, final FailureStatus status, final String message) {
    _failures = ImmutableSet.copyOf(ArgumentChecker.notEmpty(failures, "failures"));
    _status = ArgumentChecker.notNull(status, "status");
    _message = ArgumentChecker.notEmpty(message, "message");
  }

  @SuppressWarnings("unchecked")
  @Override
  public <U> Result<U> propagateFailure() {
    return (Result<U>) this;
  }

  @Override
  public <U> Result<U> ifSuccess(final Function<T, Result<U>> function) {
    return this.propagateFailure();
  }

  @Override
  public <U, V> Result<V> combineWith(final Result<U> other, final Function2<T, U, Result<V>> function) {
    if (other.isSuccess()) {
      return Result.failure(this);
    } else {
      return Result.failure(this, other);
    }
  }

  /**
   * @return false
   */
  @Override
  public boolean isSuccess() {
    return false;
  }

  //-------------------------------------------------------------------------
  @Override
  public T getValue() {
    throw new IllegalStateException("Unable to get a value from a failure result");
  }

  @Override
  public FailureStatus getStatus() {
    return _status;
  }

  @Override
  public String getFailureMessage() {
    return _message;
  }

  @Override
  public ImmutableSet<Failure> getFailures() {
    return _failures;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code MultipleFailureResult}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static MultipleFailureResult.Meta meta() {
    return MultipleFailureResult.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code MultipleFailureResult}.
   * @param <R>  the bean's generic type
   * @param cls  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> MultipleFailureResult.Meta<R> metaMultipleFailureResult(Class<R> cls) {
    return MultipleFailureResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(MultipleFailureResult.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @param <T>  the type
   * @return the builder, not null
   */
  public static <T> MultipleFailureResult.Builder<T> builder() {
    return new MultipleFailureResult.Builder<T>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public MultipleFailureResult.Meta<T> metaBean() {
    return MultipleFailureResult.Meta.INSTANCE;
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
   * Gets the message.
   * @return the value of the property, not null
   */
  private String getMessage() {
    return _message;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder<T> toBuilder() {
    return new Builder<T>(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      MultipleFailureResult<?> other = (MultipleFailureResult<?>) obj;
      return JodaBeanUtils.equal(getFailures(), other.getFailures()) &&
          JodaBeanUtils.equal(getStatus(), other.getStatus()) &&
          JodaBeanUtils.equal(getMessage(), other.getMessage());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getFailures());
    hash = hash * 31 + JodaBeanUtils.hashCode(getStatus());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMessage());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("MultipleFailureResult{");
    buf.append("failures").append('=').append(getFailures()).append(',').append(' ');
    buf.append("status").append('=').append(getStatus()).append(',').append(' ');
    buf.append("message").append('=').append(JodaBeanUtils.toString(getMessage()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code MultipleFailureResult}.
   * @param <T>  the type
   */
  public static final class Meta<T> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code failures} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableSet<Failure>> _failures = DirectMetaProperty.ofImmutable(
        this, "failures", MultipleFailureResult.class, (Class) ImmutableSet.class);
    /**
     * The meta-property for the {@code status} property.
     */
    private final MetaProperty<FailureStatus> _status = DirectMetaProperty.ofImmutable(
        this, "status", MultipleFailureResult.class, FailureStatus.class);
    /**
     * The meta-property for the {@code message} property.
     */
    private final MetaProperty<String> _message = DirectMetaProperty.ofImmutable(
        this, "message", MultipleFailureResult.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "failures",
        "status",
        "message");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 675938345:  // failures
          return _failures;
        case -892481550:  // status
          return _status;
        case 954925063:  // message
          return _message;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public MultipleFailureResult.Builder<T> builder() {
      return new MultipleFailureResult.Builder<T>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends MultipleFailureResult<T>> beanType() {
      return (Class) MultipleFailureResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code failures} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ImmutableSet<Failure>> failures() {
      return _failures;
    }

    /**
     * The meta-property for the {@code status} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FailureStatus> status() {
      return _status;
    }

    /**
     * The meta-property for the {@code message} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> message() {
      return _message;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 675938345:  // failures
          return ((MultipleFailureResult<?>) bean).getFailures();
        case -892481550:  // status
          return ((MultipleFailureResult<?>) bean).getStatus();
        case 954925063:  // message
          return ((MultipleFailureResult<?>) bean).getMessage();
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
   * The bean-builder for {@code MultipleFailureResult}.
   * @param <T>  the type
   */
  public static final class Builder<T> extends DirectFieldsBeanBuilder<MultipleFailureResult<T>> {

    private Set<Failure> _failures = ImmutableSet.of();
    private FailureStatus _status;
    private String _message;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(MultipleFailureResult<T> beanToCopy) {
      this._failures = beanToCopy.getFailures();
      this._status = beanToCopy.getStatus();
      this._message = beanToCopy.getMessage();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 675938345:  // failures
          return _failures;
        case -892481550:  // status
          return _status;
        case 954925063:  // message
          return _message;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<T> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 675938345:  // failures
          this._failures = (Set<Failure>) newValue;
          break;
        case -892481550:  // status
          this._status = (FailureStatus) newValue;
          break;
        case 954925063:  // message
          this._message = (String) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder<T> set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder<T> setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder<T> setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder<T> setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public MultipleFailureResult<T> build() {
      return new MultipleFailureResult<T>(
          _failures,
          _status,
          _message);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the failures.
     * @param failures  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<T> failures(Set<Failure> failures) {
      JodaBeanUtils.notNull(failures, "failures");
      this._failures = failures;
      return this;
    }

    /**
     * Sets the {@code failures} property in the builder
     * from an array of objects.
     * @param failures  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<T> failures(Failure... failures) {
      return failures(ImmutableSet.copyOf(failures));
    }

    /**
     * Sets the status.
     * @param status  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<T> status(FailureStatus status) {
      JodaBeanUtils.notNull(status, "status");
      this._status = status;
      return this;
    }

    /**
     * Sets the message.
     * @param message  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<T> message(String message) {
      JodaBeanUtils.notNull(message, "message");
      this._message = message;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("MultipleFailureResult.Builder{");
      buf.append("failures").append('=').append(JodaBeanUtils.toString(_failures)).append(',').append(' ');
      buf.append("status").append('=').append(JodaBeanUtils.toString(_status)).append(',').append(' ');
      buf.append("message").append('=').append(JodaBeanUtils.toString(_message));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
