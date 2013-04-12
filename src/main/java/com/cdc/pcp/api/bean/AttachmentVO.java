package com.cdc.pcp.api.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentVO {

	private Date date;
	private String username;
	private String id;
	private String action;
	private String filename;
	
	public AttachmentVO(){
		
	}
	
	public AttachmentVO(Date date, String username, String id, String action, String filename) {
		super();
		this.date = date;
		this.username = username;
		this.id = id;
		this.action = action;
		this.filename = filename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
