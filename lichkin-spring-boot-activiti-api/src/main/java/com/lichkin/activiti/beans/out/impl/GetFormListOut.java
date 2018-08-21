package com.lichkin.activiti.beans.out.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取个人表单列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
public class GetFormListOut {

	private String id;

	private String formTypeCode;

	private String formType;

	private String approvalStatus;

	private String insertTime;

}
