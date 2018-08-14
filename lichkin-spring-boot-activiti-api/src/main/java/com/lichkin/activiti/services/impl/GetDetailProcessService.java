package com.lichkin.activiti.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.ErrorCodes;
import com.lichkin.activiti.beans.in.impl.GetDetailProcessIn;
import com.lichkin.activiti.beans.out.impl.GetDetailProcessOut;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiGetDetailProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiGetDetailProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.services.impl.LKActivitiService_SingleLineProcess;
import com.lichkin.framework.defines.activiti.enums.impl.LKActivitiProcessTypeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.framework.utils.LKEnumUtils;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取流程详情服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetDetailProcessService extends LKApiService<GetDetailProcessIn, List<GetDetailProcessOut>> {

	@Override
	@Transactional
	public List<GetDetailProcessOut> handle(GetDetailProcessIn in) throws LKException {
		if (in.getProcessType() != null) {
			// 根据流程类型执行
			LKActivitiProcessTypeEnum processType = LKEnumUtils.getEnum(LKActivitiProcessTypeEnum.class, in.getProcessType());
			try {
				switch (processType) {
					case SINGLE_LINE:
						return getDetailProcess(in);
				}
			} catch (Exception e) {
				throw new LKException(ErrorCodes.process_type_config_error);
			}
		}

		throw new LKException(ErrorCodes.process_type_config_error);
	}


	@Autowired
	private LKActivitiService_SingleLineProcess slp;


	/**
	 * 获取流程详情
	 * @param in 入参
	 * @return 流程详情列表信息
	 */
	private List<GetDetailProcessOut> getDetailProcess(GetDetailProcessIn in) {
		// 初始化入参
		LKActivitiGetDetailProcessIn_SingleLineProcess i = new LKActivitiGetDetailProcessIn_SingleLineProcess(in.getProcessInstanceId());

		// 调用服务类方法
		List<LKActivitiGetDetailProcessOut_SingleLineProcess> taskList = slp.getDetailProcess(i);

		// 初始化出参
		List<GetDetailProcessOut> outList = new ArrayList<>();
		for (LKActivitiGetDetailProcessOut_SingleLineProcess task : taskList) {
			outList.add(LKBeanUtils.newInstance(task, GetDetailProcessOut.class));
		}
		// 返回结果
		return outList;
	}

}
