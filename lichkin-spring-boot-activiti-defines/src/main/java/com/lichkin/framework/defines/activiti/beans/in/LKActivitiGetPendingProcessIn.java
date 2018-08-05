package com.lichkin.framework.defines.activiti.beans.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取待办流程入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LKActivitiGetPendingProcessIn {

	/** 用户ID */
	protected final String userId;

	/** 起始行数 */
	private int firstResult;

	/** 结果行数 */
	private int maxResults;

}
