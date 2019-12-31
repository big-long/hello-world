package com.hqyj.crm.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.system.dao.RoleMapper;
import com.hqyj.crm.system.dao.RolePermissionMapper;
import com.hqyj.crm.system.entity.Role;
import com.hqyj.crm.system.entity.RolePermission;
import com.hqyj.crm.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	/**
	 * 查询所有的角色
	 */
	@Override
	public PageInfo<Role> queryAllRoles(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleMapper.queryAllRoles();
		// 准备一个PageInfo对象
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}

	/**
	 * 添加角色
	 */
	@Override
	public int roleAdd(Role role, String[] rolePermissionArry) {
		// 添加创建时间
		role.setCreateTime(new Date());
		// 将角色信息插入角色表中，此时可以得到插入的id
		roleMapper.insert(role);
		// 准备一个RolePermission对象的集合
		List<RolePermission> rolePermissionList = new ArrayList<>();
		int flag = -1;
		if (rolePermissionArry != null && rolePermissionArry.length != 0) {
			for (String permissionId : rolePermissionArry) {
				// 封装中间表对象
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getRoleId());
				rolePermission.setPermissionId(Integer.parseInt(permissionId));
				// 将角色权限对象放到list中
				rolePermissionList.add(rolePermission);
			}
			// 为中间表插入角色权限信息，
			flag = rolePermissionMapper.addRolePermissionBatch(rolePermissionList);
		}
		if (flag == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * 修改角色信息
	 */
	@Override
	public int updateRoleByRoleId(Role role, String[] rolePermissinArry) {
		// 为角色添加修改时间
		role.setCreateTime(new Date());
		// 修改角色信息
		int flag = roleMapper.updateByPrimaryKey(role);
		if (flag == 1) {
			// 修改角色权限，先删除中间表中原先已分配的权限
			rolePermissionMapper.deletePermissionByRoleId(role.getRoleId());
			// 如果选择了部分权限，如果长度为0，则未给该角色授权
			if (rolePermissinArry != null && rolePermissinArry.length != 0) {
				List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();
				for (String permissionId : rolePermissinArry) {
					// 封装中间表对象
					RolePermission rolePermission = new RolePermission();
					rolePermission.setRoleId(role.getRoleId());
					rolePermission.setPermissionId(Integer.parseInt(permissionId));
					// 讲对象存入list中
					rolePermissionList.add(rolePermission);
				}
				// 为中间表插入角色权限信息，
				int flag2 = rolePermissionMapper.addRolePermissionBatch(rolePermissionList);
				if (flag2 == 0) {
					return 0;
				}
				return 1;
			}
			return 1;
		}
		return 0;
	}

	/**
	 * 通过角色id删除角色信息
	 */
	@Override
	public int deleteRoleByRoleId(int roleId) {
		// 删除中间表的角色权限分配情况
		rolePermissionMapper.deletePermissionByRoleId(roleId);
		// 删除角色表中角色
		if (roleMapper.deleteByPrimaryKey(roleId) == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * 通过角色id查询角色信息
	 */
	@Override
	public Role queryRoleByRoleId(int roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	/**
	 * 批量删除角色
	 */
	@Override
	public int roleDeleBatch(String[] roleArry) {
		// 将角色id数组转换成int类型的数组
		int[] idArray = new int[roleArry.length];
		for (int i = 0; i < roleArry.length; i++) {
			idArray[i] = Integer.parseInt(roleArry[i]);
		}
		// 删除中间表对应角色的数据
		rolePermissionMapper.deleRolePermissionBatch(idArray);

		// 删除角色表对应的数据
		int flag = roleMapper.deleRoleBatch(idArray);
		if (flag == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * 通过角色名称查询是否存在该角色
	 */
	@Override
	public boolean queryRoleByName(String name) {
		Role role = roleMapper.queryRoleByName(name);
		if (role == null) {
			return true;
		}
		return false;
	}

	/**
	 * 验证修改角色名称的数据合理性，通过name查询角色id
	 */
	@Override
	public boolean queryRoleIdByName(String name, int roleId) {
		/* 此处可能会查询到null，需要将null转化成字符串 */
		Object res = roleMapper.queryRoleIdByName(name);
		/** 如果为null，就返回0，如果为整数，就返回该整数，需要将Object对象强转成int类型 */
		int roleId_db = (res == null) ? 0 : (Integer) res;
		if (roleId_db == 0 || roleId == roleId_db) {
			return true;
		}
		return false;
	}

	/**
	 * 验证添加角色时，输入的角色代码是否重复
	 */
	@Override
	public boolean queryRoleByRoleName(String roleName) {
		Role role = roleMapper.queryRoleByRoleName(roleName);
		if (role == null) {
			return true;
		}
		return false;
	}

	/**
	 * 验证角色代码是否重复，通过roleName查询角色id
	 */
	@Override
	public boolean queryRoleIdByRoleName(String roleName, int roleId) {
		//通过roleName查询角色id，结果可能为空
		Object res = roleMapper.queryRoleIdByRoleName(roleName);
		int roleId_db = (res == null) ? 0 : (Integer) res;
		if (roleId_db == 0 || roleId == roleId_db) {
			return true;
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see com.hqyj.crm.system.service.RoleService#queryAllRole()
	 */
	@Override
	public List<Role> queryAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.queryAllRoles();
	}
}
