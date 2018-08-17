package com.lichkin.activiti.beans.out.impl;

import com.lichkin.framework.defines.annotations.DateToString;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取流程详情出参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GetDetailProcessOut {

	/** 节点名称 */
	private String taskName;

	/** 审批人 */
	private String approveUserName;

	/** 备注 */
	private String taskComment;

	/** 节点开始时间 */
	@DateToString(value = LKDateTimeTypeEnum.STANDARD)
	private String taskStartTime;

	/** 节点结束时间 */
	@DateToString(value = LKDateTimeTypeEnum.STANDARD)
	private String taskEndTime;

	/** 流程ID */
	private String processInstanceId;

	/** 流程名称 */
	private String processName;

	/** 流程发起人 */
	private String processStartUserName;

	/** 流程发起时间 */
	@DateToString(value = LKDateTimeTypeEnum.STANDARD)
	private String processStartTime;

	/** 流程是否结束 */
	private boolean processIsEnd;

	/** 流程结束时间 */
	@DateToString(value = LKDateTimeTypeEnum.STANDARD)
	private String processEndTime;

	/** 流程删除原因 */
	private String deleteReason;

	/** 流程配置ID */
	private String processConfigId;

	/** 节点Id */
	private String taskId;

}
