package com.hqyj.crm.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.crm.system.dao.PermissionMapper;
import com.hqyj.crm.system.entity.Permission;
import com.hqyj.crm.system.ov.PermissionTree;
import com.hqyj.crm.system.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	/**
	 * 通过角色id查询角色拥有的权限
	 */
	@Override
	public List<Permission> queryPermissionsByRoleId(int roleId) {
		
		return permissionMapper.queryPermissionsByRoleId(roleId);
	}

	@Override
	public List<Permission> permissionList()
 {
		List<Permission> permissions=permissionMapper.permissionList();
		return permissions;
	}
	
	@Override
	public List<Permission> findpermissionList(String name) {
		List<Permission> permissions=permissionMapper.findpermissionList(name);
		return permissions;
	}
	
	@Override
	public int updatePermission(Permission permission) {
		
		int num=permissionMapper.updatePermission(permission);
		return num;
	}

	@Override
	public int deletepermissionByid(int id) {
		int num=permissionMapper.deletepermissionByid(id);
		return num;
	}

	@Override
	public int insert(Permission permission) {
		permission.setCreateTime(new Date());
		int num=permissionMapper.insertSelective(permission);
		return num;
	}

	@Override
	public int insertSelective(Permission record) {
		record.setCreateTime(new Date());
		int num=permissionMapper.insertSelective(record);
		return num;
	}

	/**
	 * 查询符合ztree格式的权限集合
	 */
	@Override
	public List<PermissionTree> queryAllPermissions() {
		
		return permissionMapper.queryAllPermissions();
	}

	/**
	 * 通过角色id查询角色拥有的权限，封装成一个permissiontree对象
	 */
	@Override
	public List<PermissionTree> queryPermissionsTree(int roleId) {
		
		return permissionMapper.queryPermissinsTree(roleId);
	}


}
