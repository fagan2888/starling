/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.opengamma.component.tool.ToolContextUtils;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.util.PlatformConfigUtils;

/**
 * 
 */
public class RegressionTestToolContextManager {

  private ToolContext _toolContext;
  
  private static String s_logbackPropertyName = "logback.configurationFile";
  private static String s_logbackDefaultValue = "com/opengamma/util/warn-logback.xml";
  
  static {
    if (System.getProperty(s_logbackPropertyName) == null) {
      //if not explicitly set, default to a quieter setting.
      System.setProperty(s_logbackPropertyName, s_logbackDefaultValue);
    }
  }
  
  //TODO - these probably need to be configurable
  private static String s_toolContext = "classpath:regression/regression-toolcontext.properties";
  private static String s_regressionPropertiesFile = "classpath:regression/regression-testdb.properties";
  
  private static final Logger s_logger = LoggerFactory.getLogger(RegressionTestToolContextManager.class);
  
  private final File _dumpFile;
  
  /**
   * @param dumpFile the dump. can be a zip file or directory.
   */
  public RegressionTestToolContextManager(File dumpFile) {
    _dumpFile = dumpFile;
  }



  public void init() {
    
    PlatformConfigUtils.configureSystemProperties();
    //TODO use logger
    System.out.println("Initializing DB");
    try {
      initialiseDB();
    } catch (IOException ex) {
      throw Throwables.propagate(ex);
    }
    System.out.println("Initialized DB");
    
    //start toolcontext
    System.out.println("Starting full context");
    _toolContext = ToolContextUtils.getToolContext(s_regressionPropertiesFile, ToolContext.class);
    System.out.println("Full context started");
    
  }


  /**
   * Create a new DB, schema, and populate tables.
   * @throws IOException 
   */
  private void initialiseDB() throws IOException  {
    System.out.println("Creating empty DB...");
    EmptyDatabaseCreator.createForConfig(s_toolContext);
    
    System.out.println("Creating tool context for DB...");
    ToolContext toolContext = ToolContextUtils.getToolContext(s_toolContext, ToolContext.class);
    
    //assume this is a zipfile:
    restoreFromZipfile(toolContext, _dumpFile);
    
    toolContext.close();
  }

  private void restoreFromZipfile(ToolContext toolContext, File zipFile) throws IOException {
    ZipFileRegressionIO io = new ZipFileRegressionIO(zipFile, new FudgeXMLFormat(), true);
    DatabaseRestore restore = new DatabaseRestore(
        io,
        toolContext.getSecurityMaster(),
        toolContext.getPositionMaster(),
        toolContext.getPortfolioMaster(),
        toolContext.getConfigMaster(),
        toolContext.getHistoricalTimeSeriesMaster(),
        toolContext.getHolidayMaster(),
        toolContext.getExchangeMaster(),
        toolContext.getMarketDataSnapshotMaster(),
        toolContext.getOrganizationMaster()
    );

    System.out.println("Initializing DB state...");
    restore.restoreDatabase();
  }
  
  public void close() {
    if (_toolContext != null) {
      _toolContext.close();
    }
  }
  
  /**
   * @return the tool context managed by this instance
   */
  public ToolContext getToolContext() {
    return _toolContext;
  }
  
  
}
