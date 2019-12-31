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
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, customer-scalable=no" />
<meta name="renderer" content="webkit">
<title>产品入库订单管理</title>
<script src="<%=basePath%>static/js/jquery.js"></script>
<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
<script src="<%=basePath%>static/js/bootstrap.js"></script>
<script src="<%=basePath%>static/js/pintuer.js"></script>

</head>
<body>

	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder"> 入库订单管理</strong> <a href=""
				style="float: right; display: none;">添加字段</a>
			<button class="btn btn-link icon-home" onclick="homeBack()">返回商品表</button>
			<button class="btn btn-link icon-reply" onclick="history.back()">后退</button>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left: 10px;">
				<li><button type="button" name="add"
						class="button border-main icon-plus-square-o" data-toggle="modal"
						data-target="#addStockInfo">添加订单</button></li>
				<li><button class="button border-red" onclick="delSelect()">
						<span class="icon-trash-o"></span>批量删除
					</button></li>

				<li><input type="checkbox" name="selectall" id="selectall" /><span>选择全部</span></li>
			</ul>

		</div>
		<table class="table table-hover text-center">
			<tr>

				<td>请选择</td>
				<th>入库单编号</th>
				<th>订单编号</th>
				<th>生产编号</th>
				<th>入库数量</th>
				<th>入库时间</th>
				<th>经手人</th>
				<th>备注</th>
				<th width="310">操作</th>
			</tr>
			<tbody name="list" id="vo">
				<c:forEach items="${sispage}" var="sis" varStatus="vs">
					<tr>

						<td><input type="checkbox" name="del" id="${sis.orderId}"
							value="${sis.inbillId}" />${vs.count+5*(page.pageNum-1)}</td>
						<td id="inbillId">${sis.inbillId}</td>
						<td id="orderId">${sis.orderId}</td>
						<td id="productionId">${sis.productionId}</td>
						<td id="inbillNumber">${sis.inbillNumber}</td>
						<td id="inbillDate">${sis.inbillDate}</td>
						<td id="handlerName">${sis.handlerName}</td>
						<td id="remark">${sis.remark}</td>
						<td id="deleteandupdate" style="width: 20%"><div
								class="button-group">
								<button type="button"
									class="button border-main icon-plus-square-o"
									data-toggle="modal" data-target="#updatestockInfo"
									name="update">修改</button>

								<button class="button border-red"
									onclick="delMethod('${sis.orderId}')">
									<span class="icon-trash-o"></span> 删除
								</button>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 分页栏 -->
	<div style="text-align: center" id="page">
		<ul class="pagination">
			<!--  <p style="margin-left: 10%">共${page.pages}页,当前是第${page.pageNum}页</p> -->
			<li>共${page.pages}页,当前是第${page.pageNum}页
			<li><br> <a
				href="<%=request.getContextPath()%>/stock/stockInfoList${num}?orderId=${orderId}&inbillId=${inbillId}&page=${page.firstPage}"
				onclick="changecolor(1)">首页</a> <a
				href="<%=request.getContextPath()%>/stock/stockInfoList${num}?orderId=${orderId}&inbillId=${inbillId}&page=${page.prePage}"
				onclick="changecolor(${page.pageNum}-1)">上一页</a> <c:forEach
					begin="1" end="${page.pages}" var="pageNumber">
					<a
						href="<%=request.getContextPath()%>/stock/stockInfoList${num}?orderId=${orderId}&inbillId=${inbillId}&page=${pageNumber}&size=5">
						<%--判断：主要是为了给当前页显示红色--%> <c:choose>
							<c:when test="${pageNumber==page.pageNum}">
								<span style="color: red">${pageNumber}</span>
							</c:when>
							<c:otherwise>${pageNumber}</c:otherwise>
						</c:choose>
					</a>
				</c:forEach> <a
				href="<%=request.getContextPath()%>/stock/stockInfoList${num}?orderId=${orderId}&inbillId=${inbillId}&page=${page.pageNum+1}"
				onclick="changecolor(${page.pageNum}+1)">下一页</a> <a
				href="<%=request.getContextPath()%>/stock/stockInfoList${num}?orderId=${orderId}&inbillId=${inbillId}&page=${page.lastPage}"
				onclick="changecolor(${page.pages})">尾页</a>
		</ul>
	</div>


	<!-- addStockInfo -->
	<div class="modal fade bs-example-modal-lg" id="addStockInfo"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myLargeModalLabel">新增订单</h4>
				</div>

				<div class="modal-body">

					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">入库单编号：</label>
						</div>
						<div class="col-md-6">
							<input id="addinbillId" type="text" class="input"
								name="addinbillId" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">订单编号：</label>
						</div>
						<div class="col-md-6">
							<input id="addorderId" type="text" class="input" name="name"
								value="" />
						</div>
						<div class="col-md-4" id="d2"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">生产编号：</label>
						</div>
						<div class="col-md-6">
							<input id="addproductionId" type="text" class="input"
								name="place" value="" />
						</div>
						<div class="col-md-4" id="d3"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">入库数量：</label>
						</div>
						<div class="col-md-6">
							<input id="addinbillNumber" type="text" class="input" name="num"
								value="" />
						</div>
						<div class="col-md-4" id="d4"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">经手人：</label>
						</div>
						<div class="col-md-6">
							<input id="addhandlerName" type="text" class="input"
								name="operator" value="" />
						</div>
						<div class="col-md-4" id="d4"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">备注：</label>
						</div>
						<div class="col-md-6">
							<input id="addremark" type="text" class="input" name="operator"
								value="" />
						</div>
						<div class="col-md-4" id="d4"></div>
					</div>
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="addbuton" class="btn btn-primary"
						onclick="addStockInfoMethod(${5*page.pageNum})" style="">保存</button>
				</div>

			</div>
		</div>
	</div>

	<!-- updatestockInfo -->
	<div class="modal fade bs-example-modal-lg" id="updatestockInfo"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog  modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">修改订单</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">入库单编号：</label>
						</div>
						<div class="col-md-6">
							<select id="updateinbillId" name="updateinbillId" class="input">
								<option name="updateid" value="-1" class="option">请选择</option>
								<c:forEach items="${stlist}" var="perm">
									<option name="updateid" id="per${perm.inbillId}"
										value="${perm.inbillId}" class="option">${perm.productionPlace}${perm.productName}${perm.inbillId}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">订单编号：</label>
						</div>
						<div class="col-md-6">
							<input id="updateorderId" type="text" class="input"
								name="updateorderId" value="" />
						</div>
						<div class="col-md-4" id="d2"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">生产编号：</label>
						</div>
						<div class="col-md-6">
							<input id="updateproductionId" type="text" class="input"
								name="updateproductionId" value="" />
						</div>
						<div class="col-md-4" id="d2"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">入库数量：</label>
						</div>
						<div class="col-md-6">
							<input id="updateinbillNumber" type="text" class="input"
								name="updateinbillNumber" value="" />
						</div>
						<div class="col-md-4" id="d2"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">经手人：</label>
						</div>
						<div class="col-md-6">
							<input id="updatehandlerName" type="text" class="input"
								name="updatehandlerName" value="" />
						</div>
						<div class="col-md-4" id="d2"></div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">备注：</label>
						</div>
						<div class="col-md-6">
							<input id="updateremark" type="text" class="input"
								name="updateremark" value="" />
						</div>
						<div class="col-md-4" id="d2"></div>
					</div>
				</div>

				<div class="modal-footer" style="heght: 50px">
					<a herf="<%=basePath%>customerController/manager/list"><button
							type="button" class="btn btn-default" data-dismiss="modal">关闭</button></a>
					<button type="button" class="btn btn-primary" id="updatebutton"
						onclick="pushData(${page.pageNum})">保存</button>
				</div>

			</div>
		</div>
	</div>



	<script type="text/javascript">
	//返回商品入库表
	function homeBack(){
		window.location.href="http://localhost:8080/ERP/stock/stockList"; 
	}
	$(document).on('click',"button[name='add']",function(){
		//设置新增的订单只能为当前入库单编号的订单
		$("[name=addinbillId]").attr("value",'${inbillId}')
		$("[name=addinbillId]").attr("readonly","readonly");
	})
	//新增订单	
	function addStockInfoMethod(){
			var inbillId=$("#addinbillId").val();
			var inbillNumber=$("#addinbillNumber").val();
			var handlerName=$("#addhandlerName").val();
			var orderId=$("#addorderId").val();
			var productionId=$("#addproductionId").val();
			var remark=$("#addremark").val();
			$.ajax({
				url:"http://localhost:8080/ERP/stock/insertStockInfo",
				type: "get",
				data : {inbillId:inbillId,
						inbillNumber:inbillNumber,
						handlerName:handlerName,
						orderId:orderId,
						productionId:productionId,
						remark:remark
				},
				success : function(data) {
					if(data>0){
						alert("新增成功");
						window.location.reload();
					
					}else{
						alert("新增失败")
					}
							},
						error : function() {
							alert("新增请求失败");
						}
					})
	         }
	
	    //删除订单
		function delMethod(orderId) {
			if (confirm("您确定要删除吗?")) {
				
				$.ajax({
					url:"http://localhost:8080/ERP/stock/deleteStockInfoById",
					type : "get",
					data : {
						orderId :orderId
					},
					success : function(data) {
						if (data>0) {
							alert("删除成功");
						} else {
							alert("删除失败！！！");
						}
						window.location.reload();
					},
					error : function() {
						alert("删除请求失败");
					}

				})
			}
		}
		//批量删除
		function delSelect() {
			var Checkbox = false;
			var orderId=[];
			var pageNum=0;
			//获取所有被选中的元素的value(),value值被设置为其permissionId
			$("input[name='del']").each(function(i) {
				if (this.checked == true) {
					Checkbox = true;
					orderId[i]=$(this).attr("id");
				}
			});
			if (Checkbox) {
				if( confirm("您确认要删除选中的内容吗？")){
						$.ajax({
							url:"http://localhost:8080/ERP/stock/deleteSomeStockInfo",
							type : "get",
							data : {
								orderId : orderId
							},
							success : function(data) {
								if (data>0) {
									alert("删除成功"); 
									window.location.reload();
										
								} else {
									alert("删除失败！！！");
								}
								
							},
							
							error : function() {
								alert("删除请求失败");
							}

						})
						
					}
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}
		
		//修改订单信息
		//使用ajax刷新后获取button点击事件要使用.on('click','#XXX',function(){})
		$(document).on('click',"button[name='update']",function(){
			 var orderId=$(this).parents("tr").find("#orderId").text();
			 var productionId=$(this).parents("tr").find("#productionId").text();
			 var inbillNumber=$(this).parents("tr").find("#inbillNumber").text();
			 var handlerName=$(this).parents("tr").find("#handlerName").text();
			 var remark=$(this).parents("tr").find("#remark").text();
			 var inbillId=$(this).parents("tr").find("input[name='del']").val();
			 
			 //将上面获取到自身的原本信息显示在修改框
			 $("#per"+inbillId).attr("selected",'selected');
			 $("[name=updateorderId]").val(orderId);
			 $("[name=updateproductionId]").val(productionId);
			 $("[name=updateinbillNumber]").val(inbillNumber);
			 $("[name=updatehandlerName]").val(handlerName);
			 $("[name=updateremark]").val(remark);
			 $("[name=updateorderId]").attr("readonly","readonly");
			 
		})
		function pushData(pageNum){
			 
			 //获取上面设置后显示的对应的值
			 var inbillId= $("#updateinbillId").val();
			 var orderId=$("#updateorderId").val();
			 var productionId= $("#updateproductionId").val();
			 var inbillNumber= $("#updateinbillNumber").val();
			 var handlerName=$("#updatehandlerName").val();
			 var remark=$("#updateremark").val();
				$.ajax({
					 url:"http://localhost:8080/ERP/stock/updateStockInfo",
					 type:"get",
					 data:{
						 inbillId:inbillId,
						 orderId:orderId,
						 productionId:productionId,
						 inbillNumber:inbillNumber,
						 handlerName:handlerName,
						 remark:remark
					 },
					 success:function(data){
						 if(data>0){
							 alert("修改成功");
							 window.location.reload();
						 
						 }else{
							 alert("修改失败");
						 }
						 
					 },
					 error:function(data){
						 alert("修改请求失败");
					 }
				 })
			
		}
		
		//全部选择
			$("#selectall").click( 
					
		        function(){ 
		        if(this.checked){ 
		        $("input[name='del']").each(function(){this.checked=true;}); 
		        }else{ 
		        $("input[name='del']").each(function(){this.checked=false;}); 
		        } 
		     })	
			
			 //修改和新增正则
/* 	$(function(){ */
			    $("input").change(function(){
				            //获取发生更改的input框的id
			    			var id=$(this).attr("id");
							var myDiv=$(this).parent().parent().children("div.col-md-4");
					 		myDiv2=$(this).parent().parent()
							if(id=="updateorderId"||id=="addorderId"){
								var a=$(this).val();
								var regex=/^[-|0-9|a-z|A-Z]{0,20}$/
								if(regex.test(a)==true){
								myDiv.html('<p class="icon-check" style="color:green">格式正确</p>')
								s();
						 		}else{
								myDiv.html('<p class="icon-exclamation" style="color:red"><span>请输入20位以内的订单号，只能使用字母，数字和"-"</span></p>')
					 	    	e();
							}
						}
					 		if(id=="updateproductionId"||id=="addproductionId"){
								var a=$(this).val();
								var regex=/^(\d{4}).((1[0-2])|(0?[1-9])).(([12][0-9])|(3[01])|(0?[1-9]))[a-z|A-Z]{2,4}$/
								if(regex.test(a)==true){
								myDiv.html('<p class="icon-check" style="color:green">格式正确</p>')
								s();
						 		}else{
								myDiv.html('<p class="icon-exclamation" style="color:red"><span>正确格式：yyyy.MM.dd+城市名首字母</span></p>')
					 	    	e();
							}
						}
					 		if(id=="updateinbillNumber"||id=="addinbillNumber"){
								 var a=$(this).val();
									var regex=/^[0-9]+$/
								if(regex.test(a)==true){
									myDiv.html('<p class="icon-check" style="color:green">格式正确</p>')
									s();
								 	}else{
									myDiv.html('<p class="icon-exclamation" style="color:red"><span>请输入数字</span></p>')
							 	    e();
								}
								 
							 }
					 		 if(id=="updateremark"||id=="updatehandlerName"||id=="addremark"||id=="addhandlerName"){
								 var a=$(this).val();
									var regex=/^[\u4e00-\u9fa5|a-z|A-Z]+$/
								if(regex.test(a)==true){
									myDiv.html('<p class="icon-check" style="color:green" >格式正确</p>')
									s();
								 	}else{
									myDiv.html('<p class="icon-exclamation" style="color:red"><span>只能输入字母或者汉字</span></p>')
							 	    e();
								}
								 
							 }
			            })
			      
			            function s(){
			    	        $('#addbuton').attr("disabled",false)
			    	        $("#updatebutton").attr("disabled",false)
						}
						function e(){
							$('#addbuton').attr("disabled",true)
							$("#updatebutton").attr("disabled",true)
						}

			    /*   }) */
	<%-- 	function changesearch() {

		}
		
		function updatecustomer(){
			var emId=$("#emIdup").val();
			var customername=$("#customernameup").val();
			var password=$("#passwordup").val();
			var birth=$("#birthdayup").val();
			var name=$("#nameup").val();
			var sex =$("#sexup").val();
			var tel	=$("#telup").val();
			var	address=$("#addressup").val();
			var education=$("#educationup").val();
			var	positionId=$("#positonIdup").val();
			var salary=	$("#salaryup").val();
			$.ajax({
				url:"<%=basePath%>customerController/manager/update",
				type: "post",
				data : {
					emId:emId,
					customername:customername,
					password:password,
					name:name,
					birth:birth,
					sex:sex,
					tel:tel,
					address:address,
					education:education,
					positionId:positionId,
					salary:salary
				},
				success : function(data) {
					var flag=JSON.parse(data)
					if(flag){
						window.location.href="<%=basePath%>customerController/manager/list"
							} else {
								alert("修改失败！！！");
							}
						},
						error : function() {
							alert("修改请求失败");
						}

					})
		
		} --%>
<%-- 		function showcustomer(obj){
			var emId=$(obj).attr('id');
			$.ajax({
				url:"<%=basePath%>customerController/manager/selectcustomer",
				type: "post",
				data : {emId:emId
				},
				success : function(data) 
				{
					var customer=eval(data);
					$("#emIdup").val(customer.emId);
					$("#customernameup").val(customer.customername);
					$("#passwordup").val(customer.password);
					var birthday=getMyDate(customer.birthday);
					$("#birthdayup").val(birthday);
					$("#nameup").val(customer.name);
					$("#sexup").val(customer.sex);
					$("#telup").val(customer.tel);
					$("#addressup").val(customer.address);
					$("#educationup").val(customer.education);
					$("#positonIdup").val(customer.positionId);
					$("#salaryup").val(customer.salary);
					$.ajax({
						url:"<%=basePath%>positionController/manager/selectAllPositions",
						type: "post",
						success : function(data) 
						{
							var positions=eval(data);
							var option="";
							for (var i = 0; i < positions.length; i++) {
								if(positions[i].positionId==customer.positionId){
								option+="<option value='"+positions[i].positionId+"'selected='selected'>"+positions[i].positionName+"</option>";
									
								}else{
								option+="<option value='"+positions[i].positionId+"'>"+positions[i].positionName+"</option>";
									
								}
							}$("#distributePosition").empty();
							$("#distributePosition").append(option);
						}
					})
				}
			})
		} --%>
		
	/* 	function getMyDate(str) {
		    var oDate = new Date(str),
		    oYear = oDate.getFullYear(),
		    oMonth = oDate.getMonth()+1,
		    oDay = oDate.getDate(),
		    oHour = oDate.getHours(),
		    oMin = oDate.getMinutes(),
		    oSen = oDate.getSeconds(),
		    oTime = oYear +"年"+ addZero(oMonth)+"月";
		    return oTime;
		}
		function addZero(num){
		    if(parseInt(num) < 10){
		        num = '0'+num;
		    }
		    return num;
		} */
		//添加用户的函数
	<%-- 	function addStockInfo(){
			var customername=$("#customername").val();
			var password=$("#password").val();
			var name=$("#name").val();
			$.ajax({
				url:"<%=basePath%>customerController/manager/add",
				type: "post",
				data : {customername:customername,password:password,name:name
				},
				success : function(data) {
					var flag=JSON.parse(data)
					if(flag){
						window.location.href="<%=basePath%>customerController/manager/list"
							} else {
								alert("保存失败！！！");
							}
						},
						error : function() {
							alert("新增请求失败");
						}

					})
		} --%>

		//单个删除
		<%-- function del(obj) {
			var emId=$(obj).prev().attr('id');
			if (confirm("您确定要删除吗?")) {
				$.ajax({
					url:"<%=basePath%>
		customerController/manager/dele",
					type : "post",
					data : {
						emId : emId
					},
					success : function(data) {
						var flag = JSON.parse(data)
						if (flag) {
							$("#" + emId).parent().parent().remove();
						} else {
							alert("删除失败！！！");
						}
					},
					error : function() {
						alert("删除请求失败");
					}

				})
			}
		} --%>

		//全选
		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})

		//批量删除
		function DelSelect() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false)
					return false;
				$("#listform").submit();
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}

		//批量排序
		function sorts() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");
				return false;
			}
		}

		//批量首页显示
		function changeishome(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量推荐
		function changeisvouch(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量置顶
		function changeistop(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量移动
		function changecate(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量复制
		function changecopy(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var i = 0;
				$("input[name='id[]']").each(function() {
					if (this.checked == true) {
						i++;
					}
				});
				if (i > 1) {
					alert("只能选择一条信息!");
					$(o).find("option:first").prop("selected", "selected");
				} else {

					$("#listform").submit();
				}
			} else {
				alert("请选择要复制的内容!");
				$(o).find("option:first").prop("selected", "selected");
				return false;
			}
		}
	</script>
</body>
</html>