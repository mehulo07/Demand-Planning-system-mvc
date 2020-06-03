package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class StockCategory implements Serializable {

	/**
	 * @author makwameh 
	 */
	private static final long serialVersionUID = -6140863616715708467L;
	
	private String stock_cat_id;
	private String stock_cat_name;
	private String stock_cat_desc;
	private String stock_cat_color_id;
	private String status;
	private String userAccessStatus;
	private String stock_cat_color_name;
	private String stock_cat_color_code;
	
	public String getStock_cat_color_code() {
		return stock_cat_color_code;
	}
	public void setStock_cat_color_code(String stock_cat_color_code) {
		this.stock_cat_color_code = stock_cat_color_code;
	}
	public String getStock_cat_id() {
		return stock_cat_id;
	}
	public void setStock_cat_id(String stock_cat_id) {
		this.stock_cat_id = stock_cat_id;
	}
	public String getStock_cat_name() {
		return stock_cat_name;
	}
	public void setStock_cat_name(String stock_cat_name) {
		this.stock_cat_name = stock_cat_name;
	}
	public String getStock_cat_desc() {
		return stock_cat_desc;
	}
	public void setStock_cat_desc(String stock_cat_desc) {
		this.stock_cat_desc = stock_cat_desc;
	}
	public String getStock_cat_color_id() {
		return stock_cat_color_id;
	}
	public void setStock_cat_color_id(String stock_cat_color_id) {
		this.stock_cat_color_id = stock_cat_color_id;
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
	public String getStock_cat_color_name() {
		return stock_cat_color_name;
	}
	public void setStock_cat_color_name(String stock_cat_color_name) {
		this.stock_cat_color_name = stock_cat_color_name;
	}
	@Override
	public String toString() {
		return "StockCategory [stock_cat_id=" + stock_cat_id + ", stock_cat_name=" + stock_cat_name
				+ ", stock_cat_desc=" + stock_cat_desc + ", stock_cat_color_id=" + stock_cat_color_id + ", status="
				+ status + ", userAccessStatus=" + userAccessStatus + ", stock_cat_color_name=" + stock_cat_color_name
				+ ", stock_cat_color_code=" + stock_cat_color_code + "]";
	}
	
	
}
