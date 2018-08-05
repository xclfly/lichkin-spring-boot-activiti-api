package com.lichkin.framework.defines.activiti.beans.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取流程详情入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LKActivitiGetDetailProcessIn {

	/** 流程ID */
	protected final String processInstanceId;

}
