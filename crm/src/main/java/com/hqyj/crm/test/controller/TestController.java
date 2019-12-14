package com.hqyj.crm.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.system.dao.UserMapper;
import com.hqyj.crm.system.entity.User;

@Controller
public class TestController {
		@Autowired
		private UserMapper userMapper;
	@RequestMapping("/test")
	@ResponseBody
	public User test() {
		User num = userMapper.selectByPrimaryKey(1);
		
		return num;
	}
}
