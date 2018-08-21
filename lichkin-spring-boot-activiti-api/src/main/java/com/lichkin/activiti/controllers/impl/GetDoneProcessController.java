package com.lichkin.activiti.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetDoneProcessIn;
import com.lichkin.activiti.beans.out.impl.GetDoneProcessOut;
import com.lichkin.activiti.services.impl.GetDoneProcessService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取已办流程节点控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetDoneProcess")
public class GetDoneProcessController extends LKApiController<GetDoneProcessIn, List<GetDoneProcessOut>> {

	@Autowired
	private GetDoneProcessService service;


	@Override
	protected LKApiService<GetDoneProcessIn, List<GetDoneProcessOut>> getService() {
		return service;
	}

}
