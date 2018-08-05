package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotNull;

import com.lichkin.framework.beans.impl.LKRequestPageBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取待办流程入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class GetPendingProcessIn extends LKRequestPageBean {

	/** 用户ID */
	@NotNull
	private String userId;

}
