package com.cdc.pcp.api.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoricalEntryVO {

	private String username;
	private Date date;
	private String transition;
	private String state;
	
	public HistoricalEntryVO() {
		
	}

	public HistoricalEntryVO(String noderefid, String username, Date date, String transition, String state) {
		super();
		this.username = username;
		this.date = date;
		this.transition = transition;
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransition() {
		return transition;
	}

	public void setTransition(String transition) {
		this.transition = transition;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
