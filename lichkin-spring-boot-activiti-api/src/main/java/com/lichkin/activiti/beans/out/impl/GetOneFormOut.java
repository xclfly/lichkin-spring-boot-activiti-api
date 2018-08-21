package com.lichkin.activiti.beans.out.impl;

import com.lichkin.framework.defines.activiti.enums.impl.LKApprovalStatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取单个表单
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
public class GetOneFormOut {

	/** 表单ID。 */
	private String id;

	/** 流程配置ID。 */
	private String processConfigId;

	/** form数据。 */
	private String formDataJson;

	/** 审批状态（枚举）。 */
	private LKApprovalStatusEnum approvalStatus;

	/** 创建时间。 */
	private String insertTime;

}
