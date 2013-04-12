package com.cdc.pcp.api.resource;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.fest.assertions.Assertions;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

import com.cdc.pcp.api.bean.SampleListWrapper;
import com.cdc.pcp.api.bean.SampleVO;

public class SampleFunctionalTest {

	private final String ROOT_TEST_URL = "http://localhost:9095/parapheur-services/api/samples";

	@Test
	public void shouldMakePostRequest() throws Exception {
		ClientRequest request = new ClientRequest(ROOT_TEST_URL);
		request.body(MediaType.APPLICATION_JSON, new SampleVO("", "title", "description", new Date()));
		ClientResponse<SampleListWrapper> response = request.put(SampleListWrapper.class);
		SampleListWrapper entity = response.getEntity();
		Assertions.assertThat(entity.getSamples()).isNotNull().isNotEmpty().hasSize(1);
	}
}
