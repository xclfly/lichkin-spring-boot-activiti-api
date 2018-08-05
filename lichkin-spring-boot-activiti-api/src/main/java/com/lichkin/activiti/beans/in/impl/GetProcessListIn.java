package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取流程列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class GetProcessListIn extends LKRequestBean {

	/** 部门ID */
	@NotBlank
	private String deptId;

}
