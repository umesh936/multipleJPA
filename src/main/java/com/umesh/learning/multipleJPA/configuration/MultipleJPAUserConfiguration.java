package com.umesh.learning.multipleJPA.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.umesh.learning.multipleJPA.repository", entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")
//@EnableTransactionManagement
public class MultipleJPAUserConfiguration extends MultipleJPAConfiguration implements TransactionManagementConfigurer {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/userTest");
		dataSource.setUsername("root");
		dataSource.setPassword("root123");
		dataSource.setMaxActive(5);
		return dataSource;
	}

	

	@Bean(name="jpaVendorAdapterUser")
	public JpaVendorAdapter jpaVendorAdapterUser() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter
				.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return jpaVendorAdapter;
	}

	@Bean(name="userEntityManager")
	public LocalContainerEntityManagerFactoryBean userEntityManager(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan(new String[] { "com.umesh.learning.multipleJPA.model" });
		lef.setPersistenceUnitName("user");
		return lef;
	}


	@Bean(name="userTransactionManager")
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(userEntityManager(getDataSource(),
				jpaVendorAdapterUser()).getObject());
		tm.setDataSource(getDataSource());
		return tm;
	}
}
