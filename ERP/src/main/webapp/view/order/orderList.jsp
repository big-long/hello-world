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
<html>
<head>
	<base href="<%=basePath%>" />
	<title>订单表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/ecmascript" src="<%=basePath%>static/js/jquery-3.4.1.js"></script>
	<script type="text/ecmascript" src="<%=basePath%>static/js/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath%>static/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/moment-with-locales.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/order/sweet-alert.min.js"></script>
	
	<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>static/css/ui.jqgrid-bootstrap.css" />
	<script>
		$.jgrid.defaults.width = 780;
		$.jgrid.defaults.responsive = true;
		$.jgrid.defaults.styleUI = 'Bootstrap';
	</script>
	<script src="<%=basePath%>static/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/order/order.js"></script>
</head>
<body>
	<div class="content">
		<div class="container-fluid">
			<div class="card card-primary card-outline">
				<div class="card-header">
					<h3 class="card-title">浏览订单</h3>
				</div>
				<!-- /.card-body -->
				<div class="card-body">
					<!-- 按钮 -->
					<div class="grid-btn">
						<button class="btn btn-success" data-toggle="modal" data-target="#addOrderModal" onclick="addOrderPage()">
							<i class="fa fa-plus"></i>&nbsp;添加新订单
						</button>
						<button class="btn btn-info" onclick="revieWOrders()">
							<i class="fa fa-edit"></i>&nbsp;审批订单
						</button>
						<button class="btn btn-danger" onclick="deleteOrders()">
							<i class="fa fa-trash-o"></i>&nbsp;删除记录
						</button>
						&nbsp;&nbsp;
					</div>
					<!-- 表格展示部分 -->
					 <div style="margin-top:15px;">
						<table id="jqGrid" class="table table-bordered" style="margin-top:15px;"></table>
						<div id="jqGridPager"></div>
					</div>
					<!-- 分页展示部分 -->
				</div>
			</div>
		</div>
	</div>
	
	<!--添加订单模态框  -->
	<!-- 添加角色模态框（Modal） -->
		<div class="modal fade" id="addOrderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close"  data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">
							添加新订单
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
						<!-- 产品名称 -->
							<div class="form-group">
								<label for="productName" class="col-md-2 control-label">产品名称</label>
								<div class="col-md-10 has-feedback ">
									<select class="form-control" id="productName">
									  <option>--请选择产品名称--</option>
									  <option value="">1</option>
									  <option value="">2</option>
									  <option value="">3</option>
									  <option value="">4</option>
									  <option value="">5</option>
									</select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							
							<!-- 下单客户 -->
							<div class="form-group">
								<label for="orderCustomer" class="col-md-2 control-label">下单客户</label>
								<div class="col-md-10 has-feedback ">
									  <select class="form-control" id="orderCustomer">
									  <option>--请选择客户名称--</option>
									  <option value="">1</option>
									  <option value="">2</option>
									  <option value="">3</option>
									  <option value="">4</option>
									  <option value="">5</option>
									</select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							
							
							<div class="form-group">
								<label for="orderId" class="col-md-2 control-label">订单编号</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
									<input type="text" class="form-control" id="orderId" placeholder="订单编号" disabled="disabled" title="订单编号不能修改">
								</div>
								
								<!-- ++++++++++++++++ -->
								<label for="orderDate" class="col-md-2 control-label">下单时间</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
						             <!--指定 date标记-->
						             <div class='input-group date' >
						                 <input type='text' class="form-control" id='orderDate' />
						                 <span class="input-group-addon">
						                     <span class="glyphicon glyphicon-calendar"></span>
						                 </span>
						             </div>
								</div>
							</div>
							<div class="form-group">
								<label for="orderNumber" class="col-md-2 control-label">下单数量</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
									<input type="text" class="form-control" id="orderNumber" placeholder="下单数量">
								</div>
								
								<!-- ++++++++++++++++ -->
								<label for="orderMoney" class="col-md-2 control-label">订单金额</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
									 <div class="input-group">
										<input type="text" class="form-control" id="orderMoney" placeholder="订单金额" >
										<span class="input-group-addon">￥</span>
									 </div>
								</div>
							</div>
							
							<div class="form-group">
								<!-- 货运方式 -->
								<label for="deliveryWay" class="col-md-2 control-label">货运方式</label>
								<div class="col-md-4 has-feedback ">
									  <select class="form-control" id="deliveryWay">
									  <option>--选择货运方式--</option>
									  <option value="">1</option>
									  <option value="">2</option>
									  <option value="">3</option>
									  <option value="">4</option>
									  <option value="">5</option>
									</select>
								</div><!-- /.col -->
								
								<!-- 交货时间 -->
								<label for="deliveryDate" class="col-md-2 control-label">交货时间</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
						             <!--指定 date标记-->
						             <div class='input-group date' >
						                 <input type='text' class="form-control" id='deliveryDate'/>
						                 <span class="input-group-addon">
						                     <span class="glyphicon glyphicon-calendar"></span>
						                 </span>
						             </div>
								</div>
							</div><!-- /form-group -->
							
							<div class="form-group">
							<!-- 经手人 -->
								<label for="handlerName" class="col-md-2 control-label">经手人</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
									<input type="text" class="form-control" id="handlerName" placeholder="经手人">
								</div>
								
								<!-- ++++++++++操作员++++++ -->
								<label for="operator" class="col-md-2 control-label">操作员</label>
								<div class="has-feedback col-md-4"><!-- has-success  icon-check-->
									 <div class="input-group">
										<input type="text" class="form-control" id="operator" placeholder="操作员" >
										<span class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
									 </div>
								</div>
							</div>
							
							<div class="form-group">
							<!-- 备注 -->
								<label for="remark" class="col-md-2 control-label">备注</label>
								<div class="has-feedback col-md-10"><!-- has-success  icon-check-->
									<textarea class="form-control" rows="5" id="remark"></textarea>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							关闭
						</button>
						<button type="button" class="btn btn-primary hide" onclick="updateOrder() " id="updateBtn">
							修改
						</button>
						<button type="button" class="btn btn-primary hide" onclick="addOrder()" id="addBtn">
							提交
						</button>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->


</body>
<script type="text/javascript">
	
</script>
</html>