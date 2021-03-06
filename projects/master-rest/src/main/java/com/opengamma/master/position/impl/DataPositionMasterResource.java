/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Copyright (C) 2015 - present by McLeod Moores Software Limited.
 *
 * Please see distribution for license.
 */
package com.opengamma.master.position.impl;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.opengamma.id.ObjectId;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.rest.AbstractDataResource;

/**
 * RESTful resource for positions.
 * <p>
 * The positions resource receives and processes RESTful calls to the position master.
 */
@Path("positionMaster")
public class DataPositionMasterResource extends AbstractDataResource {

  /**
   * The position master.
   */
  private final PositionMaster _posMaster;

  /**
   * Creates the resource, exposing the underlying master over REST.
   *
   * @param positionMaster  the underlying position master, not null
   */
  public DataPositionMasterResource(final PositionMaster positionMaster) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    _posMaster = positionMaster;
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the position master.
   *
   * @return the position master, not null
   */
  public PositionMaster getPositionMaster() {
    return _posMaster;
  }

  //-------------------------------------------------------------------------
  @GET
  public Response getHateaos(@Context final UriInfo uriInfo) {
    return hateoasResponse(uriInfo);
  }

  @HEAD
  @Path("positions")
  public Response status() {
    // simple HEAD to quickly return, avoiding loading the whole database
    return responseOk();
  }

  @POST
  @Path("positionSearches")
  public Response search(final PositionSearchRequest request) {
    final PositionSearchResult result = getPositionMaster().search(request);
    return responseOkObject(result);
  }

  @POST
  @Path("positions")
  public Response add(@Context final UriInfo uriInfo, final PositionDocument request) {
    final PositionDocument result = getPositionMaster().add(request);
    final URI createdUri = new DataPositionResource().uriVersion(uriInfo.getBaseUri(), result.getUniqueId());
    return responseCreatedObject(createdUri, result);
  }

  //-------------------------------------------------------------------------
  @Path("positions/{positionId}")
  public DataPositionResource findPosition(@PathParam("positionId") final String idStr) {
    final ObjectId id = ObjectId.parse(idStr);
    return new DataPositionResource(this, id);
  }

  @Path("trades/{tradeId}")
  public DataTradeResource findTrade(@PathParam("tradeId") final String idStr) {
    final ObjectId id = ObjectId.parse(idStr);
    return new DataTradeResource(this, id);
  }

}
