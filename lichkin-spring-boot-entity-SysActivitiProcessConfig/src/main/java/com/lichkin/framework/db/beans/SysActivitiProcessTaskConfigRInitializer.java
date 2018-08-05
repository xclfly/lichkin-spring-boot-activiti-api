package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class SysActivitiProcessTaskConfigRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiProcessTaskConfigEntity", "T_SYS_ACTIVITI_PROCESS_TASK_CONFIG", "SysActivitiProcessTaskConfigEntity");
		LKDBResource.addColumn("30005000", "SysActivitiProcessTaskConfigEntity", "configId");
		LKDBResource.addColumn("30005001", "SysActivitiProcessTaskConfigEntity", "taskName");
		LKDBResource.addColumn("30005002", "SysActivitiProcessTaskConfigEntity", "userId");
		LKDBResource.addColumn("30005003", "SysActivitiProcessTaskConfigEntity", "userName");
		LKDBResource.addColumn("30005004", "SysActivitiProcessTaskConfigEntity", "step");
		LKDBResource.addColumn("30005005", "SysActivitiProcessTaskConfigEntity", "id");
	}

}