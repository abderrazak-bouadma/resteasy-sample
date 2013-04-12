package com.cdc.pcp.api.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cdc.pcp.common.service.exception.ParapheurNodeException;
import com.cdc.pcp.common.service.exception.ParapheurNodeException.ParapheurNodeTypeException;

@Provider
public class ParapheurNodeExceptionMapper implements ExceptionMapper<ParapheurNodeException> {

	@Override
	public Response toResponse(ParapheurNodeException exception) {

		if (exception.getType().equals(ParapheurNodeTypeException.INVALID_NODEREF_EXCEPTION))
			return Response.status(PCPStatusType.factory().with(Status.NOT_FOUND).with(exception.getMessage())).build();
		else {
			return Response.status(PCPStatusType.factory().with(Status.INTERNAL_SERVER_ERROR).with(exception.getMessage())).build();
		}
	}

}
