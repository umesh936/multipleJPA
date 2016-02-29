package com.umesh.learning.multipleJPA.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.umesh.learning.multipleJPA.controller",
		"com.umesh.learning.multipleJPA.services",
		"com.umesh.learning.multipleJPA.model",
		"com.umesh.learning.multipleJPA.repository" })

@EnableTransactionManagement
public class MultipleJPAConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public DefaultServletHttpRequestHandler createDefaultServletHttpRequestHandler() {
		return new DefaultServletHttpRequestHandler();
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/")
				.setCachePeriod(0);// 31556926);
		registry.addResourceHandler("/images/**")
				.addResourceLocations("/images/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/")
				.setCachePeriod(0);
	}

	@Bean
	MappingJackson2HttpMessageConverter jsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		return converter;
	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

	@Bean
	RequestMappingHandlerAdapter adaptor() {
		RequestMappingHandlerAdapter adaptor = new RequestMappingHandlerAdapter();
		adaptor.getMessageConverters().add(jsonMessageConverter());
		return adaptor;
	}

}
