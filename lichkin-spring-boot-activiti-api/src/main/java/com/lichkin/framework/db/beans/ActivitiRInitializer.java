package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class ActivitiRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiApiRequestLogStartProcessEntity", "T_SYS_ACTIVITI_API_REQUEST_LOG_START_PROCESS", "SysActivitiApiRequestLogStartProcessEntity");
		LKDBResource.addColumn("00010001", "SysActivitiApiRequestLogStartProcessEntity", "userId");
		LKDBResource.addColumn("00010002", "SysActivitiApiRequestLogStartProcessEntity", "processCode");
		LKDBResource.addColumn("00010003", "SysActivitiApiRequestLogStartProcessEntity", "compId");
		LKDBResource.addColumn("00010004", "SysActivitiApiRequestLogStartProcessEntity", "locale");
		LKDBResource.addColumn("00010005", "SysActivitiApiRequestLogStartProcessEntity", "token");
		LKDBResource.addColumn("00010006", "SysActivitiApiRequestLogStartProcessEntity", "appKey");
		LKDBResource.addColumn("00010007", "SysActivitiApiRequestLogStartProcessEntity", "clientType");
		LKDBResource.addColumn("00010008", "SysActivitiApiRequestLogStartProcessEntity", "versionX");
		LKDBResource.addColumn("00010009", "SysActivitiApiRequestLogStartProcessEntity", "versionY");
		LKDBResource.addColumn("00010010", "SysActivitiApiRequestLogStartProcessEntity", "versionZ");
		LKDBResource.addColumn("00010011", "SysActivitiApiRequestLogStartProcessEntity", "systemTag");
		LKDBResource.addColumn("00010012", "SysActivitiApiRequestLogStartProcessEntity", "busId");
		LKDBResource.addColumn("00010013", "SysActivitiApiRequestLogStartProcessEntity", "checkCode");
		LKDBResource.addColumn("00010014", "SysActivitiApiRequestLogStartProcessEntity", "insertSystemTag");
		LKDBResource.addColumn("00010015", "SysActivitiApiRequestLogStartProcessEntity", "insertTime");
		LKDBResource.addColumn("00010016", "SysActivitiApiRequestLogStartProcessEntity", "insertLoginId");
		LKDBResource.addColumn("00010017", "SysActivitiApiRequestLogStartProcessEntity", "updateSystemTag");
		LKDBResource.addColumn("00010018", "SysActivitiApiRequestLogStartProcessEntity", "updateTime");
		LKDBResource.addColumn("00010019", "SysActivitiApiRequestLogStartProcessEntity", "updateLoginId");
		LKDBResource.addColumn("00010020", "SysActivitiApiRequestLogStartProcessEntity", "usingStatus");
		LKDBResource.addColumn("00010021", "SysActivitiApiRequestLogStartProcessEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiApiRequestLogCompleteProcessEntity", "T_SYS_ACTIVITI_API_REQUEST_LOG_COMPLETE_PROCESS", "SysActivitiApiRequestLogCompleteProcessEntity");
		LKDBResource.addColumn("00020001", "SysActivitiApiRequestLogCompleteProcessEntity", "processInstanceId");
		LKDBResource.addColumn("00020002", "SysActivitiApiRequestLogCompleteProcessEntity", "userId");
		LKDBResource.addColumn("00020003", "SysActivitiApiRequestLogCompleteProcessEntity", "processCode");
		LKDBResource.addColumn("00020004", "SysActivitiApiRequestLogCompleteProcessEntity", "compId");
		LKDBResource.addColumn("00020005", "SysActivitiApiRequestLogCompleteProcessEntity", "locale");
		LKDBResource.addColumn("00020006", "SysActivitiApiRequestLogCompleteProcessEntity", "token");
		LKDBResource.addColumn("00020007", "SysActivitiApiRequestLogCompleteProcessEntity", "appKey");
		LKDBResource.addColumn("00020008", "SysActivitiApiRequestLogCompleteProcessEntity", "clientType");
		LKDBResource.addColumn("00020009", "SysActivitiApiRequestLogCompleteProcessEntity", "versionX");
		LKDBResource.addColumn("00020010", "SysActivitiApiRequestLogCompleteProcessEntity", "versionY");
		LKDBResource.addColumn("00020011", "SysActivitiApiRequestLogCompleteProcessEntity", "versionZ");
		LKDBResource.addColumn("00020012", "SysActivitiApiRequestLogCompleteProcessEntity", "systemTag");
		LKDBResource.addColumn("00020013", "SysActivitiApiRequestLogCompleteProcessEntity", "busId");
		LKDBResource.addColumn("00020014", "SysActivitiApiRequestLogCompleteProcessEntity", "checkCode");
		LKDBResource.addColumn("00020015", "SysActivitiApiRequestLogCompleteProcessEntity", "insertSystemTag");
		LKDBResource.addColumn("00020016", "SysActivitiApiRequestLogCompleteProcessEntity", "insertTime");
		LKDBResource.addColumn("00020017", "SysActivitiApiRequestLogCompleteProcessEntity", "insertLoginId");
		LKDBResource.addColumn("00020018", "SysActivitiApiRequestLogCompleteProcessEntity", "updateSystemTag");
		LKDBResource.addColumn("00020019", "SysActivitiApiRequestLogCompleteProcessEntity", "updateTime");
		LKDBResource.addColumn("00020020", "SysActivitiApiRequestLogCompleteProcessEntity", "updateLoginId");
		LKDBResource.addColumn("00020021", "SysActivitiApiRequestLogCompleteProcessEntity", "usingStatus");
		LKDBResource.addColumn("00020022", "SysActivitiApiRequestLogCompleteProcessEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiProcessConfigEntity", "T_SYS_ACTIVITI_PROCESS_CONFIG", "SysActivitiProcessConfigEntity");
		LKDBResource.addColumn("00030001", "SysActivitiProcessConfigEntity", "processCode");
		LKDBResource.addColumn("00030002", "SysActivitiProcessConfigEntity", "processKey");
		LKDBResource.addColumn("00030003", "SysActivitiProcessConfigEntity", "processType");
		LKDBResource.addColumn("00030004", "SysActivitiProcessConfigEntity", "processName");
		LKDBResource.addColumn("00030005", "SysActivitiProcessConfigEntity", "compId");
		LKDBResource.addColumn("00030006", "SysActivitiProcessConfigEntity", "systemTag");
		LKDBResource.addColumn("00030007", "SysActivitiProcessConfigEntity", "busId");
		LKDBResource.addColumn("00030008", "SysActivitiProcessConfigEntity", "checkCode");
		LKDBResource.addColumn("00030009", "SysActivitiProcessConfigEntity", "insertSystemTag");
		LKDBResource.addColumn("00030010", "SysActivitiProcessConfigEntity", "insertTime");
		LKDBResource.addColumn("00030011", "SysActivitiProcessConfigEntity", "insertLoginId");
		LKDBResource.addColumn("00030012", "SysActivitiProcessConfigEntity", "updateSystemTag");
		LKDBResource.addColumn("00030013", "SysActivitiProcessConfigEntity", "updateTime");
		LKDBResource.addColumn("00030014", "SysActivitiProcessConfigEntity", "updateLoginId");
		LKDBResource.addColumn("00030015", "SysActivitiProcessConfigEntity", "usingStatus");
		LKDBResource.addColumn("00030016", "SysActivitiProcessConfigEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysActivitiProcessTaskConfigEntity", "T_SYS_ACTIVITI_PROCESS_TASK_CONFIG", "SysActivitiProcessTaskConfigEntity");
		LKDBResource.addColumn("00040001", "SysActivitiProcessTaskConfigEntity", "processConfigId");
		LKDBResource.addColumn("00040002", "SysActivitiProcessTaskConfigEntity", "taskName");
		LKDBResource.addColumn("00040003", "SysActivitiProcessTaskConfigEntity", "userId");
		LKDBResource.addColumn("00040004", "SysActivitiProcessTaskConfigEntity", "userName");
		LKDBResource.addColumn("00040005", "SysActivitiProcessTaskConfigEntity", "step");
		LKDBResource.addColumn("00040006", "SysActivitiProcessTaskConfigEntity", "compId");
		LKDBResource.addColumn("00040007", "SysActivitiProcessTaskConfigEntity", "systemTag");
		LKDBResource.addColumn("00040008", "SysActivitiProcessTaskConfigEntity", "busId");
		LKDBResource.addColumn("00040009", "SysActivitiProcessTaskConfigEntity", "checkCode");
		LKDBResource.addColumn("00040010", "SysActivitiProcessTaskConfigEntity", "insertSystemTag");
		LKDBResource.addColumn("00040011", "SysActivitiProcessTaskConfigEntity", "insertTime");
		LKDBResource.addColumn("00040012", "SysActivitiProcessTaskConfigEntity", "insertLoginId");
		LKDBResource.addColumn("00040013", "SysActivitiProcessTaskConfigEntity", "updateSystemTag");
		LKDBResource.addColumn("00040014", "SysActivitiProcessTaskConfigEntity", "updateTime");
		LKDBResource.addColumn("00040015", "SysActivitiProcessTaskConfigEntity", "updateLoginId");
		LKDBResource.addColumn("00040016", "SysActivitiProcessTaskConfigEntity", "usingStatus");
		LKDBResource.addColumn("00040017", "SysActivitiProcessTaskConfigEntity", "id");
	}

}