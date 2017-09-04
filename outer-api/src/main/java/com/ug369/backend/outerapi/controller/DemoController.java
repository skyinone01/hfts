package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.bean.request.UserRequest;
import com.ug369.backend.outerapi.annotation.MemoryCache;
import com.ug369.backend.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

	@Autowired
	private UserService userService;

	@Autowired
	private MemoryCache memoryCache;

	@RequestMapping("/init/adduser")
	public String ok() {
		UserRequest request = new UserRequest();
		request.setUsername("admin");
		request.setName("系统管理员");
		request.setRole(1L);
		request.setMobile("13800000000");
		request.setPassword("abcd1234");
		request.setDepartment("平台运营部");
		userService.createOrUpdate(request);
		return "ok";
	}

	@RequestMapping("/jsp/controller.jsp")
	public String jsp() {

		return "ok";
	}

	@RequestMapping("/uploadimage/result")
	public String img(@RequestParam("token") String token) {


		return memoryCache.get(token);
	}
}
