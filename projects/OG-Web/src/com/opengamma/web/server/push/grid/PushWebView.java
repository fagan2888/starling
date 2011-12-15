/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.server.push.grid;

import com.opengamma.engine.ComputationTargetSpecification;
import com.opengamma.engine.view.ViewComputationResultModel;
import com.opengamma.engine.view.ViewDeltaResultModel;
import com.opengamma.engine.view.client.ViewClient;
import com.opengamma.engine.view.compilation.CompiledViewDefinition;
import com.opengamma.engine.view.listener.AbstractViewResultListener;
import com.opengamma.id.UniqueId;
import com.opengamma.livedata.UserPrincipal;
import com.opengamma.util.tuple.Pair;
import com.opengamma.web.server.WebGridCell;
import com.opengamma.web.server.conversion.ConversionMode;
import com.opengamma.web.server.conversion.ResultConverterCache;
import com.opengamma.web.server.push.AnalyticsListener;
import com.opengamma.web.server.push.Viewport;
import com.opengamma.web.server.push.ViewportDefinition;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * TODO return new viewport instance rather than implementing it?
 * TODO temporary name just to distinguish it from the similarly named class in the parent package
*/
/* package */ class PushWebView implements Viewport {

  private static final Logger s_logger = LoggerFactory.getLogger(PushWebView.class);

  private final ViewClient _viewClient;
  private final UniqueId _baseViewDefinitionId;
  private final UniqueId _viewDefinitionId;
  private final ResultConverterCache _resultConverterCache;
  private final Map<String, Object> _latestResults = new HashMap<String, Object>();
  private final Object _lock = new Object();

  private PushRequirementBasedWebViewGrid _portfolioGrid;
  private PushRequirementBasedWebViewGrid _primitivesGrid;

  private ViewportDefinition _viewportDefinition;
  private AnalyticsListener _listener;
  private Map<String,Object> _gridStructures;
  private boolean _initialized = false;
  private boolean _sendAnalyticsUpdates = false;

  public PushWebView(ViewClient viewClient,
                     ViewportDefinition viewportDefinition,
                     UniqueId baseViewDefinitionId,
                     UniqueId viewDefinitionId,
                     ResultConverterCache resultConverterCache,
                     AnalyticsListener listener) {
    _viewClient = viewClient;
    _baseViewDefinitionId = baseViewDefinitionId;
    _viewDefinitionId = viewDefinitionId;
    _resultConverterCache = resultConverterCache;
    _viewportDefinition = viewportDefinition;
    _listener = listener;
    _viewClient.setResultListener(new AbstractViewResultListener() {
      
      @Override
      public UserPrincipal getUser() {
        // Authentication needed
        return UserPrincipal.getLocalUser();
      }
      
      @Override
      public void viewDefinitionCompiled(CompiledViewDefinition compiledViewDefinition, boolean hasMarketDataPermissions) {     
        s_logger.info("View definition compiled: {}", compiledViewDefinition.getViewDefinition().getName());
        initGrids(compiledViewDefinition);
      }
      
      @Override
      public void cycleCompleted(ViewComputationResultModel fullResult, ViewDeltaResultModel deltaResult) {
        s_logger.info("New result arrived for view '{}'", _viewDefinitionId);
        updateResults();
      }

    });
    _viewClient.attachToViewProcess(viewDefinitionId, viewportDefinition.getExecutionOptions());
  }

  // TODO make sure an update event is published when the view defs compile?
  private void initGrids(CompiledViewDefinition compiledViewDefinition) {
    synchronized (_lock) {
      PushWebViewPortfolioGrid portfolioGrid =
          new PushWebViewPortfolioGrid(_viewClient, compiledViewDefinition, _resultConverterCache);

      _gridStructures = new HashMap<String, Object>();

      if (portfolioGrid.getGridStructure().isEmpty()) {
        _portfolioGrid = null;
      } else {
        _portfolioGrid = portfolioGrid;
        _gridStructures.put("portfolio", _portfolioGrid.getInitialJsonGridStructure());
      }

      PushRequirementBasedWebViewGrid primitivesGrid =
          new PushWebViewPrimitivesGrid(_viewClient, compiledViewDefinition, _resultConverterCache);

      if (primitivesGrid.getGridStructure().isEmpty()) {
        _primitivesGrid = null;
      } else {
        _primitivesGrid = primitivesGrid;
        _gridStructures.put("primitives", _primitivesGrid.getInitialJsonGridStructure());
      }
      _initialized = true;
      _listener.gridStructureChanged();
      configureGridViewports();
    }
  }

  /* package */ void pause() {
    _viewClient.pause();
  }

  /* package */ void resume() {
    _viewClient.resume();
  }

  /* package */ void shutdown() {
    // Removes all listeners
    _viewClient.shutdown();
  }

  /* package */ boolean matches(UniqueId baseViewDefinitionId, ViewportDefinition viewportDefinition) {
    synchronized (_lock) {
      return _baseViewDefinitionId.equals(baseViewDefinitionId) &&
          ObjectUtils.equals(_viewportDefinition.getExecutionOptions(), viewportDefinition.getExecutionOptions()) &&
          ObjectUtils.equals(_viewportDefinition.getAggregatorName(), viewportDefinition.getAggregatorName());
    }
  }

  /**
   * Changes the {@link Viewport}.
   * @param viewportDefinition Definition of the new viewport
   * @param listener Listener for changes in the viewport's data or structure
   * @return The new viewport.
   */
  /* package */ Viewport configureViewport(ViewportDefinition viewportDefinition, AnalyticsListener listener) {
    synchronized (_lock) {
      _viewportDefinition = viewportDefinition;
      _listener = listener;
      configureGridViewports();
      return this;
    }
  }

  /**
   * Updates the grids to match {@link #_viewportDefinition}.
   */
  private void configureGridViewports() {
    if (!_initialized) {
      return;
    }
    _portfolioGrid.setViewport(_viewportDefinition.getPortfolioRows());
    _primitivesGrid.setViewport(_viewportDefinition.getPrimitiveRows());
    Set<WebGridCell> portfolioDepGraphCells = _viewportDefinition.getPortfolioDependencyGraphCells();
    Set<WebGridCell> primitiveDepGraphCells = _viewportDefinition.getPrimitiveDependencyGraphCells();
    // view cycle access is required for dep graph access but performance is better if it is disabled
    _viewClient.setViewCycleAccessSupported(!portfolioDepGraphCells.isEmpty() || !primitiveDepGraphCells.isEmpty());
    _portfolioGrid.updateDepGraphCells(portfolioDepGraphCells);
    _primitivesGrid.updateDepGraphCells(primitiveDepGraphCells);
    updateResults();
  }

  /**
   * Returns {@link #_viewClient}'s latest results if available.
   * @return {@link #_viewClient}'s latest results or {@code null} if there aren't any available
   */
  private ViewComputationResultModel getLatestResultModel() {
    if (!_viewClient.isResultAvailable()) {
      return null;
    }
    return _viewClient.getLatestResult();
  }
  
  /**
   * Updates {@link #_latestResults} with the latest results from the view client and notifies the listener
   * that the data has changed.
   */
  private void updateResults() {
    synchronized (_lock) {
      ViewComputationResultModel resultModel = getLatestResultModel();
      if (resultModel == null) {
        return;
      }
      long resultTimestamp = resultModel.getCalculationTime().toEpochMillisLong();
      Map<String, Object> portfolioResult = new HashMap<String, Object>();
      Map<String, Object> primitiveResult = new HashMap<String, Object>();

      for (ComputationTargetSpecification target : resultModel.getAllTargets()) {
        switch (target.getType()) {
          case PRIMITIVE:
            if (_primitivesGrid != null) {
              Map<String, Object> targetResult = _primitivesGrid.getTargetResult(target, resultModel.getTargetResult(target), resultTimestamp);
              if (targetResult != null) {
                Integer rowId = (Integer) targetResult.get("rowId");
                primitiveResult.put(Integer.toString(rowId), targetResult);
              }
            }
            break;
          case PORTFOLIO_NODE:
          case POSITION:
            if (_portfolioGrid != null) {
              Map<String, Object> targetResult = _portfolioGrid.getTargetResult(target, resultModel.getTargetResult(target), resultTimestamp);
              if (targetResult != null) {
                Integer rowId = (Integer) targetResult.get("rowId");
                portfolioResult.put(Integer.toString(rowId), targetResult);
              }
            }
        }
      }
      // TODO should these always be added or omitted if there are no dep graphs?
      if (_portfolioGrid != null) {
        portfolioResult.put("dependencyGraphs", _portfolioGrid.getDepGraphs(resultTimestamp));
      }
      if (_primitivesGrid != null) {
        primitiveResult.put("dependencyGraphs", _primitivesGrid.getDepGraphs(resultTimestamp));
      }
      _latestResults.clear();
      _latestResults.put("portfolio", portfolioResult);
      _latestResults.put("primitive", primitiveResult);
      if (_sendAnalyticsUpdates) {
        _sendAnalyticsUpdates = false;
        _listener.dataChanged();
      }
    }
  }

  @Override
  public Map<String, Object> getGridStructure() {
    synchronized (_lock) {
      return _gridStructures;
    }
  }

  @Override
  public Map<String, Object> getLatestResults() {
    synchronized (_lock) {
      _sendAnalyticsUpdates = true;
      return _latestResults;
    }
  }

  @Override
  public void setRunning(boolean run) {
    throw new UnsupportedOperationException("setRunning not implemented");
  }

  @Override
  public void setConversionMode(ConversionMode mode) {
    throw new UnsupportedOperationException("setConversionMode not implemented");
  }


  private Pair<String, String> getGridCsv(PushRequirementBasedWebViewGrid grid, String gridName) {
    synchronized (_lock) {
      if (grid == null) {
        return null;
      }
      ViewComputationResultModel latestResultModel = getLatestResultModel();
      if (latestResultModel == null) {
        return null;
      }
      String filename = _viewClient.getUniqueId() + "-" + gridName + "-" + latestResultModel.getValuationTime() + ".csv";
      String csv = grid.dumpContentsToCsv(latestResultModel);
      return Pair.of(filename, csv);
    }
  }
  @Override
  public Pair<String, String> getPortfolioCsv() {
    return getGridCsv(_portfolioGrid, "portfolio");
  }

  @Override
  public Pair<String, String> getPrimitivesCsv() {
    return getGridCsv(_primitivesGrid, "primitives");
  }

  private Pair<String, String> getDependencyGraphCsv(PushRequirementBasedWebViewGrid grid, String gridName, int row, int col) {
    synchronized (_lock) {
      if (grid == null) {
        return null;
      }
      PushWebViewGrid depGraphGrid = grid.getDepGraphGrid(row, col);
      if (depGraphGrid == null) {
        return null;
      }
      ViewComputationResultModel latestResultModel = getLatestResultModel();
      if (latestResultModel == null) {
        return null;
      }
      String filename = _viewClient.getUniqueId() + "-" + gridName + "[" + row + "," + col + latestResultModel.getValuationTime() + ".csv";
      String csv = depGraphGrid.dumpContentsToCsv(latestResultModel);
      return Pair.of(filename, csv);
    }
  }
  
  @Override
  public Pair<String, String> getPortfolioCsv(int row, int col) {
    return getDependencyGraphCsv(_portfolioGrid, "portfolio", row, col);
  }

  @Override
  public Pair<String, String> getPrimitivesCsv(int row, int col) {
    return getDependencyGraphCsv(_primitivesGrid, "primitives", row, col);
  }

  /* package */ UniqueId getBaseViewDefinitionId() {
    return _baseViewDefinitionId;
  }

  /* package */ String getAggregatorName() {
    return _viewportDefinition.getAggregatorName();
  }
}
