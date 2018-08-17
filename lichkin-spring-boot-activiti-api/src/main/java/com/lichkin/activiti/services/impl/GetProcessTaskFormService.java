package com.lichkin.activiti.services.impl;

import org.springframework.stereotype.Service;

import com.lichkin.activiti.beans.in.impl.GetProcessTaskFormIn;
import com.lichkin.activiti.beans.out.impl.GetProcessTaskFormOut;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysActivitiProcessTaskConfigR;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysActivitiProcessTaskConfigEntity;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取流程节点表单
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetProcessTaskFormService extends LKApiService<GetProcessTaskFormIn, GetProcessTaskFormOut> {

	@Override
	public GetProcessTaskFormOut handle(GetProcessTaskFormIn in) throws LKException {
		QuerySQL sqlObj = new QuerySQL(SysActivitiProcessTaskConfigEntity.class);
		sqlObj.select(

				SysActivitiProcessTaskConfigR.formJson,

				SysActivitiProcessTaskConfigR.userId,

				SysActivitiProcessTaskConfigR.userName);

		sqlObj.eq(SysActivitiProcessTaskConfigR.configId, in.getProcessConfigId());
		sqlObj.eq(SysActivitiProcessTaskConfigR.step, in.getStep());

		return dao.getOne(sqlObj, GetProcessTaskFormOut.class);
	}

}
