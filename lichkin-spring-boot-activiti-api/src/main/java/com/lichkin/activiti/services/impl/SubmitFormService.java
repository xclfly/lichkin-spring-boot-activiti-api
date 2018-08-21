package com.lichkin.activiti.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.activiti.beans.in.impl.SubmitFormIn;
import com.lichkin.activiti.beans.out.impl.SubmitFormOut;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.activiti.enums.impl.LKApprovalStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.services.LKApiService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 保存表单服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class SubmitFormService extends LKApiService<SubmitFormIn, SubmitFormOut> {

	@Override
	@Transactional
	public SubmitFormOut handle(SubmitFormIn in) throws LKException {
		SysActivitiFormDataEntity entity = LKBeanUtils.newInstance(true, in, SysActivitiFormDataEntity.class);
		if (StringUtils.isNotBlank(entity.getFormDataJson())) {
			String[] formDataJsons = entity.getFormDataJson().split(LKFrameworkStatics.SPLITOR);
			String formPluginsJson = formDataJsons[1];
			String formData = formDataJsons[2];

			JSONObject formDataObj = JSONObject.fromObject(formData);
			String fieldValues = "";
			// 提取要存字段的数据
			JSONArray jsonArray = JSONArray.fromObject(formPluginsJson);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject fieldObj = jsonArray.getJSONObject(i);
				Object businessField = fieldObj.get("businessField");
				if ((businessField != null) && (businessField instanceof Boolean)) {
					boolean b = ((Boolean) businessField).booleanValue();
					if (b) {
						fieldValues += formDataObj.get(fieldObj.getJSONObject("options").get("name")) + LKFrameworkStatics.SPLITOR;
					}
				}
			}

			if (StringUtils.isNotEmpty(fieldValues)) {
				String[] fieldValueAry = fieldValues.split(LKFrameworkStatics.SPLITOR);
				for (int i = 0; i < fieldValueAry.length; i++) {
					if (i == 0) {
						entity.setField1(fieldValueAry[i]);
					} else if (i == 1) {
						entity.setField2(fieldValueAry[i]);
					} else if (i == 2) {
						entity.setField3(fieldValueAry[i]);
					} else if (i == 3) {
						entity.setField4(fieldValueAry[i]);
					} else if (i == 4) {
						entity.setField5(fieldValueAry[i]);
					} else if (i == 5) {
						entity.setField6(fieldValueAry[i]);
					} else if (i == 6) {
						entity.setField7(fieldValueAry[i]);
					} else if (i == 7) {
						entity.setField8(fieldValueAry[i]);
					} else if (i == 8) {
						entity.setField9(fieldValueAry[i]);
					}
				}
			}
		}

		entity.setApprovalStatus(LKApprovalStatusEnum.PENDING);
		entity.setUsingStatus(LKUsingStatusEnum.STAND_BY);
		dao.persistOne(entity);
		return new SubmitFormOut(entity.getId());
	}

}
