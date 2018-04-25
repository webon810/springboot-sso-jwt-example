package com.jwtsso.test.comjwtssotest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//this is for springBoot 1.5.10
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//this is for springBoot 2.0.1
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

	@Value("${services.auth}")
	private String authService;

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
		registrationBean.addUrlPatterns("/protected-resource");

		return registrationBean;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
