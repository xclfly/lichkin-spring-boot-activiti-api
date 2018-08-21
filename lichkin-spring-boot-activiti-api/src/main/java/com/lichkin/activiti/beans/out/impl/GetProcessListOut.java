package com.lichkin.activiti.beans.out.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取流程列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@ToString
public class GetProcessListOut {

	private String processId;

	private String processName;

	private String processCode;

}
