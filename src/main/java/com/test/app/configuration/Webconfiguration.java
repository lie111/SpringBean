package com.test.app.configuration;

import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.test.app.services.UserService;
import com.test.app.services.impl.UserServiceImpl;

@Configuration
@ComponentScan(basePackages = {"com.test.app"})
@EnableWebMvc
public class Webconfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setPrefix("/WEB-INF/pages/");
	resolver.setSuffix(".jsp");
	return resolver;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver(){
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520);		
		return multipartResolver;
	}
	
	@Bean
	public LocalValidatorFactoryBean getLocalValidatorFactoryBean(){
		
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		return validator;
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/images/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public DataSource getdatasource(){
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/studentmanagment");
		ds.setUsername("Heng");
		ds.setPassword("12345");				
		return ds;
	}
	
	@Bean 
	DataSource getmydata(){
		
		return getdatasource();
	}
	
//	if inject via constructor
//	@Bean
//	public StudentServiceImpl ssi(){
//		
//		return new StudentServiceImpl(getdatasource());
//	}
	
//	
////	qulifier will need if two retrun the same interface
//	@Bean
//	public StudentService ssi1(){
//		
//		return new StudentServiceImpl(null);
//	}
	
//	if inject via setter
//	@Bean
//	public StudentServiceImpl ssi(){
//		
//		StudentServiceImpl obj = new StudentServiceImpl();
//		obj.setDatasource(getdatasource());
//		return obj;
//	}
}
