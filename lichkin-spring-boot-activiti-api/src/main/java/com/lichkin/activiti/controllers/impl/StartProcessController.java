package com.lichkin.activiti.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.StartProcessIn;
import com.lichkin.activiti.beans.out.impl.StartProcessOut;
import com.lichkin.activiti.services.impl.StartProcessService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 启动流程控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/StartProcess")
public class StartProcessController extends LKApiController<StartProcessIn, StartProcessOut> {

	@Autowired
	private StartProcessService service;


	@Override
	protected LKApiService<StartProcessIn, StartProcessOut> getService() {
		return service;
	}

}
