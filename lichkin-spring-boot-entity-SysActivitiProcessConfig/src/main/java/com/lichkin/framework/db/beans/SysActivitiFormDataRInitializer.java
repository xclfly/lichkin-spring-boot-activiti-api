package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class SysActivitiFormDataRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity", "T_SYS_ACTIVITI_FORM_DATA", "SysActivitiFormDataEntity");
		LKDBResource.addColumn("30007000", "SysActivitiFormDataEntity", "loginId");
		LKDBResource.addColumn("30007001", "SysActivitiFormDataEntity", "processConfigId");
		LKDBResource.addColumn("30007002", "SysActivitiFormDataEntity", "processInstanceId");
		LKDBResource.addColumn("30007003", "SysActivitiFormDataEntity", "formDataJson");
		LKDBResource.addColumn("30007004", "SysActivitiFormDataEntity", "formTypeCode");
		LKDBResource.addColumn("30007005", "SysActivitiFormDataEntity", "approvalStatus");
		LKDBResource.addColumn("30007006", "SysActivitiFormDataEntity", "field1");
		LKDBResource.addColumn("30007007", "SysActivitiFormDataEntity", "field2");
		LKDBResource.addColumn("30007008", "SysActivitiFormDataEntity", "field3");
		LKDBResource.addColumn("30007009", "SysActivitiFormDataEntity", "field4");
		LKDBResource.addColumn("30007010", "SysActivitiFormDataEntity", "field5");
		LKDBResource.addColumn("30007011", "SysActivitiFormDataEntity", "field6");
		LKDBResource.addColumn("30007012", "SysActivitiFormDataEntity", "field7");
		LKDBResource.addColumn("30007013", "SysActivitiFormDataEntity", "field8");
		LKDBResource.addColumn("30007014", "SysActivitiFormDataEntity", "field9");
		LKDBResource.addColumn("30007015", "SysActivitiFormDataEntity", "compId");
		LKDBResource.addColumn("30007016", "SysActivitiFormDataEntity", "usingStatus");
		LKDBResource.addColumn("30007017", "SysActivitiFormDataEntity", "insertTime");
		LKDBResource.addColumn("30007018", "SysActivitiFormDataEntity", "id");
	}

}