package com.v1.DemandPlanningService.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.Authentication;
import com.v1.DemandPlanningService.mapper.AuthenticationBeanMapper;

@PropertySource(value="classpath:userAuthentication.properties",ignoreResourceNotFound=true)
@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {

	private static final Logger logger = Logger.getLogger(AuthenticationDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	@Override
	public boolean authenticateDPS_User(Authentication authenticationBean) {
		logger.info("(authenticateDPS_User) Execution is start");
		
		String username = authenticationBean.getUsername();
		String password = authenticationBean.getPassword();
		Authentication authenticationBeanReturn = null;
		try {
			authenticationBeanReturn = jdbcTemplate.queryForObject(propSource.getProperty("getUserAuthDetails"), new Object[] {username}, new AuthenticationBeanMapper());
		}catch(Exception e) {
			logger.error("(authenticateDPS_User) Authentication failed"+username + "  : ",e);
			return false;
		}
		
		if(username.equals(authenticationBeanReturn.getUsername()) && password.equals(authenticationBeanReturn.getPassword())
				&& "active".equalsIgnoreCase(authenticationBeanReturn.getIsActive())) 
		{
			return true;
		}else {
			return false;	
		}
	}
}
