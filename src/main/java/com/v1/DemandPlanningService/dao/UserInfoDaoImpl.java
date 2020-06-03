package com.v1.DemandPlanningService.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
/*import org.springframework.security.authentication.BadCredentialsException;*/
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.Role;
import com.v1.DemandPlanningService.bean.UserCredential;
import com.v1.DemandPlanningService.bean.UserInfo;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.mapper.RoleBeanMapper;
import com.v1.DemandPlanningService.mapper.UserInfoMapper;

@PropertySource(value="classpath:userAuthentication.properties",ignoreResourceNotFound=true)
@Repository
public class UserInfoDaoImpl implements CURDRepository<UserInfo> {
	
	private static final Logger logger = Logger.getLogger(UserInfoDaoImpl.class);
		
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;

	@Override
	public UserInfo save(UserInfo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo update(UserInfo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getByID(Object id, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getByName(Object obj, Object status) {
		String username =(String) obj;
		try {
			return jdbcTemplate.queryForObject(propSource.getProperty("getUserAuthDetails"), new Object[] {username} , new UserInfoMapper());
		}catch(Exception e) {
			logger.error("(getUserInfoDao) Exception occure :",e);
			return null;
		}
	}

	@Override
	public UserInfo delete(UserInfo obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
