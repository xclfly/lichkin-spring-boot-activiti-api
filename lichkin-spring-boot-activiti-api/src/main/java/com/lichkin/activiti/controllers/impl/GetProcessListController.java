package com.lichkin.activiti.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetProcessListIn;
import com.lichkin.activiti.beans.out.impl.GetProcessListOut;
import com.lichkin.activiti.services.impl.GetProcessListService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取流程列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetProcessList")
public class GetProcessListController extends LKApiController<GetProcessListIn, List<GetProcessListOut>> {

	@Autowired
	private GetProcessListService service;


	@Override
	protected LKApiService<GetProcessListIn, List<GetProcessListOut>> getService() {
		return service;
	}

}
