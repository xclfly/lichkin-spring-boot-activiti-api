package com.lichkin.springframework.db.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lichkin.framework.defines.LKFrameworkStatics;

/**
 * Activiti数据库配置
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(

		entityManagerFactoryRef = "activitiLocalContainerEntityManagerFactoryBean",

		transactionManagerRef = "activitiPlatformTransactionManager",

		basePackages = { LKFrameworkStatics.DB_SECONDARY_DAO_PACKAGES }

)
public class LKDBActivitiConfigs extends LKDBConfigs {

	/** 数据源 */
	private DataSource activitiDataSource;


	/**
	 * 获取数据源
	 * @return 数据源
	 */
	public DataSource getActivitiDataSource() {
		return activitiDataSource;
	}


	/** 实体类管理对象工厂 */
	private LocalContainerEntityManagerFactoryBean activitiLocalContainerEntityManagerFactoryBean;


	/**
	 * 获取实体类管理对象工厂
	 * @return 实体类管理对象工厂
	 */
	public LocalContainerEntityManagerFactoryBean getActivitiLocalContainerEntityManagerFactoryBean() {
		return activitiLocalContainerEntityManagerFactoryBean;
	}


	/** 事务管理对象 */
	private PlatformTransactionManager activitiPlatformTransactionManager;


	/**
	 * 获取事务管理对象
	 * @return 事务管理对象
	 */
	public PlatformTransactionManager getActivitiPlatformTransactionManager() {
		return activitiPlatformTransactionManager;
	}


	/** 是否显示SQL语句 */
	@Value(value = "${lichkin.framework.db.activiti.jpa.show-sql}")
	private String showSql;

	/** 建表方式 */
	@Value(value = "${lichkin.framework.db.activiti.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	/** 命名策略 */
	@Value(value = "${lichkin.framework.db.activiti.jpa.hibernate.naming.physical-strategy}")
	private String namingPhysicalStrategy;


	/**
	 * 构建数据源
	 * @return 数据源
	 */
	@Bean(name = "activitiDataSource")
	@ConfigurationProperties(prefix = "lichkin.framework.db.activiti")
	public DataSource activitiDataSource() {
		activitiDataSource = DataSourceBuilder.create().build();
		return activitiDataSource;
	}


	/**
	 * 配置实体类管理对象工厂
	 * @param builder 实体类管理对象工厂
	 * @return 实体类管理对象工厂
	 */
	@Bean(name = "activitiLocalContainerEntityManagerFactoryBean")
	@DependsOn(value = "activitiDataSource")
	public LocalContainerEntityManagerFactoryBean activitiLocalContainerEntityManagerFactoryBean(final EntityManagerFactoryBuilder builder) {
		activitiLocalContainerEntityManagerFactoryBean = builder.dataSource(activitiDataSource)

				.properties(buildJpaProperties(showSql, ddlAuto, namingPhysicalStrategy))

				.packages(LKFrameworkStatics.DB_SECONDARY_ENTITY_PACKAGES)

				.persistenceUnit("activitiPersistenceUnit")

				.build();
		return activitiLocalContainerEntityManagerFactoryBean;
	}


	/**
	 * 定义事务管理对象
	 * @param builder 实体类管理对象工厂
	 * @return 事务管理对象
	 */
	@Bean(name = "activitiPlatformTransactionManager")
	@DependsOn(value = "activitiLocalContainerEntityManagerFactoryBean")
	public PlatformTransactionManager activitiPlatformTransactionManager(final EntityManagerFactoryBuilder builder) {
		activitiPlatformTransactionManager = new JpaTransactionManager(activitiLocalContainerEntityManagerFactoryBean(builder).getObject());
		return activitiPlatformTransactionManager;
	}

}
