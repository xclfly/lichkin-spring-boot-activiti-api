package com.lichkin.activiti.beans.in.impl;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 保存表单入参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SubmitFormIn extends LKRequestBean {

	/** form数据。 */
	private String formDataJson;

	/** 表单类型（字典）。 */
	@NotBlank
	private String formTypeCode;

	/** 流程配置ID。 */
	private String processConfigId;

}
