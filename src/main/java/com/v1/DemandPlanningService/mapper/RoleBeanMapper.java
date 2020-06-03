package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.Role;

public class RoleBeanMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Role roleObj = new Role();
		
		roleObj.setRole_id(resultSet.getString("ROLE_ID"));
		roleObj.setRole_name(resultSet.getString("ROLE_NAME"));
		roleObj.setStatus(resultSet.getString("STATUS"));
		
		return roleObj;
	}
	
}
