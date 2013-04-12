package com.cdc.pcp.api.providers;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

public class PCPStatusType implements StatusType {

	private String message;
	private Status status;
	private Family family = Family.SERVER_ERROR;

	private PCPStatusType() {
	}

	public PCPStatusType with(String message) {
		this.message = message;
		return this;
	}

	public PCPStatusType with(Status status) {
		this.status = status;
		return this;
	}

	public PCPStatusType with(Family family) {
		this.family = family;
		return this;
	}

	public static PCPStatusType factory() {
		return new PCPStatusType();
	}

	public PCPStatusType(String message) {
		this.message = message;
	}

	@Override
	public int getStatusCode() {
		return status.getStatusCode();
	}

	@Override
	public Family getFamily() {
		return family;
	}

	@Override
	public String getReasonPhrase() {
		return message;
	}

}
