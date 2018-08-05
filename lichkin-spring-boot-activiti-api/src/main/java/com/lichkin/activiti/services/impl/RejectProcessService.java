package com.lichkin.activiti.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.ErrorCodes;
import com.lichkin.activiti.beans.in.impl.RejectProcessIn;
import com.lichkin.activiti.beans.out.impl.RejectProcessOut;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiRejectProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiRejectProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.services.impl.LKActivitiService_SingleLineProcess;
import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKEnumUtils;
import com.lichkin.springframework.services.LKApiService;

/**
 * 驳回流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class RejectProcessService extends LKApiService<RejectProcessIn, RejectProcessOut> {

	@Override
	@Transactional
	public RejectProcessOut handle(RejectProcessIn in) throws LKException {
		if (in.getProcessType() != null) {
			// 根据流程类型执行
			LKActivitiProcessTypeEnum processType = LKEnumUtils.getEnum(LKActivitiProcessTypeEnum.class, in.getProcessType());
			try {
				switch (processType) {
					case SINGLE_LINE:
						return RejectProcessTask(in);
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
	 * 驳回单线流程
	 * @param in 驳回流程入参
	 * @return 驳回流程结果
	 */
	private RejectProcessOut RejectProcessTask(RejectProcessIn in) {
		// 初始化入参
		LKActivitiRejectProcessIn_SingleLineProcess i = new LKActivitiRejectProcessIn_SingleLineProcess(in.getProcessInstanceId(), in.getUserId(), in.getComment());
		// 调用服务类方法
		LKActivitiRejectProcessOut_SingleLineProcess o = slp.RejectProcess(i);

		// 初始化出参
		RejectProcessOut out = new RejectProcessOut(o.isProcessIsEnd());

		// TODO 设置其它参数

		// 返回结果
		return out;
	}

}
