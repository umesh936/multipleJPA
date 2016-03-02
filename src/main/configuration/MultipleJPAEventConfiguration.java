package com.umesh.learning.multipleJPA.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableJpaRepositories(basePackages = "com.umesh.learning.multipleJPA.repository.user", entityManagerFactoryRef = "eventEntityManagerFactory", transactionManagerRef = "eventTransactionManager")
@EnableTransactionManagement
public class MultipleJPAEventConfiguration  {

	 @Autowired
	    JpaVendorAdapter jpaVendorAdapter;
	
	@Bean(name = "dataSourceEvent")
	public DataSource getDataSourceEvent() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eventTest");
		dataSource.setUsername("root");
		dataSource.setPassword("root123");
		dataSource.setMaxActive(5);
		return dataSource;
	}
	
	 @Bean(name = "eventEntityManager")
	    public EntityManager entityManager() {
	        return eventEntityManagerFactory().createEntityManager();
	    }
	
	@Bean(name="eventEntityManagerFactory")
	public EntityManagerFactory eventEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(getDataSourceEvent());
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan(new String[] { "com.umesh.learning.multipleJPA.model" });
		lef.setPersistenceUnitName("event");
		return lef.getObject();
	}

	@Bean(name="eventTransactionManager")
	public PlatformTransactionManager eventTransactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager((EntityManagerFactory) eventEntityManagerFactory());
		return tm;
	}
}
