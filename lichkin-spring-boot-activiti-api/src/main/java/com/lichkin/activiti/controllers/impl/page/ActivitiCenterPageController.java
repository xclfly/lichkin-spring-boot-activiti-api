package com.lichkin.activiti.controllers.impl.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.application.beans.out.impl.EmployeeLoginInfo;
import com.lichkin.application.services.impl.SysUserEmployeeLoginForH5Service;
import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

/**
 * 工作流中心控制器类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Controller
@RequestMapping("/activitiCenter")
public class ActivitiCenterPageController extends LKPagesController {

	@Autowired
	private SysUserEmployeeLoginForH5Service service;


	@WithoutLogin
	@GetMapping(value = "/index" + MAPPING)
	public LKPage linkTo() {
		String compId = request.getParameter("compId");
		String token = request.getParameter("token");

		if (LKSession.getLogin(session) == null) {
			try {
				EmployeeLoginInfo loginInfo = service.findUserLoginByToken(token, compId);
				LKSession.setLogin(session, loginInfo.getEmployeeLogin());
				LKSession.setComp(session, loginInfo.getComp());
				LKSession.setUser(session, loginInfo.getEmployee());
				LKSession.setString(session, "deptId", loginInfo.getDept().getId());
				LKSession.setString(session, "activitiUserId", loginInfo.getEmployeeLogin().getId() + "_" + compId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		LKPage lkPage = new LKPage();
		if (LKSession.getLogin(session) != null) {
			lkPage.putAttribute("deptId", LKSession.getString(session, "deptId", ""));
			lkPage.putAttribute("userId", LKSession.getString(session, "activitiUserId", ""));
		}

		return lkPage;
	}

}
