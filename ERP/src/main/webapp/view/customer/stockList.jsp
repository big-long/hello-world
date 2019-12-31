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
<title>产品入库管理</title>
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
				<strong class="icon-reorder"> 入库管理</strong> 
				<a href="" style="float: right; display: none;">添加字段</a>
				<button  class="btn btn-link icon-home" onclick="homeBack()">主界面</button>
				<button class="btn btn-link icon-reply" onclick="history.back()">后退</button>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					<li><button type="button" name="add"
							class="button border-main icon-plus-square-o" data-toggle="modal"
							data-target="#addStock">添加商品</button></li>
					<li><button class="button border-red" onclick="delSelect()"><span class="icon-trash-o" ></span>批量删除
					</button></li>
					
					<!-- <a class="button border-main icon-plus-square-o"
						href="<%=basePath%>view/system/role/addRole.jsp"> 增加</a> -->
						<li><input type="text" placeholder="请输入产品名或生产商"  id="findPer" />
						<button onclick="findStock()" class="button border-blue" >
						<span class="icon-search">查商品</span></button></li>
					<li><li><input type="text" placeholder="请输入订单号"  id="findOrderId" />
						<button onclick="findStockInfo()" class="button border-blue" >
						<span class="icon-search">查订单</span></button></li>
					<li>
					<input type="checkbox" name="selectall" id="selectall"/><span>选择全部</span></li>
				</ul>
				
			</div>
			<table class="table table-hover text-center">
				<tr>
				    
					<td>请选择</td>
					<th>入库单编号</th>
					<th>产品名称</th>
					<th>生产商</th>
					<th>库存数量</th>
					<th>操作员</th>
					<th width="310">操作</th>
				</tr>
				<tbody name="list" id="vo"> 
				<c:forEach items="${stpage}" var="st" varStatus="vs">
					<tr>
						
						<td><input type="checkbox" name="del" value="${st.inbillId}" id="${page.pageNum}"/>${vs.count+5*(page.pageNum-1)}</td>
						<td id="inbillId" value="${st.inbillId}"><a href="javascript:void(0)" onclick="popup(${st.inbillId})">${st.inbillId}</a></td>
						<td id="productName" >${st.productName }</td>
						<td id="productionPlace">${st.productionPlace}</td>
						<td id="repertoryNumber">${st.repertoryNumber}</td>
						<td id="operator">${st.operator}</td>
						<td id="deleteandupdate" style="width: 20%"><div class="button-group">
								<button type="button" 
									class="button border-main icon-plus-square-o"
									data-toggle="modal" data-target="#updatestock"  name="update" >修改</button>

								<button class="button border-red"  onclick="delMethod('${st.inbillId}',${page.pageNum})">
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
			<li>共${page.pages}页,当前是第${page.pageNum}页<li><br>
			<a href="<%=request.getContextPath()%>/stock/stockList?name=${name}&page=${page.firstPage}" onclick="changecolor(1)">首页</a>
			<a href="<%=request.getContextPath()%>/stock/stockList?name=${name}&page=${page.prePage}" onclick="changecolor(${page.pageNum}-1)">上一页</a>
			<c:forEach begin="1" end="${page.pages}" var="pageNumber">
					<a href="<%=request.getContextPath()%>/stock/stockList?name=${name}&page=${pageNumber}&size=5">
				    <%--判断：主要是为了给当前页显示红色--%> <c:choose>
						<c:when test="${pageNumber==page.pageNum}">
							<span style="color: red">${pageNumber}</span>
							</c:when>
							<c:otherwise>${pageNumber}</c:otherwise>
						</c:choose>
					</a>
			</c:forEach>
			<a  href="<%=request.getContextPath()%>/stock/stockList?name=${name}&page=${page.pageNum+1}" onclick="changecolor(${page.pageNum}+1)">下一页</a>			
			<a href="<%=request.getContextPath()%>/stock/stockList?name=${name}&page=${page.lastPage}" onclick="changecolor(${page.pages})">尾页</a>
	         </ul> 
	      </div>
	

	<!-- addStock -->
	<div class="modal fade bs-example-modal-lg" id="addStock"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myLargeModalLabel">新增商品</h4>
				</div>

				<div class="modal-body">

					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">入库单编号：</label>
						</div>
						<div class="col-md-6">
							<input id="addinbillId" type="text" class="input" name="inbillId" value="" />
						</div>
						<div class="col-md-4" id="d1">
			  				
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品名称：</label>
						</div>
						<div class="col-md-6">
							<input id="addname" type="text"  class="input" name="name" value="" />
						</div>
						<div class="col-md-4" id="d2">
			  				
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">生产商：</label>
						</div>
						<div class="col-md-6">
							<input id="addplace" type="text" class="input" name="place" value="" />
						</div>
						<div class="col-md-4" id="d3">
			  
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">库存数量：</label>
						</div>
						<div class="col-md-6">
							<input id="addnum" type="text" class="input" name="num" value="" />
						</div>
						<div class="col-md-4" id="d4">
			  
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">操作员：</label>
						</div>
						<div class="col-md-6">
							<input id="addoperator" type="text" class="input" name="operator" value="" />
						</div>
						<div class="col-md-4" id="d4">
			  
						</div>
					</div>
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="addbuton" class="btn btn-primary" onclick="addStockMethod(${5*page.pageNum})" style="pointer-events：auto;">保存</button>
				</div>

			</div>
		</div>
	</div>

	<!-- updateStock -->
	 <div class="modal fade bs-example-modal-lg" id="updatestock"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog  modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="modal-title" id="myModalLabel">修改商品</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">入库单编号：</label>
						</div>
						<div class="col-md-6">
							<input id="updateinbillId" readonly="readonly"  type="text" class="input" name="updateinbillId" value="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">产品名称：</label>
						</div>
						<div class="col-md-6">
							<input id="updateproductName" type="text" class="input" name="updateproductName" value="" />
						</div>
						<div class="col-md-4" id="d2">
			  				
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">生产商：</label>
						</div>
						<div class="col-md-6">
							<input id="updateproductionPlace" type="text" class="input" name="updateproductionPlace" value="" />
						</div>
						<div class="col-md-4" id="d2">
			  				
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">库存数量：</label>
						</div>
						<div class="col-md-6">
							<input id="updaterepertoryNumber" type="text" class="input" name="updaterepertoryNumber" value="" />
						</div>
						<div class="col-md-4" id="d2">
			  				
						</div>
					</div>
					<div class="row">
						<div class="col-md-2" style="text-align: right">
							<label style="margin-top: 10px">操作员：</label>
						</div>
						<div class="col-md-6">
							<input id="updateoperator" type="text" class="input" name="updateoperator" value="" />
						</div>
						<div class="col-md-4" id="d2">
			  				
						</div>
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
	function popup(inbillId){
		window.location.href="http://localhost:8080/ERP/stock/stockInfoList1?inbillId="+inbillId;      
	}
	function homeBack(){
		window.location.href="http://localhost:8080/ERP/stock/stockList"; 
	}
	//订单号查询
	function findStockInfo(){
		var orderId=$("#findOrderId").val();
		if(orderId!=""){
			alert("查询成功")
			window.location.href="http://localhost:8080/ERP/stock/stockInfoList2?orderId="+orderId;
		}
		
	}
	//商品模糊查询
	function findStock(){
		var name=$("#findPer").val();
		if(name!=""){
				alert("查询成功");
				window.location.href="http://localhost:8080/ERP/stock/stockList?name="+name;
						
		}
	}
	//新增商品	
	function addStockMethod(page){
			var id=$("#addinbillId").val();
			var name=$("#addname").val();
			var place=$("#addplace").val();
			var num=$("#addnum").val();
			var operator=$("#addoperator").val();
			$.ajax({
				url:"http://localhost:8080/ERP/stock/insertStock",
				type: "post",
				data : {inbillId:id,
						productName:name,
						productionPlace:place,
						repertoryNumber:num,
						operator:operator
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
	
	    //删除单个
		function delMethod(inbillId,pageNum) {
			if (confirm("您确定要删除吗?")) {
				
				$.ajax({
					url:"http://localhost:8080/ERP/stock/deleteStockById",
					type : "get",
					data : {
						inbillId :inbillId
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
		}
		//批量删除
		function delSelect() {
			var Checkbox = false;
			var inbillId=[];
			var pageNum=0;
			//获取所有被选中的元素的value(),value值被设置为其permissionId
			$("input[name='del']").each(function(i) {
				if (this.checked == true) {
					Checkbox = true;
					inbillId[i]=$(this).val();
			        pageNum=Math.ceil($(this).attr("id"));
				}
			});
			if (Checkbox) {
				if( confirm("您确认要删除选中的内容吗？")){
						$.ajax({
							url:"http://localhost:8080/ERP/stock/deleteSome",
							type : "get",
							data : {
								inbillId : inbillId
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
		
		//修改商品信息
		//使用ajax刷新后获取button点击事件要使用.on('click','#XXX',function(){})
		$(document).on('click',"button[name='update']",function(){
			 var inbillId=$(this).parents("tr").find("#inbillId").text();
			 var name=$(this).parents("tr").find("#productName").text();
			 var place=$(this).parents("tr").find("#productionPlace").text();
			 var num=$(this).parents("tr").find("#repertoryNumber").text();
			 var operator=$(this).parents("tr").find("#operator").text();
			
			 //将上面获取到自身的原本信息显示在修改框
			 $("[name=updateinbillId]").val(inbillId);
			 $("[name=updateproductName]").val(name);
			 $("[name=updateproductionPlace]").val(place);
			 $("[name=updaterepertoryNumber]").val(num);
			 $("[name=updateoperator]").val(operator);
			 
		})
		function pushData(pageNum){
			 //获取上面设置的pId属性的值
			 var perId=$("[name=updateid]").attr("pId");
			 
			 //获取上面设置后显示的对应的值
			 var inbillId= $("#updateinbillId").val();
			 var name=$("#updateproductName").val();
			 var place= $("#updateproductionPlace").val();
			 var num= $("#updaterepertoryNumber").val();
			 var operator=$("#updateoperator").val();
				$.ajax({
					 url:"http://localhost:8080/ERP/stock/updateStock",
					 type:"post",
					 data:{
						 inbillId:inbillId,
						 productName:name,
						 productionPlace:place,
						 repertoryNumber:num,
						 operator:operator
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
			/* $(function(){ */
			    $("input").change(function(){
			    			
				            //获取发生更改的input框的id
							var id=$(this).attr("id");
							var myDiv=$(this).parent().parent().children("div.col-md-4");
							 myDiv2=$(this).parent().parent()
							if(id=="addinbillId"||id=="updatename"){
								var a=$(this).val();
									var regex=/^[-|0-9|a-z|A-Z]{0,20}$/
								if(regex.test(a)==true){
									myDiv.html('<p class="icon-check" style="color:green">格式正确</p>')
									s();
								 	}else{
									myDiv.html('<p class="icon-exclamation" style="color:red"><span>请输入20位以内的订单号，可以使用字母，数字和"-"</span></p>')
							 	    e();
								}
							}
							 if(id=="addname"||id=="updateproductName"||id=="addplace"||id=="updateproductionPlace"||id=="updateoperator"||id=="addoperator"){
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
							 if(id=="addnum"||id=="updaterepertoryNumber"){
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
			            })
			      
			            function s(){
			    	        $('#addbuton').attr("disabled",false)
			    	        $('#updatebutton').attr("disabled",false)
						}
						function e(){
							$('#addbuton').attr("disabled",true)
							$('#updatebutton').attr("disabled",true)
						}

			   /*    }) */
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
	<%-- 	function addStock(){
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