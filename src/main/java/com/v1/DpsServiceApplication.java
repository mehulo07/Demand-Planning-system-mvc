package com.v1;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DpsServiceApplication extends SpringBootServletInitializer {

	static Logger logger = Logger.getLogger(DpsServiceApplication.class.getName());
			
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DpsServiceApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DpsServiceApplication.class, args);
	}
	

}
