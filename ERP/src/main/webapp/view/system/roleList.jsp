<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
		<link rel="stylesheet" href="<%=basePath%>static/css/pintuer.css">
		<link rel="stylesheet" href="<%=basePath%>static/css/admin.css">
		<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=basePath%>static/css/zTreeStyle/demo.css" type="text/css">
		<link rel="stylesheet" href="<%=basePath%>static/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script src="<%=basePath%>static/js/jquery-3.4.1.js"></script>
		<script src="<%=basePath%>static/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/js/jquery.ztree.core.js"></script>
		<script src="<%=basePath%>static/js/jquery.ztree.excheck.js"></script>
		<script src="<%=basePath%>static/js/popper.main.js"></script>
		<script src="<%=basePath%>static/js/pintuer.js"></script>
	</head>
	<body>
		<form method="post" action="" id="listform">
			<div class="panel admin-panel">
				<div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
				<div class="padding border-bottom">
					<ul class="search" style="padding-left:10px;">
						<li> 
							<a class="button border-main icon-plus-square" href="" data-toggle="modal" data-target="#myModal" id="insertBtn" onclick="showAddBtn()"> 增加</a> 
							<a class="button border-red icon-trash-o" href="javascript:void(0)" onclick="DelSelect()"> 批量删除</a>
						</li>
					</ul>
				</div>
				<table class="table table-hover text-center" id="roleTable">
					<tr>
						<th width="100" style="text-align:left; padding-left:20px;"><input type="checkbox"  id="checkAll" />ID</th>
						<th>角色名称</th>
						<th>角色代码</th>
						<th width="10%">更新时间</th>
						<th width="310">操作</th>
					</tr>
					<tbody id="roleList">
						<!-- 表格内容 -->
					</tbody>
						<tr>
							<td style="text-align:left; padding:19px 0;padding-left:20px;"><input type="checkbox" id="reverseSelect" />
								反选 </td>
							<!-- <td  style="text-align:left;padding-left:20px;">
								<a href="javascript:void(0)" class="button border-red icon-trash-o"style="padding:5px 15px;" onclick="DelSelect()"> 删除</a> 
							</td> -->
							<td colspan="4">
								<div class="line" style="float:left;" ><!--  -->
						            <div class="xs6 xm4 xb3"> 
										<!-- <input type="text" class="input" placeholder="跳转到" style="width:40%"/> -->
										<input type="text" class="input border-green" id="goPageNum" placeholder="跳转到" style="width: 92px;" data-validate="required:必填">
						           	 	<input type="text" style="display:none" /><!--此输入框用于阻止form表单按enter键的默认提交-->
						           	 </div>
						            <div class="xs6 xm4 xb3">  
										<div class="button-group">
											<button type="button" class="button border-mix dropdown-toggle" style="width: 160px;margin-left: 30px;height: 42px;">
												<span class="icon-paper-plane"></span>选择每页显示条数 <span class="downward "></span>
											</button>
											<ul class="drop-menu" id="pageSizeList" style="width: 160px;margin-left: 30px;">
												<li style="list-style:none; font-size:14px"><a href="javascript:void(0)" onclick="switchPages(this)">5</a> </li>
												<li role="separator" class="divider"></li>
												<li style="list-style:none; font-size:14px"><a href="javascript:void(0)" onclick="switchPages(this)">10</a> </li>
												<li role="separator" class="divider"></li>
												<li style="list-style:none; font-size:14px"><a href="javascript:void(0)" onclick="switchPages(this)">20</a> </li>
												<li role="separator" class="divider"></li>
												<li style="list-style:none; font-size:14px"><a href="javascript:void(0)" onclick="switchPages(this)">30</a> </li>
											</ul>
										</div>
						            </div>
						         </div> 
							</td>
						</tr>
				</table>
		</div>
		</form>
		
		<!-- 添加角色模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close"  data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">
							添加角色
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
						<input type="hidden" value="" id="roleId">
							
							<div class="form-group">
								<label for="name" class="col-lg-2 control-label">角色名称</label>
								<div class="has-feedback col-lg-10"><!-- has-success  icon-check-->
									<input type="text" class="form-control" id="name" placeholder="角色名称">
									<span class=" form-control-feedback" aria-hidden="true" style="  margin-top: 9px;"></span>
									<div class="alert alert-red hide" id="alertLable">
										<span class="close rotate-hover"></span>
										<span><strong>注意：</strong>角色名称内容不能为空且不能和其他相同。</span>
									</div>
								</div>
							</div>
							
							
							
							<div class="form-group">
								<label for="roleName" class="col-lg-2 control-label">角色代码</label>
								<div class="col-lg-10 has-feedback ">
									<input type="text" class="form-control" id="roleName" placeholder="角色代码">
									<span class=" form-control-feedback" aria-hidden="true" style="  margin-top: 9px;"></span>
									<div class="alert alert-red hide" id="alertLable2">
										<span class="close rotate-hover"></span>
										<span><strong>注意：</strong>角色代码内容不能为空且不能和其他相同。</span>
									</div>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="rolePermisson" class="col-lg-2 control-label">角色权限</label>
								<div class="col-lg-10">
									<div class="content_wrap">
										<div class="zTreeDemoBackground left">
											<ul id="treeDemo" class="ztree"></ul>
										</div>
									</div>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							关闭
						</button>
						<button type="button" class="btn btn-primary hide" onclick="updateRole() " id="updateBtn">
							修改
						</button>
						<button type="button" class="btn btn-primary hide" onclick="addRole()" id="addBtn">
							提交
						</button>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
		
		
	<script type="text/javascript">
		/*第一次加载所以的角色列表  */
		function getRolesTable(pageNum,pageSize){
			//加载所有角色信息
			$.ajax({
				url:"<%=basePath%>system/roleList",
				type : "POST",
				data:{"pageNum":pageNum,"pageSize":pageSize},
				success : function(data) {
					var HTML="";
					$.each(data.list,function(i,val){
						HTML+="<tr id='tr"+val.roleId+"'><td style='text-align:left; padding-left:20px;' width='10%'><input type='checkbox' name='roleId' value='"+val.roleId+"' />"+val.roleId+"</td>";
						HTML+="<td width='20%'>"+val.name+"</td> ";
						HTML+="<td width='20%'>"+val.roleName+"</td>";
						HTML+="<td width='20%'>"+val.createTime+"</td>";
						HTML+="<td width='20%'><div class='button-group'> <a class='button border-main' href='#' onclick='updateRoleUI("+val.roleId+")'data-toggle='modal' data-target='#myModal' ><span class='icon-edit'></span> 修改</a>";
						HTML+="<a class='button border-red' href='javascript:void(0)' onclick='return del("+val.roleId+","+data.pageNum+")'><span class='icon-trash-o'></span>删除</a> </div></td> </tr>";
					});
					HTML+="<tr><td colspan='8'><div class='pagelist'>"
					HTML+="<a href='javascript:void(0)' onclick='getRolesTable("+data.firstPage+","+data.pageSize+")' >首页</a>";
					if (data.isFirstPage==true) {
						HTML+="<a href='javascript:void(0)' onclick='return false' class='button bg-gray' >上一页</a>";
					}else{
						HTML+="<a href='javascript:void(0)' onclick='getRolesTable("+data.prePage+","+data.pageSize+")' >上一页</a>";
					}
					$.each(data.navigatepageNums,function(i,pageNums){
						
						if((i+1)==data.pageNum){
							HTML+="<a href='javascript:void(0)' class='btn-info' onclick='getRolesTable("+pageNums+","+data.pageSize+")'>"+pageNums+"</a>";
						}else{
							HTML+="<a href='javascript:void(0)' onclick='getRolesTable("+pageNums+","+data.pageSize+")'>"+pageNums+"</a>";
						}
					});
					if (data.isLastPage==true) {
						HTML+="<a href='javascript:void(0)' onclick='return false' role='button' data-toggle='popover' class='button bg-gray' data-animation='true' data-content='到最后一页啦'>下一页</a>";
					}else{
						HTML+="<a href='javascript:void(0)' onclick='getRolesTable("+data.nextPage+","+data.pageSize+")'>下一页</a>";
					}
					HTML+="<a href='javascript:void(0)' onclick='getRolesTable("+data.lastPage+","+data.pageSize+")'>尾页</a>";
					HTML+="</div></td></tr>";	
					$("#roleList ").html(HTML);
				}
			});
		}
		
		
		
		/**ztree相关方法*/
		function nocheckNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nocheck = e.data.nocheck,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
		
			for (var i=0, l=nodes.length; i<l; i++) {
				nodes[i].nocheck = nocheck;
				zTree.updateNode(nodes[i]);
			}
		}
		/**得到被选中的id数组*/
		function getCheckedNode(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			nodes=treeObj.getCheckedNodes(true);
			/* 声明一个数组，用来存储选中的结点id */
			var nodeId=new Array();
		    for(var i=0;i<nodes.length;i++){
		    	/**往数组中存入id*/
                nodeId[i]=nodes[i].id;
            }
		    return nodeId;
		}
		/* 展示添加按钮 */
		function showAddBtn(){
			$("#myModal").on("shown.bs.modal",function(e){
				/* $("#myModalLabel").text(""); */
				$("#myModalLabel").text("角色添加");
				$("#updateBtn").addClass("hide"); 
				$("#addBtn").removeClass("hide");
			});
		}
		
		//验证模态框输入和修改的数据合理性
		//1..验证数据的合理性,不能加入相同的角色名和角色代码,给输入框增加失去焦点事件
			$("#name").bind("blur",function(){
				var text=$("#myModalLabel").text().trim();
				//如果是角色添加
				if (text==$.trim("角色添加")) {
					//取出输入框中输入的值
					var name=$("#name").val();
					//发送ajax请求
					if(name!=""){
					$.ajax({
						url:"<%=basePath%>system/hasRoleByName",
						type:"GET",
						data:{
							"name":name
						},
						success:function(data){
							if(data==true){
								$("#name").parent().removeClass("has-error");
								$("#name").parent().addClass("has-success");
								$("#name+span").removeClass("icon-times");
								$("#name+span").addClass("icon-check");
								$("#alertLable").removeClass("show");
								$("#alertLable").addClass("hide");
								
							}else{
								$("#name").parent().removeClass("has-success");
								$("#name").parent().addClass("has-error");
								$("#name+span").removeClass("icon-check");
								$("#name+span").addClass("icon-times");
								$("#alertLable").removeClass("hide");
								$("#alertLable").addClass("show");
							}
						},
						err:function(data){
							alert("请求失败，稍后再试");
						}
						
					});
						
				}else{
					$("#name").parent().removeClass("has-success");
					$("#name").parent().addClass("has-error");
					$("#name+span").removeClass("icon-check");
					$("#name+span").addClass("icon-times");
					$("#alertLable").removeClass("hide");
					$("#alertLable").addClass("show");
				}
					
				} 
				if(text==$.trim("角色修改")) {
					//取出输入框中输入的值
					var name=$("#name").val();
					var roleId=$("#roleId").val();
					if (name!="") {
					//发送ajax请求
					$.ajax({
						url:"<%=basePath%>system/verifyName",
						type:"GET",
						data:{
							"roleId":roleId,
							"name":name
						},
						success:function(data){
							if(data==true&&name!=""){
								$("#name").parent().removeClass("has-error");
								$("#name").parent().addClass("has-success");
								$("#name+span").removeClass("icon-times");
								$("#name+span").addClass("icon-check");
								$("#alertLable").removeClass("show");
								$("#alertLable").addClass("hide");
							}else{
								$("#name").parent().removeClass("has-success");
								$("#name").parent().addClass("has-error");
								$("#name+span").removeClass("icon-check");
								$("#name+span").addClass("icon-times");
								$("#alertLable").removeClass("hide");
								$("#alertLable").addClass("show");
							}
						},
						err:function(data){
							alert("请求失败，稍后再试");
						}
					});
				}else{
					$("#name").parent().removeClass("has-success");
					$("#name").parent().addClass("has-error");
					$("#name+span").removeClass("icon-check");
					$("#name+span").addClass("icon-times");
					$("#alertLable").removeClass("hide");
					$("#alertLable").addClass("show");
				}
				}
			});
		
		/* 2..验证输入角色代码的合理性,不能输入相同角色代码 */
		$("#roleName").bind("blur",function(){
			var text=$("#myModalLabel").text().trim();
			//如果是角色添加
			if (text==$.trim("角色添加")) {
				//取出角色代码输入框中输入的值
				var roleName=$("#roleName").val();
				if (roleName!="") {
					//发送ajax请求
					$.ajax({
						url:"<%=basePath%>system/hasRoleByRoleName",
						type:"GET",
						data:{
							"roleName":roleName
						},
						success:function(data){
							if(data==true&&roleName !=""){
								$("#roleName").parent().removeClass("has-error");
								$("#roleName").parent().addClass("has-success");
								$("#roleName+span").removeClass("icon-times");
								$("#roleName+span").addClass("icon-check");
								$("#alertLable2").removeClass("show");
								$("#alertLable2").addClass("hide");
								
							}else{
								$("#roleName").parent().removeClass("has-success");
								$("#roleName").parent().addClass("has-error");
								$("#roleName+span").removeClass("icon-check");
								$("#roleName+span").addClass("icon-times");
								$("#alertLable2").removeClass("hide");
								$("#alertLable2").addClass("show");
							}
						},
						err:function(data){
							alert("请求失败，稍后再试");
						}
						
					});
				}else{
					$("#roleName").parent().removeClass("has-success");
					$("#roleName").parent().addClass("has-error");
					$("#roleName+span").removeClass("icon-check");
					$("#roleName+span").addClass("icon-times");
					$("#alertLable2").removeClass("hide");
					$("#alertLable2").addClass("show");
				}
				
			}
			//如果是角色修改
			if (text==$.trim("角色修改")) {
				//取出输入框中输入的值
				var roleName=$("#roleName").val();
				var roleId=$("#roleId").val();
				if (roleName!="") {
				//发送ajax请求
				$.ajax({
					url:"<%=basePath%>system/verifyRoleName",
					type:"GET",
					data:{
						"roleId":roleId,
						"roleName":roleName
					},
					success:function(data){
						if(data==true&&roleName!=""){
							$("#roleName").parent().removeClass("has-error");
							$("#roleName").parent().addClass("has-success");
							$("#roleName+span").removeClass("icon-times");
							$("#roleName+span").addClass("icon-check");
							$("#alertLable2").removeClass("show");
							$("#alertLable2").addClass("hide");
						}else{
							$("#roleName").parent().removeClass("has-success");
							$("#roleName").parent().addClass("has-error");
							$("#roleName+span").removeClass("icon-check");
							$("#roleName+span").addClass("icon-times");
							$("#alertLable2").removeClass("hide");
							$("#alertLable2").addClass("show");
						}
					},
					err:function(data){
						alert("请求失败，稍后再试");
					}
				});
			}else{
				$("#roleName").parent().removeClass("has-success");
				$("#roleName").parent().addClass("has-error");
				$("#roleName+span").removeClass("icon-check");
				$("#roleName+span").addClass("icon-times");
				$("#alertLable2").removeClass("hide");
				$("#alertLable2").addClass("show");
				}
			}
		});
		
		//当表单都正确时，才显示添加按钮，添加点击属性
		$("#addBtn").mousemove(function(){
			var hasName=$("#name").parent().hasClass("has-success");
			var hasRoleName=$("#roleName").parent().hasClass("has-success");
			if(hasName==true&&hasRoleName==true){
				$("#addBtn").removeClass("disabled");
				$("#addBtn").attr("onclick","addRole()");
			}
		});
		//当表单都正确时，才显示修改按钮，添加点击属性
		$("#updateBtn").mousemove(function(){
			var hasName=$("#name").parent().hasClass("has-success");
			var hasRoleName=$("#roleName").parent().hasClass("has-success");
			if(hasName==true&&hasRoleName==true){
				$("#updateBtn").removeClass("disabled");
				$("#updateBtn").attr("onclick","updateRole()");
			}
		});
		
		
		
		/**发送ajax请求实现提交插入角色*/
		function addRole(){
			var name =$("#name").val();
			var roleName =$("#roleName").val();
			if (name!=""&&roleName!="") {
				//得到选中权限的id
				var nodeIdArray=getCheckedNode();
				//发送ajax请求
				$.ajax({
					url:"<%=basePath%>system/roleAdd",
					type:"POST",
					traditional : true,
					data:{
						"roleName":roleName,
						"name":name,
						"rolePermissionArry":nodeIdArray
					},
					dataType:"text",
					success:function(data){
						if(data=="1"){
							alert("添加成功！");
							window.location.reload();
						}else{
							alert("添加失败！")
							window.location.reload();
						}
					},
					err:function(data){
						alert("请求失败，稍后再试");
					}
				});
			}else{
				alert("角色名称或角色代码不能为空！")
			}
			
			
			
		}
		
		
		/* ztree的设置 */
		var setting = {
				check: {
					enable: true,
					nocheckInherit: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
				
			};
		/* 用来接收数据 */
		var zNodes;
		
		/**得到所有的权限*/
		function getAllPermissions(){
			$.ajax({
				url:"<%=basePath%>system/getPermissionTree",
				type:"GET",
				dataType:"json",
				success:function(data){
					/* alert(data); */
					 zNodes=data;
				 	 $.fn.zTree.init($("#treeDemo"), setting, zNodes);
					 $("#nocheckTrue").bind("click", {nocheck: true}, nocheckNode);
					 $("#nocheckFalse").bind("click", {nocheck: false}, nocheckNode);
				}
			});
		}
			/* ajax请求 */
			/*页面加载事件*/
			$(document).ready(function(){
				/*记载角色表格，初始化  */
				 getRolesTable(1,3);
				/*将所有权限渲染到添加角色权限的ztree中*/
				getAllPermissions();
				
				//解决模态框数据会缓存的问题，关闭后清除数据,并初始化ztree
				$("#myModal").on("show.bs.modal",function(e){
					//置空输入框
					$("input[type='text']").val("");
					//初始化ztree
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					/* 模态框调用show时触发一个事件，移出上一次的状态 */
					$("#name").parent().removeClass("has-success");
					$("#name").parent().removeClass("has-error");
					$("#name+span").removeClass("icon-check");
					$("#name+span").removeClass("icon-times");
					
					$("#roleName").parent().removeClass("has-success");
					$("#roleName").parent().removeClass("has-error");
					$("#roleName+span").removeClass("icon-check");
					$("#roleName+span").removeClass("icon-times");
					
					$("#alertLable").removeClass("show");
					$("#alertLable").addClass("hide");
					$("#alertLable2").removeClass("show");
					$("#alertLable2").addClass("hide");
					
					$("#addBtn").removeAttr("onclick");
					$("#addBtn").addClass("disabled");
					$("#updateBtn").removeAttr("onclick");
					$("#updateBtn").addClass("disabled");
					}
				);
				
			});
			
			
			//通过填入页码，指定页面显示条数来跳转到指定页面
			function switchPages(obj){
				var num=$("#goPageNum").val();
				var pageNum=0;
				var pageSize=$(obj).text();
				if(num==""){
					pageNum=1;
				}
				pageNum=num;
				getRolesTable(pageNum,pageSize);
				//当页面跳转后，将按钮下的列表隐藏
				$("#pageSizeList").removeClass("show");
				$("#pageSizeList").addClass("hide");
			}
			
			//当按钮获得焦点后将按钮下的列表显示出来
			 $("button[class='button border-mix dropdown-toggle']").focus(function(){
				$("#pageSizeList").removeClass("hide");
				$("#pageSizeList").addClass("show");
			}); 
			
		//当跳转页面的输入框中按了enter键时
		$("#goPageNum").keypress(function(event){
			if(event.keyCode =="13"){
			var num=$("#goPageNum").val();
			var pageNum=0;
			if(num==""){
				pageNum=1;
			}
			pageNum=num;
			getRolesTable(pageNum);
			}
		}); 
			
			
			
			
			//搜索
			function changesearch() {

			}

			//单个删除
			function del(roleId,pageNum) {
				if (confirm("数据尤珍贵，且删且珍惜?")) {
					$.ajax({
						url:"<%=basePath%>system/roleDelete",
						data:{"roleId":roleId},
						type:"GET",
						dataType:"text",
						success:function(data){
							if(data=="1"){
								alert("删除成功！");
								/* 将删除的数据从表格中移除 */
								$("#tr"+roleId).remove();
								/*跳到指定页码,再次查询当前页码*/
								getRolesTable(pageNum);
								
							}else{
								alert("删除失败！");
							}
						},
						err:function(data){
							alert("请求失败！请稍后再试");
						}
					});
				}
			}
			//在跳转到修改角色页面之前，需要先加载角色信息，角色权限。
			function updateRoleUI(roleId){
				$("#myModal").on("shown.bs.modal",function(e){
					$("#addBtn").addClass("hide");
					$("#updateBtn").removeClass("hide");
					$("#myModalLabel").text("角色修改");
				});
				//将id放入隐藏域
				$("#roleId").val(roleId);
				$("#updateBtn").removeClass("hide")
				$.ajax({
					url:"<%=basePath%>system/getRoleByRoleId",
					type:"GET",
					data:{"roleId":roleId},
					dataType:"json",
					success:function(data){
						$("#name").val(data.name);
						$("#roleName").val(data.roleName);
						//执行查询角色权限的方法,并渲染回显到ztree上
						getPermissionsByRoleId(roleId);
					},
					err:function(data){
						alert("请求失败，稍后再试");
					}
				});
			}
			
			//通过角色id查询角色的权限
			function getPermissionsByRoleId(roleId){
				$.ajax({
					url:"<%=basePath%>system/rolePermissionTree",
					type:"GET",
					data:{"roleId":roleId},
					dataType:"json",
					success:function(data){
						/* 将原先分配的权限，回显到ztree中 */
						 // 初始化
            			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
						 // 展开全部节点
			            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			            treeObj.expandAll(true);
			            		for(var i = 0; i < data.length; i++) {
			                // 回显选中的数据　　　
			              treeObj.checkNode(treeObj.getNodeByParam("id", data[i].id), true);
			             }
					},
					err:function(data){
						alert("请求失败，稍后再试");
					}
				});
			}
			//实现提交角色修改的数据
			function updateRole(){
				var roleId=$("#roleId").val();
				var name=$("#name").val();
				var roleName=$("#roleName").val();
				if (name!=""&&roleName!="") {
					var permissionIdArry=getCheckedNode();
					$.ajax({
						url:"<%=basePath%>system/roleEidt",
						type:"POST",
						traditional : true,
						data:{"roleId":roleId,"name":name,"roleName":roleName,"rolePermissinArry":permissionIdArry},
						dataType:"text",
						success:function(data){
							if(data=="1"){
								alert("修改成功！");
								window.location.reload();
							}else{
								alert("修改失败！");
								window.location.reload();
							}
						},
						err:function(data){
							alert("请求失败，稍后再试");
						}
					});
				}else{
					alert("修改内容不能为空！")
				}
			}
			
			
			
			
			//全选
			$("#checkAll").click(function() {
				var flag=$("#checkAll").prop("checked");
				$("input[name='roleId']").each(function(i,e) {
					if (flag) {
						$(e).prop("checked",true);
					} else {
						$(e).prop("checked",false);
					}
				});
			})
			
			//反选
			$("#reverseSelect").click(function() {
				$("input[name='roleId']").each(function(i,e) {
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
				
				$("input[name='roleId']").each(function() {
					if (this.checked == true) {
						Checkbox = true;
					}
				});
				if (Checkbox) {
					var t = confirm("您确认要删除选中的内容吗？");
					if (t == false){ 
						return false;
					}
					//发送ajax请求，批量删除
					//取得选中的id
					//定义一个数组，用于接收选中行的id
					var roleIdArray=new Array();
					
					$("input[name='roleId']").each(function(){
						if (this.checked) {
							roleIdArray.push($(this).val());
						} 
					});
					//发送ajax请求
					$.ajax({
					url:"<%=basePath%>system/roleDeleBatch",
					type:"POST",
					traditional : true,
					data:{"roleArry":roleIdArray},
					dataType:"text",
					success:function(data){
						if(data=="1"){
							alert("删除成功！");
							$("input[name='roleId']").each(function(){
								if (this.checked) {
									$(this).parent().parent().remove();
								} 
							});
						}else{
							alert("删除失败！");
						} 
					},
					err:function(data){
						alert("请求失败，稍后再试");
					}
					});
					/* $("#listform").submit(); */
				} else {
					alert("请选择您要删除的内容!");
					return false;
				}
			}

			//批量排序
			function sorts() {
				var Checkbox = false;
				$("input[name='roleId']").each(function() {
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
				$("input[name='roleId']").each(function() {
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
				$("input[name='roleId']").each(function() {
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
				$("input[name='roleId']").each(function() {
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
				$("input[name='roleId']").each(function() {
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
				$("input[name='roleId']").each(function() {
					if (this.checked == true) {
						Checkbox = true;
					}
				});
				if (Checkbox) {
					var i = 0;
					$("input[name='roleId']").each(function() {
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