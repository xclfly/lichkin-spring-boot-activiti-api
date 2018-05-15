package com.lichkin.activiti.beans.out.impl;

import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 启动流程出参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StartProcessOut {

	/** 流程ID */
	private final String processInstanceId;

	/** 流程类型 */
	private final LKActivitiProcessTypeEnum processType;

}
