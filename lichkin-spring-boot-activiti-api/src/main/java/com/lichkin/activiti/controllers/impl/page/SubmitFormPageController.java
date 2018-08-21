package com.lichkin.activiti.controllers.impl.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

/**
 * 发起流程控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Controller
@RequestMapping("/submitForm")
public class SubmitFormPageController extends LKPagesController {

	@WithoutLogin
	@GetMapping(value = "/{processCode}" + MAPPING)
	public LKPage linkTo(@PathVariable String processCode) {
		String processId = request.getParameter("processId");
		String formId = request.getParameter("formId");
		LKPage lkPage = new LKPage();
		// 发起表单所用参数
		lkPage.putAttribute("processId", processId);
		lkPage.putAttribute("processCode", processCode);

		// 提交表单所用参数
		lkPage.putAttribute("formId", formId);
		if (StringUtils.isNotBlank(formId)) {
			lkPage.putAttribute("userId", LKSession.getString(session, "activitiUserId", ""));
			lkPage.putAttribute("userName", LKSession.getUser(session).getUserName());
		}

		return lkPage;
	}

}
