package com.v1.DemandPlanningService.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.v1.DemandPlanningService.bean.Users;



@Controller
public class LoginController {
	
	private Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/login" )	
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String err, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request ) throws UnknownHostException {
		ModelAndView model = new ModelAndView();	
		if(err != null) {
			model.addObject("error", "Username or password is wrong");	
		}else {
			model.addObject("error", " ");
		}
		model.setViewName("login");
		return model;
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextLogoutHandler x = new SecurityContextLogoutHandler();
			x.setInvalidateHttpSession(true);
			x.setClearAuthentication(true);
			x.logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

}
