package com.lichkin.framework.activiti.beans.in.impl;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 启动流程 节点入参 -> 单线流程节点信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
public class LKActivitiStartProcessTaskIn_SingleLineProcess implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -5808670463488845838L;

	/** 节点操作人员ID */
	private String userId;

	/** 节点操作人员Name */
	private String userName;

	/** 节点名称 */
	private String taskName;
}
