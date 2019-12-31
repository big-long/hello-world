package com.hqyj.crm.system.dao;

import java.util.List;

import com.hqyj.crm.system.entity.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int addRolePermissionBatch(List<RolePermission> rolePermissionList);

	int deletePermissionByRoleId(Integer roleId);

	/**批量删除角色权限中间表*/
	int deleRolePermissionBatch(int[] idArray);
}