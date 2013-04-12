package com.cdc.pcp.api.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdc.pcp.api.interceptors.CertificateSecurityInterceptor;
import com.cdc.pcp.api.util.CertificateHandler;
import com.cdc.pcp.common.service.PCPNodeService;
import com.cdc.pcp.common.service.PCPUserService;

/**
 * <p>
 * Makes {@link HttpServletRequest} and {@link HttpServletResponse} available to
 * children classes. Also the username of the current authenticated user.
 */
public abstract class PCPGenericResource {

	@Context
	protected HttpServletRequest httpRequest;
	@Context
	protected HttpServletResponse httpResponse;
	@Autowired
	PCPNodeService nodeService;
	@Autowired
	PCPUserService userService;

	private static final Log LOGGER = LogFactory.getLog(PCPGenericResource.class);

	/**
	 * <p>
	 * The <code>username</code> is retrieved from the
	 * {@link HttpServletRequest} which was already populated the attribut
	 * {@link CertificateHandler#USERNAME_HTTP_REQUEST_KEY} by the
	 * {@link CertificateSecurityInterceptor}
	 * 
	 * @return authenticated username and never could be <code>null</code> as
	 *         {@link CertificateSecurityInterceptor} throws a
	 *         {@link Status#UNAUTHORIZED} status if the user is unrecognized.
	 */
	protected final String getUsername() {
		String username = (String) httpRequest.getAttribute(CertificateHandler.USERNAME_HTTP_REQUEST_KEY);
		LOGGER.info(username + " Authenticated user :" + username + " on User-Agent :" + httpRequest.getHeader("User-Agent"));
		return username;
	}

	protected boolean hasPermissionToAccessDocument(String nodeRefId) {
		return nodeService.hasPermission(nodeService.getParapheurNodeInformation(nodeRefId), userService.getUserInformation(getUsername()));
	}
}
