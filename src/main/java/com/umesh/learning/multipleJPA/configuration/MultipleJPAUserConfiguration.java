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

@Configuration
@EnableJpaRepositories(basePackages = { "com.umesh.learning.multipleJPA.repository.user" }, entityManagerFactoryRef = "userEntityManagerFactory", transactionManagerRef = "userTransactionManager")
@EnableTransactionManagement
public class MultipleJPAUserConfiguration {

	@Autowired
	JpaVendorAdapter jpaVendorAdapter;

	@Bean(name = "dataSourceUser")
	public DataSource getDataSourceUser() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/userTest");
		dataSource.setUsername("root");
		dataSource.setPassword("xxxxxx");
		dataSource.setMaxActive(5);
		return dataSource;
	}

	@Bean(name = "userEntityManager")
	public EntityManager userEntityManager() {
		return userEntityManagerFactory().createEntityManager();
	}

	@Bean(name = "userEntityManagerFactory")
	public EntityManagerFactory userEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(getDataSourceUser());
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan(new String[] { "com.umesh.learning.multipleJPA.model.user" });
		lef.setPersistenceUnitName("user");
		lef.afterPropertiesSet();
		return lef.getObject();
	}

	@Bean(name = "userTransactionManager")
	public PlatformTransactionManager userTransactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager(
				(EntityManagerFactory) userEntityManagerFactory());
		return tm;
	}
}
