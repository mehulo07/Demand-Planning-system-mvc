package com.v1.DemandPlanningService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.dao.DashboardDAO;
import com.v1.DemandPlanningService.service.DashboardService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


@Controller
public class DashboardController {

	private Logger log = Logger.getLogger(DashboardController.class);
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private DashboardDAO dashboardDAO;
	
	
	@PostMapping(value = "/productCategoryWiseData")
	@ResponseBody
	public String getProductCategoryWiseData(@RequestParam String productCategoryValue,@RequestParam String productCategory, HttpServletRequest request, HttpSession session) {

		JSONObject obj = new JSONObject();
		Double noOfPages = 1.0;
		try {
			if (session == null) {
				obj.put("Expire", "Expire");
				return obj.toString();
			}
			int totalPages = 0;
			String sortBy = request.getParameter("sidx");
			int currentPage = Integer.valueOf(request.getParameter("page")).intValue();
			String sortOrder = request.getParameter("sord");
			int recordSize = Integer.valueOf(request.getParameter("rows")).intValue();
			String searchField = request.getParameter("searchField");
			String searchString = request.getParameter("searchString");
			String searchOper = request.getParameter("searchOper");
			boolean isSearch = Boolean.valueOf(request.getParameter("_search")).booleanValue();

			System.err.println("searchString is :"+searchString + " isSearch is : "+isSearch);
			
			JSONArray objArray = dashboardService.getProductCategoryWiseDataArray(productCategoryValue,productCategory, currentPage, recordSize , isSearch ,searchString );

			if(!isSearch) {
				noOfPages = Double.valueOf(Double.valueOf(dashboardService.getTotalAllProductCategoryWiseData(productCategoryValue,productCategory)).doubleValue() / Double.valueOf(recordSize).doubleValue());
				if ((noOfPages.doubleValue() > 0.0D) && (noOfPages.doubleValue() < 1.0D)) {
					totalPages = 1;
				} else if (noOfPages.doubleValue() % 1.0D > 0.0D) {
					totalPages = noOfPages.intValue() + 1;
				} else {
					totalPages = noOfPages.intValue();
				}
			}else {
				totalPages = 1;
			}

			obj.put("page", Integer.valueOf(currentPage));
			obj.put("total", Integer.valueOf(totalPages));
			obj.put("records", noOfPages);
			obj.put("rows", objArray);

		} catch (Exception e) {
			System.out.println("exception while get dashboard data");
			e.printStackTrace();
			obj.put("error", e.getLocalizedMessage());
			log.info("(productCategoryWiseData) Exception is ", e);
		}
		return obj.toString();
	}
	
	@PostMapping(value = "/editBNLeadTime")
	@ResponseBody
	public String editBNLeadTime(@RequestParam String id,@RequestParam String leadTime, HttpSession session) {
		
		JSONObject obj = new JSONObject();
		try {
			if (session == null) {
				obj.put("Expire", "Expire");
				return obj.toString();
			}
			
			obj.put("success",dashboardDAO.getEditBNLeadTime(id,leadTime));
			
		} catch (Exception e) {
			obj.put("error", e.getLocalizedMessage());
			log.info("(editBNLeadTime) Exception is ", e);
		}
		return obj.toString();
	}
	
	
	@PostMapping(value = "/getGraphData")
	@ResponseBody
	public Response getGraphData(@RequestParam(value="year") int year , @RequestParam(value="categoryId") String categoryId ) {
		Response response = new Response();
		try {
			dashboardService.getGraphDataService(year, categoryId, response);
		}catch(Exception e) {
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)DpsConstant.EXCEPTION_WHILE_DATA_FATCHING);
		}
		return response;
	}
	
	@PostMapping(value = "/getDashboardData")
	@ResponseBody
	public Response getDashboardData(@RequestParam(value="year") int year , @RequestParam(value="categoryId") String categoryId ) {
		Response response = new Response();
		try {
			JSONObject obj = new JSONObject();
			Integer liceanceCount = 0;
			liceanceCount = dashboardService.getTotalLiceanceCountService(year, categoryId);
			obj.put("LiceanceCount", liceanceCount);
			response.setStatusCode(DpsConstant.SUCCESS_CODE);
			response.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			response.setObject((Object)obj);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception while dashboard count data");
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)DpsConstant.EXCEPTION_WHILE_DATA_FATCHING);
		}
		return response;
	}
	
	@PostMapping(value = "/getDashboardCalculatedData")
	@ResponseBody
	public String getDashboardCalculatedData(@RequestParam(value="year") int year, @RequestParam(value="categoryId") String categoryId ) {
		Response response = new Response();
		JSONArray jarr = null;
		JSONObject mainObj = new JSONObject();
		String returnVal =null;
		try {
			jarr = dashboardService.getDashboardCalculatedDataService(year, categoryId );
			mainObj.put("page", Integer.valueOf(01));
			mainObj.put("total", Integer.valueOf(10));
			mainObj.put("records", 1);
			mainObj.put("rows", jarr);
			returnVal = mainObj.toString();
		}catch(Exception e) {
			
		}
		return returnVal;
	}
	
}
