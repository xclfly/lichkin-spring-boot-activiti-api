package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.LKMappedBaseSysEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Activiti流程节点配置信息实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiProcessTaskConfigEntity extends LKMappedBaseSysEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 888888666666902L;

	/** 流程配置信息ID */
	@Column(nullable = false, length = ID_LENGTH)
	private String processConfigId;

	/** 节点名称 */
	@Column(nullable = false, length = VALUE_32_LENGTH)
	private String taskName;

	/** 节点操作人员ID（框架约定表中的userLoginId，最终同步到activiti的user表中对应的id。） */
	@Column(nullable = false, length = ID_LENGTH)
	private String userId;

	/** 节点操作人员姓名 */
	@Column(nullable = false, length = NAME_LENGTH)
	private String userName;

	/** 节点步骤 */
	@Column(nullable = false)
	private Byte step;


	@Override
	protected Object[] getCheckCodeFieldValues() {
		return new Object[] { processConfigId, taskName, userId, userName, step };
	}

}
