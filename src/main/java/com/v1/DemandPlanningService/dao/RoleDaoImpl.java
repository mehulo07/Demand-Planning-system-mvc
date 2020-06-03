package com.v1.DemandPlanningService.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.Role;
import com.v1.DemandPlanningService.mapper.RoleBeanMapper;

@PropertySource(value="classpath:userAuthentication.properties",ignoreResourceNotFound=true)
@Repository
public class RoleDaoImpl  implements CURDRepository<Role>{

	private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	
	@Override
	public Role save(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role update(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getByID(Object id, Object status) {
		logger.info("(getRole) Execution Start");
		Role roleBean = null;
		try {
			roleBean =  jdbcTemplate.queryForObject(propSource.getProperty("getRole"), new Object[] {id , status} , new RoleBeanMapper());
		}catch(Exception e) {
			logger.error("(getRoleDao) Exception occure :",e);
		}
		return roleBean;
	}

	@Override
	public Role getByName(Object obj, Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role delete(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
