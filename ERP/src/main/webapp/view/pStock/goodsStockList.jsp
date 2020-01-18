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
<link rel="stylesheet"
	href="<%=basePath%>static/css/bootstrap-datetimepicker.min.css">
<%-- <link rel="stylesheet"
	href="<%=basePath%>static/css/client/client.css"> --%>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/stock/goodsStockList.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/moment-with-locales.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/bootstrap-datetimepicker.min.js"></script>
<%-- <script src="<%=basePath%>static/js/zdl/zdl.js"></script> --%>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel text-center">
			<div class="panel-head">
				<strong style="font-size: 30px"> 商品库存管理</strong>
			</div>

			<div class="padding border-botton">
				<ul class="search" style="padding-left: 10px;">
					<!-- <li><button type="button"
							class="button button-big bg-main dialogs" data-toggle="click"
							data-target="#mydialog" data-mask="1" data-width="60%">弹出对话框</button></li> -->
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o"
							data-target="#modal" data-toggle="modal">添加新出库单</button></li>
					<li><button type="button" class="button border-red"
							onclick="delSelect()">
							<span class="icon-trash-o"></span>批量删除
						</button></li>
					<li><button type="button" class=" button" id="reversCheck">反选</button></li>
					<li class="line"><input type="text" placeholder="请输入搜索关键字"
						name="keyword" class="input"
						style="width: 250px; line-height: 17px; display: inline-block" />

						<button type="button" class="button border-main icon-search"
							onclick="refush()">
							查询</a></li>


				</ul>
			</div>
			<div class="big-box">
				<table class="table table-hover table-bordered text-center tbl">
					<thead>
						<tr class="blue">
							<th width="4%"><input type="checkbox"
								onclick="checkAll(this)" id="ckA" />编号</th>
							<th>商品名称</th>
							<th>单价</th>
							<th>库存数量</th>
							<th>经手人</th>
							<th>类别</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="list">

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


	<div class="modal fade bs-example-modal-lg" id="modal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">添加新的出库单</h4>
				</div>

				<div class="modal-body">


					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">出库编号：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="outputId" value="" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">出库日期：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="outputDate" value="" />
						</div>
					</div>



					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">销售编号：</label>
						</div>
						<div class="col-md-4 field">
							<select class="input" name="sellId">
								<option></option>
								<c:forEach items="${sells }" var="sell">
									<option value="${sell.sellId }">${sell.sellId }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">订单编号：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="orderId" value="" />
						</div>


					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">产品名称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="productName" value=""
								data-validate="plusinteger:无效数字" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">客户名称：</label>
						</div>

						<div class="col-md-4 field has-feedback">
							<div class='input-group date'>
								<input type='text' class="input" name="clientName" /> <span
									class="input-group-addon"> <span class="icon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">库存数量：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="stockNumber" value="" />
						</div>
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">出库数量：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="outputNumber" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">经手人员：</label>
						</div>

						<div class="col-md-4 field">
							<input type="text" class="input" name="handlerName" value="" />
						</div>

						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">操作人员：</label>
						</div>

						<div class="col-md-4 field">
							<input type="text" class="input" name="operator"
								value="${USER.account }" disabled="disabled" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 field" style="text-align: right">
							<label style="margin-top: 10px">备注：</label>
						</div>
						<div class="col-md-10 field">
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
</body>
</html>