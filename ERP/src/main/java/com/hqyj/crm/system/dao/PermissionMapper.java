package com.hqyj.crm.system.dao;

import java.util.List;
import java.util.Set;

import com.hqyj.crm.system.entity.Permission;
import com.hqyj.crm.system.ov.PermissionTree;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
    /**通过角色id查询权限*/
	List<Permission> queryPermissionsByRoleId(int roleId);
    
    List<Permission> permissionList();

	int updatePermission(Permission permission);

	int deletepermissionByid(int id);

	int addPermission(Permission permission);


	/**返回一个符合ztree格式的权限集合*/
	List<PermissionTree> queryAllPermissions();

	List<PermissionTree> queryPermissinsTree(int roleId);
	
	List<Permission> findpermissionList(String name);
	/**
	 * 
	 * <p>
	 * 	授权时获取权限信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月17日
	 * @param roleId
	 * @return
	 */
	Set<String> selectPermissionByRoleId(int roleId);

	

}