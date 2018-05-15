package com.lichkin.framework.defines.activiti.beans.in;

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

	/** 流程主键 */
	protected final String processKey;

	/** 流程名称 */
	protected final String processName;

}
