package com.cdc.pcp.api.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PreferencesVO {
	
	private boolean notifSignedResult;
	private boolean notifApprovedResult;
	private boolean notifAcknowledgedResult;

	private boolean notifReceived;
	private boolean notifApproved;
	private boolean notifSigned;
	private String signataireRole;
	
	private String notifEmails;	
	
	private boolean autoSendForVisa;
	private boolean autoSendForSign;
	private boolean autoValidation;
	private boolean autoFiling;
	
	private int carrouselTimer;
	private String preferedAbonne;
	
	private String tableSortType;
	
	private boolean periodicAlertsEnabled;
	private String periodicAlertDays;
	
	public PreferencesVO() {
		// TODO Auto-generated constructor stub
	}

	public PreferencesVO(boolean notifSignedResult, boolean notifApprovedResult, boolean notifAcknowledgedResult, boolean notifReceived, boolean notifApproved,
			boolean notifSigned, String signataireRole, String notifEmails, boolean autoSendForVisa, boolean autoSendForSign, boolean autoValidation,
			boolean autoFiling, int carrouselTimer, String preferedAbonne, String tableSortType, boolean periodicAlertsEnabled, String periodicAlertDays) {
		super();
		this.notifSignedResult = notifSignedResult;
		this.notifApprovedResult = notifApprovedResult;
		this.notifAcknowledgedResult = notifAcknowledgedResult;
		this.notifReceived = notifReceived;
		this.notifApproved = notifApproved;
		this.notifSigned = notifSigned;
		this.signataireRole = signataireRole;
		this.notifEmails = notifEmails;
		this.autoSendForVisa = autoSendForVisa;
		this.autoSendForSign = autoSendForSign;
		this.autoValidation = autoValidation;
		this.autoFiling = autoFiling;
		this.carrouselTimer = carrouselTimer;
		this.preferedAbonne = preferedAbonne;
		this.tableSortType = tableSortType;
		this.periodicAlertsEnabled = periodicAlertsEnabled;
		this.periodicAlertDays = periodicAlertDays;
	}
	
	public boolean getNotifSignedResult() {
		return notifSignedResult;
	}

	public void setNotifSignedResult(boolean notifSignedResult) {
		this.notifSignedResult = notifSignedResult;
	}

	public boolean getNotifApprovedResult() {
		return notifApprovedResult;
	}

	public void setNotifApprovedResult(boolean notifApprovedResult) {
		this.notifApprovedResult = notifApprovedResult;
	}

	public boolean getNotifAcknowledgedResult() {
		return notifAcknowledgedResult;
	}

	public void setNotifAcknowledgedResult(boolean notifAcknowledgedResult) {
		this.notifAcknowledgedResult = notifAcknowledgedResult;
	}

	public boolean getNotifReceived() {
		return notifReceived;
	}

	public void setNotifReceived(boolean notifReceived) {
		this.notifReceived = notifReceived;
	}

	public boolean getNotifApproved() {
		return notifApproved;
	}

	public void setNotifApproved(boolean notifApproved) {
		this.notifApproved = notifApproved;
	}

	public boolean getNotifSigned() {
		return notifSigned;
	}

	public void setNotifSigned(boolean notifSigned) {
		this.notifSigned = notifSigned;
	}

	public String getSignataireRole() {
		return signataireRole;
	}

	public void setSignataireRole(String signataireRole) {
		this.signataireRole = signataireRole;
	}

	public String getNotifEmails() {
		return notifEmails;
	}

	public void setNotifEmails(String notifEmails) {
		this.notifEmails = notifEmails;
	}

	public boolean getAutoSendForVisa() {
		return autoSendForVisa;
	}

	public void setAutoSendForVisa(boolean autoSendForVisa) {
		this.autoSendForVisa = autoSendForVisa;
	}

	public boolean getAutoSendForSign() {
		return autoSendForSign;
	}

	public void setAutoSendForSign(boolean autoSendForSign) {
		this.autoSendForSign = autoSendForSign;
	}

	public boolean getAutoValidation() {
		return autoValidation;
	}

	public void setAutoValidation(boolean autoValidation) {
		this.autoValidation = autoValidation;
	}

	public boolean getAutoFiling() {
		return autoFiling;
	}

	public void setAutoFiling(boolean autoFiling) {
		this.autoFiling = autoFiling;
	}

	public int getCarrouselTimer() {
		return carrouselTimer;
	}

	public void setCarrouselTimer(int carrouselTimer) {
		this.carrouselTimer = carrouselTimer;
	}

	public String getPreferedAbonne() {
		return preferedAbonne;
	}

	public void setPreferedAbonne(String preferedAbonne) {
		this.preferedAbonne = preferedAbonne;
	}

	public String getTableSortType() {
		return tableSortType;
	}

	public void setTableSortType(String tableSortType) {
		this.tableSortType = tableSortType;
	}

	public boolean getPeriodicAlertsEnabled() {
		return periodicAlertsEnabled;
	}

	public void setPeriodicAlertsEnabled(boolean periodicAlertsEnabled) {
		this.periodicAlertsEnabled = periodicAlertsEnabled;
	}

	public String getPeriodicAlertDays() {
		return periodicAlertDays;
	}

	public void setPeriodicAlertDays(String periodicAlertDays) {
		this.periodicAlertDays = periodicAlertDays;
	}

}
