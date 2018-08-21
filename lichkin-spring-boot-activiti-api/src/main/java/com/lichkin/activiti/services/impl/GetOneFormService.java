package com.lichkin.activiti.services.impl;

import org.springframework.stereotype.Service;

import com.lichkin.activiti.beans.in.impl.GetOneFormIn;
import com.lichkin.activiti.beans.out.impl.GetOneFormOut;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.services.LKApiBusGetOneService;

/**
 * 获取表单列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetOneFormService extends LKApiBusGetOneService<GetOneFormIn, GetOneFormOut, SysActivitiFormDataEntity> {

}
