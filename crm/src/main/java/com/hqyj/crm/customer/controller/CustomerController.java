package com.hqyj.crm.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hqyj.crm.customer.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;

}
