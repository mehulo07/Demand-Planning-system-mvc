package com.v1.DemandPlanningService.bean;

import java.io.Serializable;

public class ProductInfo implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = 8096340022868708880L;
	
	private String catalogNo;
	private String productDesc;
	private String category;
	private String contract;
	private int limit;
	
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		return "ProductInfo [catalogNo=" + catalogNo + ", productDesc=" + productDesc + ", category=" + category
				+ ", contract=" + contract + ", limit=" + limit + "]";
	}
	
}
