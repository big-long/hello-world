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
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, provider-scalable=no" />
<meta name="renderer" content="webkit">
<title>供应商管理</title>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<%-- <link rel="stylesheet"
	href="<%=basePath%>static/css/provider/provider.css"> --%>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/zdl/providerList.js"></script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel text-center">
			<div class="panel-head">
				<strong style="font-size: 30px"> 供应商列表</strong>
			</div>

			<div class="padding border-botton">
				<ul class="search" style="padding-left: 10px;">
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o" data-toggle="modal"
							data-target="#addProvider">添加供应商</button></li>
					<li><button type="button" class="button border-red"
							onclick="delSelect()">
							<span class="icon-trash-o"></span>批量删除
						</button></li>
					<li><button type="button" class=" button" id="reversCheck">反选</button></li>
					<li class="row"><input type="text" placeholder="请输入搜索关键字"
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
							<th width="4%"><input type="checkbox" onclick="checkAll(this)" id="ckA"/>编号</th>
							<th style="display: none"></th>
							<th>供应商简称</th>
							<th>电话</th>
							<th>联系人</th>
							<th>邮箱</th>
							<th>开户银行</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="providerList">

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

	<!-- addprovider -->
	<div class="modal fade bs-example-modal-lg" id="addProvider"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">添加新的供应商资料</h4>
				</div>

				<div class="modal-body">
					<input id="id" type="hidden" name="providerId" />

					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">供应商全称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="providerName" value=""data-validate="required:必填"  />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">供应商简称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="providerForShort" value="" data-validate="required:必填" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">地址：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="address" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">邮政编码：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="postalcode" value="" data-validate="zip:无效邮编" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">联系人：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="linkman" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">联系人号码：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="linkmanPhone" value="" data-validate="tel:无效的电话号码" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">电话：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="phone" value="" data-validate="tel:无效的电话号码" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">传真：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="facsimile" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">开户银行：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="depositBank" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">银行账号：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="account" value="" data-validate="length#==19:无效的账号,number:无效的账号" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">E-mail：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="email" value="" data-validate="email:无效的邮箱" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">信誉度：</label>
						</div>
						<div class="col-md-4 field">
							<select class="input" name="credibility" value="">
								<option>一星</option>
								<option>二星</option>
								<option>三星</option>
								<option>四星</option>
								<option>五星</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">商品备注：</label>
						</div>
						<div class="col-md-10">
							<textarea rows="3" cols="110" class="input" name="remark"></textarea>
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