package com.hqyj.crm.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

//import com.longmao.demo.modules.account.dao.ResourceDao;
//import com.longmao.demo.modules.account.entity.Resource;
//import com.longmao.demo.modules.common.annotation.PermissionName;
//@Component
public class PermissionUtil {
	
	
	
//	@Autowired
//	private  RequestMappingHandlerMapping handlerMapping;
//	private static RequestMappingHandlerMapping rmhm;
//	@Autowired
//	private  ResourceDao resourceDaoIni;
//	private static ResourceDao resourceDao;
//    @PostConstruct
//    public void init(){
//    	this.rmhm=handlerMapping;
//    	this.resourceDao=resourceDaoIni;
//    }
//	
//	public static void loadPermission() {
//		List<Resource> resources = resourceDao.selectResources();
//		List<String> permissions=new ArrayList<String>();
//		for (Resource resource : resources) {
//			String permission = resource.getPermission();
//			permissions.add(permission);
//		}
//		//1获取controller中所有带有@RequestMapper标签的方法
//		Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
//		Collection<HandlerMethod> methods = handlerMethods.values();
//		Resource resource=null;
//		for (HandlerMethod method : methods) {
//			//2遍历方法，找到带有@requiresPermission权限控制标签的方法
//			RequiresPermissions anno = method.getMethodAnnotation(RequiresPermissions.class);
//			if(anno!=null) {
//				String permission = anno.value()[0];
//				if(permissions.contains(permission)) {
//					continue;
//				}
//				//3将权限值获取到，并封装到Permission中
//				PermissionName permissionName = method.getMethodAnnotation(PermissionName.class);
//				String resourceName = permissionName.value();
//				
//				//4将URL获得，封装到Resource中
//				RequestMapping requestMapping = method.getMethodAnnotation(RequestMapping.class);
//				String resourceUrl = requestMapping.value()[0];
//				resourceUrl="/account"+resourceUrl;
//				resource=new Resource();
//				resource.setPermission(permission);
//				resource.setResourceName(resourceName);
//				resource.setResourceUrl(resourceUrl);
//				resource.setCreateDate(new Date(System.currentTimeMillis()));
//				
//				resourceDao.insertResourceByResource(resource);
//			}
//		}
//	}
}
