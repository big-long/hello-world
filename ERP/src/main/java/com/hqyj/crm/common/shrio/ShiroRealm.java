package com.hqyj.crm.common.shrio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hqyj.crm.system.dao.PermissionMapper;
import com.hqyj.crm.system.dao.RoleMapper;
import com.hqyj.crm.system.dao.UserMapper;
import com.hqyj.crm.system.entity.Permission;
import com.hqyj.crm.system.entity.Role;
import com.hqyj.crm.system.entity.User;


public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		System.err.println("权限分配");
		String username = (String)principals.getPrimaryPrincipal();
		User user=userMapper.queryUserByUsername(username);
		
//		String RoleId = user.getRoleId();
		
		List<Role> roles = roleMapper.selectByUserId(user.getUserId());
		if (roles != null&&!roles.isEmpty()) {
			Set<String> roleList = new HashSet<String>();
			Set<String> permissions=null;
			for (Role role : roles) {
				String RoleName = role.getRoleName();
				int roleId=role.getRoleId();
				roleList.add(RoleName);
				permissions=permissionMapper.selectPermissionByRoleId(roleId);
			}
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleList);
			info.addStringPermissions(permissions);
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken tokem = (UsernamePasswordToken) token;
		String username = tokem.getUsername();
		if (username != null && !"".equals(username)) {
			User user=userMapper.queryUserByUsername(username);
			if(user==null) {
				return null;
			}
			AuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
			return info;
		}
		return null;
	}

}
