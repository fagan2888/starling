/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve.strips;

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

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * A bill node.
 */
@BeanDefinition
public class BillNode extends CurveNode {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The (approximate) bill tenor.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _maturityTenor;

  /**
   * For the builder.
   */
  /* package */ BillNode() {
    super();
  }

  /**
   * Sets the node name to null.
   * @param maturityTenor The approximate bill maturity tenor, not null
   * @param curveNodeIdMapperName The curve node id mapper name, not null
   */
  public BillNode(final Tenor maturityTenor, final String curveNodeIdMapperName) {
    super(curveNodeIdMapperName);
    setMaturityTenor(maturityTenor);
  }

  /**
   * @param maturityTenor The approximate bill maturity tenor, not null
   * @param curveNodeIdMapperName The curve node id mapper name, not null
   * @param name The node name, not null
   */
  public BillNode(final Tenor maturityTenor, final String curveNodeIdMapperName, final String name) {
    super(curveNodeIdMapperName, name);
    setMaturityTenor(maturityTenor);
  }

  @Override
  public Tenor getResolvedMaturity() {
    return _maturityTenor;
  }

  @Override
  public <T> T accept(final CurveNodeVisitor<T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitBillNode(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BillNode}.
   * @return the meta-bean, not null
   */
  public static BillNode.Meta meta() {
    return BillNode.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(BillNode.Meta.INSTANCE);
  }

  @Override
  public BillNode.Meta metaBean() {
    return BillNode.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the (approximate) bill tenor.
   * @return the value of the property, not null
   */
  public Tenor getMaturityTenor() {
    return _maturityTenor;
  }

  /**
   * Sets the (approximate) bill tenor.
   * @param maturityTenor  the new value of the property, not null
   */
  public void setMaturityTenor(Tenor maturityTenor) {
    JodaBeanUtils.notNull(maturityTenor, "maturityTenor");
    this._maturityTenor = maturityTenor;
  }

  /**
   * Gets the the {@code maturityTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> maturityTenor() {
    return metaBean().maturityTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public BillNode clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BillNode other = (BillNode) obj;
      return JodaBeanUtils.equal(getMaturityTenor(), other.getMaturityTenor()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaturityTenor());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("BillNode{");
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
    buf.append("maturityTenor").append('=').append(JodaBeanUtils.toString(getMaturityTenor())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BillNode}.
   */
  public static class Meta extends CurveNode.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code maturityTenor} property.
     */
    private final MetaProperty<Tenor> _maturityTenor = DirectMetaProperty.ofReadWrite(
        this, "maturityTenor", BillNode.class, Tenor.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "maturityTenor");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 45907375:  // maturityTenor
          return _maturityTenor;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BillNode> builder() {
      return new DirectBeanBuilder<BillNode>(new BillNode());
    }

    @Override
    public Class<? extends BillNode> beanType() {
      return BillNode.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code maturityTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> maturityTenor() {
      return _maturityTenor;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 45907375:  // maturityTenor
          return ((BillNode) bean).getMaturityTenor();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 45907375:  // maturityTenor
          ((BillNode) bean).setMaturityTenor((Tenor) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((BillNode) bean)._maturityTenor, "maturityTenor");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}