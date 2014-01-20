/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.marketdata.manipulator.dsl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Maps;
import com.opengamma.DataNotFoundException;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.util.ArgumentChecker;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.Script;

/**
 * Base class for scripts that create {@link Simulation}s and {@link Scenario}s. The methods in this class are available
 * in the script and form the basis of a DSL.
 */
public abstract class SimulationScript extends Script {

  /** The currently building simulation. */
  private Simulation _simulation;
  /** The currently building scenario. */
  private Scenario _scenario;

  public SimulationScript() {
    initialize();
  }

  public SimulationScript(Binding binding) {
    super(binding);
    initialize();
  }

  // TODO is there a nicer way to do this?
  private void initialize() {
    InputStream scriptStream = SimulationScript.class.getResourceAsStream("InitializeScript.groovy");
    try {
      evaluate(IOUtils.toString(scriptStream));
    } catch (IOException e) {
      throw new OpenGammaRuntimeException("Failed to initialize DSL script", e);
    }
  }

  /**
   * Defines a method in the DSL taking a block to define the script parameters and their types. It checks the
   * parameters are available in the script's binding and that they have the expected type.
   * <pre>
   * parameters {
   *   foo String
   *   bar Number
   * }
   * </pre>
   * @param body The block that defines the script's parameters
   */
  public void parameters(Closure<?> body) {
    ParametersDelegate parametersDelegate = new ParametersDelegate();
    body.setDelegate(parametersDelegate);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
    // check the parameters are all in the binding and have the expected types
    Binding binding = getBinding();
    Map<String, Class<?>> parameters = parametersDelegate.getParameters();
    for (Map.Entry<String, Class<?>> entry : parameters.entrySet()) {
      String varName = entry.getKey();
      Class<?> varType = entry.getValue();
      if (!binding.hasVariable(varName)) {
        throw new DataNotFoundException("Parameter named " + varName + " not found");
      }
      Object varValue = binding.getVariable(varName);
      if (!varType.isInstance(varValue)) {
        throw new IllegalArgumentException("Parameter " + varName + " has type " + varValue.getClass().getName() + ", " +
                                                "required type is " + varType.getName());
      }
    }
  }

  /**
   * Delegate for the closure that declares the script parameters and their types.
   */
  private static class ParametersDelegate extends GroovyObjectSupport {

    /** Map of parameter names to types. */
    private final Map<String, Class<?>> _params = Maps.newHashMap();

    @Override
    public Object invokeMethod(String name, Object args) {
      ArgumentChecker.notEmpty(name, "name");
      if (!(args instanceof Object[])) {
        throw new IllegalArgumentException();
      }
      Object[] argArray = (Object[]) args;
      if (argArray.length != 1 || !argArray[0].getClass().equals(Class.class)) {
        throw new IllegalArgumentException("parameter declarations must be of the form 'var Type");
      }
      if (_params.containsKey(name)) {
        throw new IllegalArgumentException("parameter " + name + " can only be declared once");
      }
      _params.put(name, (Class<?>) argArray[0]);
      return null;
    }

    private Map<String, Class<?>> getParameters() {
      return _params;
    }
  }

  /**
   * Defines a method in the DSL that takes a closure defining a simulation.
   * @param name The simulation name
   * @param body The block that defines the simulation
   * @return The simulation
   */
  public Simulation simulation(String name, Closure<?> body) {
    _simulation = new Simulation(name);
    body.setDelegate(_simulation);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
    return _simulation;
  }

  /**
   * Defines a method in the DSL that takes a closure defining a scenario.
   * @param name The scenario name, not empty
   * @param body The block that defines the scenario
   * @return The scenario
   */
  public Scenario scenario(String name, Closure<?> body) {
    // scenarios can be defined as part of a simulation or stand-alone
    if (_simulation != null) {
      _scenario = _simulation.scenario(name);
    } else {
      _scenario = new Scenario(name);
    }
    body.setDelegate(_scenario);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
    return _scenario;
  }

  /**
   * Defines a method in the DSL that takes a closure which defines how to select and transform a curve.
   * @param body The block that defines the selection and transformation
   */
  public void curve(Closure<?> body) {
    YieldCurveBuilder selector = new YieldCurveBuilder(_scenario);
    body.setDelegate(selector);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
  }

  /**
   * Defines a method in the DSL that takes a closure which defines how to select and transform a curve.
   * @param body The block that defines the selection and transformation
   */
  public void curveData(Closure<?> body) {
    YieldCurveDataBuilder selector = new YieldCurveDataBuilder(_scenario);
    body.setDelegate(selector);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
  }

  /**
   * Defines a method in the DSL that takes a closure which defines how to select and transform a market data point.
   * @param body The block that defines the selection and transformation
   */
  public void marketData(Closure<?> body) {
    PointBuilder selector = new PointBuilder(_scenario);
    body.setDelegate(selector);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
  }

