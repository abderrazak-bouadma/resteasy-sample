package com.cdc.pcp.api.providers;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class IOExceptionMapper implements ExceptionMapper<IOException> {

	@Override
	public Response toResponse(IOException exception) {
		return Response.status(new PCPStatusType(exception.getMessage())).build();
	}

}
