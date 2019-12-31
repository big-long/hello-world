package com.hqyj.crm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.crm.system.dao.RolePermissionMapper;
import com.hqyj.crm.system.service.RolePermissionService;
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
}
