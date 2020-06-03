package com.v1.DemandPlanningService.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;*/
import org.springframework.stereotype.Service;

import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.bean.Role;
import com.v1.DemandPlanningService.bean.UserCredential;
import com.v1.DemandPlanningService.bean.UserInfo;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.dao.RoleDaoImpl;
import com.v1.DemandPlanningService.dao.UserInfoDaoImpl;

import net.sf.json.JSONObject;

@Service
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserInfoDaoImpl userInfoDaoImpl;
	
	@Autowired
	private RoleDaoImpl roleDaoImpl;
	

	
	@SuppressWarnings("unused")
	public void loginService(UserCredential userCredential , Response response , JSONObject jsonObject) {
		logger.info("(Authenticate_DPSUserService) Execution start");
		UserInfo userInfo = null;
		String token = null;
		String userRole = null;
		boolean isAuthenticated = false;
		Map<String , Object > claims = new HashMap<String, Object>();
		//List<GrantedAuthority> grantedAuthorities =  null;
		
		try {
			userInfo = userInfoDaoImpl.getByName((Object)userCredential.getUsername() , DpsConstant.STATUS_ACTIVE);
			if(userInfo == null) {
				throw new Exception(DpsConstant.USER_NOT_FOUND);
			}
			isAuthenticated = validateUser(userCredential , userInfo);
				
			if(isAuthenticated) {
				userRole = DpsConstant.ROLE_PREFIX + getRoleNameService(userInfo.getUserRoleId(), DpsConstant.STATUS_ACTIVE);
				userInfo.setUserRoleName(userRole);
			//	grantedAuthorities =  AuthorityUtils.commaSeparatedStringToAuthorityList(userRole);
				//claims.put("authorities",grantedAuthorities);
				/*token = jwtTokenUtil.generateToken(userInfo , claims);*/
				
				if(userRole ==  null) {
					throw new Exception(DpsConstant.USER_ROLE_NOT_FOUND);
				}
				if(token == null ) {
					throw new Exception(DpsConstant.EXCEPTION_WHILE_TOKEN_GENERATUIN);
				}
				userInfo.setPassword(null);
				jsonObject.put("token",token);
				jsonObject.put("userDetails", userInfo);
				
			}else {
				throw new Exception(DpsConstant.CLIENT_AUTHENTICATION_ERROR_MESSAGE_INVALID);
			}
			
			response.setStatusCode(DpsConstant.SUCCESS_CODE);
			response.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			response.setObject((Object)jsonObject);
			
		}catch(Exception e) {
			logger.error("(Authenticate_DPSUserService) Exception occure.",e);
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)e.getMessage());
		}
		logger.info("(Authenticate_DPSUserService) Execution complete. :"+userCredential.getUsername());
	}
	
	public boolean validateUser(UserCredential userCredential ,UserInfo userInfo) throws Exception {
		logger.debug("(validateUser) Execution start.");
		if(userCredential.getUsername().equalsIgnoreCase(userInfo.getUserEmail()) && userCredential.getPassword().equals(userInfo.getPassword()) && DpsConstant.STATUS_ACTIVE.equalsIgnoreCase(userInfo.getStatus())) {
			System.out.println("USER VALIDATED SUCCESSFULLY.");
			return true;
		}else {
			//throw new BadCredentialsException(DpsConstant.CLIENT_AUTHENTICATION_ERROR_MESSAGE_INVALID);
			throw new Exception();
		}
	}
	
	//If exception then login process stop.
	public String getRoleNameService(String roleId , String status){
		logger.info("(getRoleName) Execution start");
		Role roleBean = roleDaoImpl.getByID(roleId, status);
		
		if(roleBean!= null) {
			return roleBean.getRole_name(); 
		}else {
			return null;
		}
		
	}
	
}
