package com.v1.DemandPlanningService.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.mapper.StringDataMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class StringDetailDaoImpl implements CURDRepository<String> {

	private static final Logger logger = Logger.getLogger(StringDetailDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	
	
	
	@Override
	public String save(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getByID(Object catalogNo, Object contract) {
		String productDesc = null;
		try {
			productDesc = jdbcTemplate.queryForObject(propSource.getProperty("getProductName"), new Object[] { (String) contract , (String) catalogNo},  new StringDataMapper());
		}catch(Exception e) {
			logger.error("StringDetailDaoImpl (getByID) Exception occure.",e);
		}
		return productDesc;
	}

	@Override
	public String getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
