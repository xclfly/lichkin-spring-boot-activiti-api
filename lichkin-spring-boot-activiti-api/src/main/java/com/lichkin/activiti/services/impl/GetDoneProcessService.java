package com.lichkin.activiti.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.activiti.beans.in.impl.GetDoneProcessIn;
import com.lichkin.activiti.beans.out.impl.GetDoneProcessOut;
import com.lichkin.framework.defines.activiti.beans.in.LKActivitiGetDoneProcessIn;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiGetDoneProcessOut;
import com.lichkin.framework.defines.activiti.services.impl.LKActivitiService;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取已办流程服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetDoneProcessService extends LKApiService<GetDoneProcessIn, List<GetDoneProcessOut>> {

	@Override
	@Transactional
	public List<GetDoneProcessOut> handle(GetDoneProcessIn in) throws LKException {
		return getDoneProcess(in);
	}


	@Autowired
	private LKActivitiService service;


	/**
	 * 获取已办流程
	 * @param in 入参
	 * @return 已办流程列表
	 */
	private List<GetDoneProcessOut> getDoneProcess(GetDoneProcessIn in) {
		// 初始化入参
		LKActivitiGetDoneProcessIn i = new LKActivitiGetDoneProcessIn(in.getUserId());
		if (in.getPageNumber() == null) {
			in.setPageNumber(1);
		}
		if (in.getPageSize() == null) {
			in.setPageSize(10);
		}
		i.setFirstResult((in.getPageNumber() - 1) * in.getPageSize());
		i.setMaxResults(in.getPageSize());

		// 调用服务类方法
		List<LKActivitiGetDoneProcessOut> taskList = service.getDoneProcess(i);

		// 初始化出参
		List<GetDoneProcessOut> outList = new ArrayList<>();
		for (LKActivitiGetDoneProcessOut task : taskList) {
			outList.add(LKBeanUtils.newInstance(false, task, GetDoneProcessOut.class));
		}
		// 返回结果
		return outList;
	}

}
