package com.v1.DemandPlanningService.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.ProductCategory;
import com.v1.DemandPlanningService.mapper.ProductCategoryBeanMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class ProductCategoryDaoImpl implements CURDRepository<ProductCategory> {

	private static final Logger logger = Logger.getLogger(ProductCategoryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	
	
	@Override
	public ProductCategory save(ProductCategory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductCategory update(ProductCategory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductCategory getByID(Object id, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductCategory getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductCategory> getCategoryListByUserId(String userId , String status){
		logger.debug("(getTemplateListByUserId)  Execution start.");
		try {
			return  (ArrayList<ProductCategory>) jdbcTemplate.query(propSource.getProperty("getProductCategoryDetail"),new Object[] { userId , status }, new ProductCategoryBeanMapper());	
		}catch(Exception e){
			logger.error("(getTemplateListByUserId) Exception occure.",e);
			return null;
		}
	}

	@Override
	public ProductCategory delete(ProductCategory obj) {
		return null;
	}
	
	public String getCategoryName(String categoryId) {
		return  jdbcTemplate.queryForObject(propSource.getProperty("getCategoryName"), new Object[] {categoryId} , String.class );
	}
	
}
