package com.v1.DemandPlanningService.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {
	
	
	
	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String getDashboardPage() {
		
		return "dashboard";
	}
	
	@RequestMapping(value = { "/viewProduct" }, method = RequestMethod.GET)
	public String getViewProductPage() {
		
		return "productSetting";
	}
	
	
	@RequestMapping(value = { "/testController" }, method = RequestMethod.POST)
	@ResponseBody
	public String testController(HttpServletRequest request , HttpSession session) {
		
		
		String test = request.getParameter("test");
		
		System.out.println("test");
		return "test";
	}

}
