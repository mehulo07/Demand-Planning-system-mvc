package com.v1.DemandPlanningService.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.v1.DemandPlanningService.bean.ProductParamMaster;
import com.v1.DemandPlanningService.bean.ProductSeattingSearch;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.mapper.ProductParamMasterBeanMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class ProductParamMasterDaoImpl implements CURDRepository<ProductParamMaster> {
	private static final Logger logger = Logger.getLogger(ProductParamMasterDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;

	@Override
	@Transactional
	public ProductParamMaster save(ProductParamMaster productParamMasterBean) {
		logger.info("(insertProductSettingDao) Execution Start.");
		try {
	        Object[] params = new Object[] { productParamMasterBean.getTransaction_id(), productParamMasterBean.getRef_prod_cat_id(), 
	        		productParamMasterBean.getCatalog_no(), productParamMasterBean.getUpdated_by(),DpsConstant.STATUS_ACTIVE};
	        
	        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR };
			int row = jdbcTemplate.update(propSource.getProperty("insertProductSettingMaster"),params,types);
//			System.out.println("row update is :"+row);
			logger.info("(insertProductSettingDao) Row In Master Tab Inserted : " + row);
			
			if(row>0) {
				int [] batch = jdbcTemplate.batchUpdate(propSource.getProperty("insertProductSettingDetail"),new BatchPreparedStatementSetter() {
			
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, productParamMasterBean.getTransaction_id());
						ps.setString(2, productParamMasterBean.getProductParamDetailBeanList().get(i).getStk_cat_id());
						ps.setInt(3, productParamMasterBean.getProductParamDetailBeanList().get(i).getStk_cat_from());
						ps.setInt(4, productParamMasterBean.getProductParamDetailBeanList().get(i).getStk_cat_to());
						ps.setString(5, productParamMasterBean.getProductParamDetailBeanList().get(i).getUpdated_by());
					}
					
					public int getBatchSize() {
						return productParamMasterBean.getProductParamDetailBeanList().size();
					}
				});
				logger.info("(insertProductSettingDao) Row In Detail Tab Inserted : " + batch);
			}
			logger.info("(insertProductSettingDao) Execution Ends.");
		}catch (Exception e) {
			System.out.println("EXCEPTION WHILE SAVING DATA");
			e.printStackTrace();
			logger.error("(insertProductSettingDao) Exception : ",e);
		}
		return productParamMasterBean;
	}

	@Override
	@Transactional
	public ProductParamMaster update(ProductParamMaster productParamMasterBean) {
		logger.info("(update) Execution Start.");
		try {
	        Object[] params = new Object[] { productParamMasterBean.getUpdated_by(),DpsConstant.STATUS_INACTIVE,productParamMasterBean.getTransaction_id()};
	        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR};
			int row = jdbcTemplate.update(propSource.getProperty("updateProductSettingMaster"),params,types);
			logger.info("(update) Row In Master Tab Inserted : " + row);
			logger.info("(update) Execution Ends.");
			return productParamMasterBean;
		}catch (Exception e) {
			logger.error("(update) Exception : ",e);
			return productParamMasterBean;
		}
	}

	@Override
	public ProductParamMaster getByID(Object categoryId, Object status) {
		logger.debug("ProductParamMaster (getByID) Execution start.");
		ProductParamMaster productParamMaster = null;
		try {
			productParamMaster = jdbcTemplate.queryForObject(propSource.getProperty("getProductParamMasterByTransactionId"), new Object[] {(String)categoryId}, new ProductParamMasterBeanMapper());
		}catch(Exception e) {
			logger.error("ProductParamMaster (getByID) Exception :",e);
		}
		return productParamMaster;
	}
	
	

	@Override
	public ProductParamMaster getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ProductParamMaster getByStatus(ProductSeattingSearch productSeattingSearch, String catalog_no ,Date from , Date to) {
		logger.debug("ProductParamMasterDaoImpl (getByStatus) Execution start.");
		ProductParamMaster productParamMaster = null;
		try {
			productParamMaster = jdbcTemplate.queryForObject(propSource.getProperty("getProductParamHead"), 
						new Object[] { productSeattingSearch.getCategory() , catalog_no == null ? "-" : catalog_no
								, productSeattingSearch.getStatus() , String.valueOf(from) , String.valueOf(to) }, new ProductParamMasterBeanMapper());	
		}catch(Exception e) {
			logger.error("ProductParamMasterDaoImpl (getByStatus) Exception :",e);
		}
		return productParamMaster;
	}
	
	@Override
	@Transactional
	public ProductParamMaster delete(ProductParamMaster productParamMasterBean) {
		logger.info("(delete) Execution Start.");
		try {
			if(productParamMasterBean.getTransaction_id()!=null && !"".equalsIgnoreCase(productParamMasterBean.getTransaction_id())){
				String catalogNo =productParamMasterBean.getCatalog_no();
				String categoryId = productParamMasterBean.getRef_prod_cat_id(); 
				
				if (catalogNo == null || "".equals(catalogNo)) {
					catalogNo = "-";
				}
				if (categoryId == null || "".equals(categoryId)) {
					categoryId = "-";
				}
				int row = jdbcTemplate.update(propSource.getProperty("deleteProductSettingMasterWithTransId"),productParamMasterBean.getTransaction_id() , categoryId , catalogNo);
				logger.info("(delete) Row In Master Tab with transaction id : " + row);
			}else {
		        String qry = propSource.getProperty("deleteProductSettingMasterWithOutTransId");
		        if(productParamMasterBean.getCatalog_no()!=null && !"".equalsIgnoreCase(productParamMasterBean.getCatalog_no())) {
		        	qry += " and catalog_no = '" + productParamMasterBean.getCatalog_no() + "' ";
		        }else {
		        	qry += " and catalog_no is null ";
		        }
				int row = jdbcTemplate.update(qry,productParamMasterBean.getRef_prod_cat_id());
				logger.info("(delete) Row In Master Tab without transaction id : " + row);
			}
			
			int row = jdbcTemplate.update(propSource.getProperty("deleteProductSettingDetailTab"));
			logger.info("(delete) Row In Detail Tab : " + row);
			logger.info("(delete) Execution Ends.");
			return productParamMasterBean;
		}catch (Exception e) {
			logger.error("(delete) Exception : ",e);
			return productParamMasterBean;
		}
	}
}
