package com.lichkin.framework.defines.activiti.beans.in;

import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 启动流程入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LKActivitiStartProcessIn {

	/** 流程配置主键 */
	protected final String processConfigId;

	/** 流程主键(对应activiti中设置的key) */
	protected final String processKey;

	/** 业务key */
	private final String businessKey;

	/** 流程名称 */
	protected final String processName;

	/** 流程类型 */
	protected final LKActivitiProcessTypeEnum processType;

	/** 备注 */
	protected final String comment;

}
