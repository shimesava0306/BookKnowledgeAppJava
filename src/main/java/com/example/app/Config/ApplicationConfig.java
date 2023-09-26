package com.example.app.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
registry.addResourceHandler("/uploads/**")
.addResourceLocations("file:///C:/Users/zd2N05/pleiades/workspace/BookKnowledgeApp/src/main/resources/static/img/uploads/");
}
}