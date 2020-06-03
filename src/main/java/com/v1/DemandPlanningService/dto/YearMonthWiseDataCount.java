package com.v1.DemandPlanningService.dto;

import java.io.Serializable;

public class YearMonthWiseDataCount implements Serializable{

	/**
	 * @author mehul
	 */
	private static final long serialVersionUID = -3187146533382471357L;

	private int year;
	private int month;
	private int count=0;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "YearMonthWiseDataCount [year=" + year + ", month=" + month + ", count=" + count + "]";
	}
	
}
