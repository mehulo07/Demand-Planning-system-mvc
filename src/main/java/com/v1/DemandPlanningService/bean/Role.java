package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class Role implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = -8215846597353060643L;

	private String role_id;
	private String role_name;
	private String status;
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "RoleBean [role_id=" + role_id + ", role_name=" + role_name + ", status=" + status + "]";
	}
	
}
