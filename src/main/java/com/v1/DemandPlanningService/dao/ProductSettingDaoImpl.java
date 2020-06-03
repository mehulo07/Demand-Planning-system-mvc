package com.v1.DemandPlanningService.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.v1.DemandPlanningService.bean.ProductInfo;
import com.v1.DemandPlanningService.bean.ProductParamMaster;
import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.mapper.ProductInfoBeanMapper;
import com.v1.DemandPlanningService.mapper.ProductParamMasterBeanMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class ProductSettingDaoImpl implements ProductSettingDao{

	private static final Logger logger = Logger.getLogger(ProductSettingDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;

	@Override
	public List<ProductInfo> getProductListDao(ProductInfo productInfoBean , int limit) {
		logger.info("(getTemplateTypeBeanDetailDao) ExecutionStart");
		String tempProdName =  productInfoBean.getProductDesc() + "%";
		ArrayList<ProductInfo> list = null;
		try {
			list = (ArrayList<ProductInfo>) jdbcTemplate.query(propSource.getProperty("getProductList"),new Object[] { productInfoBean.getContract() , productInfoBean.getCategory() , tempProdName , limit }, new ProductInfoBeanMapper());	
		}catch(Exception e) {
			logger.error(" Exception while  (getTemplateTypeBeanDetailDao) ",e);
			list = null;
		}
		return list;
	}

	@Override
	@Transactional
	public Response insertProductSettingDao(ProductParamMaster productParamMasterBean,
			Response responseBean) {
		logger.info("(insertProductSettingDao) Execution Start.");
		try {
	        Object[] params = new Object[] { productParamMasterBean.getTransaction_id(), productParamMasterBean.getRef_prod_cat_id(), productParamMasterBean.getCatalog_no(), productParamMasterBean.getUpdated_by(),DpsConstant.STATUS_ACTIVE};
	        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR };
			int row = jdbcTemplate.update(propSource.getProperty("insertProductSettingMaster"),params,types);
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
			responseBean.setStatusCode(DpsConstant.SUCCESS_CODE);
			responseBean.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			responseBean.setObject((Object)"Product Setting Inserted Successfully");
		}catch (Exception e) {
			logger.error("(insertProductSettingDao) Exception : ",e);
			responseBean.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			responseBean.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			responseBean.setObject((Object)"Error");
		}
		return responseBean;
	}
	@Override
	@Transactional
	public boolean updateProductSettingDao(ProductParamMaster productParamMasterBean,
			Response responseBean) {
		logger.info("(updateProductSettingDao) Execution Start.");
		try {
	        Object[] params = new Object[] { productParamMasterBean.getUpdated_by(),DpsConstant.STATUS_INACTIVE,productParamMasterBean.getTransaction_id()};
	        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR};
			int row = jdbcTemplate.update(propSource.getProperty("updateProductSettingMaster"),params,types);
			logger.info("(updateProductSettingDao) Row In Master Tab Inserted : " + row);
			logger.info("(updateProductSettingDao) Execution Ends.");
			return true;
		}catch (Exception e) {
			logger.error("(updateProductSettingDao) Exception : ",e);
			return false;
		}
	}

	@Override
	public boolean getInsertFlag(ProductParamMaster productParamMasterBean) throws Exception{
		logger.info("(getInsertFlag) Execution Start.");
		ArrayList<ProductParamMaster> tmpBean = new ArrayList<ProductParamMaster>();
		if(productParamMasterBean.getCatalog_no()!=null && !productParamMasterBean.getCatalog_no().equalsIgnoreCase("")) {
			tmpBean = (ArrayList<ProductParamMaster>) jdbcTemplate.query(propSource.getProperty("getProductSettingMasterWithCatalog"),
					new Object[] {productParamMasterBean.getRef_prod_cat_id(),productParamMasterBean.getCatalog_no(),DpsConstant.STATUS_ACTIVE}, new ProductParamMasterBeanMapper());
			if(tmpBean.size()>0) {
				if(tmpBean.size() == 1) {
					productParamMasterBean.setTransaction_id(tmpBean.get(0).getTransaction_id());
				}else {
					productParamMasterBean.setTransaction_id(tmpBean.get((tmpBean.size() - 1)).getTransaction_id());
				}
				logger.info("(getInsertFlag) Execution Ends.");
				return false;
			}else {
				logger.info("(getInsertFlag) Execution Ends.");
				return true;
			}
		}else {
			tmpBean = (ArrayList<ProductParamMaster>) jdbcTemplate.query(propSource.getProperty("getProductSettingMasterWithOutCatalog"), 
					new Object[] {productParamMasterBean.getRef_prod_cat_id(),DpsConstant.STATUS_ACTIVE}, new ProductParamMasterBeanMapper());
			if(tmpBean.size()>0) {
				if(tmpBean.size() == 1) {
					productParamMasterBean.setTransaction_id(tmpBean.get(0).getTransaction_id());
				}else {
					productParamMasterBean.setTransaction_id(tmpBean.get((tmpBean.size() - 1)).getTransaction_id());
				}
				logger.info("(getInsertFlag) Execution Ends.");
				return false;
			}else {
				logger.info("(getInsertFlag) Execution Ends.");
				return true;
			}
		}
	}
}
