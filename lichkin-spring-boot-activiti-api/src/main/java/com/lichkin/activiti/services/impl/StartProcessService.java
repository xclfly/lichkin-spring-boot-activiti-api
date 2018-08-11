package com.lichkin.activiti.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.ErrorCodes;
import com.lichkin.activiti.beans.in.impl.StartProcessIn;
import com.lichkin.activiti.beans.out.impl.StartProcessOut;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiStartProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiStartProcessTaskIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiStartProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.services.impl.LKActivitiService_SingleLineProcess;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysActivitiProcessTaskConfigR;
import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.entities.impl.SysActivitiApiRequestLogStartProcessEntity;
import com.lichkin.springframework.entities.impl.SysActivitiProcessConfigEntity;
import com.lichkin.springframework.entities.impl.SysActivitiProcessTaskConfigEntity;
import com.lichkin.springframework.services.LKApiService;

/**
 * 启动流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class StartProcessService extends LKApiService<StartProcessIn, StartProcessOut> {

	@Override
	@Transactional
	public StartProcessOut handle(StartProcessIn in) throws LKException {
		// 记录请求日志
		SysActivitiApiRequestLogStartProcessEntity log = LKBeanUtils.newInstance(false, in, SysActivitiApiRequestLogStartProcessEntity.class);
		dao.mergeOne(log);

		// 查询流程配置信息
		SysActivitiProcessConfigEntity config = dao.findOneById(SysActivitiProcessConfigEntity.class, in.getProcessConfigId());
		if (config != null) {
			// 根据流程类型执行
			LKActivitiProcessTypeEnum processType = config.getProcessType();
			try {
				switch (processType) {
					case SINGLE_LINE:
						return startSingleLineProcess(in, config);
				}
			} catch (Exception e) {
				throw new LKException(ErrorCodes.start_process_failed);
			}
		}

		throw new LKException(ErrorCodes.process_type_config_error);
	}


	@Autowired
	private LKActivitiService_SingleLineProcess slp;


	/**
	 * 启动单线流程
	 * @param in 入参
	 * @param config 流程配置信息
	 * @return 流程实例
	 * @throws LKException
	 */
	private StartProcessOut startSingleLineProcess(StartProcessIn in, SysActivitiProcessConfigEntity config) throws LKException {
		// 初始化入参
		LKActivitiStartProcessIn_SingleLineProcess i = new LKActivitiStartProcessIn_SingleLineProcess(config.getProcessKey(), config.getProcessName(), config.getProcessType(), in.getComment());

		// 查询流程对应的节点信息
		QuerySQL sql = new QuerySQL(false, SysActivitiProcessTaskConfigEntity.class);
		sql.eq(SysActivitiProcessTaskConfigR.configId, config.getId());
		sql.addOrders(new Order(SysActivitiProcessTaskConfigR.step));
		List<SysActivitiProcessTaskConfigEntity> taskConfigList = dao.getList(sql, SysActivitiProcessTaskConfigEntity.class);
		List<LKActivitiStartProcessTaskIn_SingleLineProcess> taskList = new ArrayList<>();
		for (int j = 0; j < taskConfigList.size(); j++) {
			SysActivitiProcessTaskConfigEntity task = taskConfigList.get(j);
			// 第一步为发起人 不需要配置
			if (j == 0) {
				task.setUserId(in.getUserId());
				task.setUserName(in.getUserName());
			} else {
				// 未配置流程节点
				if (StringUtils.isBlank(task.getUserId())) {
					throw new LKException(ErrorCodes.process_type_config_error);
				}
			}
			taskList.add(LKBeanUtils.newInstance(task, LKActivitiStartProcessTaskIn_SingleLineProcess.class));
		}
		i.setTaskList(taskList);

		// 调用服务类方法
		LKActivitiStartProcessOut_SingleLineProcess o = slp.startProcess(i);

		// 初始化出参
		StartProcessOut out = new StartProcessOut(o.getProcessInstanceId(), config.getProcessType());

		// 返回结果
		return out;
	}

}
