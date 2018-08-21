package com.lichkin.activiti.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.activiti.services.impl.UpdateFormService;
import com.lichkin.framework.beans.impl.LKRequestIDsUsingStatusBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.springframework.controllers.LKSameInApiBusUpdateUsingStatusController;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

/**
 * 修改表单状态
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@RestController
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USEREMPLOYEE + "/Activiti/UpdateForm")
public class UpdateFormController extends LKSameInApiBusUpdateUsingStatusController<LKRequestIDsUsingStatusBean, SysActivitiFormDataEntity> {

	@Autowired
	private UpdateFormService service;


	@Override
	protected LKApiBusUpdateUsingStatusService<LKRequestIDsUsingStatusBean, SysActivitiFormDataEntity> getService() {
		return service;
	}

}
