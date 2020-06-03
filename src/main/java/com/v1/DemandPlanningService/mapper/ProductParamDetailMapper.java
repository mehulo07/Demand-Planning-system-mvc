package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.ProductParamDetail;

public class ProductParamDetailMapper implements RowMapper<ProductParamDetail> {

	@Override
	public ProductParamDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductParamDetail productParamDetail = new ProductParamDetail();
		
		productParamDetail.setTransaction_id(rs.getString("TRANSACTION_ID"));
		productParamDetail.setStk_cat_id(rs.getString("STK_CAT_ID"));
		productParamDetail.setStk_cat_name(rs.getString("STOCK_CAT_NAME"));
		productParamDetail.setStk_cat_from(rs.getInt("STK_CAT_FROM"));
		productParamDetail.setStk_cat_to(rs.getInt("STK_CAT_TO"));
		
		return productParamDetail;
	}
	
}
