package com.lichkin.framework.defines.activiti.beans.out;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 获取待办流程出参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LKActivitiGetPendingProcessOut {

	/** 流程ID */
	private final String processInstanceId;

	/** 流程类型 */
	private String processType;

	/** 流程名称 */
	private String processName;

	/** 流程发起人 */
	private String processStartUserName;

	/** 流程发起时间 */
	private Date processStartTime;

	/** 节点名称 */
	private String taskName;

	/** 节点开始时间 */
	private Date taskStartTime;

}
