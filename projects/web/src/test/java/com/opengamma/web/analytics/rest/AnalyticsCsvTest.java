/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.analytics.rest;

import com.opengamma.web.analytics.push.WebPushTestUtils;

/**
 * Tests getting analytics as CSV from a real engine.  Requires an engine running on {@code localhost}.
 */
public class AnalyticsCsvTest {

  public static void main(final String[] args) throws Exception {
    final WebPushTestUtils webPushTestUtils = new WebPushTestUtils();
    final String clientId = webPushTestUtils.handshake();
    final String viewDefJson = "{" +
        "\"viewDefinitionName\": \"Single Swap Test View\", " +
        //"\"snapshotId\": \"Tst~123\", " + // use live data
        "\"portfolioViewport\": {" +
        "\"rowIds\": [0, 1, 2, 3], " +
        "\"lastTimestamps\": [null, null, null, null], " +
        "\"dependencyGraphCells\": [[0, 0], [1, 2]]" +
        "}," +
        "\"primitivesViewport\": {" +
        "\"rowIds\": [0, 1, 2, 3], " +
        "\"lastTimestamps\": [null, null, null, null], " +
        "\"dependencyGraphCells\": [[0, 0]]" +
        "}" +
        "}";
    final String viewportUrl = webPushTestUtils.createViewport(clientId, viewDefJson);
    //noinspection InfiniteLoopStatement
    while (true) {
      final String csv = webPushTestUtils.readFromPath(viewportUrl + "/report/csv");
      System.out.println(csv);

      Thread.sleep(2000);
    }
  }
}
