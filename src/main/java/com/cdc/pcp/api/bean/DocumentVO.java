package com.cdc.pcp.api.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentVO {
	
	private String id;
	private String filename;
	private String label;
	private String circuit;
	private String subscriber;
	
	public DocumentVO() {
		// TODO Auto-generated constructor stub
	}
	
	public DocumentVO(String noderefid, String filename, String label, String circuit, String subscriber) {
		super();
		this.id = noderefid;
		this.filename = filename;
		this.label = label;
		this.circuit = circuit;
		this.subscriber = subscriber;
	}

	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCircuit() {
		return circuit;
	}
	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}
	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
