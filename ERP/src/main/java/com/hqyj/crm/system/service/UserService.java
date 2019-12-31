package com.hqyj.crm.system.service;

import java.util.List;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.system.entity.Role;
import com.hqyj.crm.system.entity.User;

public interface UserService {
	/**
	 * 
	 * @author 赵丹钊
	 */

	List<User> queryAllUser();

	Result addUser(User user);

	Result deleteUserByUserId(int userId);

	Result getUserDetail(int userId);

	Result eidtUser(User user, int roleId);

	List<String> queryRoleByUserId(int userId);
	
	/**
	 * 
	 * <p>
	 * 
	 * </p>
	 * @author zdl
	 * @Date 2019年12月17日
	 * @param username
	 * @param password
	 * @return
	 */
	User queryUserByUsernameAndPassword(User user);


	/**
	 * @param userId
	 * @return
	 */
	User queryUserById(int userId);

	/**
	 * @param user
	 * @return
	 */
	User insertUser(User user);

	/**
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * @param userIdArray
	 * @return
	 */
	Result userDeleBatch(String[] userArry);

	/**
	 * @param userId
	 * @return
	 */
	String queryRoleNameByUserId(int userId);

	Result queryRolesByUserId(int userId);

	Result getUserPageInfo(Integer pageNum, Integer pageSize);

	/**
	 * @param userId
	 * @return
	 */
	




}
