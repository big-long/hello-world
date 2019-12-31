package com.hqyj.crm.common.shrio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ResolveException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if(ex instanceof UnauthorizedException) {
			System.err.println("没有权限");
			return new ModelAndView("common/noPermission");
		}else {
			return new ModelAndView("error");
		}
	}

}
