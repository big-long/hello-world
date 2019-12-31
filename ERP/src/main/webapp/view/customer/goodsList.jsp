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
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, goods-scalable=no" />
<meta name="renderer" content="webkit">
<title>客户管理</title>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<%-- <link rel="stylesheet"
	href="<%=basePath%>static/css/goods/goods.css"> --%>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/zdl/goodsList.js"></script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel text-center">
			<div class="panel-head">
				<strong style="font-size: 30px"> 商品列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o" data-toggle="modal"
							data-target="#addGoods" onclick="emptyModel()">添加商品</button></li>
					<li><button type="button" class="button border-red"
							onclick="deleteMany()">
							<span class="icon-trash-o"></span>批量删除
						</button></li>
					<li><button type="button" class=" button" id="reversCheck">反选</button></li>
					<li><select name="type" class="input"
						style="width: 200px; line-height: 17px;" onchange="changeSearch()">
							<option value="">全部类型</option>
							<c:forEach items="${categorys }" var="category">
								<option value="${category }">${category }</option>
							</c:forEach>

					</select></li>
					<li class="row"><input type="text" placeholder="请输入搜索关键字"
						name="keyword" class="input"
						style="width: 250px; line-height: 17px; display: inline-block" />

						<button type="button" class="button border-main icon-search"
							onclick="changeSearch()">
							查询</a></li>




				</ul>
				<div></div>
			</div>
			<div class="big-box">
				<table class="table table-hover table-bordered text-center tbl">
					<thead>
						<tr class="blue">
							<th width="4%"><input type="checkbox" id="ckA"
								onclick="checkAll(this)" /> 编号</th>
							<th style="display: none"></th>
							<th>商品名称</th>
							<th>产地</th>
							<th>单位</th>
							<th>规格</th>
							<th>批号</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="goodsList">

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

	<!-- addgoods -->
	<div class="modal fade bs-example-modal-lg" id="addGoods" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">添加新的商品资料</h4>
				</div>

				<div class="modal-body">
					<input id="id" type="hidden" name="goodsId" />

					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">商品名称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="goodsName" value=""
								data-validate="required:必填" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">商品简称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="goodsNickname" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产地：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="productionPlace" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">单位：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="unit" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">规格：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="size" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">包装：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="pack" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">批号：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="batchNumber" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">批文号：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="approvalNumber" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">供应商：</label>
						</div>
						<div class="col-md-4 field">
							<select name="provider" class="input">
							<option value=""></option>
								<c:forEach items="${providers }" var="provider">
									<option value="${provider }">${provider }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">类别：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="category" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">单价：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="price" value="" />
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
		gotoPage(1, 6);
	</script>
</body>
</html>