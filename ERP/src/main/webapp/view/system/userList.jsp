<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>static/css/customer/customer.css">
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/system/userList.js"></script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel text-center">
			<div class="panel-head">
				<strong class="icon-reorder"> 内容列表</strong> <a href=""
					style="float: right; display: none;">添加字段</a>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o" data-toggle="modal"
							onclick="emptyModel()" data-target="#addUser">添加用户</button></li>
					<li><button type="button" name="deleteMany"
							class="button border-red" onclick="delSelect()">
							<span class="icon-trash-o"></span>批量删除
						</button></li>


				</ul>
			</div>
			<div class="big-box">
				<table class="table table-hover text-center">
					<tr>
						<th width="100" style="text-align: left; padding-left: 20px;"><input
							type="checkbox" onclick="checkAll(this)" id="ckA" />编号</th>
						<th>用户姓名</th>
						<th>账户</th>
						<th width="10%">创建时间</th>
						<th width="310">操作</th>
					</tr>
					<tbody id="userList">

					</tbody>
				</table>
			</div>
			<div class="box-footer line">
				<div class="">
					<div class="form-group form-inline" id="totalPage"></div>

				</div>
				<div class="box-tools">
					<ul class="pagination">

					</ul>
				</div>
			</div>
		</div>
	</form>






	<!-- addUser -->
	<div class="modal fade bs-example-modal-lg" id="addUser" tabindex=""
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myLargeModalLabel">用户</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="userId" />
					<div class="row">
						<div class="col-md-3" style="text-align: right">
							<label style="margin-top: 10px">用户名：</label>
						</div>
						<div class="col-md-9">
							<input type="text" class="input" name="username" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-3" style="text-align: right">
							<label style="margin-top: 10px">账户名：</label>
						</div>
						<div class="col-md-9">
							<input type="text" class="input" name="account" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-3" style="text-align: right">
							<label style="margin-top: 10px">职位：</label>
						</div>
						<div class="col-md-9">
							<select name="roleId" class="input w50"><option value="">--请选择角色--</option>
								<c:forEach items="${role_dbList}" var="role">
									<option value="${role.roleId}">${role.name}</option>
								</c:forEach></select> </select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" onclick="pushData()"
							style="">保存</button>
					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>