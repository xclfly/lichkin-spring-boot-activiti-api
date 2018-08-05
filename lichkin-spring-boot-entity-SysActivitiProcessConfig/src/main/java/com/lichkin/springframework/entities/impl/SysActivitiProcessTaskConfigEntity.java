package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Activiti流程节点配置信息实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiProcessTaskConfigEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 30005L;

	/** 流程ID */
	@Column(nullable = false, length = 64)
	private String configId;

	/** 节点名称 */
	@Column(nullable = false, length = 128)
	private String taskName;

	/** 节点操作人员ID(需和activiti表中的userId一致) */
	@Column(nullable = false, length = 64)
	private String userId;

	/** 节点操作人员Name */
	@Column(nullable = false, length = 64)
	private String userName;

	/** 节点步骤 */
	@Column(nullable = false)
	private Byte step;

}
