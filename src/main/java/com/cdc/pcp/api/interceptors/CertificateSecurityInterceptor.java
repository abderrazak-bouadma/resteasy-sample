package com.cdc.pcp.api.interceptors;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import com.cdc.pcp.api.util.CertificateHandler;
import com.cdc.pcp.common.manager.CryptoManager;
import com.cdc.pcp.common.manager.DefaultCryptoManager;
import com.cdc.pcp.common.model.UserInformation;
import com.cdc.pcp.common.service.PCPUserService;

/**
 * <p>
 * <a href=
 * "http://docs.jboss.org/resteasy/docs/1.1.GA/userguide/html/Interceptors.html"
 * >RESTEasy interceptor</a> used to authenticate the client by expecting a
 * {@link X509Certificate} instance and by doing some internal actions to
 * authenticate that user toward backend.
 * 
 * @since the beginning
 */
@Provider
@ServerInterceptor
public class CertificateSecurityInterceptor implements PreProcessInterceptor {

	private static final Log LOG = LogFactory.getLog(CertificateSecurityInterceptor.class);

	private CertificateHandler certificateHandler = new CertificateHandler();
	private CryptoManager cryptoManager = new DefaultCryptoManager();
	private PCPUserService pcpUserService;

	public CertificateSecurityInterceptor() {
		pcpUserService = (PCPUserService) InterceptorSpringContextLoader.factory().getSpringContext().getBean("pcpUserService");
	}

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {

		if (certificateHandler.isClientCertificateAvailableInHttpRequest(request)) {
			try {
				X509Certificate certificate = certificateHandler.getClientCertificate(request);
				String username = certificateHandler.getDNParameter(certificate, CertificateHandler.EMAIL_DN_ELEMENT_KEY);
				String password = cryptoManager.generateClientCertificatePassword(username);
				UserInformation userInformation = processLoginAttempt(username, password);
				request.setAttribute(CertificateHandler.USERNAME_HTTP_REQUEST_KEY, userInformation.getUsername());
			} catch (CertificateException e) {
				LOG.error("USER IS NOT AUTHORIZED", e);
				throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).build());
			} catch (Exception e) {
				LOG.error("USER IS NOT AUTHORIZED", e);
				throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		} else {
			List<String> requestHeader = request.getHttpHeaders().getRequestHeader("Referer");
			if ( requestHeader!=null && !requestHeader.isEmpty()) {
				String referer = requestHeader.get(0);
				//TODO hack used to bypass the Security to 'swagg' a little bit !
				if (referer != null && referer.contains("/swagger")) {
					return null;
				}
			}
			return (ServerResponse) Response.status(Response.Status.UNAUTHORIZED).build();
		}
		return null;
	}

	/**
	 * Authentification en utilisant le service Alfresco exporte par RMI
	 * 
	 * @throws Exception
	 */
	private UserInformation processLoginAttempt(String username, String password) throws Exception {
		if (username == null || password == null) {
			return null;
		}
		if (username.trim().isEmpty()) {
			return null;
		}
		return pcpUserService.doLogin(username, password);

	}

}
