package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang3.ArrayUtils;

import com.lichkin.springframework.entities.suppers.LKMappedBaseSysApiRequestLogEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 启动流程接口请求日志实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiApiRequestLogStartProcessEntity extends LKMappedBaseSysApiRequestLogEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 8888886666666991L;

	/** 用户ID */
	@Column(nullable = false, length = 32)
	private String userId;

	/** 公司ID */
	@Column(nullable = false, length = 32)
	private String compId;

	/** 流程编码 */
	@Column(nullable = false, length = 128)
	private String processCode;


	@Override
	protected Object[] getCheckCodeFieldValues() {
		return ArrayUtils.addAll(super.getCheckCodeFieldValues(), userId, compId, processCode);
	}

}
