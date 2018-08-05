package com.lichkin.activiti.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetPendingProcessIn;
import com.lichkin.activiti.beans.out.impl.GetPendingProcessOut;
import com.lichkin.activiti.services.impl.GetPendingProcessService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取待办流程节点控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.ROOT_QUERY)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetPendingProcess")
public class GetPendingProcessController extends LKApiController<GetPendingProcessIn, List<GetPendingProcessOut>> {

	@Autowired
	private GetPendingProcessService service;


	@Override
	protected LKApiService<GetPendingProcessIn, List<GetPendingProcessOut>> getService() {
		return service;
	}

}
