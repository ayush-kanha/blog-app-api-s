package com.blog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.security.CustomUserDetailsService;
import com.blog.security.JwtAuthenticationEntryPoint;
import com.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.antMatchers("/api/v1/auth/login").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean  
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Bean
//	public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
//		
//		EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
//	            EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
//	        contextSourceFactoryBean.setPort(0);
//	        return contextSourceFactoryBean;
//		
//	}
//	
//	@Bean
//	AuthenticationManager authenticationManager(BaseL contextSource)
//	{
//		
//	}
	
	
	
}

// This one is deprecated


//
//	@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	
//	http
//	.csrf()
//	.disable()
//	.authorizeHttpRequests()
//	.anyRequest()
//	.authenticated()
//	.and()
//	.httpBasic();
//	
//	
//	return http.build();
//}
//	
//}
