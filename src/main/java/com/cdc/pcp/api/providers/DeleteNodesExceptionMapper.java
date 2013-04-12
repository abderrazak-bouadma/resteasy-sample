package com.cdc.pcp.api.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cdc.pcp.common.service.exception.DeleteNodesException;

@Provider
public class DeleteNodesExceptionMapper implements ExceptionMapper<DeleteNodesException> {

	@Override
	public Response toResponse(DeleteNodesException exception) {
		return Response.status(new PCPStatusType(exception.getMessage())).build();
	}

}
