package com.v1.DemandPlanningService.bean;

import java.io.Serializable;
import java.util.List;

public class ProductSeattingSearch implements Serializable {

	/**
	 * @author makwameh
	 */
	private static final long serialVersionUID = 1065864372777025740L;

	private int year;
	private List<Integer> month;
	private String status;
	private boolean search;
	private String catalog_no;
	private String category;

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<Integer> getMonth() {
		return month;
	}
	public void setMonth(List<Integer> month) {
		this.month = month;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isSearch() {
		return search;
	}
	public void setSearch(boolean search) {
		this.search = search;
	}
	public String getCatalog_no() {
		return catalog_no;
	}
	public void setCatalog_no(String catalog_no) {
		this.catalog_no = catalog_no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductSeattingSearch [year=" + year + ", month=" + month + ", status=" + status + ", search=" + search
				+ ", catalog_no=" + catalog_no + ", category=" + category + "]";
	}

}
