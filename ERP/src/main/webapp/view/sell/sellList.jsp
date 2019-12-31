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
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/sellList.js"></script>

</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 内容列表</strong> <a href=""
					style="float: right; display: none;">添加字段</a>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o" data-toggle="modal"
							data-target="#addSell">增加产品销售订单</button></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="100" style="text-align: left; padding-left: 20px;">销售单编号</th>
					<th>产品名称</th>
					<th>客户名称</th>
					<th>销售数量</th>
					<th >销售日期</th>
					<th >销售状态</th>
					<th >状态</th>
				</tr>
				<tbody  id="userTbody"> <c:forEach
					items="${sell_dbList}" var="sell">
					<tr>
						<td name ="sellId" style="text-align: left; padding-left: 20px;" id="sellId"><input type='checkbox' name='sellId3' value='${sell.sellId}' /> ${sell.sellId}</td>
						<td name="productName" id="productName">${sell.productName}</td>
						<td name="customerName" id="customerName">${sell.customerName}</td>
						<td name="sellNumber" id="sellNumber">${sell.sellNumber}</td>
						<td name="sellDate" id="sellDate">${sell.sellDate}</td>
						<td name="sellStatus" id="sellStatus">${sell.sellStatus}</td>
						<td name="status" id="status">${sell.status}</td>
						<td>
							<div class="button-group">
								<button type="button" id="${user.userId}"
										class="button border-main icon-plus-square-o"
										data-toggle="modal" data-target="#editSell" name="update">修改</button>

								<a class="button border-red"
									href="<%=basePath%>PSell/sellDelete?sellId=${sell.sellId}"
									onclick="return del(1,1,1)"><span class="icon-trash-o"></span>
									删除</a>
							</div>
						</td>
					</tr>
					</tbody>
				</c:forEach>



				<tr>
					<td colspan="7" style="text-align: left; padding-left: 20px;">
					<!-- 批量删除 -->
						<a
						href="javascript:void(0)" class="button border-red icon-trash-o"
						style="padding: 5px 15px;" onclick="DelSelect()"> 批量删除</a>
						</td>
				</tr>
				
				<tr>
					<td colspan="8"><div class="pagelist">
						<div style="text-align: center" >
						    <ul class="pagination">
							<!--  <p style="margin-left: 10%">共${page.pages}页,当前是第${page.pageNum}页</p> -->
							<li>共${sellPage.pages}页,当前是第${sellPage.pageNum}页<li><br>
							<a href="http://localhost:8080/ERP/PSell/sellList?page=${sellPage.firstPage}" onclick="changecolor(1)">首页</a>
							<a href="http://localhost:8080/ERP/PSell/sellList?page=${sellPage.prePage}" onclick="changecolor(${sellPage.pageNum}-1)">上一页</a>
							<c:forEach begin="1" end="${sellPage.pages}" var="pageNumber">
									<a href="http://localhost:8080/ERP/PSell/sellList?page=${pageNumber}&size=5">
								    <%--判断：主要是为了给当前页显示红色--%> <c:choose>
										<c:when test="${pageNumber==sellPage.pageNum}">
											<span style="color: red">${pageNumber}</span>
											</c:when>
											<c:otherwise>${pageNumber}</c:otherwise>
										</c:choose>
									</a>
							</c:forEach>
							<a  href="http://localhost:8080/ERP/system/userList?page=${userpage.pageNum+1}" onclick="changecolor(${userpage.pageNum}+1)">下一页</a>			
							<a href="http://localhost:8080/ERP/system/userList?page=${userpage.lastPage}" onclick="changecolor(${userpage.pages})">尾页</a>
		         		</ul> 
					      </div>
					</td>
				</tr>
			</table>
		</div>
	</form>
	
	<!-- addSell -->
	<div class="modal fade bs-example-modal-lg" id="addSell" tabindex=""
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myLargeModalLabel">产品销售订单</h4>
				</div>
				<div class="modal-body">
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售编号：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="sellId" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售日期：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="sellDate" value="" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品名称：</label>
						</div>
						<div class="col-md-10">
							<input id="name" type="text" class="input" name="productName"
								value="" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">客户名称：</label>
						</div>
						<div class="col-md-10">
							<input id="name" type="text" class="input" name="customerName"
								value="" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">订单编号：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="orderId" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售数量：</label>
						</div>
						<div class="col-md-3">
							<input type="text" class="input" name="sellNumber" value="" />
						</div>
						<div class="col-md-1" style="text-align: left">
							<label style="margin-top: 10px">(台)</label>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">总金额：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="totalMoney" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">结算方式：</label>
						</div>
						<div class="col-md-4">
							<select name="settlementWay" class="input w50" >
								<option value="" >请选择</option>
								<option value="现金" >现金</option>
								<option value="微信">微信</option>
								<option value="支付宝">支付宝</option>
								<option value="银联">银联</option>
							</select>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售状态：</label>
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px" name="sellStatus" value="待提货">待提货</label>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">经手人：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="handlerName" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">操作员：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="Operator" value="" />
						</div>
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
	

	<!-- editSell -->
	<div class="modal fade bs-example-modal-lg" id="editSell" tabindex=""
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myLargeModalLabel">产品销售订单</h4>
				</div>
				<div class="modal-body">
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售编号：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="sellId2" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售日期：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="sellDate2" value="" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品名称：</label>
						</div>
						<div class="col-md-10">
							<input id="name" type="text" class="input" name="productName2"
								value="" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">客户名称：</label>
						</div>
						<div class="col-md-10">
							<input id="name" type="text" class="input" name="customerName2"
								value="" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">订单编号：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="orderId2" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售数量：</label>
						</div>
						<div class="col-md-3">
							<input type="text" class="input" name="sellNumber2" value="" />
						</div>
						<div class="col-md-1" style="text-align: left">
							<label style="margin-top: 10px">(台)</label>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">总金额：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="totalMoney2" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">结算方式：</label>
						</div>
						<div class="col-md-4">
							<select name="settlementWay2" class="input w50" >
								<option value="" >请选择</option>
								<option value="现金" >现金</option>
								<option value="微信">微信</option>
								<option value="支付宝">支付宝</option>
								<option value="银联">银联</option>
							</select>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">销售状态：</label>
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px" name="sellStatus2" value="待提货">待提货</label>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">经手人：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="handlerName2" value="" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">操作员：</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="input" name="Operator2" value="" />
						</div>
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

	
	<script type="text/javascript">
	
	</script>
</body>
</html>