package com.v1.DemandPlanningService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:application.properties")

public class Authenticator {

	@Autowired
	private Environment prop;

	/*
	 * public Connection getConnection( appUser) {
	 * 
	 * String userName = appUser.getUsername(); String password =
	 * appUser.getPassword();
	 * 
	 * Connection con = null; try { con =
	 * DriverManager.getConnection(prop.getProperty("spring.datasource.url"),
	 * userName, password); if (con == null) { throw new
	 * BadCredentialsException("Incorrect UserName/Password."); } } catch
	 * (SQLException e) { throw new
	 * BadCredentialsException("Incorrect UserName/Password."); } catch (Exception
	 * e) { throw new BadCredentialsException("Incorrect UserName/Password."); }
	 * return con; }
	 */
}
