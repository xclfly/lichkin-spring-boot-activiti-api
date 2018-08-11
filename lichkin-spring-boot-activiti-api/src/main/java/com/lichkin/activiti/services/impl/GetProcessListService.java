package com.lichkin.activiti.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.activiti.beans.in.impl.GetProcessListIn;
import com.lichkin.activiti.beans.out.impl.GetProcessListOut;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysActivitiProcessConfigR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysActivitiProcessConfigEntity;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取流程列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetProcessListService extends LKApiService<GetProcessListIn, List<GetProcessListOut>> {

	@Override
	@Transactional
	public List<GetProcessListOut> handle(GetProcessListIn in) throws LKException {
		List<GetProcessListOut> processList = new ArrayList<>();

		// 查询部门特有的工作流
		QuerySQL sql = new QuerySQL(false, SysActivitiProcessConfigEntity.class);
		sql.eq(SysActivitiProcessConfigR.compId, in.getCompId());
		sql.eq(SysActivitiProcessConfigR.deptId, in.getDeptId());
		sql.eq(SysActivitiProcessConfigR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysActivitiProcessConfigR.available, LKYesNoEnum.YES);
		List<SysActivitiProcessConfigEntity> deptProList = dao.getList(sql, SysActivitiProcessConfigEntity.class);
		StringBuffer processCodeBuf = new StringBuffer();
		if (CollectionUtils.isNotEmpty(deptProList)) {
			for (int i = 0; i < deptProList.size(); i++) {
				SysActivitiProcessConfigEntity processConfig = deptProList.get(i);
				if (i < (deptProList.size() - 1)) {
					processCodeBuf.append(processConfig.getProcessCode() + LKFrameworkStatics.SPLITOR);
				} else {
					processCodeBuf.append(processConfig.getProcessCode());
				}
				GetProcessListOut out = new GetProcessListOut();
				out.setProcessId(processConfig.getId());
				out.setProcessName(processConfig.getProcessName());
				processList.add(out);
			}
		}

		if (StringUtils.isNotBlank(in.getDeptId())) {
			// 查询公司的工作流（去除和部门重复的工作流信息）
			sql = new QuerySQL(false, SysActivitiProcessConfigEntity.class);
			sql.eq(SysActivitiProcessConfigR.compId, in.getCompId());
			sql.eq(SysActivitiProcessConfigR.deptId, "");
			sql.notIn(SysActivitiProcessConfigR.processCode, processCodeBuf.toString());
			sql.eq(SysActivitiProcessConfigR.usingStatus, LKUsingStatusEnum.USING);
			sql.eq(SysActivitiProcessConfigR.canUse, LKYesNoEnum.YES);
			List<SysActivitiProcessConfigEntity> compProList = dao.getList(sql, SysActivitiProcessConfigEntity.class);
			for (int i = 0; i < compProList.size(); i++) {
				GetProcessListOut out = new GetProcessListOut();
				out.setProcessId(compProList.get(i).getId());
				out.setProcessName(compProList.get(i).getProcessName());
				processList.add(out);
			}
		}

		return processList;
	}

}
