package com.lichkin.activiti.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.beans.in.impl.RejectProcessIn;
import com.lichkin.activiti.beans.out.impl.RejectProcessOut;
import com.lichkin.activiti.services.impl.RejectProcessService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiController;
import com.lichkin.springframework.services.LKApiService;

/**
 * 驳回流程节点控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKApiType(apiType = ApiType.ROOT_QUERY)
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_APP_USEREMPLOYEE + "/Activiti/RejectProcess")
public class RejectProcessController extends LKApiController<RejectProcessIn, RejectProcessOut> {

	@Autowired
	private RejectProcessService service;


	@Override
	protected LKApiService<RejectProcessIn, RejectProcessOut> getService() {
		return service;
	}

}
