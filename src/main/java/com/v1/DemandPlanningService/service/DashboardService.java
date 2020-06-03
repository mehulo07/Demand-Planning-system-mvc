package com.v1.DemandPlanningService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.gson.JsonArray;
import com.v1.DemandPlanningService.bean.ProductCategoryAction;
import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.dao.DashboardDAO;
import com.v1.DemandPlanningService.dto.YearMonthWiseDataCount;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DashboardService {

	@Autowired
	private DashboardDAO dashboardDAO;

	@Autowired
	private ProductSettingService productSettingService;
	
	public JSONArray getProductCategoryWiseDataArray(String productCategoryValue, String productCategory,int currentPage, int recordSize , boolean isSearch , String searchString) {

		currentPage--;
		int startIndex = currentPage * recordSize + 1;
		int endIndex = startIndex + recordSize - 1;
		List<ProductCategoryAction> pList = dashboardDAO.getProductCategoryWiseDataList(productCategoryValue,productCategory, startIndex, endIndex , isSearch , searchString); 
		
		
		return getProductCategoryWiseDataArray(pList);
	}

	private JSONArray getProductCategoryWiseDataArray(List<ProductCategoryAction> pList) {

		JSONObject pListJson = null;
		JSONArray tempArray = null;
		if (pList == null) {
			return null;
		}
		if (pList.size() == 0) {
			return null;
		}
		JSONArray pListJsonArray = new JSONArray();
		for (ProductCategoryAction pl : pList) {
			tempArray = new JSONArray();		

			tempArray.add(pl.getId());
			tempArray.add(pl.getProcductCategory());
			tempArray.add(pl.getStockCategory());
			tempArray.add(pl.getPartNo());
			tempArray.add(pl.getPartDesc());
			tempArray.add(pl.getCsProfit());
			tempArray.add(pl.getCsUnits());
			tempArray.add(pl.getCsWeeks());
			tempArray.add(pl.getCsPallets());
			tempArray.add(pl.getRosUnits());
			tempArray.add(pl.getRosTrend());
			tempArray.add(pl.getItsUnits());			
			tempArray.add(pl.getItsWeeks());			
			tempArray.add(pl.getItsDelevery());
			tempArray.add(pl.getItsPallets());
			tempArray.add(pl.getBnUnits());
			tempArray.add(pl.getBnWeeks());
			tempArray.add(pl.getBnLead());
			

			pListJson = new JSONObject();
			pListJson.put("id", pl.getId().trim());
			pListJson.put("cell", tempArray);
			pListJsonArray.add(pListJson);
			tempArray = null;
			pListJson = null;
		}
		pList.clear();
		return pListJsonArray;
	}

	public Long getTotalAllProductCategoryWiseData(String productCategoryValue, String productCategory) {
		return dashboardDAO.getTotalAllProductCategoryWiseData(productCategoryValue, productCategory);
	}
	
	public void getDashboardDataService(int year , String categoryId , Response response) {
		
	}
	
	public Integer getTotalLiceanceCountService(int year , String categoryId ) {
		return dashboardDAO.getTotalLiceanceCountDao(year,categoryId);
	}

	public void getGraphDataService(int year , String categoryId , Response response) {
		
		Integer ncso , profitableLine , shortDated , dataCount ;
		String category = null;
		List<String> typeArr = new ArrayList<String>();
		JSONArray responseArray = new JSONArray();
		List<YearMonthWiseDataCount>  monthWiseCategoryDataCount = null;
		List<YearMonthWiseDataCount>  monthWiseShortDateDataCount = null;
		List<YearMonthWiseDataCount>  monthWiseNCSO_DataCount = null;
		List<YearMonthWiseDataCount>  monthWiseProfitable_DateDataCount = null;
		try {
			category = productSettingService.getCategoryName(categoryId);
			//GetCategoryDataDone
			monthWiseCategoryDataCount =  getLiceanceCountService(year ,category,categoryId);
			monthWiseShortDateDataCount = getShortDatedCountService(year ,category,categoryId);
			monthWiseProfitable_DateDataCount = getProfitableLineCountService(DpsConstant.DPS_PROFITALE_LINE_FLAG , year ,category,categoryId);
			monthWiseNCSO_DataCount = getNCSOCountService(DpsConstant.DPS_NCSO_FLAG , year ,category,categoryId);

			typeArr.add("NCSOs");
			typeArr.add("Short-dated");
			typeArr.add("Profitable Lines");
			
			//SetGraphData into CategoryObject
			getCategoryObject( category , responseArray,monthWiseCategoryDataCount);  
			//SetGraphData into NCSO,Profitable Lines and sortDated 
			getGraphOtherDetails(responseArray , monthWiseNCSO_DataCount , monthWiseProfitable_DateDataCount , monthWiseShortDateDataCount , typeArr);
			
			response.setStatusCode(DpsConstant.SUCCESS_CODE);
			response.setStatusMessage(DpsConstant.SUCCESS_MESSAGE);
			response.setObject((Object)responseArray);
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Exception while get data and prepare response");
			
			response.setStatusCode(DpsConstant.CLIENT_ERROR_CODE);
			response.setStatusMessage(DpsConstant.ERROR_MESSAGE);
			response.setObject((Object)DpsConstant.EXCEPTION_WHILE_DATA_FATCHING);
		}
	}
	
	public List<YearMonthWiseDataCount> getLiceanceCountService(int year,String category,String categoryId) {
		return dashboardDAO.getLiceanceCountDAO(year,category,categoryId);
	}
	
	public List<YearMonthWiseDataCount> getShortDatedCountService(int year,String category,String categoryId) {
		return dashboardDAO.getShortDatedCountDAO(year,category,categoryId);
	}
	
	public List<YearMonthWiseDataCount> getNCSOCountService(String flag ,int year,String category,String categoryId) {
		return dashboardDAO.getNCSOCountDAO(flag ,year,category,categoryId);
		//return new Integer(20);
	}

	public List<YearMonthWiseDataCount> getProfitableLineCountService(String flag , int year,String category,String categoryId) {
		return dashboardDAO.getProfitableLineCountDAO(flag , year,category,categoryId);
		//return new Integer(30);
	}
	
	public void getCategoryObject( String category , JSONArray responseArray ,List<YearMonthWiseDataCount>  MonthWiseCategoryData) {
		
		JSONObject outerMainObject = new JSONObject();
		JSONObject itemStyleObject = new JSONObject();
		JSONObject normalObject = new JSONObject();
		JSONObject labelObject = new JSONObject();
		JSONObject textStyleObject = new JSONObject();
		JSONArray dataArray = new JSONArray();
		
		textStyleObject.put("color", "#fff");
		
		labelObject.put("show", true);
		labelObject.put("textStyle",textStyleObject);
		labelObject.put("position", "insideTop");
		
		normalObject.put("barBorderRadius", 0);
		normalObject.put("color", "#635bd6");
		normalObject.put("label", labelObject);

		itemStyleObject.put("normal", normalObject);
		
		if(MonthWiseCategoryData!=null) {
			for(int i=1;i<13;i++) {
				int tempCount = 0;
				for(YearMonthWiseDataCount obj :MonthWiseCategoryData) {
					if(obj.getMonth() == i) {
						tempCount=obj.getCount();
					}
				}
				dataArray.add(tempCount);
			}
		}else{
			for(int i=1;i<13;i++) {
				dataArray.add(0);
			}
		}
		
		outerMainObject.put("name", category);
		outerMainObject.put("type", "bar");
		outerMainObject.put("stack", "split");
		outerMainObject.put("barMaxWidth", 50);
		outerMainObject.put("barGap", "50%");
		outerMainObject.put("itemStyle", itemStyleObject);
		outerMainObject.put("data", dataArray);
		
		responseArray.add(outerMainObject);
	}
	
	public void getGraphOtherDetails(JSONArray responseArray ,List<YearMonthWiseDataCount>  monthWiseNCSO_DataCount ,
			List<YearMonthWiseDataCount>  monthWiseProfitable_DateDataCount,List<YearMonthWiseDataCount>  monthWiseShortDateDataCount
			, List<String> typeArr) {
		
		for(String dataType : typeArr ) {
			JSONObject outerMainObject = new JSONObject();
			JSONObject itemStyleObject = new JSONObject();
			JSONObject normalObject = new JSONObject();
			JSONObject labelObject = new JSONObject();
			JSONArray dataArray = new JSONArray();
			List<YearMonthWiseDataCount> value = null; 
			
			labelObject.put("show", true);
			labelObject.put("position", "insideTop");
			normalObject.put("barBorderRadius", 0);
			
			if(dataType.equalsIgnoreCase("NCSOs")) {
				normalObject.put("color", "#f742aa");	
				value = monthWiseNCSO_DataCount;
			}else if(dataType.equalsIgnoreCase("Short-dated")) {
				normalObject.put("color", "#5b3d6d");
				value = monthWiseShortDateDataCount;
			}else if(dataType.equalsIgnoreCase("Profitable Lines")) {
				normalObject.put("color", "#6d3d67");
				value = monthWiseProfitable_DateDataCount;
			}else {
				normalObject.put("color", "#FF0000");
			}
			normalObject.put("label", labelObject);
			itemStyleObject.put("normal", normalObject);

			
			if(value!=null) {
				for(int i=1;i<13;i++) {
					int tempCount = 0;
					for(YearMonthWiseDataCount obj :value) {
						if(obj.getMonth() == i) {
							tempCount=obj.getCount();
						}
					}
						dataArray.add(tempCount);
				}
			}else{
				for(int i=1;i<13;i++) {
					dataArray.add(0);
				}
			}
			outerMainObject.put("name", dataType);
			outerMainObject.put("type", "bar");
			outerMainObject.put("stack", "split");
			outerMainObject.put("itemStyle", itemStyleObject);
			outerMainObject.put("data", dataArray);
			
			responseArray.add(outerMainObject);
		}
		//System.out.println("Graph responseArray is :"+responseArray);
	}
	
	public JSONArray getDashboardCalculatedDataService(int year ,String categoryId ) {
		 return dashboardDAO.getDashboardCalculatedDataDao(year,categoryId);
	}
	
}
