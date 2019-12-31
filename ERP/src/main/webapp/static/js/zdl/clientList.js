function changeSearch(){
	var keyWord =$("[name=keyword]").val();
	$.ajax({
		url:"http://localhost:8080/ERP/client/getClientByKeyWord",
		type:"get",
		data:{
			keyWord:keyWord
		},
		success:function(result){
			if(result.status==200){
				loadPage(result.object);
			}else{
				alert(result.message);
			}
		},
		error:function(result){
			alert(result.message);
		}
	})
}
//修改用户模态框准备数据
function update(obj) {
	var clientId = $(obj).parents("tr").find("#clientId").text();
	var clientForShort = $(obj).parents("tr").find("#clientForShort").text();
	var phone = $(obj).parents("tr").find("#phone").text();
	var linkman = $(obj).parents("tr").find("#linkman").text();
	var email = $(obj).parents("tr").find("#email").text();
	var depositBank = $(obj).parents("tr").find("#depositBank").text();
	$.ajax({
		url : "http://localhost:8080/ERP/client/getClientInfo",
		type : "get",
		data : {
			clientId : clientId
		},
		success : function(result) {
			if(result.status==200){
				var client=result.object;
				$("h4").text("修改客户资料");
				$("[name=clientId]").val(clientId);
				$("[name=clientName]").val(client.clientName);
				$("[name=clientForShort]").val(clientForShort);
				$("[name=address]").val(client.address);
				$("[name=postalcode]").val(client.postalcode);
				$("[name=phone]").val(phone);
				$("[name=facsimile]").val(client.facsimile);
				$("[name=linkman]").val(linkman);
				$("[name=linkmanPhone]").val(client.linkmanPhone);
				$("[name=email]").val(email);
				$("[name=depositBank]").val(depositBank);
				$("[name=account]").val(client.account);
			}else{
				alert(result.message);
			}
			
			
		},
		error : function() {
			alert(result.message);
		}

	})
	/*$("h4").text("修改客户信息");*/
	

}
// 加载客户数据与分页信息
function loadPage(result) {
	var data = result.list;
	var html = ""
	for (var i = 0; i < data.length; i++) {
		html += "<tr><td ><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i + 1 + result.pageSize * (result.pageNum - 1)) + "</td>";
		html += "<td id='clientId' style='display:none'>"
				+ data[i].clientId + "</td>";
		html += "<td id='clientForShort' >" + data[i].clientForShort + "</td>";
		html += "<td id='phone' >" + data[i].phone + "</td>";
		html += "<td id='linkman' >" + data[i].linkman + "</td>";
		html += "<td id='email' >" + data[i].email + "</td>";
		html += "<td id='depositBank'>" + data[i].depositBank + "</td>";
		html += "<td ><div><button type='button' clientId='"
				+ data[i].clientclientId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addClient'onclick='update(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='del(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#clientList").html(html);
	html = "";
	html += "总共"
			+ result.pages
			+ "页，共"
			+ result.total
			+ "条数据。 每页 <select class='form-control' onchange='gotoPage(1,this.value)'";
	for (var j = 4; j < 14; j++) {
		if (j == result.pageSize) {
			html += "<option selected value='" + j + "'>" + j + "</option>";
		} else {
			html += "<option value='" + j + "'>" + j + "</option>";
		}
	}
	/*
	 * html+="<c:forEach var='temp' begin='1' end='10'> <option
	 * "+result.pageSize+"==temp?'selected':''} value='${temp}'>"+temp+"</option>";
	 */
	html += "</select> 条";
	$("#totalPage").html(html);
	var htmlArr = [];
	html = null;
	htmlArr
			.push("<div class='button-group border-blue'><button type='button' class='button button-little'  onclick='gotoPage(1,"
					+ result.pageSize + ")' aria-label='首页'>首页</button>");
	if (!result.isFirstPage) {
		htmlArr
				.push("<button type='button' class='button button-little' onclick='gotoPage("
						+ result.pERPage
						+ ","
						+ result.pageSize
						+ ")' aria-label='上一页'>上一页</button>");
	} else {
		htmlArr
				.push("<button type='button' class='button button-little' disabled='disabled' onclick='gotoPage("
						+ result.pERPage
						+ ","
						+ result.pageSize
						+ ")' aria-label='上一页'>上一页</button>");

	}
	var start = 1;
	var end = result.pages;
	if (result.pages > 5) {
		start = result.pageNum - 2 > 0 ? result.pageNum - 2 : 1;
		end = end - 2 > result.pageNum ? result.pageNum + 2 : end;
	}
	if (start > 1) {
		htmlArr.push("...");
	}
	for (var k = start; k <= end; k++) {
		if (k == result.pageNum) {
			htmlArr
					.push("<button type='button' class='button button-little active' id='ck' onclick='gotoPage("
							+ k
							+ ","
							+ result.pageSize
							+ ")' aria-label='第"
							+ k + "页'>" + k + "</button>");

		} else {
			htmlArr
					.push("<button type='button' class='button button-little' onclick='gotoPage("
							+ k
							+ ","
							+ result.pageSize
							+ ")' aria-label='第"
							+ k + "页'>" + k + "</button>");
		}
	}
	if (end < result.pages) {
		htmlArr.push("...");
	}
	if (!result.isLastPage) {
		htmlArr
				.push("<button type='button' class='button button-little' onclick='gotoPage("
						+ result.nextPage
						+ ","
						+ result.pageSize
						+ ")' aria-label='下一页'>下一页</button>");
	} else {
		htmlArr
				.push("<button type='button' class='button button-little' disabled='disabled' onclick='gotoPage("
						+ result.nextPage
						+ ","
						+ result.pageSize
						+ ")' aria-label='下一页'>下一页</button>");
	}
	htmlArr
			.push("<button type='button' class='button button-little' onclick='gotoPage("
					+ result.pages
					+ ","
					+ result.pageSize
					+ ")' aria-label='尾页'>尾页</button></div>");
	html = htmlArr.join(" ");
	$(".pagination").html(html);

}
// 删除指定客户
function del(obj) {
	var clientId = $(obj).parents("tr").find("#clientId").text();
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "http://localhost:8080/ERP/client/delete",
			type : "get",
			data : {
				clientId : clientId
			},
			success : function(result) {
				if(result.status==200){
					$(obj).parent().parent().remove();
				}else{
					alert(result.message);
				}
				
			},
			error : function() {
				alert(result.message);
			}

		})
	}
}
// 跳转到指定页面
function gotoPage(pageNum, pageSize) {
	$.ajax({
		url : "http://localhost:8080/ERP/client/getPageInfo",
		type : "get",
		data : {
			pageNum : pageNum,
			pageSize : pageSize,
		},
		success : function(result) {
			if(result.status==200){
				loadPage(result.object);
			}else{
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		}
	})

}
// 增加/修改客户信息，将信息发送到后台
function pushData() {
//	var t=$("[name=clientName]").val();
	var obj=$(".check-error");
	if(obj.length!=0){
		return;
	}
	var clientId = $("[name=clientId]").val();
	var clientName = $("[name=clientName]").val();
	var clientForShort = $("[name=clientForShort]").val();
	var address = $("[name=address]").val();
	var postalcode = $("[name=postalcode]").val();
	var phone = $("[name=phone]").val();
	var facsimile = $("[name=facsimile]").val();
	var email = $("[name=email]").val();
	var linkman = $("[name=linkman]").val();
	var linkmanPhone = $("[name=linkmanPhone]").val();
	var depositBank = $("[name=depositBank]").val();
	var account = $("[name=account]").val();
		$.ajax({
		url : "http://localhost:8080/ERP/client/update",
		type : "post",
		data : {
			clientId : clientId,
			clientName : clientName,
			clientForShort : clientForShort,
			address : address,
			postalcode : postalcode,
			phone : phone,
			facsimile : facsimile,
			email : email,
			linkman : linkman,
			linkmanPhone : linkmanPhone,
			depositBank : depositBank,
			account : account
		},
		success : function(result) {
			if(result.status==200){
				$('#addClient').modal('hide');
				refush();
			}else{
				alert(result.message);
			}
		},
		error : function(data) {
			alert(result.message);
		}
	})

}
function remove(obj){
	var flag=$(obj).prop("checked");
	if(flag){
		return;
	}
	$("#ckA").attr("checked",false);
}
// 批量删除
function delSelect() {
	var id_arr = [];
	$("input[name='id[]']").each(function() {
		if ($(this).prop("checked")) {
			id_arr.push($(this).parent().next().text())
		}
	});
	if (id_arr == null) {
		return;
	}
	$.ajax({
		url : "http://localhost:8080/ERP/client/deleteMany",
		data : {
			id_arr : id_arr
		},
		traditional : true,
		success : function(result) {
			if(result.status==200){
				refush();
			}else{
				alert(result.message);
			}
		},	
		error : function() {
			alert(reuslt.message);
		}

	})
}
// 刷新页面
function refush() {
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	gotoPage(pageNum, pageSize);
}
// 全选
function checkAll(obj) {
	var flag = $(obj).prop("checked");
	$("input[name='id[]']").each(function() {
		if (flag) {
			this.checked = true;
		} else {
			this.checked = false;
		}
	});
}
// 页面加载事件
$(function() {

	// 置空新增客户模态框
	$("[name=add]").bind("click", function() {

		$("h4").text("添加新的客户资料");
		$("[name=clientId]").val("");
		$("[name=clientName]").val("");
		$("[name=clientForShort]").val("");

		$("[name=address]").val("");
		$("[name=postalcode]").val("");
		$("[name=phone]").val("");
		$("[name=facsimile]").val("");
		$("[name=linkman]").val("");
		$("[name=linkmanPhone]").val("");
		$("[name=email]").val("");
		$("[name=depositBank]").val("");
		$("[name=account]").val("");

	})
	
	// 反选
	$("#reversCheck").click(function() {
		$("input[name='id[]']").each(function() {
			if (this.checked) {
				this.checked = false;
			} else {
				this.checked = true;
			}
		});
	})
	$("#addClient").on("hide.bs.modal",function(){
		$(".field").each(function(){
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
})