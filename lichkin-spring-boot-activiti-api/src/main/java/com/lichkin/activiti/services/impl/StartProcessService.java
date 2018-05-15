package com.lichkin.activiti.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.ErrorCodes;
import com.lichkin.activiti.beans.in.impl.StartProcessIn;
import com.lichkin.activiti.beans.out.impl.StartProcessOut;
import com.lichkin.activiti.daos.impl.SysActivitiConfigDao;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiStartProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiStartProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.services.impl.LKActivitiService_SingleLineProcess;
import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysActivitiConfigEntity;
import com.lichkin.springframework.services.LKApiService;

/**
 * 启动流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class StartProcessService extends LKApiService<StartProcessIn, StartProcessOut> {

	@Autowired
	private SysActivitiConfigDao configDao;


	@Override
	@Transactional
	public StartProcessOut handle(StartProcessIn in) throws LKException {
		// TODO 记录请求日志

		// 查询流程配置信息
		SysActivitiConfigEntity config = configDao.findOneByProcessKey(in.getProcessKey());

		// 根据流程类型执行
		LKActivitiProcessTypeEnum processType = config.getProcessType();
		switch (processType) {
			case SINGLE_LINE:
				return startSingleLineProcess(in, config);
		}

		throw new LKRuntimeException(ErrorCodes.process_type_config_error);
	}


	@Autowired
	private LKActivitiService_SingleLineProcess slp;


	/**
	 * 启动单线流程
	 * @param in 入参
	 * @param config 流程配置信息
	 * @return 流程实例
	 */
	private StartProcessOut startSingleLineProcess(StartProcessIn in, SysActivitiConfigEntity config) {
		// 初始化入参
		LKActivitiStartProcessIn_SingleLineProcess i = new LKActivitiStartProcessIn_SingleLineProcess(in.getProcessKey(), config.getProcessName());

		// TODO 根据业务表设置其它参数

		// 调用服务类方法
		LKActivitiStartProcessOut_SingleLineProcess o = slp.startProcess(i);

		// 初始化出参
		StartProcessOut out = new StartProcessOut(o.getProcessInstanceId(), config.getProcessType());

		// TODO 设置其它参数

		// 返回结果
		return out;
	}

}
