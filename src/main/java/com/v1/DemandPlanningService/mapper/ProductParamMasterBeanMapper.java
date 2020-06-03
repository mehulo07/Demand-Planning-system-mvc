package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.ProductParamMaster;

public class ProductParamMasterBeanMapper implements RowMapper<ProductParamMaster> {

	@Override
	public ProductParamMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductParamMaster bean = new ProductParamMaster();
		
		bean.setTransaction_id(rs.getString("TRANSACTION_ID"));
		bean.setRef_prod_cat_id(rs.getString("REF_PROD_CAT_ID"));
		bean.setCatalog_no(rs.getString("CATALOG_NO")!=null?rs.getString("CATALOG_NO"):"");
		
		return bean;
	}

}
