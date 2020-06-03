package com.v1.DemandPlanningService.dao;

import com.v1.DemandPlanningService.bean.Authentication;

public interface AuthenticationDao {
	
	boolean authenticateDPS_User(Authentication authenticationBean);
	
}
