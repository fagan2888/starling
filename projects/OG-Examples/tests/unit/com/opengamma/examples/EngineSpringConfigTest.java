/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.examples;

import org.eclipse.jetty.server.Server;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opengamma.util.test.AbstractSpringContextValidationTestNG;


public class EngineSpringConfigTest extends AbstractSpringContextValidationTestNG {

  @Test(dataProvider = "runModes", dataProviderClass = EngineSpringConfigTest.class)
  public void testJettyServerLoaderBean(final String opengammaPlatformRunmode) {
    loadFileSystemResource(opengammaPlatformRunmode, "config/engine-spring.xml");
    assertContextLoaded();
    assertBeanExists(Server.class, "server");
  }
  
  @DataProvider(name = "runModes")
  public static Object[][] data_runMode() {
    return new Object[][] {
      {"example"},
    };
  }
}
