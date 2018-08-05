package com.lichkin.activiti.beans.in.impl.page;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivitiCenterIn extends LKRequestBean {

	private String deptId;

	private String userId;

	private String userName;
}
