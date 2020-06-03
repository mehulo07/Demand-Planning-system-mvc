package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class Request extends UserInfo implements Serializable  {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = -7983044545945617431L;
	
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
