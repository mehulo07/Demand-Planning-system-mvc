package com.v1.DemandPlanningService.utility;


import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class DPSUtility {
	
	public static final String monthName[] = {"January" , "February" , "March" , "April" , "May" , "June" , 
			"July" , "August" , "September" , "October" , "November" , "December"};
	
	public Date getFirstDate(int year , int month) {
		YearMonth  yearMonth = YearMonth.of( year, month);  
		LocalDate firstOfMonth = yearMonth.atDay( 1 );
		
		return java.sql.Date.valueOf( firstOfMonth );
	}
		
	public Date getLastDate(int year , int month) {
		
		YearMonth  yearMonth = YearMonth.of( year, month);  
		LocalDate last = yearMonth.atEndOfMonth();
		
		return java.sql.Date.valueOf( last);
	}
	
	public Integer getFirstMonth(List<Integer> listOfMonth) {
		Integer min = listOfMonth
			      .stream()
			      .mapToInt(v -> v)
			      .min().orElseThrow(NoSuchElementException::new);
		return min;
	}
	
	public Integer getLastMonth(List<Integer> listOfMonth) {
		
		Integer max = listOfMonth
			      .stream()
			      .mapToInt(v -> v)
			      .max().orElseThrow(NoSuchElementException::new);
		
		return max;
	}
	
	public String getMonth(int i) {
		return monthName[--i];
	}
	
	public int getCurrentMonthNumber() {
		java.util.Date date= new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		
		return month+1;
	}
	
	public int getCurrentYear() {
		java.util.Date date= new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		
		return year;
	}
	
	public <T> List<T> convertArrayToList(T array[]){
        List<T> list = new ArrayList(); 
        for (T t : array) { 
            list.add(t); 
        }
    	return list;
	}
}
