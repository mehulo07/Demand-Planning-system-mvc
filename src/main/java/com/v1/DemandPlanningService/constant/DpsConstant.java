package com.v1.DemandPlanningService.constant;

import org.springframework.stereotype.Service;

@Service
public class DpsConstant {
	
	
	public static final String SUCCESS_MESSAGE ="OK";
	public static final String ERROR_MESSAGE ="ERROR";
	
	public static final int SUCCESS_CODE =200;
	public static final int CLIENT_ERROR_CODE =400;
	public static final int FORBIDDEN = 403; 
	
	
	public static final String ROLE_PREFIX ="ROLE_";
	public static final String CLIENT_AUTHENTICATION_ERROR_MESSAGE_NULL ="Username or Password is null.";
	public static final String CLIENT_AUTHENTICATION_ERROR_MESSAGE_INVALID ="Username or Password is wrong.";
	public static final String CLIENT_AUTHENTICATION_ERROR_MESSAGE_TOKEN_NULL = "User token is null.";
	public static final String CLIENT_AUTHENTICATION_ERROR_MESSAGE_TOKEN_INVALID = "User token is invalid.";
	
	public static final String USER_NOT_FOUND = "User not found.";
	public static final String USER_ROLE_NOT_FOUND = "User role not found";
	public static final String EXCEPTION_WHILE_TOKEN_GENERATUIN = "Exception while token generation.";
	
	public static final String CLIENT_AUTHENTICATION_ERROR_MESSAGE_REQUIRE_INFO_NULL = "Require information is null.";
	
	public static final String EXCEPTION_WHILE_DATA_FATCHING = "Exception while getting data.";
	
	public static final String STATUS_ACTIVE = "active";
	public static final String STATUS_INACTIVE = "inactive";
	
	public static final String DPS_COMPANY_PREFIX = "DPS";
	public static final String DPS_PRODUCT_CONTRACT ="LAX";
	
	public static final String DPS_NCSO_FLAG = "N";
	public static final String DPS_PROFITALE_LINE_FLAG = "P";
	
	public static final String DATA_NOT_FOUND ="Data not found";
	
	
}
