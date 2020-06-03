package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String email;
	private String password;
	private String userDepartment;
	private String userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;	
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", userDepartment=" + userDepartment + ", userRole=" + userRole + "]";
	}
	
}
