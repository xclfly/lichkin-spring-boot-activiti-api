package com.lichkin.framework.activiti.beans.in.impl;

import com.lichkin.framework.defines.activiti.beans.in.LKActivitiCompleteProcessIn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 办理流程入参 -> 单线流程
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class LKActivitiComplateProcessIn_SingleLineProcess extends LKActivitiCompleteProcessIn {

	public LKActivitiComplateProcessIn_SingleLineProcess(String processInstanceId, String userId, String comment) {
		super(processInstanceId, userId, comment);
	}

}
