package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 办理流程入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class CompleteProcessIn extends LKRequestBean {

	/** 用户ID */
	@NotNull
	private String userId;

	/** 流程类型 */
	@NotNull
	private String processType;

	/** 流程ID */
	@Pattern(regexp = "[1-9][0-9]+")
	@NotNull
	private String processInstanceId;

	/** 审批表单 */
	private String formDataJson;

	/** 审批备注信息 */
	private String comment;

}
