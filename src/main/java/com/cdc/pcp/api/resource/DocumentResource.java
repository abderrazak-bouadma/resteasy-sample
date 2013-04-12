package com.cdc.pcp.api.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.cdc.pcp.api.util.BeanConverter;
import com.cdc.pcp.common.manager.Extension;
import com.cdc.pcp.common.model.Annexe;
import com.cdc.pcp.common.model.Commentaire;
import com.cdc.pcp.common.model.HistoricalEntry;
import com.cdc.pcp.common.model.MetaDataList;
import com.cdc.pcp.common.model.ParapheurNodeInformation;
import com.cdc.pcp.common.model.UserInformation;
import com.cdc.pcp.common.service.PCPExtensionService;
import com.cdc.pcp.common.service.PCPPESService;
import com.cdc.pcp.common.service.PCPTaskService;
import com.cdc.pcp.common.service.PCPWorkflowService;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "/documents", description = "Every thing related to a Node within Alfresco")
@Path("/documents")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class DocumentResource extends PCPGenericResource {

	private static final String DEFAULT_XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static final String XML_PES_XSLT_DECLARATION = "<?xml-stylesheet type=\"text/xsl\" href=\"xsl/PES2XHTML-iframe.xslt\"?>";
	private static final String XML_XHL_XSLT_DECLARATION = "<?xml-stylesheet type=\"text/xsl\" href=\"xsl/XHL2XHTML-iframe.xslt\"?>";

	@Autowired
	private PCPTaskService taskService;
	@Autowired
	private PCPWorkflowService workflowService;
	@Autowired
	private PCPExtensionService extensionService;
	@Autowired
	private PCPPESService pesService;

	@GET
	public Response getAllDocuments() {
		UserInformation userInformation = userService.getUserInformation(getUsername());
		try {
			return Response.ok(BeanConverter.toDocumentVOList(taskService.getTaskList(userInformation))).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@ApiOperation(value = "gets metadatas of the document", notes = "Retourne Les MetasData du document indique", responseClass = "List[com.cdc.pcp.api.bean.MetaDataVO]")
	@GET
	@Path("/{id}/metas")
	public Response getMetas(@PathParam("id") String id) {
		
		if (hasPermissionToAccessDocument(id)) {
				ParapheurNodeInformation nodeInformation = nodeService.getParapheurNodeInformation(id);
				MetaDataList metadatalist = nodeService.getMetaDataList(nodeInformation);
				
				if(metadatalist.getMetaDataList().isEmpty())
					return Response.noContent().build();
				else
					return Response.ok(BeanConverter.toMetaDataVOList(metadatalist.getMetaDataList())).build();

		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/{id}/notes")
	@ApiOperation(value = "gets notes of the document", notes = "Retourne Les notes", responseClass = "List[com.cdc.pcp.api.bean.NoteVO]")
	public Response getNotes(@PathParam("id") String id) throws Exception {
	
		if (hasPermissionToAccessDocument(id)) {
				ParapheurNodeInformation nodeInformation = nodeService.getParapheurNodeInformation(id);
				List<Commentaire> commentaires = nodeService.getDocComments(nodeInformation);
				
				if(commentaires.isEmpty())
					return Response.noContent().build();
				else
					return Response.ok(BeanConverter.toNoteVOList(commentaires)).build();
			
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/{id}/attachments")
	@ApiOperation(value = "gets attachments of the document", notes = "Retourne Les pieces jointes", responseClass = "List[com.cdc.pcp.api.bean.AttachmentVO]")
	public Response getAttachments(@PathParam("id") String id) {

		if (hasPermissionToAccessDocument(id)) {
			try {
				ParapheurNodeInformation nodeInformation = nodeService.getParapheurNodeInformation(id);
				List<Annexe> annexes = nodeService.getDocAnnexes(nodeInformation);
				
				if(annexes.isEmpty())
					return Response.noContent().build();
				else
					return Response.ok(BeanConverter.toAttachmentVOList(annexes)).build();
				
			} catch (Exception e) {
				return Response.noContent().build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/{id}/actions")
	@ApiOperation(value = "gets actions of the document", notes = "Retourne Les Actions", responseClass = "List[com.cdc.pcp.api.bean.ActionVO]")
	public Response getActions(@PathParam("id") String id) {

		if (hasPermissionToAccessDocument(id)) {
			try {
				UserInformation userInformation = userService.getUserInformation(getUsername());
				List<String> taskTransitions = taskService.getTaskTransitions(id, userInformation);
				
				if(taskTransitions.isEmpty())
					return Response.noContent().build();
				else
					return Response.ok(BeanConverter.toActionVOList(taskTransitions)).build();
				
			} catch (Exception e) {
				return Response.noContent().build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/{id}/history")
	@ApiOperation(value = "gets history of the document", notes = "Retourne l'historique", responseClass = "List[com.cdc.pcp.api.bean.HistoricalEntryVO]")
	public Response getHistory(@PathParam("id") String id) {

		if (hasPermissionToAccessDocument(id)) {
			try {
				UserInformation userInformation = userService.getUserInformation(getUsername());
				List<HistoricalEntry> HistoricalEntryList = workflowService.getHistory(id, userInformation);
				
				if(HistoricalEntryList.isEmpty())
					return Response.noContent().build();
				else
					return Response.ok(BeanConverter.toHistoricalEntryVOList(HistoricalEntryList)).build();
				
			} catch (Exception e) {
				return Response.noContent().build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/{id}/content")
	@ApiOperation(value = "gets content of the document", notes = "Retourne le contenu")
	public Response getContent(@PathParam("id") String id) throws IOException {

		if (hasPermissionToAccessDocument(id)) {

			ParapheurNodeInformation nodeInformation = nodeService.getParapheurNodeInformation(id);
			Extension extension = extensionService.getExtensionForFilename(nodeInformation.getFilename());

			// positionnement des headers HTTP
			processHttpHeaders(nodeInformation, extension);

			//
			OutputStream responseOutputStream = httpResponse.getOutputStream();
			RemoteInputStream remoteInputStream = nodeService.getNodeInputStream(id);
			InputStream fileInputStream = RemoteInputStreamClient.wrap(remoteInputStream);

			// traitement dans le cas du PES
			if (extension.equals(Extension.XML_EXTENSION)) {
				processPESDocument(id, nodeInformation, extension, responseOutputStream, fileInputStream);
			}

			// chargment du document et envoi
			FileCopyUtils.copy(fileInputStream, responseOutputStream);

			return null;
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	private void processPESDocument(String id, ParapheurNodeInformation nodeInformation, Extension extension, OutputStream responseOutputStream,
			InputStream fileInputStream) {
		try {
			boolean isCurrentDocumentPES = pesService.isPESDocument(nodeInformation.getFilename(), fileInputStream, true,
					nodeInformation.getCinematique());
			if (isCurrentDocumentPES || extension.equals(Extension.XHL_EXTENSION)) {
				// TODO use NIO
				byte[] xmlBeginningBytes = new byte[1024];
				int bytesRead = fileInputStream.read(xmlBeginningBytes);
				String xmlBeginningString = new String(xmlBeginningBytes, 0, bytesRead);
				int indexAfterXmlDeclaration;
				if (xmlBeginningString.indexOf("<?") != -1) {
					indexAfterXmlDeclaration = xmlBeginningString.indexOf("?>") + 2;
					String xmlDeclaration = xmlBeginningString.substring(0, indexAfterXmlDeclaration);
					responseOutputStream.write(xmlDeclaration.getBytes());
				} else {
					indexAfterXmlDeclaration = 0;
					responseOutputStream.write(DEFAULT_XML_DECLARATION.getBytes());
				}
				if (isCurrentDocumentPES)
					responseOutputStream.write(XML_PES_XSLT_DECLARATION.getBytes());
				else
					responseOutputStream.write(XML_XHL_XSLT_DECLARATION.getBytes());

				responseOutputStream.write(xmlBeginningString.substring(indexAfterXmlDeclaration).getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void processHttpHeaders(ParapheurNodeInformation nodeInformation, Extension extension) {
		httpResponse.setHeader("Content-Length", String.valueOf(nodeInformation.getFileSize()));
		httpResponse.setContentType(extension.contentType);
		if (extension.isPragmaPrivate)
			httpResponse.setHeader("pragma", "private");
	}

	@GET
	@Path("/hello/{yourName}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Says Hello :)", notes = "Says Hello :)")
	public Response sayHello(@PathParam("yourName") String yourName) {
		return Response.ok("Hello " + yourName).build();
	}

}