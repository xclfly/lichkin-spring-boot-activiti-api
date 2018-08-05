package com.lichkin.framework.defines.activiti.beans.out;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 获取流程详情出参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LKActivitiGetDetailProcessOut {

	/** 流程ID */
	private final String processInstanceId;

}
