package com.v1.DemandPlanningService.bean;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {

	/**
	 * @author makwameh
	 */
	
	private static final long serialVersionUID = -534203874992084245L;

	//Shoud be UserId
	private String custId;
	private String password;
	private String userName;
	private String userEmail;
	private String userRoleId;
	private String userRoleName;
	private String status;
	private boolean isAuthenticated;
	private List<String> allowedCategory;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public List<String> getAllowedCategory() {
		return allowedCategory;
	}
	public void setAllowedCategory(List<String> allowedCategory) {
		this.allowedCategory = allowedCategory;
	}

	@Override
	public String toString() {
		return "UserInfo [custId=" + custId + ", password=" + password + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userRoleId=" + userRoleId + ", userRoleName=" + userRoleName + ", status=" + status
				+ ", allowedCategory=" + allowedCategory + "]";
	}
	
}
