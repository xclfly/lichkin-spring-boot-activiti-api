package com.lichkin.activiti.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetFormListIn;
import com.lichkin.activiti.beans.out.impl.GetFormListOut;
import com.lichkin.activiti.services.impl.GetFormListService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取表单列表控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetFormList")
public class GetFormListController extends LKApiController<GetFormListIn, List<GetFormListOut>> {

	@Autowired
	private GetFormListService service;


	@Override
	protected LKApiService<GetFormListIn, List<GetFormListOut>> getService() {
		return service;
	}

}
