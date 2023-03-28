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
import org.springframework.security.web.SecurityFilterChain;

import com.blog.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
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
