package com.hqyj.crm.system.service;

import java.util.List;

import com.hqyj.crm.system.entity.Permission;
import com.hqyj.crm.system.ov.PermissionTree;

public interface PermissionService {

	List<Permission> queryPermissionsByRoleId(int roleId);

	List<Permission> permissionList();

	int updatePermission(Permission permission);

	int deletepermissionByid(int id);
	
	int insert(Permission record);

    int insertSelective(Permission record);

	List<PermissionTree> queryAllPermissions();

	List<PermissionTree> queryPermissionsTree(int roleId);

	List<Permission> findpermissionList(String name);
}
