package com.lichkin.activiti.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.GetDetailProcessIn;
import com.lichkin.activiti.beans.out.impl.GetDetailProcessOut;
import com.lichkin.activiti.services.impl.GetDetailProcessService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取流程详情控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.ROOT_QUERY)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/GetDetailProcess")
public class GetDetailProcessController extends LKApiController<GetDetailProcessIn, List<GetDetailProcessOut>> {

	@Autowired
	private GetDetailProcessService service;


	@Override
	protected LKApiService<GetDetailProcessIn, List<GetDetailProcessOut>> getService() {
		return service;
	}

}
