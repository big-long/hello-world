<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
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
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>

<style type="text/css">
/* div {
	width: 500px;
	height: 1000px;
	position: absolute;
	top: 200px;
	left: 200px;
} */
</style>
</head>
<body>
	
		<div class="banner" data-pointer="1" data-interval="6" data-item="4"
			 data-style="border-red">
			<ul class="carousel" >
			
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/0.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/1.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/2.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/3.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/4.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/5.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/6.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/11.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/13.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/14.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/16.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/17.jpg"></li>
			<li class="item"><img alt="加载失败"
				src="<%=basePath%>static/images/info/19.jpg"></li>
			
		</ul>
</div>
</body>
</html>