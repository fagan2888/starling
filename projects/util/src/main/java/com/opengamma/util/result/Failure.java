/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.result;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.base.Throwables;
import com.opengamma.util.ArgumentChecker;

/**
 * Contains the details of a failed function call.
 */
@BeanDefinition
public final class Failure implements ImmutableBean {

  /**
   * The status associated with the failure.
   */
  @PropertyDefinition(validate = "notNull")
  private final FailureStatus _status;
  /**
   * The error message associated with the failure.
   */
  @PropertyDefinition(validate = "notNull")
  private final String _message;
  /**
   * Stack trace where the failure occurred.
   * If the failure was caused by an {@code Exception} its stack trace is used, otherwise it's the
   * location where the failure was created.
   */
  @PropertyDefinition(validate = "notNull")
  private final String _stackTrace;
  /**
   * The type of the exception that caused the failure, null if it wasn't caused by an exception.
   */
  @PropertyDefinition
  private final Class<? extends Exception> _causeType;

  /**
   * @param status the status, not null
   * @param message the failure message, not null
   * @param cause the cause, not null
   */
  Failure(final FailureStatus status, final String message, final Exception cause) {
    _status = ArgumentChecker.notNull(status, "status");
    _message = ArgumentChecker.notEmpty(message, "message");
    _causeType = ArgumentChecker.notNull(cause, "cause").getClass();
    _stackTrace = Throwables.getStackTraceAsString(cause);
  }

  /**
   * @param status the status, not null
   * @param message the failure message, not null
   */
  Failure(final FailureStatus status, final String message) {
    _status = ArgumentChecker.notNull(status, "status");
    _message = ArgumentChecker.notEmpty(message, "message");
    _stackTrace = Throwables.getStackTraceAsString(new Exception());
    _causeType = null;
  }

  /**
   * @param status the status, not null
   * @param cause the cause, not null
   */
  Failure(final FailureStatus status, final Exception cause) {
    this(status, getMessage(cause), cause);
  }

  /**
   * @param cause the cause, not null
   * @param message the failure message, not null
   */
  Failure(final Exception cause, final String message) {
    this(FailureStatus.ERROR, message, cause);
  }

  /**
   * @param cause the cause, not null
   */
  Failure(final Exception cause) {
    this(FailureStatus.ERROR, cause);
  }

