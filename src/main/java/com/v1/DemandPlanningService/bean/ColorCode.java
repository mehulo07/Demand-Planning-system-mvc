package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class ColorCode implements Serializable {

	/**
	 *	@author makwameh 
	 */
	private static final long serialVersionUID = -6270757308344309206L;
	
	private String color_id;
	private String color_code;
	private String color_name;
	private String status;
	public String getColor_id() {
		return color_id;
	}
	public void setColor_id(String color_id) {
		this.color_id = color_id;
	}
	public String getColor_code() {
		return color_code;
	}
	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ColorCodeBean [color_id=" + color_id + ", color_code=" + color_code + ", color_name=" + color_name
				+ ", status=" + status + "]";
	}
	
}
