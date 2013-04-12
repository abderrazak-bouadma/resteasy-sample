package com.cdc.pcp.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import com.cdc.pcp.api.bean.SampleListWrapper;
import com.cdc.pcp.api.bean.SampleVO;
import com.wordnik.swagger.annotations.Api;

@Path("/samples")
@Api(value = "/samples", description = "Sample Resource")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

	private static SampleListWrapper data;

	public SampleResource() {
		data = new SampleListWrapper();
	}

	@GET
	public Response findAll() {
		return Response.ok().entity(data).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@BadgerFish
	public Response addSample(@GZIP SampleVO sampleVO) {
		data.add(sampleVO);
		return Response.ok().entity(data).build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeSample(@PathParam("id") String id) {
		if (data.removeById(id)) {
			return Response.ok().entity(data).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

}
