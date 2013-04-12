package com.cdc.pcp.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdc.pcp.api.util.BeanConverter;
import com.cdc.pcp.common.model.UserInformation;
import com.cdc.pcp.common.service.PCPTaskService;
import com.wordnik.swagger.annotations.Api;

@Api(value = "/preferences", description = "Everything related to preferences users")
@Path("/preferences")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class PreferencesResource extends PCPGenericResource {

	@Autowired
	private PCPTaskService taskService;


	@GET
	public Response getPreferences() {
		UserInformation userInformation = userService.getUserInformation(getUsername());
		try {
			return Response.ok(BeanConverter.toDocumentVOList(taskService.getTaskList(userInformation))).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
