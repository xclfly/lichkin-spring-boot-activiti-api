package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Activiti流程配置信息实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiProcessConfigEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 30004L;

	/** 流程编码 */
	@Column(nullable = false, length = 128)
	private String processCode;

	/** 流程主键 */
	@Column(nullable = false, length = 128)
	private String processKey;

	/** 流程类型 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 32)
	private LKActivitiProcessTypeEnum processType;

	/** 流程名称 */
	@Column(nullable = false, length = 64)
	private String processName;

	/** 部门ID */
	@Column(nullable = false, length = 128)
	private String deptId;

	/** 是否可用（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 3, nullable = false)
	private LKYesNoEnum available;

}
