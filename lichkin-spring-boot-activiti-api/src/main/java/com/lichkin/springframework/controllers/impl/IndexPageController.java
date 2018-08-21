package com.lichkin.springframework.controllers.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/111")
public class IndexPageController extends LKPagesController {

	@GetMapping(value = "/index" + MAPPING)
	public LKPage toIndex() {
		return null;
	}

}
