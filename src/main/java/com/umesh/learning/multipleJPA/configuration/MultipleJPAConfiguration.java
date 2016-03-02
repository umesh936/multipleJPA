package com.umesh.learning.multipleJPA.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
		"com.umesh.learning.multipleJPA.model.user",
		"com.umesh.learning.multipleJPA.model.event",
		"com.umesh.learning.multipleJPA.repository.event",
		"com.umesh.learning.multipleJPA.repository.user" })
public class MultipleJPAConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter
				.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return jpaVendorAdapter;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

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
				.setCachePeriod(0);
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

	/*
	 * @Bean(name = "dataSourceEvent") public DataSource getDataSourceEvent() {
	 * BasicDataSource dataSource = new BasicDataSource();
	 * dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	 * dataSource.setUrl("jdbc:mysql://localhost:3306/eventTest");
	 * dataSource.setUsername("root"); dataSource.setPassword("root123");
	 * dataSource.setMaxActive(5); return dataSource; }
	 * 
	 * @Bean(name = "eventEntityManager") public EntityManager
	 * userEntityManager() { return
	 * eventEntityManagerFactory().createEntityManager(); }
	 * 
	 * @Bean(name = "eventEntityManagerFactory") public EntityManagerFactory
	 * eventEntityManagerFactory() { LocalContainerEntityManagerFactoryBean lef
	 * = new LocalContainerEntityManagerFactoryBean();
	 * lef.setDataSource(getDataSourceEvent());
	 * lef.setJpaVendorAdapter(jpaVendorAdapter()); lef.setPackagesToScan(new
	 * String[] { "com.umesh.learning.multipleJPA.model.event" });
	 * lef.setPersistenceUnitName("event"); lef.afterPropertiesSet(); return
	 * lef.getObject(); }
	 * 
	 * @Bean(name = "eventTransactionManager") public PlatformTransactionManager
	 * eventTransactionManager() { JpaTransactionManager tm = new
	 * JpaTransactionManager( (EntityManagerFactory)
	 * eventEntityManagerFactory()); return tm; }
	 */

}
