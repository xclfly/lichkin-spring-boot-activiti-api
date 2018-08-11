package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class SysActivitiProcessConfigRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiProcessConfigEntity", "T_SYS_ACTIVITI_PROCESS_CONFIG", "SysActivitiProcessConfigEntity");
		LKDBResource.addColumn("30004000", "SysActivitiProcessConfigEntity", "processCode");
		LKDBResource.addColumn("30004001", "SysActivitiProcessConfigEntity", "processKey");
		LKDBResource.addColumn("30004002", "SysActivitiProcessConfigEntity", "processType");
		LKDBResource.addColumn("30004003", "SysActivitiProcessConfigEntity", "processName");
		LKDBResource.addColumn("30004004", "SysActivitiProcessConfigEntity", "deptId");
		LKDBResource.addColumn("30004005", "SysActivitiProcessConfigEntity", "available");
		LKDBResource.addColumn("30004006", "SysActivitiProcessConfigEntity", "compId");
		LKDBResource.addColumn("30004007", "SysActivitiProcessConfigEntity", "usingStatus");
		LKDBResource.addColumn("30004008", "SysActivitiProcessConfigEntity", "insertTime");
		LKDBResource.addColumn("30004009", "SysActivitiProcessConfigEntity", "id");
	}

}