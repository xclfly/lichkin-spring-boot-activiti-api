package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.springframework.entities.suppers.LKMappedBaseSysEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Activiti配置信息实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiConfigEntity extends LKMappedBaseSysEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 8888886666666901L;

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


	@Override
	protected Object[] getCheckCodeFieldValues() {
		return new Object[] { processKey, processType };
	}

}
