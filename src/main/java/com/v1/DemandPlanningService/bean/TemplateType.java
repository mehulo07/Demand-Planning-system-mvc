package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class TemplateType implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = 7170591240965596954L;
	
	private String prod_type_id;
	private String prod_type_name;
	private String prod_type_desc;
	private String status;
	private String userAccessStatus; 
	
	public String getProd_type_id() {
		return prod_type_id;
	}
	public void setProd_type_id(String prod_type_id) {
		this.prod_type_id = prod_type_id;
	}
	public String getProd_type_name() {
		return prod_type_name;
	}
	public void setProd_type_name(String prod_type_name) {
		this.prod_type_name = prod_type_name;
	}
	public String getProd_type_desc() {
		return prod_type_desc;
	}
	public void setProd_type_desc(String prod_type_desc) {
		this.prod_type_desc = prod_type_desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserAccessStatus() {
		return userAccessStatus;
	}
	public void setUserAccessStatus(String userAccessStatus) {
		this.userAccessStatus = userAccessStatus;
	}
	
	@Override
	public String toString() {
		return "TemplateTypeBean [prod_type_id=" + prod_type_id + ", prod_type_name=" + prod_type_name
				+ ", prod_type_desc=" + prod_type_desc + ", status=" + status + ", userAccessStatus=" + userAccessStatus + "]";
	}
	
}
