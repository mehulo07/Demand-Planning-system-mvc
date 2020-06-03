package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class Authentication implements Serializable{

	/**
	 * @author makwameh
	 */
	
	private static final long serialVersionUID = 7991672615729937231L;
	
	private String username;
	private String password;
	private String isActive;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "RequestBean [username=" + username + ", password=" + password + ", isActive=" + isActive + "]";
	}
	
}
