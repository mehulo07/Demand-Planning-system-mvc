package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.StockCategory;

public class StockCategoryBeanMapper implements RowMapper<StockCategory> {

	@Override
	public StockCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		StockCategory bean = new StockCategory();
		bean.setStock_cat_id(rs.getString("STOCK_CAT_ID"));
		bean.setStock_cat_name(rs.getString("STOCK_CAT_NAME"));
		bean.setStock_cat_desc(rs.getString("STOCK_CAT_DESC"));
		bean.setStatus(rs.getString("STATUS"));
//		bean.setStock_cat_color_id(rs.getString("STOCK_CAT_COLOR_ID"));
		bean.setStock_cat_color_name(rs.getString("stock_cat_color_name")!=null?rs.getString("stock_cat_color_name"):"");
		bean.setStock_cat_color_code(rs.getString("stock_cat_color_code")!=null?rs.getString("stock_cat_color_code"):"");
		bean.setUserAccessStatus(rs.getString("ACCESS_"));
		
		return bean;
	}
	
	
}
