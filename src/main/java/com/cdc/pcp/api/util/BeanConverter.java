package com.cdc.pcp.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cdc.pcp.api.bean.ActionVO;
import com.cdc.pcp.api.bean.AttachmentVO;
import com.cdc.pcp.api.bean.DocumentVO;
import com.cdc.pcp.api.bean.HistoricalEntryVO;
import com.cdc.pcp.api.bean.MetaDataVO;
import com.cdc.pcp.api.bean.NoteVO;
import com.cdc.pcp.api.bean.PreferencesVO;
import com.cdc.pcp.common.model.Annexe;
import com.cdc.pcp.common.model.Commentaire;
import com.cdc.pcp.common.model.HistoricalEntry;
import com.cdc.pcp.common.model.MetaData;
import com.cdc.pcp.common.model.ParapheurNodeInformation;
import com.cdc.pcp.common.model.UserInformation;
import com.ibm.icu.text.SimpleDateFormat;

public class BeanConverter {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd/MM/yyyy");

	public static List<DocumentVO> toDocumentVOList(List<ParapheurNodeInformation> nodeInformationList) {
		List<DocumentVO> result = new ArrayList<DocumentVO>();
		for (ParapheurNodeInformation nodeInformation : nodeInformationList) {
			DocumentVO doc = new DocumentVO();
			doc.setId(nodeInformation.getNodeRefId());
			doc.setFilename(nodeInformation.getFilename());
			doc.setLabel(nodeInformation.getLibelle());
			doc.setCircuit(nodeInformation.getCinematique().getNom());
			doc.setSubscriber(nodeInformation.getCinematique().getAbonne().getSiren());
			result.add(doc);
		}
		return result;
	}
	
	public static PreferencesVO toPreferencesVo(Properties preferences) {
		PreferencesVO result = new PreferencesVO();
		result.setAutoFiling(Boolean.valueOf(preferences.getProperty(UserInformation.AUTO_FILING)));
		result.setAutoSendForSign(Boolean.valueOf(preferences.getProperty(UserInformation.AUTO_SENDFORSIGN)));
		result.setAutoSendForVisa(Boolean.valueOf(preferences.getProperty(UserInformation.AUTO_SENDFORVISA)));
		result.setAutoValidation(Boolean.valueOf(preferences.getProperty(UserInformation.AUTO_VALIDATION)));
		result.setCarrouselTimer(Integer.valueOf(preferences.getProperty(UserInformation.CARROUSEL_TIMER)));
		result.setNotifAcknowledgedResult(Boolean.valueOf(preferences.getProperty(UserInformation.NOTIF_ACKNOWLEDGED_RESULT)));
		result.setNotifApproved(Boolean.valueOf(preferences.getProperty(UserInformation.NOTIF_APPROVED)));	
		result.setNotifApprovedResult(Boolean.valueOf(preferences.getProperty(UserInformation.NOTIF_APPROVED_RESULT)));
		result.setNotifEmails(preferences.getProperty(UserInformation.NOTIF_EMAILS));
		result.setNotifReceived(Boolean.valueOf(preferences.getProperty(UserInformation.NOTIF_RECEIVED)));
		result.setNotifSigned(Boolean.valueOf(preferences.getProperty(UserInformation.NOTIF_SIGNED)));
		result.setNotifSignedResult(Boolean.valueOf(preferences.getProperty(UserInformation.NOTIF_SIGNED_RESULT)));
		result.setPeriodicAlertDays(preferences.getProperty(UserInformation.PERIODIC_ALERTS_DAYS));
		result.setPeriodicAlertsEnabled(Boolean.valueOf(preferences.getProperty(UserInformation.PERIODIC_ALERTS_ENABLED)));
		result.setPreferedAbonne(preferences.getProperty(UserInformation.PREFERED_ABONNE));
		result.setSignataireRole(preferences.getProperty(UserInformation.SIGNATAIRE_ROLE));
		result.setTableSortType(preferences.getProperty(UserInformation.TABLE_SORT_TYPE));
		return result;
	}
	
	public static List<NoteVO> toNoteVOList(List<Commentaire> commentaires) {
		List<NoteVO> result = new ArrayList<NoteVO>();
		for (Commentaire commentaire : commentaires) {
			NoteVO note = new NoteVO();
			note.setAuthor(commentaire.getAuteur());
			note.setMessage(commentaire.getMessage());
			note.setDate(commentaire.getDate());
			result.add(note);
		}
		return result;
	}
	
	public static List<MetaDataVO> toMetaDataVOList(List<MetaData> metadatas) {
		List<MetaDataVO> result = new ArrayList<MetaDataVO>();
		for (MetaData metadata : metadatas) {
			MetaDataVO metadataVO = new MetaDataVO();
			metadataVO.setName(metadata.getName());
			metadataVO.setValue(metadata.getValue());
			result.add(metadataVO);
		}
		return result;
	}
	
	public static List<AttachmentVO> toAttachmentVOList(List<Annexe> annexes) {
		List<AttachmentVO> result = new ArrayList<AttachmentVO>();
		for (Annexe annexe : annexes) {
			AttachmentVO attachmentVO = new AttachmentVO();
			attachmentVO.setDate(annexe.getDate());
			attachmentVO.setFilename(annexe.getFilename());
			attachmentVO.setId(annexe.getNoderefid());
			attachmentVO.setUsername(annexe.getUtilisateur());
			attachmentVO.setAction(annexe.getAction());
			result.add(attachmentVO);
		}
		return result;
	}
	
	public static List<ActionVO> toActionVOList(List<String> actions) {
		List<ActionVO> result = new ArrayList<ActionVO>();
		for (String action : actions) {
			result.add(new ActionVO(action));
		}
		return result;
	}
	
	public static List<HistoricalEntryVO> toHistoricalEntryVOList(List<HistoricalEntry> history) {
		List<HistoricalEntryVO> result = new ArrayList<HistoricalEntryVO>();
		for (HistoricalEntry historicalEntry : history) {
			HistoricalEntryVO historicalEntryVO = new HistoricalEntryVO();
			historicalEntryVO.setDate(historicalEntry.getDate());
			historicalEntryVO.setUsername(historicalEntry.getUsername());
			historicalEntryVO.setTransition(historicalEntry.getTransition());
			result.add(historicalEntryVO);
		}
		return result;
	}
	
	
	
}
