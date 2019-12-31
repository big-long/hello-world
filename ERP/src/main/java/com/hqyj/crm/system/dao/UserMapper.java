package com.hqyj.crm.system.dao;

import java.util.List;

import com.hqyj.crm.system.entity.Role;
import com.hqyj.crm.system.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
	 * 
	 * @author 赵丹钊
	 */

	List<User> queryAllUser();

	User getUserDetail(int userId);

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

	User queryUserByUsername(String username);

	/**
	 * @param user
	 * @return
	 */
	User insertUserByUser(User user);

	/**
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * @param idArray
	 * @return
	 */
	int deleUserBatch(int[] idArray);

	/**
	 * @param userId
	 * @return
	 */
	String queryRoleNameByUserId(int userId);

	Role queryRolesByUserId(int userId);
}