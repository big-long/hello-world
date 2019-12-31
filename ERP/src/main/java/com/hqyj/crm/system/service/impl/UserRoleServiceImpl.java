package com.hqyj.crm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.crm.system.dao.UserRoleMapper;
import com.hqyj.crm.system.entity.UserRole;
import com.hqyj.crm.system.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;

	/* (non-Javadoc)
	 * @see com.hqyj.crm.system.service.UserRoleService#addUserRole(int, int)
	 */
	@Override
	public int addUserRole(int userId, int roleId) {
		
		return (Integer) null;
	}

	/* (non-Javadoc)
	 * @see com.hqyj.crm.system.service.UserRoleService#addUserRole(com.hqyj.crm.system.entity.UserRole)
	 */
	@Override
	public int addUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return userRoleMapper.insertSelective(userRole);
	}

	/* (non-Javadoc)
	 * @see com.hqyj.crm.system.service.UserRoleService#editUserRole(com.hqyj.crm.system.entity.UserRole)
	 */
	@Override
	public int editUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return userRoleMapper.updateByPrimaryKeySelective(userRole);
	}

	/* (non-Javadoc)
	 * @see com.hqyj.crm.system.service.UserRoleService#editUserRole(int, int)
	 */
	
}
