package com.v1.DemandPlanningService.dao;

public interface CURDRepository<T> {
 
	T save(T obj);
	
	T update(T obj);
	
	T getByID(Object id , Object status);
	
	T getByName(Object obj , Object status);
	
	T delete(T obj);
	
}
