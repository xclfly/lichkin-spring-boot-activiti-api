package com.lichkin.framework.activiti.beans.in.impl;

import com.lichkin.framework.defines.activiti.beans.in.LKActivitiStartProcessIn;

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

	public LKActivitiStartProcessIn_SingleLineProcess(String processKey, String processName) {
		super(processKey, processName);
	}

}
