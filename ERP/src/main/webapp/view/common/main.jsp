<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>ERP管理系统</title>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<script src="<%=basePath%>static/js/jquery.js"></script>
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="<%=basePath%>static/images/ERP.png"
					class="radius-circle rotate-hover" height="50" alt="" />ERP管理系统
			</h1>
		</div>
		<div class="head-l">
			<a class="button button-little bg-green"
				href="<%=basePath%>/system/getInfo" target="right"><span
				class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##"
				class="button button-little bg-blue"><span class="icon-wrench"></span>
				清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red"
				href="<%=basePath%>logout"><span class="icon-power-off"></span>
				退出登录</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<shiro:hasAnyRoles name="root,admin">
			<h2>
				<span class="icon-pencil-square-o"></span>系统管理
			</h2>
		</shiro:hasAnyRoles>
		<ul>
			<li><a href="<%=basePath%>system/userList" target="right"><span
					class="icon-caret-right"></span>用户管理</a></li>
			<li><a href="<%=basePath%>system/toRoleList" target="right"><span
					class="icon-caret-right"></span>角色管理</a></li>
			<li><a href="<%=basePath%>system/permissionList" target="right"><span
					class="icon-caret-right"></span>权限管理</a></li>

		</ul>

		<h2>
			<span class="icon-pencil-square-o"></span>基本信息
		</h2>
		<ul>
			<li><a href="<%=basePath%>client/list" target="right"><span
					class="icon-caret-right"></span>客户资料</a></li>
			<li><a href="<%=basePath%>provider/list" target="right"><span
					class="icon-caret-right"></span>供应商资料</a></li>
			<li><a href="<%=basePath%>goods/list" target="right"><span
					class="icon-caret-right"></span>商品信息</a></li>
			<li><a href="<%=basePath%>product/list" target="right"><span
					class="icon-caret-right"></span>产品资料</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>生产管理
		</h2>
		<ul>
			<li><a href="<%=basePath%>regist/list" target="right"><span
					class="icon-caret-right"></span>产品登记</a></li>
			<li><a href="<%=basePath%>picklist/list" target="right"><span
					class="icon-caret-right"></span>领料管理</a></li>
			<li><a href="<%=basePath%>plan/list" target="right"><span
					class="icon-caret-right"></span>计划管理</a></li>

		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>采购管理
		</h2>
		<ul>
			<li><a href="<%=basePath%>purchase/list" target="right"><span
					class="icon-caret-right"></span>采购单管理</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>库存管理
		</h2>
		<ul>
			<li><a href="<%=basePath%>goodsStock/list" target="right"><span
					class="icon-caret-right"></span>商品库存管理</a></li>
			<li><a href="<%=basePath%>goodsStock/gStockList" target="right"><span
					class="icon-caret-right"></span>采购入库管理</a></li>
			<li><a href="<%=basePath%>goodsStock/pickoutList" target="right"><span
					class="icon-caret-right"></span>领料出库管理</a></li>
			<li><a href="<%=basePath%>proStock/list" target="right"><span
					class="icon-caret-right"></span>产品库存管理</a></li>
			<li><a href="<%=basePath%>stock/list" target="right"><span
					class="icon-caret-right"></span>产品入库管理</a></li>
			<li><a href="<%=basePath%>output/list" target="right"><span
					class="icon-caret-right"></span>销售出库管理</a></li>

		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>订单管理
		</h2>
		<ul>
			<li><a href="<%=basePath%>order/toOrderList" target="right"><span
					class="icon-caret-right"></span>订单浏览</a></li>
			<li><a href="javascript:void(0)" target="right"> <span
					class="icon-caret-right"></span>订单跟踪
			</a></li>


		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>销售管理
		</h2>
		<ul>
			<li><a href="<%=basePath%>pSell/list" target="right"><span
					class="icon-caret-right"></span>销售单浏览</a></li>
			<li><a href="javascript:void(0)" target="right"> <span
					class="icon-caret-right"></span>待销售订单
			</a></li>
			<li><a href="javascript:void(0)" target="right"> <span
					class="icon-caret-right"></span>销售统计
			</a></li>


		</ul>


	</div>
	<script type="text/javascript">
		$(function() {
			$(".leftnav h2").click(function() {
				$(this).next().slideToggle(200);
				$(this).toggleClass("on");
			})
			$(".leftnav ul li a").click(function() {
				$("#a_leader_txt").text($(this).text());
				$("#a_leader_txt").attr("href", $(this).attr("href"));
				$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
			})
		});
	</script>
	<ul class="bread">
		<li><a href="<%=basePath%>/system/getInfo" target="right"
			class="icon-home"> 首页</a></li>
		<li><a href="##" id="a_leader_txt" target="right">网站信息</a></li>
		<li><b>当前语言：</b><span style="color: red;">中文</php></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎你<b>${USER.account}</b></li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" rameborder="0"
			src="<%=basePath%>/system/getInfo" name="right" width="100%"
			height="100%"></iframe>
	</div>
	<div style="text-align: center;">
		<p>
			来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a>
		</p>
	</div>
</body>

</html>
