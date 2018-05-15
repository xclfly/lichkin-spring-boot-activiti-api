package com.lichkin.framework.activiti.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.activiti.beans.in.impl.LKActivitiStartProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiStartProcessOut_SingleLineProcess;
import com.lichkin.framework.defines.activiti.services.LKActivitiStartProcessService;
import com.lichkin.framework.defines.activiti.statics.LKActivitiStatics;

/**
 * 启动流程服务类 -> 单线流程
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class LKActivitiService_SingleLineProcess implements LKActivitiStatics, LKActivitiStartProcessService<LKActivitiStartProcessIn_SingleLineProcess, LKActivitiStartProcessOut_SingleLineProcess> {

	@Autowired
	private RuntimeService runtimeService;


	@Override
	@Transactional(transactionManager = "activitiPlatformTransactionManager")
	public LKActivitiStartProcessOut_SingleLineProcess startProcess(LKActivitiStartProcessIn_SingleLineProcess in) {
		Map<String, Object> variables = new HashMap<>();
		// 设置通用流程变量
		variables.put(KEY_PROCESS_KEY, in.getProcessKey());
		variables.put(KEY_PROCESS_NAME, in.getProcessName());

		// TODO 设置其它流程变量

		// 启动流程
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(in.getProcessKey(), variables);

		// 初始化返回值
		LKActivitiStartProcessOut_SingleLineProcess out = new LKActivitiStartProcessOut_SingleLineProcess(instance.getProcessInstanceId());

		// TODO 设置其它参数

		// 返回结果
		return out;
	}

}
