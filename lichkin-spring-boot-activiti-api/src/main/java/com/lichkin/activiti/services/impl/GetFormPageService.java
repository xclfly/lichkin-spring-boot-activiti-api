package com.lichkin.activiti.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.activiti.beans.in.impl.GetFormListIn;
import com.lichkin.activiti.beans.out.impl.GetFormListOut;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysActivitiFormDataR;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.projects.utils.LKDictUtils;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.services.LKApiService;

/**
 * 获取表单列表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class GetFormPageService extends LKApiService<GetFormListIn, Page<GetFormListOut>> {

	@Override
	@Transactional
	public Page<GetFormListOut> handle(GetFormListIn in) throws LKException {
		QuerySQL sql = new QuerySQL(SysActivitiFormDataEntity.class);
		sql.select(0, SysDictionaryR.dictName, "formType");
		sql.select(SysActivitiFormDataR.id);
		sql.select(SysActivitiFormDataR.approvalStatus);
		sql.select(SysActivitiFormDataR.formTypeCode);
		sql.select(SysActivitiFormDataR.insertTime);

		LKDictUtils.processCode(sql, SysActivitiFormDataR.formTypeCode, 0, in.getLocale());

		sql.eq(SysActivitiFormDataR.compId, in.getCompId());
		sql.eq(SysActivitiFormDataR.loginId, in.getLoginId());
		sql.neq(SysActivitiFormDataR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.addOrders(new Order(SysActivitiFormDataR.insertTime, false));

		sql.setPage(in);

		return dao.getPage(sql, GetFormListOut.class);
	}

}
