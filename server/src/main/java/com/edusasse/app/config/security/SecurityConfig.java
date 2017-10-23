package com.edusasse.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.edusasse.app.security.jwt.JWTConfigurer;
import com.edusasse.app.security.jwt.TokenProvider;
import com.edusasse.app.web.error.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	
	private final TokenProvider tokenProvider;

	public SecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().authorizeRequests()
			.antMatchers("/signup").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/public").permitAll()
			.mvcMatchers("/auth/admin").hasRole("ADMIN")
	        .mvcMatchers("/auth").hasAnyRole("ADMIN","USER")
	        .antMatchers("/*").permitAll()
			.anyRequest().authenticated().
		and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).
		and().apply(new JWTConfigurer(this.tokenProvider));
	}

}