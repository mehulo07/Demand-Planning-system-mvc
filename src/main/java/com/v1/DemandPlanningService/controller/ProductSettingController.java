package com.v1.DemandPlanningService.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.v1.DemandPlanningService.bean.ProductInfo;
import com.v1.DemandPlanningService.bean.ProductParamDetail;
import com.v1.DemandPlanningService.bean.ProductParamMaster;
import com.v1.DemandPlanningService.bean.ProductSeattingSearch;
import com.v1.DemandPlanningService.bean.Request;
import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.dao.TransactionSeqDaoImpl;
import com.v1.DemandPlanningService.service.ProductSettingService;
import com.v1.DemandPlanningService.utility.DPSUtility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ProductSettingController {

	private static final Logger logger = Logger.getLogger(ProductSettingController.class);
	
	@Autowired
	private ProductSettingService productSettingService;
	
	@Autowired
	private TransactionSeqDaoImpl transactionSeqDaoImpl;
		
	@Autowired
	private DPSUtility dpsUtility;
	
	@CrossOrigin 
	@PostMapping({"/getProductSettingConfig"})
	@ResponseBody
	public Response getProductSettingDetails(@RequestBody Request request) {
		logger.info("(getProductSettingDetails) Execution start");
		Response returnObject =new Response();
		String customerId = null;
		
		System.out.println("Get data");
		
		try {
			returnObject = productSettingService.getProductSettingDetailsService(request.getCustId());
			returnObject.setStatusCode(DpsConstant.SUCCESS_CODE);
			returnObject.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
		}catch(Exception e) {
			returnObject.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			returnObject.setStatusMessage(DpsConstant.ERROR_MESSAGE);
		}
		
		return returnObject;
	}
	
	@PostMapping({"/search"})
	@ResponseBody
	public Response ProductCategoryParamList(@RequestBody ProductSeattingSearch productSeattingSearch , BindingResult result) {
		logger.info("(getProductParamList) Execution start");
		Response response = new Response();
		try {
			if(!result.hasErrors()) {
				productSettingService.ProductCategoryParamList(productSeattingSearch , response);	
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)DpsConstant.EXCEPTION_WHILE_DATA_FATCHING);
		}
		return response;
	}
	
	@GetMapping({"/getProductList"})
	@ResponseBody
	public List<ProductInfo> getProductList(@RequestParam(name="productDesc") String productDesc ,
			@RequestParam(name="catalogNo") String catalogNo ,
			@RequestParam(name="category") String category ,
			@RequestParam(name="contract") String contract,
			@RequestParam(name="limit") int limit) {

		logger.info("(getProductList) Execution start");
		
		List<ProductInfo> productList = null;
		ProductInfo productInfoBean = new ProductInfo();
		
		productInfoBean.setProductDesc(productDesc);
		productInfoBean.setCatalogNo(catalogNo);
		productInfoBean.setCategory(category);
		productInfoBean.setContract(contract);
		productInfoBean.setLimit(limit);
		
		try {
			productList = productSettingService.getProductListService(productInfoBean, limit);
		}catch(Exception e) {
			System.out.println("inside catch");
			e.printStackTrace();
			productList = null;
		}
		logger.info("(getProductList) Execution over");
		return productList;
	}
	
	@PostMapping({"/save"})
	@ResponseBody
	public Response saveCategoryProductParam(@RequestParam(value="query", required = false) String productList , 
			@RequestParam(value="transaction_id", required = false) String transaction_id ,
			@RequestParam(value="ref_prod_cat_id", required = false) String ref_prod_cat_id ,
			@RequestParam(value="catalog_Desc", required = false) String catalog_Desc ,
			@RequestParam(value="catalog_no", required = false) String catalog_no) {
		
		logger.debug("(actionOnProductSetting) Execution Start");
		
		ProductParamMaster productParamMaster = new ProductParamMaster();
		Response response = new Response();
		
		
		
		try {
			ProductParamDetail[] productParamDetails = new Gson().fromJson(productList, ProductParamDetail[].class);
			
			productParamMaster.setProductParamDetailBeanList(dpsUtility.convertArrayToList(productParamDetails));
			productParamMaster.setCatalog_Desc(catalog_Desc);
			productParamMaster.setCatalog_no(catalog_no);
			productParamMaster.setRef_prod_cat_id(ref_prod_cat_id);
			productParamMaster.setTransaction_id(transaction_id);
			productParamMaster.setUpdated_by("bnssop");
			
			productSettingService.saveCategoryProductParam(productParamMaster, response);
		}catch(Exception e) {
			logger.error("(actionOnProductSetting) ",e);
		}
		return response;
	}
	
	@PostMapping("/getParamsByProduct")
	@ResponseBody
	public String getParamsByProduct(HttpServletRequest request,ProductSeattingSearch productSettingSearch) {
		logger.info("getParamsByProduct() Execution Starts.");
		JSONObject mainObj = new JSONObject();
		
		JSONArray jarr = null;
		int totalPages = 0;
		String sortBy = request.getParameter("sidx");
		int currentPage = Integer.valueOf(request.getParameter("page")).intValue();
		String sortOrder = request.getParameter("sord");
		int recordSize = Integer.valueOf(request.getParameter("rows")).intValue();
		String[] month = request.getParameterValues("monthz[]");
		List<Integer> monthInt = new ArrayList<Integer>();
		
		for(String mon : month) {
			try {
				monthInt.add(Integer.parseInt(mon));
			}catch (Exception e) {
				logger.error("Parsing error",e);
				e.printStackTrace();
			}
		}
		productSettingSearch.setMonth(monthInt);
		if(productSettingSearch.getCatalog_no().equalsIgnoreCase("")) {
			productSettingSearch.setCatalog_no(null);
		}
		
		try {
			jarr = productSettingService.getParamsByProduct(currentPage, recordSize,productSettingSearch);
			Double noOfPages = Double.valueOf(Double.valueOf(1000).doubleValue() / Double.valueOf(recordSize).doubleValue());
			
			if ((noOfPages.doubleValue() > 0.0D) && (noOfPages.doubleValue() < 1.0D)) {
				totalPages = 1;
			} else if (noOfPages.doubleValue() % 1.0D > 0.0D) {
				totalPages = noOfPages.intValue() + 1;
			} else {
				totalPages = noOfPages.intValue();
			}
			
			mainObj.put("page", Integer.valueOf(currentPage));
			mainObj.put("total", Integer.valueOf(totalPages));
			mainObj.put("records", 1);
			mainObj.put("rows", jarr);
			logger.info("getParamsByProduct() Execution Ends.");

		} catch (Exception e) {
			logger.error("Exception in getParamsByProduct() Execution : ", e);
			mainObj.put("status", e.getLocalizedMessage());
		}
		return mainObj.toString();
	}
	@PostMapping("/getParamsByCategory")
	@ResponseBody
	public String getParamsByCategory(HttpServletRequest request,ProductSeattingSearch productSettingSearch) {
		logger.info("getParamsByCategory() Execution Starts.");
		JSONObject mainObj = new JSONObject();
		JSONArray jarr = null;
		int totalPages = 0;
		String sortBy = request.getParameter("sidx");
		int currentPage = Integer.valueOf(request.getParameter("page")).intValue();
		String sortOrder = request.getParameter("sord");
		int recordSize = Integer.valueOf(request.getParameter("rows")).intValue();
		String[] month = request.getParameterValues("monthz[]");
		List<Integer> monthInt = new ArrayList<Integer>();
	
		for(String mon : month) {
			try {
				monthInt.add(Integer.parseInt(mon));
			}catch (Exception e) {
				logger.error("Parsing error",e);
				e.printStackTrace();
			}
		}
		
		productSettingSearch.setMonth(monthInt);
		if(productSettingSearch.getCatalog_no().equalsIgnoreCase("")) {
			productSettingSearch.setCatalog_no(null);
		}
		try {
			jarr = productSettingService.getParamsByCategory(currentPage, recordSize,productSettingSearch);
			Double noOfPages = Double
					.valueOf(Double.valueOf(1000).doubleValue() / Double.valueOf(recordSize).doubleValue());
			
			if ((noOfPages.doubleValue() > 0.0D) && (noOfPages.doubleValue() < 1.0D)) {
				totalPages = 1;
			} else if (noOfPages.doubleValue() % 1.0D > 0.0D) {
				totalPages = noOfPages.intValue() + 1;
			} else {
				totalPages = noOfPages.intValue();
			}
			
			mainObj.put("page", Integer.valueOf(currentPage));
			mainObj.put("total", Integer.valueOf(totalPages));
			mainObj.put("records", 1);
			mainObj.put("rows", jarr);
			
			logger.info("getParamsByCategory() Execution Ends.");
		} catch (Exception e) {
			logger.error("Exception in getParamsByCategory() Execution : ", e);
			mainObj.put("status", e.getLocalizedMessage());
		}
		return mainObj.toString();
	}
	
	
	@PostMapping("reUse")
	@ResponseBody
	public Response reUse(@RequestParam(value="query", required = false) String productList , 
			@RequestParam(value="transaction_id", required = false) String transaction_id ,
			@RequestParam(value="ref_prod_cat_id", required = false) String ref_prod_cat_id ,
			@RequestParam(value="catalog_Desc", required = false) String catalog_Desc ,
			@RequestParam(value="catalog_no", required = false) String catalog_no) {
		
		logger.debug("(reUse) Controller.");
		Response response = new Response();
		ProductParamMaster productParamMaster = new ProductParamMaster();
		int month;
		int year;
		Date startDate , endDate ;
		try {
			
			year = dpsUtility.getCurrentYear();
			month = dpsUtility.getCurrentMonthNumber();
			startDate =  dpsUtility.getFirstDate(year, month);
			endDate =  dpsUtility.getLastDate(year, month);

			ProductParamDetail[] productParamDetails = new Gson().fromJson(productList, ProductParamDetail[].class);
			
			productParamMaster.setProductParamDetailBeanList(dpsUtility.convertArrayToList(productParamDetails));
			productParamMaster.setCatalog_Desc(catalog_Desc);
			productParamMaster.setCatalog_no(catalog_no);
			productParamMaster.setRef_prod_cat_id(ref_prod_cat_id);
			productParamMaster.setTransaction_id(transaction_id);
			productParamMaster.setUpdated_by("bnssop");
			
			//call for setting
			productSettingService.reUseService(ref_prod_cat_id, catalog_no, productParamMaster , startDate, endDate, response);
		}catch(Exception e) {
			logger.error("(ReUse) Exception ",e);
		}
		return response;
	}	
}