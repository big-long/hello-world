function refush() {
	var keyWord = $("[name=keyword]").val();
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	$.ajax({
		url : "http://localhost:8080/ERP/purchase/getPurchaseByKeyWord",
		type : "get",
		data : {
			keyWord : keyWord,
			pageNum : pageNum,
			pageSize : pageSize
		},
		success : function(result) {
			if (result.status == 200) {
				loadPage(result.object);
			} else {
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		}
	})
}
// 修改用户模态框准备数据
function update(obj) {
	var purchaseId = $(obj).parents("tr").find("#purchaseId").text();
	var goodsName = $(obj).parents("tr").find("#goodsName").text();
	var purchaseNumber = $(obj).parents("tr").find("#purchaseNumber").text();
	var purchaseDate = $(obj).parents("tr").find("#purchaseDate").text();
	
	
	$.ajax({
		url : "http://localhost:8080/ERP/purchase/getPurchaseInfo",
		type : "get",
		data : {
			purchaseId : purchaseId
		},
		success : function(result) {
			if (result.status == 200) {
				var purchase = result.object;
				$("h4").text("修改采购单");
				$("[name=category]").val(purchase.category);
				$("[name=goodsId]").val(purchase.goodsId);
				$("[name=providerName]").val(purchase.providerName);
				$("[name=goodsNickname]").val(purchase.goodsNickname);
				$("[name=price]").val(purchase.price);
				$("[name=purchaseAmount]").val(purchase.purchaseAmount);
				$("[name=amountPayable]").val(purchase.amountPayable);
				$("[name=amountPaid]").val(purchase.amountPayable);
				$("[name=unpaidAmount]").val(purchase.unpaidAmount);
				$("[name=settlementWay]").val(purchase.settlementWay);
				$("[name=handlerName]").val(purchase.handlerName);
				
				$("[name=purchaseId]").val(purchaseId);
				$("[name=goodsName]").val(goodsName);
				$("[name=purchaseNumber]").val(purchaseNumber);
				$("[name=operator]").val(purchase.operator);
				$("[name=purchaseDate]").val(purchaseDate);
				$("[name=remark]").val(purchase.remark);
			} else {
				alert(result.message);
			}

		},
		error : function() {
			alert(result.message);
		}

	})
	/* $("h4").text("修改客户信息"); */

}
// 加载客户数据与分页信息
function loadPage(result) {
	var data = result.list;
	var html = ""
	for (var i = 0; i < data.length; i++) {
		html += "<tr><td ><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i + 1 + result.pageSize * (result.pageNum - 1)) + "</td>";
		html += "<td id='purchaseId' >" + data[i].purchaseId + "</td>";
		html += "<td id='goodsName' >" + data[i].goodsName + "</td>";
		html += "<td id='purchaseNumber' >" + data[i].purchaseNumber + "</td>";
		html += "<td id='purchaseDate' >" + data[i].purchaseDate + "</td>";
		if(data[i].status==0){
			html += "<td id='status' >进行中</td>";
		}else if(data[i].status==1){
			html += "<td id='status' >已完成</td>";
		}
		html += "<td ><div><button type='button' purchaseId='"
				+ data[i].purchaseId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addPurchase'onclick='update(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='del(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#purchaseList").html(html);
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
	var purchaseId = $(obj).parents("tr").find("#purchaseId").text();
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "http://localhost:8080/ERP/purchase/delete",
			type : "get",
			data : {
				purchaseId : purchaseId
			},
			success : function(result) {
				if (result.status == 200) {
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
}
// 跳转到指定页面
function gotoPage(pageNum, pageSize) {
	$.ajax({
		url : "http://localhost:8080/ERP/purchase/getPageInfo",
		type : "get",
		data : {
			pageNum : pageNum,
			pageSize : pageSize,
		},
		success : function(result) {
			if (result.status == 200) {
				loadPage(result.object);
			} else {
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
	// var t=$("[name=purchaseName]").val();
	var obj = $(".check-error");
	if (obj.length != 0) {
		return;
	}
	var goodsName =$("[name=goodsName]").val();
	var category =$("[name=category]").val();
	var goodsId =$("[name=goodsId]").val();
	var providerName =$("[name=providerName]").val();
	var goodsNickname =$("[name=goodsNickname]").val();
	var price =$("[name=price]").val("");
	var purchaseAmount =$("[name=purchaseAmount]").val();
	var amountPayable =$("[name=amountPayable]").val();
	var amountPaid =$("[name=amountPaid]").val();
	var unpaidAmount =$("[name=unpaidAmount]").val();
	var settlementWay =$("[name=settlementWay]").val();
	var handlerName =$("[name=handlerName]").val();
	var purchaseNumber = $("[name=purchaseNumber]").val();
	var operator = $("[name=operator]").val();
	var purchaseDate = $("[name=purchaseDate]").val();
	var remark = $("[name=remark]").val();
	var purchaseId = $("[name=purchaseId]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/purchase/update",
		type : "post",
		data : {
			purchaseId : purchaseId,
			goodsName : goodsName,
			/*category : category,
			goodsId : goodsId,*/
			providerName : providerName,
			/*goodsNickname : goodsNickname,*/
			/*price : price,*/
			purchaseAmount : purchaseAmount,
			amountPayable : amountPayable,
			amountPaid : amountPaid,
			unpaidAmount : unpaidAmount,
			settlementWay : settlementWay,
			handlerName : handlerName,
			purchaseNumber : purchaseNumber,
			operator : operator,
			purchaseDate : purchaseDate,
			remark : remark
		},
		success : function(result) {
			if (result.status == 200) {
				$('#addPurchase').modal('hide');
				refush();
			} else {
				alert(result.message);
			}
		},
		error : function(data) {
			alert(result.message);
		}
	})

}
function remove(obj) {
	var flag = $(obj).prop("checked");
	if (flag) {
		return;
	}
	$("#ckA").attr("checked", false);
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
		url : "http://localhost:8080/ERP/purchase/deleteMany",
		data : {
			id_arr : id_arr
		},
		traditional : true,
		success : function(result) {
			if (result.status == 200) {
				refush();
			} else {
				alert(result.message);
			}
		},
		error : function() {
			alert(reuslt.message);
		}

	})
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

		$("h4").text("添加新的领料单");

		$("[name=goodsName]").val("");
		$("[name=purchaseNumber]").val("");
		$("[name=purchaseDate]").val("");
		$("[name=category]").val("");
		$("[name=goodsId]").val("");
		$("[name=providerName]").val("");
		$("[name=goodsNickname]").val("");
		$("[name=price]").val("");
		$("[name=purchaseAmount]").val("");
		$("[name=amountPayable]").val("");
		$("[name=amountPaid]").val("");
		$("[name=unpaidAmount]").val("");
		$("[name=settlementWay]").val("");
		$("[name=handlerName]").val("");
		$("[name=remark]").val("");
		$.ajax({
			url : "http://localhost:8080/ERP/purchase/generatePurchaseId",
			type : "get",
			success : function(result) {
				if (result.status == 200) {
					$("[name=purchaseId]").val(result.object);
				} else {
					alert(result.message);
				}
			},
			error : function(result) {
				alert(result.message);
			}
		})

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
	$("#addPurchase").on("hide.bs.modal", function() {
		$(".field").each(function() {
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
	$("[name=purchaseDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
	$("[name=goodsName]").bind("change",function(){
		var goodsName=$(this).val();
		if(goodsName==""){
			return;
		}
		$.ajax({
			url:"http://localhost:8080/ERP/purchase/getGoodsInfo",
			type:"get",
			data:{
				goodsName:goodsName
			},
			success:function(result){
				if(result.status==200){
					var purchase=result.object;
					$("[name=category]").val(purchase.category);
					$("[name=goodsId]").val(purchase.goodsId);
					$("[name=goodsNickname]").val(purchase.goodsNickname);
					$("[name=price]").val(purchase.price);
				}else{
					alert(result.message);
				}
			},
			error:function(result){
				alert(result.message);
			}
		})
	});
	$("[name=purchaseNumber]").bind("blur",function(){
		var num=$(this).val();
		var price=$("[name=price]").val();
		if(/^[+]?\d+$/.test(num)){
			$("[name=purchaseAmount]").val(num*price);
		}
	})
	
})