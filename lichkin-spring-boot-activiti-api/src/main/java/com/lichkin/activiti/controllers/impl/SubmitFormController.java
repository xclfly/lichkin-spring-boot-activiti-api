package com.lichkin.activiti.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.SubmitFormIn;
import com.lichkin.activiti.beans.out.impl.SubmitFormOut;
import com.lichkin.activiti.services.impl.SubmitFormService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 保存表单控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/SubmitForm")
public class SubmitFormController extends LKApiController<SubmitFormIn, SubmitFormOut> {

	@Autowired
	private SubmitFormService service;


	@Override
	protected LKApiService<SubmitFormIn, SubmitFormOut> getService() {
		return service;
	}

}
