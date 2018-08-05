package com.lichkin.framework.defines.activiti.services;

import java.util.List;

import com.lichkin.framework.defines.activiti.beans.in.LKActivitiGetDoneProcessIn;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiGetDoneProcessOut;

/**
 * 获取已办流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface LKActivitiGetDoneProcessService<GetDoneProcessIn extends LKActivitiGetDoneProcessIn, GetDoneProcessOut extends LKActivitiGetDoneProcessOut> {

	/**
	 * 获取已办流程
	 * @param in 入参
	 * @return 流程实例
	 */
	public List<GetDoneProcessOut> getDoneProcess(GetDoneProcessIn in);

}
