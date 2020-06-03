package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class Response extends Status implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = 762535504895863333L;

	private Object object;

	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	@Override
	public String toString() {
		return "ResponseBean [object=" + object + "]";
	}
	
}