  /**
   * Defines a method in the DSL that takes a closure which defines how to select and transform a volatility surface.
   * @param body The block that defines the selection and transformation
   */
  public void surface(Closure<?> body) {
    SurfaceBuilder selector = new SurfaceBuilder(_scenario);
    body.setDelegate(selector);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
  }

  /**
   * Defines a method in the DSL that takes a closure which defines how to select and transform spot rates.
   * @param body The block that defines the selection and transformation
   */
  public void spotRate(Closure<?> body) {
    SpotRateBuilder builder = new SpotRateBuilder(_scenario);
    body.setDelegate(builder);
    body.setResolveStrategy(Closure.DELEGATE_FIRST);
    body.call();
  }

  /**
   * Delegate class for closures that define a surface transformation in the DSL.
   */
  private static final class SurfaceBuilder extends VolatilitySurfaceSelector.Builder {

    private SurfaceBuilder(Scenario scenario) {
      super(scenario);
    }

    @SuppressWarnings("unused")
    public void apply(Closure<?> body) {
      VolatilitySurfaceManipulatorBuilder builder = new VolatilitySurfaceManipulatorBuilder(getScenario(), getSelector());
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
    }
  }

  /**
   * Delegate class for blocks that define a market data point transformation in the DSL.
   */
  private static final class PointBuilder extends PointSelector.Builder {

    private PointBuilder(Scenario scenario) {
      super(scenario);
    }

    @SuppressWarnings("unused")
    public void apply(Closure<?> body) {
      PointManipulatorBuilder builder = new PointManipulatorBuilder(getScenario(), getSelector());
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
    }
  }

  /**
   * Delegate class for closures that define a curve transformation in the DSL.
   */
  private static final class YieldCurveBuilder extends YieldCurveSelector.Builder {

    private YieldCurveBuilder(Scenario scenario) {
      super(scenario);
    }

    @SuppressWarnings("unused")
    public void apply(Closure<?> body) {
      YieldCurveManipulatorBuilder builder = new GroovyYieldCurveManipulatorBuilder(getSelector(), getScenario());
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
    }
  }

  /**
   * Delegate class for closures that define a spot rate transformation in the DSL.
   */
  private static final class SpotRateBuilder extends SpotRateSelectorBuilder {

    private SpotRateBuilder(Scenario scenario) {
      super(scenario);
    }

    public void apply(Closure<?> body) {
      SpotRateManipulatorBuilder builder = new SpotRateManipulatorBuilder(getScenario(), getSelector());
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
    }
  }

  /**
   * Delegate class for closures that define a curve data transformation in the DSL.
   * This affects raw curve data before it's fitted.
   */
  private static final class YieldCurveDataBuilder extends YieldCurveDataSelectorBuilder {

    private YieldCurveDataBuilder(Scenario scenario) {
      super(scenario);
    }

    @SuppressWarnings("unused")
    public void apply(Closure<?> body) {
      YieldCurveDataManipulatorBuilder builder = new GroovyYieldCurveDataManipulatorBuilder(getSelector(), getScenario());
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
    }
  }

  private static final class GroovyYieldCurveDataManipulatorBuilder extends YieldCurveDataManipulatorBuilder {

    /* package */ GroovyYieldCurveDataManipulatorBuilder(YieldCurveDataSelector selector, Scenario scenario) {
      super(selector, scenario);
    }

    public void bucketedShifts(/*BucketedShiftType type, */Closure<?> body) {
      // TODO need a builder specifically for the DSL. YieldCurveDataBucketedShiftsManipulatorBuilder. phew
    }

    public void pointsShifts(/*BucketedShiftType type, */Closure<?> body) {
      // TODO need a builder specifically for the DSL. YieldCurveDataPointShiftsManipulatorBuilder. phew
    }

  }

    /**
   * Delegate class for closures that defines closure compatible builder methods
   * for {@link YieldCurveManipulatorBuilder} in the DSL.
   */
  private static final class GroovyYieldCurveManipulatorBuilder extends YieldCurveManipulatorBuilder {

    /* package */ GroovyYieldCurveManipulatorBuilder(YieldCurveSelector selector, Scenario scenario) {
      super(selector, scenario);
    }

    @SuppressWarnings("unused")
    public void bucketedShifts(/*BucketedShiftType type, */Closure<?> body) {
      BucketedShiftManipulatorBuilder builder = new BucketedShiftManipulatorBuilder(getSelector(), getScenario()/*, type*/);
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
      builder.apply();
    }

    @SuppressWarnings("unused")
    public void pointShifts(Closure<?> body) {
      PointShiftManipulatorBuilder builder = new PointShiftManipulatorBuilder(getSelector(), getScenario());
      body.setDelegate(builder);
      body.setResolveStrategy(Closure.DELEGATE_FIRST);
      body.call();
      builder.apply();
    }
    
  }
  
}
