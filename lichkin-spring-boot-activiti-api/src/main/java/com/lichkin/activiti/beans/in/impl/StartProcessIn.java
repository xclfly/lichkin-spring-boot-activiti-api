package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotNull;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 启动流程入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class StartProcessIn extends LKRequestBean {

	/** 用户ID */
	@NotNull
	private String userId;

	/** 发起人名称 */
	@NotNull
	private String userName;

	/** 流程ID */
	@NotNull
	private String processConfigId;

	/** 业务key */
	@NotNull
	private String businessKey;

	/** 备注 */
	private String comment;

}
