package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotNull;

import com.lichkin.framework.beans.impl.LKAfterLoginRequestBean;

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
public class StartProcessIn extends LKAfterLoginRequestBean {

	/** 用户ID */
	@NotNull
	private String userId;

	/** 公司ID */
	@NotNull
	private String compId;

	/** 流程编码 */
	@NotNull
	private String processCode;

}
