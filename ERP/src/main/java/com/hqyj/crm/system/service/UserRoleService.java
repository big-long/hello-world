package com.hqyj.crm.system.service;

import com.hqyj.crm.system.entity.UserRole;

public interface UserRoleService {

	/**
	 * @param id
	 * @param roleId
	 * @return
	 */
	int addUserRole(int userId, int roleId);

	/**
	 * @param userRole
	 * @return
	 */
	int addUserRole(UserRole userRole);

	/**
	 * @param userId
	 * @param roleId
	 * @return
	 */

	/**
	 * @param userRole
	 * @return
	 */
	int editUserRole(UserRole userRole);

}
