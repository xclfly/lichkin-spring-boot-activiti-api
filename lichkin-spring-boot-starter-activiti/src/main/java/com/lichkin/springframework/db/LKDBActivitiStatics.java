package com.lichkin.springframework.db;

/**
 * 数据库常量定义
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface LKDBActivitiStatics {

	/** 前缀 */
	public static final String KEY = "activiti";

	/** JPA属性定义KEY */
	public static final String JPA_PORPERTEIS = KEY + "JPAProperties";

	/** 配置前缀 */
	public static final String CONFIG_KEY_PREFIX = "lichkin.framework.db." + KEY;

	/** JPA配置前缀 */
	public static final String JPA_PORPERTEIS_CONFIG_KEY_PREFIX = CONFIG_KEY_PREFIX + ".jpa";

	/** 数据源配置前缀 */
	public static final String DATA_SOURCE_PORPERTEIS_CONFIG_KEY_PREFIX = CONFIG_KEY_PREFIX + ".datasource";

	/** 持久定义KEY */
	public static final String PERSISTENCE_UNIT = KEY + "PersistenceUnit";

	/** 实体类管理对象工厂定义KEY */
	public static final String LOCAL_CONTAINER_ENTITY_MANAGER_FACTORY_BEAN = KEY + "LocalContainerEntityManagerFactoryBean";

	/** 数据源定义KEY */
	public static final String DATA_SOURCE = KEY + "DataSource";

	/** 数据源属性定义KEY */
	public static final String DATA_SOURCE_PORPERTEIS = KEY + "DataSourceProperties";

	/** 事务管理对象定义KEY */
	public static final String PLATFORM_TRANSACTION_MANAGER = KEY + "PlatformTransactionManager";

	/** dao对象名称 */
	public static final String DAO_NAME = "daoA";

	/** dao扫描包 */
	public static final String DAO_PACKAGES = "com.lichkin.**.dao.impl";

	/** entity扫描包 */
	public static final String ENTITY_PACKAGES = "com.lichkin.**.entity.impl";

}
