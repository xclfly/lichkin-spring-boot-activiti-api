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
		LKDBResource.addColumn("30005000", "SysActivitiProcessConfigEntity", "processCode");
		LKDBResource.addColumn("30005001", "SysActivitiProcessConfigEntity", "processKey");
		LKDBResource.addColumn("30005002", "SysActivitiProcessConfigEntity", "processType");
		LKDBResource.addColumn("30005003", "SysActivitiProcessConfigEntity", "processName");
		LKDBResource.addColumn("30005004", "SysActivitiProcessConfigEntity", "deptId");
		LKDBResource.addColumn("30005005", "SysActivitiProcessConfigEntity", "available");
		LKDBResource.addColumn("30005006", "SysActivitiProcessConfigEntity", "stepCount");
		LKDBResource.addColumn("30005007", "SysActivitiProcessConfigEntity", "compId");
		LKDBResource.addColumn("30005008", "SysActivitiProcessConfigEntity", "usingStatus");
		LKDBResource.addColumn("30005009", "SysActivitiProcessConfigEntity", "insertTime");
		LKDBResource.addColumn("30005010", "SysActivitiProcessConfigEntity", "id");
	}

}