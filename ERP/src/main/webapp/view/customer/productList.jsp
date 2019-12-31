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
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, product-scalable=no" />
<meta name="renderer" content="webkit">
<title>客户管理</title>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<%-- <link rel="stylesheet"
	href="<%=basePath%>static/css/product/product.css"> --%>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>
<script src="<%=basePath%>static/js/zdl/productList.js"></script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel text-center">
			<div class="panel-head">
				<strong style="font-size: 30px"> 产品列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o" data-toggle="modal"
							data-target="#addProduct" onclick="emptyModel()">添加产品</button></li>
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
							搜索</a></li>




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
							<th>产品名称</th>
							<th>产地</th>
							<th>单位</th>
							<th>规格</th>
							<th>批号</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="productList">
						<%-- <c:forEach items="${products.list }" var="product">
							<tr>

								<td id="productId"><input type="checkbox" name="id[]" value="" />${product.productId }</td>
								<td id="productName">${product.productName }</td>
								<td id=productionPlace>${product.productionPlace}</td>
								<td id="unit">${product.unit }</td>
								<td id="size">${product.size }</td>
								<td id="batchNumber">${product.batchNumber }</td>
								
								<td style="width: 10%"><div class="button-group">
										<button type="button" id="${product.productId }"
											class="button border-main icon-plus-square-o"
											data-toggle="modal" data-target="#addproduct" onclick="getData(this)">修改</button>

										<button class="button border-red" onclick="deleteOne(this)">
											<span class="icon-trash-o"></span> 删除
										</button>
									</div></td>
							</tr>
						</c:forEach> --%>
					</tbody>


				</table>
			</div>
			<div class="box-footer line">
				<div class="">
					<div class="form-group form-inline" id="totalPage">
						<%-- 	总共${products.pages}页，共${products.total} 条数据。 每页 <select
							class="form-control" onchange="gotoPage(1,this.value)">
							<c:forEach var="temp" begin="1" end="10">
								<option ${products.pageSize==temp?'selected':''}
									value="${temp}">${temp}</option>
							</c:forEach>
						</select> 条 --%>
					</div>
				</div>
				<div class="box-tools">
					<ul class="pagination">
						<%-- <li><a
							href="<%=basePath%>product/list?pageNum=1&pageSize=${products.pageSize}"
							aria-label="Previous">首页</a></li>
						<li><a
							href="<%=basePath%>product/list?pageNum=${products.prePage}&pageSize=${products.pageSize}">上一页</a></li>

						<!-- 处理页码 -->
						<c:forEach var="num"
							begin="${(products.pageNum-5>0?(products.pageNum-5):1)}"
							end="${(products.pageNum+5<=products.pages)?(products.pageNum+5):products.pages}">

							<li><a
								href="<%=basePath%>product/list?pageNum=${num}&pageSize=${products.pageSize}">
									<!-- 判断：主要是为了给当前页显示红色  --><c:choose>
										<c:when test="${num==products.pageNum}">
											<span style="color: red">${num}</span>
										</c:when>
										<c:otherwise>${num}</c:otherwise>
									</c:choose>

							</a></li>

						</c:forEach>

						<li><button type="button"
							onclick="gotoPage(${products.nextPage},${products.pageSize })">下一页</bttton></li>
						<li><a
							href="<%=basePath%>product/list?pageNum=${products.nextPage}&pageSize=${products.pageSize}"
							aria-label="Next">尾页</a></li> --%>
					</ul>
				</div>
			</div>
		</div>
	</form>

	<!-- addproduct -->
	<div class="modal fade bs-example-modal-lg" id="addProduct"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">添加新的产品资料</h4>
				</div>

				<div class="modal-body">
					<input id="id" type="hidden" name="productId" />

					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品名称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="productName" value=""
								data-validate="required:必填" />
						</div>
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品简称：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="productNickname" value="" />
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
							<label style="margin-top: 10px">产商：</label>
						</div>
						<div class="col-md-4 field">
							<input type="text" class="input" name="producer" value=""
								data-validate="required:必填" />
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
							<div class='input-group'>
								<input type="text" class="input" name="price" value=""
									data-validate="currency:无效金额" /> <span
									class="input-group-addon"> <span class="icon-jpy"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品备注：</label>
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