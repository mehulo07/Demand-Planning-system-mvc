package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.ProductInfo;

public class ProductInfoBeanMapper implements RowMapper<ProductInfo> {

	@Override
	public ProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductInfo productInfo  = new ProductInfo();
		
		productInfo.setCatalogNo(rs.getString("CATALOG_NO"));
		productInfo.setProductDesc(rs.getString("CATALOG_DESC"));
		productInfo.setCategory(rs.getString("CATALOG_TYPE"));
		productInfo.setContract(rs.getString("CONTRACT"));
		
		return productInfo;
	}

}