  /**
   * Extracts the message from an exception.
   *
   * @param cause  an exception that caused a failure
   * @return the exception's message or it's simple class name if it doesn't have one
   */
  private static String getMessage(final Exception cause) {
    final String message = ArgumentChecker.notNull(cause, "cause").getMessage();
    return !StringUtils.isEmpty(message) ? message : cause.getClass().getSimpleName();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code Failure}.
   * @return the meta-bean, not null
   */
  public static Failure.Meta meta() {
    return Failure.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(Failure.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static Failure.Builder builder() {
    return new Failure.Builder();
  }

  private Failure(
      FailureStatus status,
      String message,
      String stackTrace,
      Class<? extends Exception> causeType) {
    JodaBeanUtils.notNull(status, "status");
    JodaBeanUtils.notNull(message, "message");
    JodaBeanUtils.notNull(stackTrace, "stackTrace");
    this._status = status;
    this._message = message;
    this._stackTrace = stackTrace;
    this._causeType = causeType;
  }

  @Override
  public Failure.Meta metaBean() {
    return Failure.Meta.INSTANCE;
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
   * Gets the status associated with the failure.
   * @return the value of the property, not null
   */
  public FailureStatus getStatus() {
    return _status;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the error message associated with the failure.
   * @return the value of the property, not null
   */
  public String getMessage() {
    return _message;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets stack trace where the failure occurred.
   * If the failure was caused by an {@code Exception} its stack trace is used, otherwise it's the
   * location where the failure was created.
   * @return the value of the property, not null
   */
  public String getStackTrace() {
    return _stackTrace;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the type of the exception that caused the failure, null if it wasn't caused by an exception.
   * @return the value of the property
   */
  public Class<? extends Exception> getCauseType() {
    return _causeType;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      Failure other = (Failure) obj;
      return JodaBeanUtils.equal(_status, other._status) &&
          JodaBeanUtils.equal(_message, other._message) &&
          JodaBeanUtils.equal(_stackTrace, other._stackTrace) &&
          JodaBeanUtils.equal(_causeType, other._causeType);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(_status);
    hash = hash * 31 + JodaBeanUtils.hashCode(_message);
    hash = hash * 31 + JodaBeanUtils.hashCode(_stackTrace);
    hash = hash * 31 + JodaBeanUtils.hashCode(_causeType);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("Failure{");
    buf.append("status").append('=').append(_status).append(',').append(' ');
    buf.append("message").append('=').append(_message).append(',').append(' ');
    buf.append("stackTrace").append('=').append(_stackTrace).append(',').append(' ');
    buf.append("causeType").append('=').append(JodaBeanUtils.toString(_causeType));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code Failure}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code status} property.
     */
    private final MetaProperty<FailureStatus> _status = DirectMetaProperty.ofImmutable(
        this, "status", Failure.class, FailureStatus.class);
    /**
     * The meta-property for the {@code message} property.
     */
    private final MetaProperty<String> _message = DirectMetaProperty.ofImmutable(
        this, "message", Failure.class, String.class);
    /**
     * The meta-property for the {@code stackTrace} property.
     */
    private final MetaProperty<String> _stackTrace = DirectMetaProperty.ofImmutable(
        this, "stackTrace", Failure.class, String.class);
    /**
     * The meta-property for the {@code causeType} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<? extends Exception>> _causeType = DirectMetaProperty.ofImmutable(
        this, "causeType", Failure.class, (Class) Class.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "status",
        "message",
        "stackTrace",
        "causeType");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -892481550:  // status
          return _status;
        case 954925063:  // message
          return _message;
        case 2026279837:  // stackTrace
          return _stackTrace;
        case -1443456189:  // causeType
          return _causeType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public Failure.Builder builder() {
      return new Failure.Builder();
    }

    @Override
    public Class<? extends Failure> beanType() {
      return Failure.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
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

    /**
     * The meta-property for the {@code stackTrace} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> stackTrace() {
      return _stackTrace;
    }

    /**
     * The meta-property for the {@code causeType} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Class<? extends Exception>> causeType() {
      return _causeType;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -892481550:  // status
          return ((Failure) bean).getStatus();
        case 954925063:  // message
          return ((Failure) bean).getMessage();
        case 2026279837:  // stackTrace
          return ((Failure) bean).getStackTrace();
        case -1443456189:  // causeType
          return ((Failure) bean).getCauseType();
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
   * The bean-builder for {@code Failure}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<Failure> {

    private FailureStatus _status;
    private String _message;
    private String _stackTrace;
    private Class<? extends Exception> _causeType;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(Failure beanToCopy) {
      this._status = beanToCopy.getStatus();
      this._message = beanToCopy.getMessage();
      this._stackTrace = beanToCopy.getStackTrace();
      this._causeType = beanToCopy.getCauseType();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -892481550:  // status
          return _status;
        case 954925063:  // message
          return _message;
        case 2026279837:  // stackTrace
          return _stackTrace;
        case -1443456189:  // causeType
          return _causeType;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -892481550:  // status
          this._status = (FailureStatus) newValue;
          break;
        case 954925063:  // message
          this._message = (String) newValue;
          break;
        case 2026279837:  // stackTrace
          this._stackTrace = (String) newValue;
          break;
        case -1443456189:  // causeType
          this._causeType = (Class<? extends Exception>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    /**
     * @deprecated Use Joda-Convert in application code
     */
    @Override
    @Deprecated
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    /**
     * @deprecated Use Joda-Convert in application code
     */
    @Override
    @Deprecated
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    /**
     * @deprecated Loop in application code
     */
    @Override
    @Deprecated
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public Failure build() {
      return new Failure(
          _status,
          _message,
          _stackTrace,
          _causeType);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the status associated with the failure.
     * @param status  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder status(FailureStatus status) {
      JodaBeanUtils.notNull(status, "status");
      this._status = status;
      return this;
    }

    /**
     * Sets the error message associated with the failure.
     * @param message  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder message(String message) {
      JodaBeanUtils.notNull(message, "message");
      this._message = message;
      return this;
    }

    /**
     * Sets stack trace where the failure occurred.
     * If the failure was caused by an {@code Exception} its stack trace is used, otherwise it's the
     * location where the failure was created.
     * @param stackTrace  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder stackTrace(String stackTrace) {
      JodaBeanUtils.notNull(stackTrace, "stackTrace");
      this._stackTrace = stackTrace;
      return this;
    }

    /**
     * Sets the type of the exception that caused the failure, null if it wasn't caused by an exception.
     * @param causeType  the new value
     * @return this, for chaining, not null
     */
    public Builder causeType(Class<? extends Exception> causeType) {
      this._causeType = causeType;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("Failure.Builder{");
      buf.append("status").append('=').append(JodaBeanUtils.toString(_status)).append(',').append(' ');
      buf.append("message").append('=').append(JodaBeanUtils.toString(_message)).append(',').append(' ');
      buf.append("stackTrace").append('=').append(JodaBeanUtils.toString(_stackTrace)).append(',').append(' ');
      buf.append("causeType").append('=').append(JodaBeanUtils.toString(_causeType));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
