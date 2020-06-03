package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.UserCredential;

public class UserCredentialMapper implements RowMapper<UserCredential> {

	@Override
	public UserCredential mapRow(ResultSet rs, int rowNum) throws SQLException {
			
		UserCredential userCredential = new UserCredential();
				
		userCredential.setUsername(rs.getString("username"));
		userCredential.setPassword(rs.getString("password"));
		userCredential.setIsActive(rs.getString("status"));
		
		return userCredential;
	}

}
