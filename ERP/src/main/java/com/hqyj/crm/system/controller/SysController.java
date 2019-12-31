package com.hqyj.crm.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.common.util.VerifyCodeUtil;
import com.hqyj.crm.system.entity.Permission;
import com.hqyj.crm.system.entity.Role;
import com.hqyj.crm.system.entity.User;
import com.hqyj.crm.system.ov.PermissionTree;
import com.hqyj.crm.system.service.PermissionService;
import com.hqyj.crm.system.service.RolePermissionService;
import com.hqyj.crm.system.service.RoleService;
import com.hqyj.crm.system.service.UserRoleService;
import com.hqyj.crm.system.service.UserService;

@Controller
@RequestMapping("/system")
public class SysController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionService rolePermissionService;

	@RequestMapping("/noPermission")
	public String noPermission() {
		return "common/noPermission";
	}

	/**
	 * 
	 * <p>
	 * 跳转到主框架页面，包含导航菜单的主框架页面
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月29日
	 * @return
	 */
	@RequestMapping("/toMain")
	public String toMain() {
		return "common/main";
	}

	/**
	 * <p>
	 * 跳转到登陆页面，主要用于shrio拦截后访问
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月20日
	 * @return 登陆页面
	 */
	@RequestMapping("/login")
	public String login() {
		return "common/login";
	}

	/**
	 * 
	 * <p>
	 * 生成验证码
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月21日
	 * @param session
	 * @param response
	 */
	@RequestMapping("/captcha.jpg")
	public void getcaptcha(HttpSession session, HttpServletResponse response) {
		String code = VerifyCodeUtil.generateVerifyCode(4); // 四位随机编码
		session.setAttribute(session.getId(), code); // 放到session
		try {
			VerifyCodeUtil.outputImage(100, 40, response.getOutputStream(), code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 生成图片流

	}

	/**
	 * 
	 * <p>
	 * 使用了shrio登陆认证，并将登陆成功后的用户user存放在session中
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月20日
	 * @param user
	 * @param request
	 * @param rememberMe 是否记住我
	 * @return 返回主页面或者登陆页面
	 */
	@PostMapping(value = "/doLogin")
	public String doLogin(User user, HttpServletRequest request, boolean rememberMe, String captcha) {
		HttpSession session = request.getSession();
		// 验证码校验，为空或不正确直接返回登陆页面
		String attribute = (String) session.getAttribute(session.getId());
		if (null == captcha || !captcha.equalsIgnoreCase(attribute)) {
			return "common/login";
		}
		// 判断是否需要登陆认证
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(rememberMe);
			try {
				subject.login(token);
				subject.checkRoles();
				user = userService.queryUserByUsernameAndPassword(user);
				session.setAttribute("USER", user);
				return "common/main";
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return "common/login";
			}
		}
		return "common/main";
	}

	/**
	 * 
	 * <p>
	 * 返回主页面 主要是页面获取主页面信息
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月20日
	 * @return 跳转到主页面
	 */
	@RequestMapping("/getInfo")
	public String getInfo() {
		return "common/info";
	}

	/**
	 * @Title: queryAllRoles @Description: TODO 分页查询所有的角色 @param
	 * PageInfo<Role> @return 返回类型 @throws
	 */
	@PostMapping(value = "/roleList")
	@ResponseBody
	public PageInfo<Role> queryAllRoles(ModelMap modelMap, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "3") int pageSize) {
		return roleService.queryAllRoles(pageNum, pageSize);
	}

	/**
	 * @Title: roleAdd @Description: TODO 增加角色 一个角色对象。rolePermissionArry
	 * 为新增角色的权限id，是一个数组需要从前端传过来 @param 参数 @param role @return int
	 * 返回类型，返回1表示插入成功，返回0表示插入失败 @throws
	 * 
	 */
	@PostMapping(value = "/roleAdd")
	@ResponseBody
	public int roleAdd(Role role, String[] rolePermissionArry) {
//		rolePermissionArry 为新增角色的权限id，是一个数组需要从前端传过来

		return roleService.roleAdd(role, rolePermissionArry);
	}

	/**
	 * 
	 * @Title: queryPermissionsByRoleId @Description: TODO:通过角色id查询对应的所有权限 @param
	 * int roleId @return List<Permission> 返回类型 @throws
	 */
	@RequestMapping(value = "/rolePermissions")
	@ResponseBody
	public List<Permission> queryPermissionsByRoleId(int roleId) {
		return permissionService.queryPermissionsByRoleId(roleId);
	}

	/**
	 * @Title: roleEidt
	 * @Description: TODO 修改角色信息
	 * @param role rolePermissinArry
	 * @param      角色对象，权限id数组
	 * @return int 返回1表示修改成功，返回0表示修改失败 @throws
	 */
	@PostMapping(value = "/roleEidt")
	@ResponseBody
	public int roleEidt(Role role, String[] rolePermissinArry) {
		return roleService.updateRoleByRoleId(role, rolePermissinArry);
	}

	/**
	 * @Title: hasRoleByName @Description: TODO:通过name查询是否已存在改角色name @param @param
	 * name @param @return 参数 @return int 返回类型 @throws
	 */
	@RequestMapping(value = "/hasRoleByName", method = RequestMethod.GET)
	@ResponseBody
	public boolean hasRoleByName(String name) {
		return roleService.queryRoleByName(name);
	}

	/**
	 * @Title: verifyName @Description:
	 * TODO:验证修改角色名称的数据合理性，通过name查询角色id @param @param name @param @return 参数 @return
	 * boolean 返回类型 @throws
	 */
	@RequestMapping(value = "/verifyName", method = RequestMethod.GET)
	@ResponseBody
	public boolean verifyName(String name, int roleId) {
		return roleService.queryRoleIdByName(name, roleId);
	}

	/**
	 * 
	 * @Title: hasRoleByRoleName @Description:
	 * TODO:验证添加角色时，输入的角色代码是否重复 @param @param roleName @param @return 参数 @return
	 * boolean 返回类型 @throws
	 */
	@RequestMapping(value = "/hasRoleByRoleName", method = RequestMethod.GET)
	@ResponseBody
	public boolean hasRoleByRoleName(String roleName) {
		return roleService.queryRoleByRoleName(roleName);
	}

	/**
	 * 
	 * @Title: verifyRoleName @Description:
	 * TODO:验证角色代码是否重复，通过roleName查询角色id @param @param roleNname @param @param
	 * roleId @param @return 参数 @return boolean 返回类型 @throws
	 */
	@RequestMapping(value = "/verifyRoleName", method = RequestMethod.GET)
	@ResponseBody
	public boolean verifyRoleName(String roleName, int roleId) {
		return roleService.queryRoleIdByRoleName(roleName, roleId);
	}

	/**
	 * 
	 * @Title: roleDelete
	 * @Description: TODO 通过角色id删除角色信息
	 * @param roleId @return int 返回类型 @throws
	 */
	@RequestMapping(value = "/roleDelete")
	@ResponseBody
	public int roleDelete(int roleId) {
		return roleService.deleteRoleByRoleId(roleId);
	}

	@RequestMapping("/loginout")
	public String loginout() {
		return "system/login";
	}

	/**
	 * 
	 * <p>
	 * 返回到用户界面
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月29日
	 * @param request
	 * @param pageNum  当前页码
	 * @param pageSize 页面大小
	 * @param user     用户信息
	 * @return
	 */
	@RequestMapping("/userList")
	public String queryUsers(HttpServletRequest request) {
//		List<User> user_dbList =userService.queryAllUser();
		List<Role> role_dbList = roleService.queryAllRole();
		request.setAttribute("role_dbList", role_dbList);
//		request.setAttribute("user_dbList", user_dbList);
		return "system/userList";
	}

	@RequestMapping("/getUserPageInfo")
	@ResponseBody
	public Result getUserPageInfo(@RequestParam(required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(required = true, defaultValue = "8") Integer pageSize) {
		return userService.getUserPageInfo(pageNum, pageSize);
	}

	@RequestMapping("/userDelete")
	@ResponseBody
	public Result deleteUserByUserId(Integer userId) {
		return userService.deleteUserByUserId(userId);
	}

	@RequestMapping("/userDetail")
	@ResponseBody
	public Result getUserDetail(int userId) {
		return userService.getUserDetail(userId);
	}

	@PostMapping("/userEidt")
	@ResponseBody
	public Result eidtUser(User user, int roleId) {
		return userService.eidtUser(user, roleId);
	}

	@RequestMapping("/userRole")
	@ResponseBody
	public List<String> getRoleByUserId(int userId) {
		return userService.queryRoleByUserId(userId);
	}

	// 批量删除
	@PostMapping("/userDeleBatch")
	@ResponseBody
	public Result deleUserBatch(String[] userArry) {
		return userService.userDeleBatch(userArry);
	}

	// 得到角色的名字
	@RequestMapping("/getRoleName")
	@ResponseBody
	public Result getRoleNameByUserId(int userId) {
		return userService.queryRolesByUserId(userId);
	}

	// 权限
	// 权限列表
	@RequestMapping(value = "/permissionList")
	public String find2(String name, @RequestParam(required = true, defaultValue = "1") Integer page,
			HttpServletRequest request) {
		List<Permission> pslist = permissionService.permissionList();
		request.getSession().setAttribute("pslist", pslist);
		request.getSession().setAttribute("name", name);
		PageHelper.startPage(page, 5);
		List<Permission> ps = new ArrayList<Permission>();
		if (name != null) {
			name = "%" + name + "%";
			ps = permissionService.findpermissionList(name);
		} else {
			ps = permissionService.permissionList();
		}
		request.getSession().setAttribute("ps", ps);
		PageInfo<Permission> p = new PageInfo<Permission>(ps);
		request.getSession().setAttribute("page", p);
		return "system/permissionlist";
	}

	// 权限列表模糊查询
	@RequestMapping(value = "/findpermissionList")
	@ResponseBody
	public List<Permission> find(String name) {
		String pName = "%" + name + "%";
		List<Permission> ps = permissionService.findpermissionList(pName);
		return ps;
	}

	/**
	 * 
	 * @Title: getPermissionTree @Description: TODO
	 * 查询所有权限，返回一个PermissionTree对象 @param @param roleId @param @return 参数 @return
	 * List<PermissionTree> 返回类型 @throws
	 */
	@RequestMapping(value = "/getPermissionTree")
	@ResponseBody
	public List<PermissionTree> getPermissionTree() {
		return permissionService.queryAllPermissions();
	}

	/**
	 * @Title: getRoleByRoleId @Description: TODO 通过角色id查询角色信息 @param @param
	 * roleId @param @return 参数 @return Role 返回类型 @throws
	 */
	@RequestMapping(value = "/getRoleByRoleId")
	@ResponseBody
	public Role getRoleByRoleId(int roleId) {
		return roleService.queryRoleByRoleId(roleId);
	}

	/**
	 * 
	 * @Title: getPermissionTreeByRoleId @Description: TODO
	 * ：通过角色id查询所拥有的的权限 @param @param roleId @param @return 参数 @return
	 * List<PermissionTree> 返回类型 @throws
	 */
	@RequestMapping(value = "/rolePermissionTree")
	@ResponseBody
	public List<PermissionTree> getPermissionTreeByRoleId(int roleId) {
		return permissionService.queryPermissionsTree(roleId);
	}

	/**
	 * @Title: deleRoleBatch @Description: TODO: 通过角色id数组，批量删除 @param @param
	 * roleArry @param @return 参数 @return int 返回类型 @throws
	 */
	@PostMapping(value = "/roleDeleBatch")
	@ResponseBody
	public int deleRoleBatch(String[] roleArry) {
		return roleService.roleDeleBatch(roleArry);
	}

	// 修改权限
	@RequestMapping(value = "/permissionEidt", method = RequestMethod.POST)
	@ResponseBody
	public int updatepermission(Permission permission) {
		int num = permissionService.updatePermission(permission);
		return num;

	}

	// 删除权限
	@RequestMapping("/permissionDelete")
	@ResponseBody
	public int permissionDelete(Integer id) {
		int num = permissionService.deletepermissionByid(id);

		return num;
	}

	// 批量删除
	@RequestMapping("/deleteSome")
	@ResponseBody
	public int deleteSome(@RequestParam(value = "checkId[]") List checkId) {
		try {
			for (Object id : checkId) {
				if (id != "") {
					permissionService.deletepermissionByid(Integer.parseInt(id.toString()));
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	// 增加权限
	@RequestMapping(value = "permissionAdd", method = RequestMethod.POST)
	@ResponseBody
	public int permissionAdd(Permission permission) {
		int num = permissionService.insertSelective(permission);

		return num;
	}

	/**
	 * 
	 * @Title: toRoleList @Description: TODO 跳转到角色列表页面 @param @return 参数 @return
	 * String 返回类型 @throws
	 */
	@RequiresRoles(value = { "admin", "root" }, logical = Logical.OR)
	@RequestMapping(value = "/toRoleList")
	public String toRoleList() {
		return "system/roleList";
	}
}