package com.lichkin.activiti.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetProcessTaskFormIn;
import com.lichkin.activiti.beans.out.impl.GetProcessTaskFormOut;
import com.lichkin.activiti.services.impl.GetProcessTaskFormService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取流程节点表单
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetProcessTaskForm")
public class GetProcessTaskFormController extends LKApiController<GetProcessTaskFormIn, GetProcessTaskFormOut> {

	@Autowired
	private GetProcessTaskFormService service;


	@Override
	protected LKApiService<GetProcessTaskFormIn, GetProcessTaskFormOut> getService() {
		return service;
	}

}
