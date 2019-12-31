package com.hqyj.crm.test.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.baseinfo.entity.Client;
import com.hqyj.crm.baseinfo.entity.Product;
import com.hqyj.crm.baseinfo.service.ClientService;
import com.hqyj.crm.baseinfo.service.ProductService;

@Controller
public class TestController {
	@Autowired
	private ClientService userMapper;
	@Autowired
	private ProductService productMapper;

	@RequestMapping("/test")
	@ResponseBody
	public List<Client> test() {
		return userMapper.queryClients();
		
	}
	@RequestMapping("/test1")
	@ResponseBody
	public List<Product> test1() {
		return productMapper.queryProducts();
		
	}
}
