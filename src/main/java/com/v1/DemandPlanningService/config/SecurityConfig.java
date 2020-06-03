package com.v1.DemandPlanningService.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = { "com.*","com.v1.DemandPlanningService.config" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	SessionRegistry sessionRegistry;
	
	@Autowired
	MyAuthenticationFailureHandler myAuthenticationFailureHandler;

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().anyRequest()
					.authenticated().and().formLogin().loginPage("/login")
					.successHandler(customSuccessHandler).failureHandler(myAuthenticationFailureHandler).usernameParameter("ssoId")
					.passwordParameter("password").and().logout().invalidateHttpSession(true)
					.logoutUrl("login?logout").deleteCookies("JSESSIONID")
					.and().csrf().disable().exceptionHandling()
					.accessDeniedPage("/Access_Denied");
					http.csrf().disable();
					
					http.sessionManagement().sessionAuthenticationErrorUrl("/sessionIn").maximumSessions(5)
					.maxSessionsPreventsLogin(false).expiredUrl("/sessionIn").sessionRegistry(sessionRegistry).and()
					.sessionFixation().newSession();
			http.csrf().disable();

			
		} finally {

		}
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticator());
	}

	@Bean
	public AuthenticationProvider authenticator() throws Exception {
		return new MyAuthentictionClass();
	}
}
