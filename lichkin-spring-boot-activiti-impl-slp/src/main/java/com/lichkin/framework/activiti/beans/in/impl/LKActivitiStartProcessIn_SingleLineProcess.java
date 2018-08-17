package com.lichkin.framework.activiti.beans.in.impl;

import java.util.List;

import com.lichkin.framework.defines.activiti.beans.in.LKActivitiStartProcessIn;
import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 启动流程入参 -> 单线流程
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class LKActivitiStartProcessIn_SingleLineProcess extends LKActivitiStartProcessIn {

	public LKActivitiStartProcessIn_SingleLineProcess(String processConfigId, String processKey, String processName, LKActivitiProcessTypeEnum processType, String comment) {
		super(processConfigId, processKey, processName, processType, comment);
	}


	/** 流程节点信息 */
	private List<LKActivitiStartProcessTaskIn_SingleLineProcess> taskList;

}
