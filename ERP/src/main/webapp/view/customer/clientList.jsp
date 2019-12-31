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
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, client-scalable=no" />
<meta name="renderer" content="webkit">
<title>客户管理</title>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<%-- <link rel="stylesheet"
	href="<%=basePath%>static/css/client/client.css"> --%>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/zdl/clientList.js"></script>
<%-- <script src="<%=basePath%>static/js/zdl/zdl.js"></script> --%>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel text-center">
			<div class="panel-head">
				<strong style="font-size: 30px"> 客户列表</strong>
			</div>

			<div class="padding border-botton">
				<ul class="search" style="padding-left: 10px;">
					<!-- <li><button type="button"
							class="button button-big bg-main dialogs" data-toggle="click"
							data-target="#mydialog" data-mask="1" data-width="60%">弹出对话框</button></li> -->
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o"
							data-target="#addClient" data-toggle="modal">添加客户</button></li>
					<li><button type="button" class="button border-red"
							onclick="delSelect()">
							<span class="icon-trash-o"></span>批量删除
						</button></li>
					<li><button type="button" class=" button" id="reversCheck">反选</button></li>
					<li class="line"><input type="text" placeholder="请输入搜索关键字"
						name="keyword" class="input"
						style="width: 250px; line-height: 17px; display: inline-block" />

						<button type="button" class="button border-main icon-search"
							onclick="changeSearch()">
							查询</a></li>


				</ul>
			</div>
			<div class="big-box">
				<table class="table table-hover table-bordered text-center tbl">
					<thead>
						<tr class="blue">
							<th width="4%"><input type="checkbox" onclick="checkAll(this)" id="ckA" />编号</th>
							<th style="display: none"></th>
							<th>供应商简称</th>
							<th>电话</th>
							<th>联系人</th>
							<th>邮箱</th>
							<th>开户银行</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="clientList">

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

	<!-- addclient -->
	<!-- <div id="mydialog">
		<div class="dialog">

			<div class="dialog-head" style="text-align: center">
				<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				</button>
				<span class="dialog-close close rotate-hover"></span>
				<h4 id="myModalLabel">添加新的客户资料</h4>

			</div>






			<div class="dialog-body">
				<input id="id" type="hidden" name="clientId" />

				<div class="line">
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">客户全称：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="clientName" value="" />
					</div>
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">客户简称：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="clientForShort" value="" />
					</div>
				</div>
				<div class="line">
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">地址：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="address" value="" />
					</div>
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">邮政编码：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="postalcode" value="" />
					</div>
				</div>
				<div class="line">

					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">电话：</label>
					</div>
					<div class="form-group">
						<div class="x5">
							<input onblur="check(this)" type="text" class="input"
								name="phone" value="" data-validate="tel:请输入正确的电话号码" />
						</div>
					</div>
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">传真：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="facsimile" value="" />
					</div>
				</div>

				<div class="line">
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">联系人：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="linkman" value="" />
					</div>

					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">联系人号码：</label>
					</div>
					<div class="form-group">
					<div class="x5">
						<input onblur="check(this)" type="text" class="input"
							name="linkmanPhone" value="" data-validate="number:纯数字" />
					</div>
					</div>
				</div>
				<div class="line">
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">E-mail：</label>
					</div>
					<div class="form-group">
						<div class="x5 field">
							<input onblur="check(this)" type="text" class="input" name="email" value=""
							data-validate="email:请输入正确的邮箱" />
						</div>
					</div>
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">开户银行：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="depositBank" value="" />
					</div>
				</div>
				<div class="line">
					<div class="x1" style="text-align: right">
						<label style="margin-top: 10px">银行账号：</label>
					</div>
					<div class="x5">
						<input type="text" class="input" name="account" value="" />
					</div>
				</div>

			</div>
			<div class="dialog-foot">
				<button type="button" class="button dialog-close">关闭</button>
				<button type="button" class="button bg-green dialog-close"
					name="submit" onclick="pushData()">保存</button>
			</div>

		</div>
	</div> -->
	<div class="modal fade bs-example-modal-lg" id="addClient"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">添加新的客户资料</h4>
				</div>

				<div class="modal-body">
					<input id="id" type="hidden" name="clientId" />

					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">客户全称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="clientName" value=""
								data-validate="required:必填" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">客户简称：</label>
						</div>
						<div class="col-md-4 field " data-toggle="show" data-place="top"
							content="test">
							<input type="text" class="input" name="clientForShort" value=""
								data-validate="required:必填" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">地址：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="address" value="" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">邮政编码：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="postalcode" value=""
								data-validate="zip:邮编不正确" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">联系人：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="linkman" value="" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">联系人号码：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="linkmanPhone" value=""
								data-validate="number:请填电话号码 ,mobile:无效的电话号码" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">电话：</label>
						</div>

						<div class="col-md-4 field">
							<input type="text" class="input" name="phone" value=""
								data-validate="number:请填电话号码 ,tel:无效的电话号码" />
						</div>

						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">传真：</label>
						</div>

						<div class="col-md-4 field">
							<input type="text" class="input" name="facsimile" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">开户银行：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="depositBank" value="" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">银行账号：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="account" value=""
								data-validate="length#==19:无效的账号" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">E-mail：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="email" value=""
								data-validate="email:无效的邮箱" />
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" name="submit"
						 onclick="pushData()">保存</button>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		gotoPage(1, 8);
	</script>
</body>
</html>