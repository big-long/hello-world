package com.hqyj.crm.system.dao;

import com.hqyj.crm.system.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

	/**
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int addUserRole(int userId, int roleId);

	int deleteManyByUserId(int[] idArray);
}