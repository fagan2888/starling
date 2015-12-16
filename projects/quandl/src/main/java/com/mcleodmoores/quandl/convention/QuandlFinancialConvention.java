/**
 * Copyright (C) 2014-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.quandl.convention;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.mcleodmoores.quandl.util.ArgumentChecker;
import com.opengamma.financial.convention.FinancialConvention;
import com.opengamma.financial.convention.FinancialConventionVisitor;
import com.opengamma.id.ExternalIdBundle;

/**
 * Base class for conventions specific to this project.
 */
@BeanDefinition
public abstract class QuandlFinancialConvention extends FinancialConvention {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * For the builder.
   */
  protected QuandlFinancialConvention() {
    super();
  }

  /**
   * @param name The name of the convention, not null
   * @param externalIdBundle The external ids associated with this convention, not null
   */
  public QuandlFinancialConvention(final String name, final ExternalIdBundle externalIdBundle) {
    super(name, externalIdBundle);
  }

  @Override
  public <T> T accept(final FinancialConventionVisitor<T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    if (visitor instanceof QuandlFinancialConventionVisitor) {
      return accept((QuandlFinancialConventionVisitor<T>) visitor);
    }
    throw new IllegalStateException("This convention type " + getClass() + " is not supported by "
        + visitor.getClass());
  }

  /**
   * Visitor for project-specific {@link FinancialConvention}s.
   * @param visitor The visitor, not null
   * @param <T> The type of the result
   * @return The result
   */
  public abstract <T> T accept(QuandlFinancialConventionVisitor<T> visitor);

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code QuandlFinancialConvention}.
   * @return the meta-bean, not null
   */
  public static QuandlFinancialConvention.Meta meta() {
    return QuandlFinancialConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(QuandlFinancialConvention.Meta.INSTANCE);
  }

  @Override
  public QuandlFinancialConvention.Meta metaBean() {
    return QuandlFinancialConvention.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("QuandlFinancialConvention{");
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
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code QuandlFinancialConvention}.
   */
  public static class Meta extends FinancialConvention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends QuandlFinancialConvention> builder() {
      throw new UnsupportedOperationException("QuandlFinancialConvention is an abstract class");
    }

    @Override
    public Class<? extends QuandlFinancialConvention> beanType() {
      return QuandlFinancialConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}