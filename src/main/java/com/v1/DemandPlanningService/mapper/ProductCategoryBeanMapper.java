package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.ProductCategory;

public class ProductCategoryBeanMapper implements RowMapper<ProductCategory> {

	@Override
	public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductCategory productCategoryBean = new ProductCategory();
		
		productCategoryBean.setProd_cat_id(rs.getString("PROD_CAT_ID"));
		productCategoryBean.setProd_cat_name(rs.getString("PROD_CAT_NAME"));
		productCategoryBean.setProd_cat_desc(rs.getString("PROD_CAT_DESC"));
		productCategoryBean.setStatus(rs.getString("STATUS"));
		productCategoryBean.setUserAccessStatus(rs.getString("ACCESS_"));
		
		return productCategoryBean;
	}
 
}
