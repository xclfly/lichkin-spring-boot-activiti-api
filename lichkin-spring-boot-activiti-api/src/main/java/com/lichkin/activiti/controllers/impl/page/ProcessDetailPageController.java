package com.lichkin.activiti.controllers.impl.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

/**
 * 流程详情控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Controller
@RequestMapping("/processDetail")
public class ProcessDetailPageController extends LKPagesController {

	@WithoutLogin
	@GetMapping(value = "/index" + MAPPING)
	public LKPage linkTo() {
		String processType = request.getParameter("processType");
		String processInstanceId = request.getParameter("processInstanceId");
		String processState = request.getParameter("processState");

		LKPage lkPage = new LKPage();
		lkPage.putAttribute("userId", LKSession.getString(session, "activitiUserId", ""));
		lkPage.putAttribute("processType", processType);
		lkPage.putAttribute("processInstanceId", processInstanceId);
		lkPage.putAttribute("processState", processState);
		return lkPage;
	}

}
