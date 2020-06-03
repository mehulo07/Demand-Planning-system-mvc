package com.v1.DemandPlanningService.dao;

import java.util.List;

import com.v1.DemandPlanningService.bean.ProductInfo;
import com.v1.DemandPlanningService.bean.ProductParamMaster;
import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.bean.TransactionSeq;

public interface ProductSettingDao {
	
	public List<ProductInfo> getProductListDao(ProductInfo productInfoBean , int limit);
	public Response insertProductSettingDao(ProductParamMaster productParamMasterBean , Response responseBean);
	public boolean updateProductSettingDao(ProductParamMaster productParamMasterBean,Response responseBean);
	public boolean getInsertFlag(ProductParamMaster productParamMasterBean) throws Exception;
	
}
