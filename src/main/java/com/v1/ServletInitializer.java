package com.v1;

import org.apache.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	static Logger logger = Logger.getLogger(ServletInitializer.class.getName());

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.debug("inside ServletInitializer");
		return application.sources(DpsServiceApplication.class);
	}

}
