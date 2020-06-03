package com.v1.DemandPlanningService.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.TemplateType;
import com.v1.DemandPlanningService.mapper.TemplateTypeBeanMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class TemplateTypeDaoImpl implements CURDRepository<TemplateType> {
	
	private static final Logger logger = Logger.getLogger(TemplateTypeDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	

	@Override
	public TemplateType save(TemplateType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateType update(TemplateType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateType getByID(Object id, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateType getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TemplateType> getTemplateListByUserId(String userId , String status){
		logger.debug("(getTemplateListByUserId)  Execution start.");
		try {
			return  (ArrayList<TemplateType>) jdbcTemplate.query(propSource.getProperty("getTemplateTypeBeanDetail"),new Object[] { userId , status }, new TemplateTypeBeanMapper());	
		}catch(Exception e){
			logger.error("(getTemplateListByUserId) Exception occure.",e);
			return null;
		}
	}

	@Override
	public TemplateType delete(TemplateType obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
