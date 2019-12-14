package com.hqyj.crm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.crm.system.dao.userRoleMapper;
import com.hqyj.crm.system.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private userRoleMapper userRoleMapper;
}
