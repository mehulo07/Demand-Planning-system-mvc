package com.v1.DemandPlanningService.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.DashboardCalculation;
import com.v1.DemandPlanningService.bean.ProductCategoryAction;
import com.v1.DemandPlanningService.bean.Response;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.dto.YearMonthWiseDataCount;
import com.v1.DemandPlanningService.mapper.DashboardCalculationMapper;
import com.v1.DemandPlanningService.mapper.ProductCategoryActionMapper;
import com.v1.DemandPlanningService.mapper.YearMonthWiseDataCountMapper;

import net.sf.json.JSONArray;

@PropertySource(value="classpath:dashboard.properties",ignoreResourceNotFound=true)
@Repository
public class DashboardDAO {
private static final Logger logger = Logger.getLogger(ProductSettingDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namejdbcTemplate;
	
	@Autowired
	private Environment propSource;

	public List<ProductCategoryAction> getProductCategoryWiseDataList(String productCategoryValue, String productCategory, int startIndex,int endIndex ,boolean isSearch ,String catalogNo) {
		
		String query  =  propSource.getProperty("getProductCategoryWiseDataList") ;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCategoryValue", productCategoryValue);
		paramMap.put("productCategory", productCategory);
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", endIndex);
		
		if(isSearch) {
			paramMap.put("catalogNo", catalogNo);
			System.out.println("catalogNo"+catalogNo);
			query = query.replace("@", " AND   CATALOG_NO = :catalogNo ");
		}else {
			query = query.replace("@", " ");
		}
		RowMapper<ProductCategoryAction> rowMapper = new ProductCategoryActionMapper();
		return namejdbcTemplate.query(query, paramMap, rowMapper);
	}

	public Long getTotalAllProductCategoryWiseData(String productCategoryValue, String productCategory) {
		String query = propSource.getProperty("getTotalAllProductCategoryWiseData") ;
		return jdbcTemplate.queryForObject(query, new Object[] { productCategory , productCategoryValue}, Long.class);
	}

	public String getEditBNLeadTime(String id, String leadTime) throws SQLException {
		String query = propSource.getProperty("getEditBNLeadTime") ;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("BN_LEAD_TIME", Integer.parseInt(( (leadTime == null  ||  leadTime.trim().equals("")) ? "0" : leadTime)));
		paramMap.put("id", Integer.parseInt(id));
		
		int i = namejdbcTemplate.update(query,   paramMap);
		return "Lead time has been updated.";
	}
	
	public List<YearMonthWiseDataCount> getLiceanceCountDAO(int year,String category,String categoryId) {
		return (List<YearMonthWiseDataCount>) jdbcTemplate.query((propSource.getProperty("getLiceanceCount")), new Object[] {categoryId , year}, new YearMonthWiseDataCountMapper());	
	}
	
	public List<YearMonthWiseDataCount> getShortDatedCountDAO(int year,String category,String categoryId) {
		return (List<YearMonthWiseDataCount>) jdbcTemplate.query((propSource.getProperty("getShortDatedData")), new Object[] {year , categoryId}, new YearMonthWiseDataCountMapper());
	}

	public List<YearMonthWiseDataCount> getProfitableLineCountDAO(String flag , int year,String category,String categoryId) {
		return (List<YearMonthWiseDataCount>) jdbcTemplate.query((propSource.getProperty("getProfitableNcsoDataCount")), new Object[] {year ,flag , categoryId} , new YearMonthWiseDataCountMapper());
	}
	
	public List<YearMonthWiseDataCount> getNCSOCountDAO(String flag , int year,String category,String categoryId) {
		return (List<YearMonthWiseDataCount>) jdbcTemplate.query((propSource.getProperty("getProfitableNcsoDataCount")), new Object[] {year ,flag , categoryId} , new YearMonthWiseDataCountMapper());
	}
	
	public JSONArray getDashboardCalculatedDataDao(int year , String categoryId ) {
		return  jdbcTemplate.queryForObject(propSource.getProperty("getCalculatedDataForDashBoard"), new Object[] { year, categoryId} , new DashboardCalculationMapper());
	}
	
	public Integer getTotalLiceanceCountDao(int year , String categoryId ) {
		return  jdbcTemplate.queryForObject(propSource.getProperty("getTotalLiceanceCount"), new Object[] { categoryId , year } , Integer.class);
		
	}
	
}
