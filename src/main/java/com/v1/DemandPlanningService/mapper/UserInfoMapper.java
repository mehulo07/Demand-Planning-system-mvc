package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.UserInfo;

public class UserInfoMapper implements RowMapper<UserInfo>{

	@Override
	public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		UserInfo userInfo = new UserInfo();
		
		userInfo.setCustId(resultSet.getString("USER_ID"));
		userInfo.setUserEmail(resultSet.getString("EMAIL_ID"));
		userInfo.setPassword(resultSet.getString("password"));
		//userInfo.setUserName(resultSet.getString("NAME"));
		userInfo.setStatus(resultSet.getString("STATUS"));
		userInfo.setUserRoleId(resultSet.getString("USER_ROLE_ID"));
		
		return userInfo;
	}

}
