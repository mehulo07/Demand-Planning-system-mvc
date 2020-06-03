package com.v1.DemandPlanningService.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.ProductParamDetail;
import com.v1.DemandPlanningService.mapper.ProductParamDetailMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class ProductParamDetailDaoImpl implements CURDRepository<ProductParamDetail> {

	private static final Logger logger = Logger.getLogger(ProductParamDetailDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;

	
	@Override
	public ProductParamDetail save(ProductParamDetail obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductParamDetail update(ProductParamDetail obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductParamDetail getByID(Object id, Object status) {
		logger.debug("ProductParamDetail (getByID) Execution start.");
		//getProductDetailByTransactionId
		
		ProductParamDetail roductParamDetail= jdbcTemplate.queryForObject(propSource.getProperty("getProductDetailByTransactionId"),
				new Object[] { String.valueOf(id) }, new ProductParamDetailMapper());
		
		return roductParamDetail;
	}
	
	public List<ProductParamDetail> getAllByID(Object id) {
		logger.debug("ProductParamDetail (getByID) Execution start.");
		List<ProductParamDetail> roductParamDetail = null;
		try {
			roductParamDetail= jdbcTemplate.query(propSource.getProperty("getProductDetailByTransactionId"),
					new Object[] { String.valueOf(id) }, new ProductParamDetailMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return roductParamDetail;
	}

	@Override
	public ProductParamDetail getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductParamDetail delete(ProductParamDetail obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
