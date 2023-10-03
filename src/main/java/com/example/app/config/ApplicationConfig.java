package com.example.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.app.filter.AuthFilter;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:/home/trainee/uploads/");
	}

	@Bean
	FilterRegistrationBean<AuthFilter> authFilter() {
		var bean = new FilterRegistrationBean<AuthFilter>(new AuthFilter());
		bean.addUrlPatterns("/post/*");
		bean.addUrlPatterns("/mypage/*");
		return bean;
	}
}