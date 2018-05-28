package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang3.ArrayUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 审批流程接口请求日志实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiApiRequestLogCompleteProcessEntity extends LKMappedBaseSysActivitiApiRequestLogEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 888888666666992L;

	/** 流程类型 */
	@Column(nullable = false, length = ID_LENGTH)
	private String processInstanceId;


	@Override
	protected Object[] getCheckCodeFieldValues() {
		return ArrayUtils.addAll(super.getCheckCodeFieldValues(), processInstanceId);
	}

}
