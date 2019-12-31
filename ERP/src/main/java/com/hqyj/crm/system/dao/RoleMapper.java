package com.hqyj.crm.system.dao;

import java.util.List;

import com.hqyj.crm.system.entity.Role;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer roleId);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleId);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/** 查询所有的角色,分页 */
	List<Role> queryAllRoles();

	/**
	 * 
	 * <p>
	 * 授权查询角色
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月17日
	 * @param userId
	 * @return
	 */
	List<Role> selectByUserId(Integer userId);

	/** 批量删除角色 */
	int deleRoleBatch(int[] idArray);

	/** 通过name查询是否存在该角色 */
	Role queryRoleByName(String name);

	/** 验证修改角色名称的数据合理性，通过name查询角色id */
	Object queryRoleIdByName(String name);

	/** 验证添加角色时，输入的角色代码是否重复 */
	Role queryRoleByRoleName(String roleName);

	/**通过roleName查询角色id*/
	Object queryRoleIdByRoleName(String roleName);

}