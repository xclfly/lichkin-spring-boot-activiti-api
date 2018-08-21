package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.activiti.enums.impl.LKApprovalStatusEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工申请表单数据实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiFormDataEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 30007L;

	/** 登录ID。 */
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 流程配置ID。 */
	@Column(length = 64)
	private String processConfigId;

	/** 流程ID（启动流程后此值才有）。 */
	@Column(length = 64)
	private String processInstanceId;

	/** form数据。 */
	@Lob
	@Column(nullable = false)
	private String formDataJson;

	/** 表单类型（字典）。 */
	@Column(length = 20, nullable = false)
	private String formTypeCode;

	/** 审批状态（枚举）。 */
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private LKApprovalStatusEnum approvalStatus;

	/** 业务字段。 */
	@Column(length = 50)
	private String field1;

	/** 业务字段。 */
	@Column(length = 50)
	private String field2;

	/** 业务字段。 */
	@Column(length = 50)
	private String field3;

	/** 业务字段。 */
	@Column(length = 50)
	private String field4;

	/** 业务字段。 */
	@Column(length = 50)
	private String field5;

	/** 业务字段。 */
	@Column(length = 50)
	private String field6;

	/** 业务字段。 */
	@Column(length = 50)
	private String field7;

	/** 业务字段。 */
	@Column(length = 50)
	private String field8;

	/** 业务字段。 */
	@Column(length = 50)
	private String field9;

}
