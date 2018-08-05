package com.lichkin.activiti.controllers.impl.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

@Controller

@RequestMapping("/startProcess")
public class StartProcessPageController extends LKPagesController {

	@WithoutLogin
	@GetMapping(value = "/index" + MAPPING)
	public LKPage linkTo() {
		String processId = request.getParameter("processId");
		LKPage lkPage = new LKPage();
		lkPage.putAttribute("processId", processId);
		lkPage.putAttribute("userId", LKSession.getLoginId(session));
		lkPage.putAttribute("userName", LKSession.getUser(session).getUserName());
		return lkPage;
	}

}
