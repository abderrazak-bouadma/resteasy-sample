package com.cdc.pcp.api.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AbonneVO {

	private String siren;
	private String name;
	public String getSiren() {
		return siren;
	}
	public void setSiren(String siren) {
		this.siren = siren;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
