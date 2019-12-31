package com.hqyj.crm.system.service.impl;

import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.system.dao.UserMapper;
import com.hqyj.crm.system.dao.UserRoleMapper;
import com.hqyj.crm.system.entity.Role;
import com.hqyj.crm.system.entity.User;
import com.hqyj.crm.system.entity.UserRole;
import com.hqyj.crm.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	/**
	 * 
	 * @author 赵丹钊
	 */
	@Autowired
	private UserMapper userMapper;
	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public List<User> queryAllUser() {
		return userMapper.queryAllUser();
	}

	@Override
	public Result addUser(User user) {
		int n = userMapper.insert(user);
		if (n == 0) {
			return new Result(500, "用户新增失败");
		}
		return new Result(200,"success");
	}

	@Override
	public Result deleteUserByUserId(int userId) {
		int n = userMapper.deleteByPrimaryKey(userId);
		if (n == 0) {
			return new Result(500, "删除失败");
		}
		return new Result(200, "success");
	}

	@Override
	public Result getUserDetail(int userId) {
		User user = userMapper.getUserDetail(userId);
		return new Result(200, "success", user);
	}

	@Override
	public Result eidtUser(User user, int roleId) {
		if (user.getUserId() == null) {
			int n = userMapper.insertSelective(user);
			if (n == 0) {
				return new Result(500, "用户新增失败");
			}
			UserRole userRole = new UserRole(user.getUserId(), roleId);
			int m = userRoleMapper.insertSelective(userRole);
			if (m == 0) {
				return new Result(500, "分配角色失败");
			}
		} else {
			int n = userMapper.updateByPrimaryKeySelective(user);
			if (n == 0) {
				return new Result(500, "用户修改失败");
			}
			UserRole userRole = new UserRole(user.getUserId(), roleId);
			int m = userRoleMapper.updateByPrimaryKeySelective(userRole);
			if (m == 0) {
				return new Result(500, "分配角色失败");
			}
		}
		return new Result(200,"success");
	}

	@Override
	public List<String> queryRoleByUserId(int userId) {
		List<String> li = userMapper.queryRoleByUserId(userId);
		System.out.println(li);
		return userMapper.queryRoleByUserId(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hqyj.crm.system.service.UserService#queryUserById(int)
	 */
	@Override
	public User queryUserById(int userId) {
		return userMapper.getUserDetail(userId);
	}

	/**
	 * zdl
	 */
	@Override
	public User queryUserByUsernameAndPassword(User user) {
		user = userMapper.queryUserByUsernameAndPassword(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hqyj.crm.system.service.UserService#insertUser(com.hqyj.crm.system.entity
	 * .User)
	 */
	@Override
	public User insertUser(User user) {
		userMapper.insert(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hqyj.crm.system.service.UserService#updateUser(com.hqyj.crm.system.entity
	 * .User)
	 */
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hqyj.crm.system.service.UserService#userDeleBatch(java.lang.String[])
	 */
	@Override
	public Result userDeleBatch(String[] userArry) {
		if(userArry==null||userArry.length==0) {
			return new Result(500,"请至少选择一条数据");
		}
		int[] idArray = new int[userArry.length];
		
		for (int i = 0; i < userArry.length; i++) {
			idArray[i] = Integer.parseInt(userArry[i]);
		}
		

		// 删除角色表对应的数据
		int flag = userMapper.deleUserBatch(idArray);
		// 删除中间表对应角色的数据
		int n =userRoleMapper.deleteManyByUserId(idArray);
		if (flag == userArry.length&&flag==n) {
			return new Result(200,"success");
		}
		return new Result(500,"系统异常");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hqyj.crm.system.service.UserService#queryRoleNameByUserId(int)
	 */
	@Override
	public String queryRoleNameByUserId(int userId) {
		// TODO Auto-generated method stub
		return userMapper.queryRoleNameByUserId(userId);
	}

	@Override
	public Result queryRolesByUserId(int userId) {
		Role role = userMapper.queryRolesByUserId(userId);
		return new Result(200,"success",role);
	}

	@Override
	public Result getUserPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userMapper.queryAllUser();
		return new Result(200, "success", new PageInfo<User>(users));
	}

}
