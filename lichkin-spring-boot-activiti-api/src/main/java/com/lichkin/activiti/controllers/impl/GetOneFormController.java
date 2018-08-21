package com.lichkin.activiti.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetOneFormIn;
import com.lichkin.activiti.beans.out.impl.GetOneFormOut;
import com.lichkin.activiti.services.impl.GetOneFormService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取单个表单控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetOneForm")
public class GetOneFormController extends LKApiController<GetOneFormIn, GetOneFormOut> {

	@Autowired
	private GetOneFormService service;


	@Override
	protected LKApiService<GetOneFormIn, GetOneFormOut> getService() {
		return service;
	}

}
