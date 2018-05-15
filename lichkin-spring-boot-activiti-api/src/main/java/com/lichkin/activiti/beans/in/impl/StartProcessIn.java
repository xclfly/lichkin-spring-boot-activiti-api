package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lichkin.framework.defines.beans.LKRequestBean;

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
	@Pattern(regexp = "[A-Za-z0-9]{32}")
	@NotNull
	private String userId;

	/** 公司ID */
	@Pattern(regexp = "[A-Za-z0-9]{32}")
	@NotNull
	private String compId;

	/** 流程主键 */
	@NotNull
	private String processKey;

}
