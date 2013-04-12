package com.cdc.pcp.api.resource;

import static org.fest.assertions.Assertions.assertThat;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

public class DocumentResourceFunctionalTest {

	private final String ROOT_TEST_URL = "http://localhost:9095/parapheur-services/api/documents";

	@Test
	public void documentResourceShouldSayHello() throws Exception {
		String username = "John Doe";
		ClientRequest request = new ClientRequest(ROOT_TEST_URL + "/hello" + "/" + username);
		ClientResponse<String> response = request.get(String.class);
		String message = response.getEntity();
		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(message).isNotNull().isNotEmpty().isEqualTo("Hello " + username);
	}
}
