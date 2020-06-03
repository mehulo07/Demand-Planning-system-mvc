package com.v1.DemandPlanningService.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.Role;
import com.v1.DemandPlanningService.bean.StockCategory;
import com.v1.DemandPlanningService.mapper.StockCategoryBeanMapper;


@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class StockCategoryDaoImpl implements CURDRepository<StockCategory> {

	private static final Logger logger = Logger.getLogger(StockCategoryDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	
	
	@Override
	public StockCategory save(StockCategory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockCategory update(StockCategory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockCategory getByID(Object id, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockCategory getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StockCategory> getCategoryListByUserId(String userId , String status){
		logger.debug("(getCategoryListByUserId)  Execution start.");
		try {
			return  (ArrayList<StockCategory>) jdbcTemplate.query(propSource.getProperty("getStockCategoryBeanDetail"),new Object[] { userId , status }, new StockCategoryBeanMapper());	
		}catch(Exception e){
			logger.error("(getCategoryListByUserId) Exception occure.",e);
			return null;
		}
	}

	@Override
	public StockCategory delete(StockCategory obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
