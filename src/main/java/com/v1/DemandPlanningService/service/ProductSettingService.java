package com.v1.DemandPlanningService.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.DemandPlanningService.bean.ProductInfo;
import com.v1.DemandPlanningService.bean.ProductParamDetail;
import com.v1.DemandPlanningService.bean.ProductParamMaster;
import com.v1.DemandPlanningService.bean.ProductSeattingSearch;
import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.bean.TransactionSeq;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.dao.ProductCategoryDaoImpl;
import com.v1.DemandPlanningService.dao.ProductParamDetailDaoImpl;
import com.v1.DemandPlanningService.dao.ProductParamMasterDaoImpl;
import com.v1.DemandPlanningService.dao.ProductSettingDao;
import com.v1.DemandPlanningService.dao.StringDetailDaoImpl;
import com.v1.DemandPlanningService.dao.TemplateTypeDaoImpl;
import com.v1.DemandPlanningService.dao.TransactionSeqDaoImpl;
import com.v1.DemandPlanningService.utility.DPSUtility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ProductSettingService {

	private static final Logger logger = Logger.getLogger(ProductSettingService.class);
	
	@Autowired
	private ProductSettingDao prodSettingDao;
	
	@Autowired
	private TemplateTypeDaoImpl templateTypeDaoImpl;
	
	@Autowired
	private ProductCategoryDaoImpl productCategoryDaoImpl;
	
	@Autowired
	private ProductParamDetailDaoImpl productParamDetailDaoImpl;
	
	@Autowired
	private TransactionSeqDaoImpl transactionSeqDaoImpl;
	
	@Autowired
	private ProductParamMasterDaoImpl  productParamMasterDaoImpl;
	
	@Autowired
	private DPSUtility dPSUtility;
	
	@Autowired
	private StringDetailDaoImpl stringDetailDaoImpl;
	
	public Response getProductSettingDetailsService(String userId) {
		logger.info("(getProductSettingDetailsService) Execution Start.");
		Response res = new Response();
		JSONObject obj = new JSONObject ();
		
		obj.put("ProdCategorys", productCategoryDaoImpl.getCategoryListByUserId(userId, DpsConstant.STATUS_ACTIVE));
		//obj.put("stockCategoryDetails", stockCategoryDaoImpl.getCategoryListByUserId(userId, DpsConstant.STATUS_ACTIVE));
		obj.put("TemplateTypes", templateTypeDaoImpl.getTemplateListByUserId(userId, DpsConstant.STATUS_ACTIVE));
		//obj.put("TransactionId", transactionSeqDaoImpl.getByName(DpsConstant..DPS_COMPANY_PREFIX,DpsConstant.STATUS_ACTIVE));
		res.setObject((Object)obj);
		logger.info("(getProductSettingDetailsService) Execution Ends.");
		
		return res;
	}
	
	
	public List<ProductInfo> getProductListService(ProductInfo productInfoBean , int limit){
		logger.info("(getProductListService) Execution:");
		return prodSettingDao.getProductListDao(productInfoBean, limit);
	}
	
	@Transactional
	public Response saveCategoryProductParam(ProductParamMaster productParamMasterBean , Response responseBean) {
		logger.info("(saveCategoryProductParam) Execution Start.");
		try {
			//First delete 
			productParamMasterDaoImpl.delete(productParamMasterBean);
			//Get transaction id
			TransactionSeq seq = transactionSeqDaoImpl.getByName("DPS", "");
			productParamMasterBean.setTransaction_id(seq.getTransaction_id());
			//Transaction saved.
			productParamMasterDaoImpl.save(productParamMasterBean);
			
			responseBean.setStatusCode(DpsConstant.SUCCESS_CODE);
			responseBean.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			responseBean.setObject((Object)productParamMasterBean.getTransaction_id());
		}catch (Exception e) {
			responseBean.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			responseBean.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			responseBean.setObject((Object)"Error");
		}
		logger.info("(saveCategoryProductParam) Execution Ends.");
		return responseBean;
	}
	
	public void ProductCategoryParamList(ProductSeattingSearch productSeattingSearch , Response response) {
		logger.debug("(saveCategoryProductParam) Execution Ends.");
		
		ProductParamMaster categoryProductParamMaster = null;
		List<Integer> month = null;
		JSONObject jsonObject = new JSONObject(); 
		Date firstDay , lastDay;
		Map<String, Object > categoryProductList = new HashMap<>();
		Map<String , Object > monthMap= new HashMap<>();
		Map<String , Object > yearMap = new HashMap<>();
		List<TransactionSeq> transactions = null;
		List<ProductParamMaster> productParamMasterList = new ArrayList<>();
		int year;
		
		try {
			
			year = productSeattingSearch.getYear();
			month = productSeattingSearch.getMonth();
			firstDay = dPSUtility.getFirstDate( year, month.get(0) );
			lastDay = dPSUtility.getLastDate( year , month.get(0) );
			
			if(productSeattingSearch.getCatalog_no() == null || productSeattingSearch.getCatalog_no().equalsIgnoreCase("")) {
				categoryProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch,null,firstDay,lastDay);
				if(categoryProductParamMaster != null) {
					categoryProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(categoryProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					categoryProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(categoryProductParamMaster.getTransaction_id()));
					
					//getAllTransaction
					transactions = transactionSeqDaoImpl.getAllByID(productSeattingSearch.getCategory(),firstDay,lastDay);
					//iterate transactions
					for (TransactionSeq tempTransactionSeq: transactions) {
						ProductParamMaster tempProductParamMaster = null;
						tempProductParamMaster = productParamMasterDaoImpl.getByID(tempTransactionSeq.getTransaction_id(), DpsConstant.STATUS_ACTIVE);
						tempProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(tempProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
						if(tempProductParamMaster != null) {
							tempProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(tempProductParamMaster.getTransaction_id()));
							productParamMasterList.add(tempProductParamMaster);
						}
					}
				}
			}else {
				categoryProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch, null,firstDay,lastDay);
				if(categoryProductParamMaster != null) {
					categoryProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(categoryProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					categoryProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(categoryProductParamMaster.getTransaction_id()));
				}
				
				ProductParamMaster tempProductParamMaster = null;
				tempProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch, productSeattingSearch.getCatalog_no(),firstDay,lastDay);
				if(tempProductParamMaster  != null) {
					tempProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(tempProductParamMaster .getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					tempProductParamMaster .setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(tempProductParamMaster.getTransaction_id()));
					productParamMasterList.add(tempProductParamMaster);
				}
			}
			jsonObject.put("year", year);
			jsonObject.put("month", dPSUtility.getMonth(month.get(0)));
			jsonObject.put("CategoryData", categoryProductParamMaster);
			jsonObject.put("ProductData", productParamMasterList);
			
			response.setStatusCode(DpsConstant.SUCCESS_CODE);
			response.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			response.setObject((Object)jsonObject);
			
		}catch(Exception e) {
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)e.getMessage());
		}
		
	}
	public JSONArray getParamsByProduct(Integer currentPage, Integer recordSize,ProductSeattingSearch productSeattingSearch) {
		logger.info("getParamsByProduct() Execution Starts.");
		JSONArray jarr = new JSONArray();
		currentPage--;
		int startIndex = currentPage * recordSize + 1;
		int endIndex = startIndex + recordSize - 1;
		ProductParamMaster categoryProductParamMaster = null;
		List<Integer> month = null;
		JSONObject jsonObject = new JSONObject(); 
		Date firstDay , lastDay;
		List<TransactionSeq> transactions = null;
		List<ProductParamMaster> productParamMasterList = new ArrayList<>();
		int year;
		try {
			year = productSeattingSearch.getYear();
			month = productSeattingSearch.getMonth();
			firstDay = dPSUtility.getFirstDate( year, month.get(0) );
			lastDay = dPSUtility.getLastDate( year , month.get(0) );
			
			if(productSeattingSearch.getCatalog_no() == null || productSeattingSearch.getCatalog_no().equalsIgnoreCase("")) {
				categoryProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch,null,firstDay,lastDay);
				if(categoryProductParamMaster != null) {
					categoryProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(categoryProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					categoryProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(categoryProductParamMaster.getTransaction_id()));
					
					//getAllTransaction
					transactions = transactionSeqDaoImpl.getAllByID(productSeattingSearch.getCategory() , firstDay , lastDay);
					//iterate transactions
					for (TransactionSeq tempTransactionSeq: transactions) {
						ProductParamMaster tempProductParamMaster = null;
						tempProductParamMaster = productParamMasterDaoImpl.getByID(tempTransactionSeq.getTransaction_id(), DpsConstant.STATUS_ACTIVE);
						tempProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(tempProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
						
						if(tempProductParamMaster != null) {
							tempProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(tempProductParamMaster.getTransaction_id()));
							productParamMasterList.add(tempProductParamMaster);
						}
					}
				}
			}else {
				categoryProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch, null,firstDay,lastDay);
				if(categoryProductParamMaster != null) {
					categoryProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(categoryProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					categoryProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(categoryProductParamMaster.getTransaction_id()));
				}
				
				ProductParamMaster tempProductParamMaster = null;
				tempProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch, productSeattingSearch.getCatalog_no(),firstDay,lastDay);
				if(tempProductParamMaster  != null) {
					tempProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(tempProductParamMaster .getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					tempProductParamMaster .setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(tempProductParamMaster.getTransaction_id()));
					productParamMasterList.add(tempProductParamMaster);
				}
			}
			int rowId = 0;
			for(ProductParamMaster list : productParamMasterList) {
				for(ProductParamDetail bean : list.getProductParamDetailBeanList()) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					
					arr.add(list.getCatalog_Desc());
					arr.add(bean.getStk_cat_name());
					arr.add(bean.getStk_cat_from());
					arr.add(bean.getStk_cat_to());
					arr.add(list.getTransaction_id());
					
					obj.put("id", "prodParam"+rowId++);
					obj.put("cell", arr);
					jarr.add(obj);
				}
			}
		} finally {
			logger.info("getParamsByProduct() Execution Ends.");
		}
		return jarr;
	}
	public JSONArray getParamsByCategory(Integer currentPage, Integer recordSize,ProductSeattingSearch productSeattingSearch) {
		logger.info("getParamsByCategory() Execution Starts.");
		JSONArray jarr = new JSONArray();
		currentPage--;
		int startIndex = currentPage * recordSize + 1;
		int endIndex = startIndex + recordSize - 1;
		ProductParamMaster categoryProductParamMaster = null;
		List<Integer> month = null;
		Date firstDay , lastDay;
		List<TransactionSeq> transactions = null;
		List<ProductParamMaster> productParamMasterList = new ArrayList<>();
		int year;
		try {
			year = productSeattingSearch.getYear();
			month = productSeattingSearch.getMonth();
			firstDay = dPSUtility.getFirstDate( year, month.get(0) );
			lastDay = dPSUtility.getLastDate( year , month.get(0) );
			
			if(productSeattingSearch.getCatalog_no() == null || productSeattingSearch.getCatalog_no().equalsIgnoreCase("")) {
				categoryProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch,null,firstDay,lastDay);
				if(categoryProductParamMaster != null) {
					categoryProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(categoryProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					categoryProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(categoryProductParamMaster.getTransaction_id()));
					
					//getAllTransaction
					transactions = transactionSeqDaoImpl.getAllByID(productSeattingSearch.getCategory(),firstDay,lastDay);
					//iterate transactions
					for (TransactionSeq tempTransactionSeq: transactions) {
						ProductParamMaster tempProductParamMaster = null;
						tempProductParamMaster = productParamMasterDaoImpl.getByID(tempTransactionSeq.getTransaction_id(), DpsConstant.STATUS_ACTIVE);
						tempProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(tempProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
						if(tempProductParamMaster != null) {
							tempProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(tempProductParamMaster.getTransaction_id()));
							productParamMasterList.add(tempProductParamMaster);
						}
					}
				}
			}else {
				categoryProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch, null,firstDay,lastDay);
				if(categoryProductParamMaster != null) {
					categoryProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(categoryProductParamMaster.getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					categoryProductParamMaster.setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(categoryProductParamMaster.getTransaction_id()));
				}
				
				ProductParamMaster tempProductParamMaster = null;
				tempProductParamMaster = productParamMasterDaoImpl.getByStatus(productSeattingSearch, productSeattingSearch.getCatalog_no(),firstDay,lastDay);
				if(tempProductParamMaster  != null) {
					tempProductParamMaster.setCatalog_Desc(stringDetailDaoImpl.getByID(tempProductParamMaster .getCatalog_no(), DpsConstant.DPS_PRODUCT_CONTRACT));
					tempProductParamMaster .setProductParamDetailBeanList(productParamDetailDaoImpl.getAllByID(tempProductParamMaster.getTransaction_id()));
					productParamMasterList.add(tempProductParamMaster);
				}
			}
			
			int rowId = 0;
			if(categoryProductParamMaster!=null) {
				for(ProductParamDetail bean : categoryProductParamMaster.getProductParamDetailBeanList()) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					
					arr.add(bean.getStk_cat_name());
					arr.add(bean.getStk_cat_from());
					arr.add(bean.getStk_cat_to());
					arr.add(bean.getStk_cat_id());
					arr.add(categoryProductParamMaster.getTransaction_id());
					
					obj.put("id", "catParam"+rowId++);
					obj.put("cell", arr);
					jarr.add(obj);
				}
			}
		} finally {
			logger.info("getParamsByCategory() Execution Ends.");
		}
		return jarr;
	}
	
	@Transactional
	public Response reUseService(String ref_prod_cat_id, String  catalog_no, ProductParamMaster productParamMasterInsert, Date from , Date to , Response response ) {
		logger.debug("reUseService() Execution Start.");
		
		ProductParamMaster productParamMaster = new ProductParamMaster(); 
		try {
			//get transaction_id
			TransactionSeq transactionSeq = transactionSeqDaoImpl.checkMasterEntryMonthWise(ref_prod_cat_id, catalog_no , from , to);
			
			if(transactionSeq != null) {
				productParamMaster.setTransaction_id(transactionSeq.getTransaction_id());
				productParamMaster.setRef_prod_cat_id(ref_prod_cat_id);
				//Delete from master and detail
				productParamMasterDaoImpl.delete(productParamMaster);
			}
			
			//get new sequence id
			TransactionSeq seq = transactionSeqDaoImpl.getByName("DPS", "");
			productParamMasterInsert.setTransaction_id(seq.getTransaction_id());
			productParamMasterDaoImpl.save(productParamMasterInsert);
			
			response.setStatusCode(DpsConstant.SUCCESS_CODE);
			response.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			response.setObject((Object)productParamMasterInsert.getTransaction_id());

			
			logger.debug("reUseService() Execution End.");
		}catch(Exception e) {
			logger.error("Exception in reUseService() :",e);
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)"Error");
		}
		return response;
	}

	//ProductSettingService
	public String getCategoryName(String categoryId) {
		return productCategoryDaoImpl.getCategoryName(categoryId);
	}
}
