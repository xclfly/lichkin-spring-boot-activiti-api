package com.lichkin.framework.defines.activiti.services;

import java.util.List;

import com.lichkin.framework.defines.activiti.beans.in.LKActivitiGetPendingProcessIn;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiGetPendingProcessOut;

/**
 * 获取待办流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface LKActivitiGetPendingProcessService<GetPendingProcessIn extends LKActivitiGetPendingProcessIn, GetPendingProcessOut extends LKActivitiGetPendingProcessOut> {

	/**
	 * 获取待办流程
	 * @param in 入参
	 * @return 流程实例
	 */
	public List<GetPendingProcessOut> getPendingProcess(GetPendingProcessIn in);

}
