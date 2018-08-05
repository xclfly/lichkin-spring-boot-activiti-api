package com.lichkin.framework.defines.activiti.beans.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 审批流程入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LKActivitiCompleteProcessIn {

	/** 流程ID */
	protected final String processInstanceId;

	/** 审批人ID */
	protected final String userId;

	/** 审批备注信息 */
	protected final String comment;

}
