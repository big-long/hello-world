function gotoPage(pageNum, pageSize) {
	$.ajax({
		url : "http://localhost:8080/ERP/pSell/getPageInfo",
		type : "get",
		data : {
			pageNum : pageNum,
			pageSize : pageSize
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
function loadPage(result) {
	var data = result.list;
	var html = ""
	for (var i = 0; i < data.length; i++) {
		html += "<tr><td  width=''><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i+1+result.pageSize*(result.pageNum-1)) + "</td>";
		html += "<td id='sellId'>"
			+ data[i].sellId + "</td>";
		html += "<td id='productName' width=''>" + data[i].productName
				+ "</td>";
		html += "<td id='customerName' width=''>" + data[i].customerName
				+ "</td>";
		html += "<td id='sellNumber' width=''>" + data[i].sellNumber + "</td>";
		html += "<td id='sellDate' width=''>" + data[i].sellDate + "</td>";
		html += "<td id='sellStatus' width=''>" + data[i].sellStatus + "</td>";
		html += "<td ><div><button type='button' id='"
				+ data[i].sellId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addSell'onclick='getData(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='deleteOne(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#sellList").html(html);
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
						+ result.prePage
						+ ","
						+ result.pageSize
						+ ")' aria-label='上一页'>上一页</button>");
	} else {
		htmlArr
				.push("<button type='button' class='button button-little' disabled='disabled' onclick='gotoPage("
						+ result.prePage
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
function refush() {
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	gotoPage(pageNum, pageSize);
}
function pushData() {
	var sellId = $("input[name=sellId]").val();
	var sellDate = $("input[name=sellDate]").val();
	var orderId = $("[name=orderId]").val();
	var settlementWay = $("[name=settlementWay]").val();
	var handlerName = $("input[name=handlerName]").val();
	var sellStatus = $("[name=sellStatus]").val();
	var Operator = $("input[name=Operator]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/pSell/sellUpdate",
		type : "post",
		data : {
			sellId : sellId,
			sellDate : sellDate,
			orderId : orderId,
			settlementWay : settlementWay,
			handlerName : handlerName,
			Operator : Operator,
			sellStatus : sellStatus
		},
		success : function(result) {
			if (result.status == 200) {
				$('#addSell').modal('hide');
				refush();
			} else {
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		}
	})
}

function delSelect() {
	// 发送ajax请求，批量删除
	// 取得选中的id
	// 定义一个数组，用于接收选中行的id
	var sellIdArray = new Array();

	$("input[name='id[]']").each(function() {

		if (this.checked) {
			// alert($(this).val());
			sellIdArray.push($(this).parent().next().text());
		}
	});
	if (confirm("您确认要删除选中的内容吗？")) {
		$.ajax({
			url : "http://localhost:8080/ERP/pSell/sellDeleBatch",
			type : "POST",
			data : {
				"sellArray" : sellIdArray
			},
			traditional : true,
			success : function(result) {
				if (result.status == 200) {
					refush();
				} else {
					alert(result.message);
				}
			},
			err : function(result) {
				alert(result.message);
			}
		});
	}
}
function emptyModel() {
	// var sellId = $("input[name=sellId]").val();
	$("input[name=sellDate]").val("");
	$("input[name=productName]").val("");
	$("input[name=customerName]").val("");
	$("input[name=sellNumber]").val("");
	$("input[name=totalMoney]").val("");
	$("[name=orderId]").val("");
	$("[name=settlementWay]").val("");
	$("input[name=handlerName]").val("");
	$("[name=sellStatus]").val("");
	$.ajax({
		url:"http://localhost:8080/ERP/pSell/getSellId",
		type:"get",
		success:function(result){
			if(result.status==200){
				$("input[name=sellId]").val(result.object);
			}
			else{
				alert(result.message);
			}
		},
		error:function(result){
			alert(result.message);
		}
	})
}
function getData(obj) {

	// 爬取所需要的数据
	var sellId = $(obj).parents("tr").find("#sellId").text();
	var productName = $(obj).parents("tr").find("#productName").text();
	var customerName = $(obj).parents("tr").find("#customerName").text();
	var sellNumber = $(obj).parents("tr").find("#sellNumber").text();
	var sellDate = $(obj).parents("tr").find("#sellDate").text();
	var sellStatus = $(obj).parents("tr").find("#sellStatus").text();
	//var status = $(this).parents("tr").find("#status").text();
			
	$.ajax({
		url:"http://localhost:8080/ERP/pSell/getSellInfo",
		data:{
			sellId:sellId
		},
		success:function(result){
			if(result.status==200){
				var sell=result.object;
				$("h4").text("修改销售订单");
				$("[name=sellId]").val(sellId);
				$("[name=productName]").val(productName);
				$("[name=customerName]").val(customerName);
				$("[name=sellNumber]").val(sellNumber);
				$("[name=sellDate]").val(sellDate);
				$("[name=sellStatus]").val(sellStatus);
				$("[name=orderId]").val(sell.orderId);
				$("[name=settlementWay]").val(sell.settlementWay);
				$("[name=totalMoney]").val(sell.totalMoney);
				$("[name=handlerName]").val(sell.handlerName);
				$("[name=operator]").val(sell.operator);
				$("[name=remark]").val(sell.remark);
				
			}else{
				alert(result.message);
			}
		},
		error:function(result){
			alert(result.message);
		}
		
	})
	
}
function deleteOne(obj){
	var sellId=$(obj).parents("tr").find("#sellId").text();
	$.ajax({
		url:"http://localhost:8080/ERP/pSell/sellDelete",
		type:"get",
		data:{
			sellId:sellId
		},
		success:function(result){
			if(result.status==200){
				refush();
			}
			else{
				alert(result.message);
			}
		},
		error:function(result){
			alert(result.message);
		}
	})
	
}
$(function() {
	$("[name=orderId]").bind("change",function(){
		var orderId=$(this).val();
		$.ajax({
			url:"http://localhost:8080/ERP/pSell/getOrderInfo",
			type:"get",
			data:{
				orderId:orderId
			},
			success:function(result){
				if(result.status==200){
					$("input[name=productName]").val(result.object.productName);
					$("input[name=customerName]").val(result.object.orderCustomer);
					$("input[name=sellNumber]").val(result.object.orderNumber);
					$("input[name=totalMoney]").val(result.object.orderMoney);
				}
				else{
					alert(result.message);
				}
			},
			error:function(result){
				alert(result.message);
			}
		})
	})

	gotoPage(1,8);
	
	$("[name=sellDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
})
