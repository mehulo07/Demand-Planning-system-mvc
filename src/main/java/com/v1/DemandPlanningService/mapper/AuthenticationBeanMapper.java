package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.Authentication;


public class AuthenticationBeanMapper implements RowMapper<Authentication> {
	
	@Override
	public Authentication mapRow(ResultSet resultset, int rowNum) throws SQLException {
		Authentication authenticationBean = new Authentication();
		
		authenticationBean.setUsername(resultset.getString("username"));
		authenticationBean.setPassword(resultset.getString("password"));
		authenticationBean.setIsActive(resultset.getString("status"));
		
		return authenticationBean;
	}
}
