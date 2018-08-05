package com.lichkin.activiti.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.ErrorCodes;
import com.lichkin.activiti.beans.in.impl.CompleteProcessIn;
import com.lichkin.activiti.beans.out.impl.CompleteProcessOut;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiComplateProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiCompleteProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.services.impl.LKActivitiService_SingleLineProcess;
import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.framework.utils.LKEnumUtils;
import com.lichkin.springframework.entities.impl.SysActivitiApiRequestLogCompleteProcessEntity;
import com.lichkin.springframework.services.LKApiService;

/**
 * 办理流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class CompleteProcessService extends LKApiService<CompleteProcessIn, CompleteProcessOut> {

	@Override
	@Transactional
	public CompleteProcessOut handle(CompleteProcessIn in) throws LKException {
		// 记录请求日志
		SysActivitiApiRequestLogCompleteProcessEntity log = LKBeanUtils.newInstance(false, in, SysActivitiApiRequestLogCompleteProcessEntity.class);
		dao.mergeOne(log);

		if (in.getProcessType() != null) {
			// 根据流程类型执行
			LKActivitiProcessTypeEnum processType = LKEnumUtils.getEnum(LKActivitiProcessTypeEnum.class, in.getProcessType());
			try {
				switch (processType) {
					case SINGLE_LINE:
						return completeProcessTask(in);
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
	 * 办理单线流程
	 * @param in 办理流程入参
	 * @return 办理流程结果
	 */
	private CompleteProcessOut completeProcessTask(CompleteProcessIn in) {
		// 初始化入参
		LKActivitiComplateProcessIn_SingleLineProcess i = new LKActivitiComplateProcessIn_SingleLineProcess(in.getProcessInstanceId(), in.getUserId(), in.getComment());
		// 调用服务类方法
		LKActivitiCompleteProcessOut_SingleLineProcess o = slp.completeProcess(i);

		// 初始化出参
		CompleteProcessOut out = new CompleteProcessOut(o.isProcessIsEnd());

		// TODO 设置其它参数

		// 返回结果
		return out;
	}

}
