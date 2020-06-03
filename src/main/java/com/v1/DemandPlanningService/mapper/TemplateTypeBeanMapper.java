package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.TemplateType;

public class TemplateTypeBeanMapper implements RowMapper<TemplateType> {

	@Override
	public TemplateType mapRow(ResultSet rs, int rowNum) throws SQLException {
		TemplateType bean = new TemplateType();
		bean.setProd_type_id(rs.getString("PROD_TYPE_ID"));
		bean.setProd_type_name(rs.getString("PROD_TYPE_NAME"));
		bean.setProd_type_desc(rs.getString("PROD_TYPE_DESC"));
		bean.setStatus(rs.getString("STATUS"));
		bean.setUserAccessStatus(rs.getString("ACCESS_"));
		
		return bean;
	}

}
