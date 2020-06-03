package com.v1.DemandPlanningService.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.v1.DemandPlanningService.bean.Users;

@PropertySource("classpath:application.properties")
@Component
public class MyAuthentictionClass implements AuthenticationProvider {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String dbUserName = "";
		String dbPassword = "";
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		String role ="";
		
		try {
			
			List<Users> a = jdbcTemplate.query("select * from ifsapp.DPS_USER_TAB where USER_ID = ? and STATUS = 'active' ",
					new UserMapper(), username);
			if (a.size() > 0) {
				dbUserName = a.get(0).getUserId();
				dbPassword = a.get(0).getPassword();
				role = a.get(0).getUserRole();
			} else {
				dbUserName = "";
				dbPassword = "";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (dbUserName.equalsIgnoreCase(username)  && dbPassword.equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.EMPTY_LIST);
		}else if ( dbUserName == "" && dbPassword == "" ) {
			session.invalidate();
			throw new BadCredentialsException("External system authentication failed");
		}else if (dbPassword == "") {
			session.invalidate();
			throw new BadCredentialsException("External system authentication failed2");
		}else {
			session.invalidate();
			throw new BadCredentialsException("External system authentication failed3");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
}

class UserMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users user = new Users();
		
		user.setUserId(rs.getString("USER_ID") != null ? rs.getString("USER_ID") : "");
		user.setEmail(rs.getString("EMAIL_ID") != null ? rs.getString("EMAIL_ID") : "");
		user.setPassword(rs.getString("password") != null ? rs.getString("password") : "");
		user.setUserRole(rs.getString("USER_ROLE_ID") != null ? rs.getString("USER_ROLE_ID") : "");
		
		return user;
	}
}
