package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.lichkin.springframework.entities.LKMappedBaseSysApiRequestLogEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 流程接口请求日志实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@MappedSuperclass
public class LKMappedBaseSysActivitiApiRequestLogEntity extends LKMappedBaseSysApiRequestLogEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = -888888666666990L;

	/** 用户ID（框架约定表中的userLoginId，最终同步到activiti的user表中对应的id。） */
	@Column(nullable = false, length = ID_LENGTH)
	private String userId;

	/** 公司ID */
	@Column(nullable = false, length = ID_LENGTH)
	private String compId;

	/** 流程编码 */
	@Column(nullable = false, length = VALUE_128_LENGTH)
	private String processCode;


	@Override
	protected Object[] getCheckCodeFieldValues() {
		return new Object[] { super.getCheckCodeFieldValues(), userId, compId, processCode };
	}

}
