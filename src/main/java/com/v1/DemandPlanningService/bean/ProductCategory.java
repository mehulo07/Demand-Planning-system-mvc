package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class ProductCategory implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = 4082962895991073123L;
	
	private String prod_cat_id;
	private String prod_cat_name;
	private String prod_cat_desc;
	private String status;
	private String userAccessStatus;
	
	public String getProd_cat_id() {
		return prod_cat_id;
	}
	public void setProd_cat_id(String prod_cat_id) {
		this.prod_cat_id = prod_cat_id;
	}
	public String getProd_cat_name() {
		return prod_cat_name;
	}
	public void setProd_cat_name(String prod_cat_name) {
		this.prod_cat_name = prod_cat_name;
	}
	public String getProd_cat_desc() {
		return prod_cat_desc;
	}
	public void setProd_cat_desc(String prod_cat_desc) {
		this.prod_cat_desc = prod_cat_desc;
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
		return "ProductCategoryBean [prod_cat_id=" + prod_cat_id + ", prod_cat_name=" + prod_cat_name
				+ ", prod_cat_desc=" + prod_cat_desc + ", status=" + status + ", userAccessStatus=" + userAccessStatus
				+ "]";
	}
	
}
