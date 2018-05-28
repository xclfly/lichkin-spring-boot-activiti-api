package com.lichkin.springframework.entities.impl;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 启动流程接口请求日志实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysActivitiApiRequestLogStartProcessEntity extends LKMappedBaseSysActivitiApiRequestLogEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 888888666666991L;

}
