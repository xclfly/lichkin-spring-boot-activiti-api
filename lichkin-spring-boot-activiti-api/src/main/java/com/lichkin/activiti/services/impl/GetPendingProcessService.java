package com.lichkin.activiti.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.activiti.beans.in.impl.GetPendingProcessIn;
import com.lichkin.activiti.beans.out.impl.GetPendingProcessOut;
import com.lichkin.framework.defines.activiti.beans.in.LKActivitiGetPendingProcessIn;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiGetPendingProcessOut;
import com.lichkin.framework.defines.activiti.services.impl.LKActivitiService;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取待办流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetPendingProcessService extends LKApiService<GetPendingProcessIn, List<GetPendingProcessOut>> {

	@Override
	@Transactional
	public List<GetPendingProcessOut> handle(GetPendingProcessIn in) throws LKException {
		return getPendingProcess(in);
	}


	@Autowired
	private LKActivitiService service;


	/**
	 * 获取待办流程
	 * @param in 入参
	 * @return 待办流程列表
	 */
	private List<GetPendingProcessOut> getPendingProcess(GetPendingProcessIn in) {
		// 初始化入参
		LKActivitiGetPendingProcessIn i = new LKActivitiGetPendingProcessIn(in.getUserId());
		if (in.getPageNumber() == null) {
			in.setPageNumber(0);
		}
		if (in.getPageSize() == null) {
			in.setPageSize(10);
		}
		i.setFirstResult(in.getPageNumber() * in.getPageSize());
		i.setMaxResults(in.getPageSize());

		// 调用服务类方法
		List<LKActivitiGetPendingProcessOut> taskList = service.getPendingProcess(i);

		// 初始化出参
		List<GetPendingProcessOut> outList = new ArrayList<>();
		for (LKActivitiGetPendingProcessOut out : taskList) {
			outList.add(LKBeanUtils.newInstance(false, out, GetPendingProcessOut.class));
		}
		// 返回结果
		return outList;
	}

}
