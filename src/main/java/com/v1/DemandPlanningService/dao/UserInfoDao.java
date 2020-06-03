package com.v1.DemandPlanningService.dao;

import com.v1.DemandPlanningService.bean.Authentication;
import com.v1.DemandPlanningService.bean.Role;
import com.v1.DemandPlanningService.bean.UserInfo;

public interface UserInfoDao {
	
	public UserInfo login(Authentication authenticationBean );
	
	public Role getRoleDao(String roleId,String roleStatus);
}
