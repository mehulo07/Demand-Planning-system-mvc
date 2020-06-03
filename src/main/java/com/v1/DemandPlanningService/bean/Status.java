package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class Status implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = 2898555620097826405L;
	
	private int statusCode;
	private String statusMessage;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "StatusBean [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}
	
}
