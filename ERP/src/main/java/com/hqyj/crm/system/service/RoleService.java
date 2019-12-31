package com.hqyj.crm.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.crm.system.entity.Role;

public interface RoleService {

	/** 查询所有的角色 */
	PageInfo<Role> queryAllRoles(int pageNum, int pageSize);

	/** 添加角色信息 */
	int roleAdd(Role role, String[] rolePermissionArry);

	/** 修改角色 */
	int updateRoleByRoleId(Role role, String[] rolePermissinArry);

	/** 删除角色信息 */
	int deleteRoleByRoleId(int roleId);

	Role queryRoleByRoleId(int roleId);

	/** 批量删除角色 */
	int roleDeleBatch(String[] roleArry);

	/** 通过name查询是否存在改角色 */
	boolean queryRoleByName(String name);

	/** 验证修改角色名称的数据合理性，通过name查询角色id */
	boolean queryRoleIdByName(String name, int roleId);

	/**
	 * @return
	 */
	List<Role> queryAllRole();

	/** 验证添加角色时，输入的角色代码是否重复 */
	boolean queryRoleByRoleName(String roleName);

	/** 验证角色代码是否重复，通过roleName查询角色id */
	boolean queryRoleIdByRoleName(String roleName, int roleId);

	

}
