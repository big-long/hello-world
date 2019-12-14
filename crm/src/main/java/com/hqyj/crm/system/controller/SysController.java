package com.hqyj.crm.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hqyj.crm.system.service.PermissionService;
import com.hqyj.crm.system.service.RolePermissionService;
import com.hqyj.crm.system.service.RoleService;
import com.hqyj.crm.system.service.UserRoleService;
import com.hqyj.crm.system.service.UserService;

@Controller
public class SysController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
}
