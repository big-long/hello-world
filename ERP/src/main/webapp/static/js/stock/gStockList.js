function refush() {
	var keyWord = $("[name=keyword]").val();
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	gotoPage(pageNum, pageSize);
	/*
	 * $.ajax({ url : "http://localhost:8080/ERP/goodsStock/getOutputByKeyWord",
	 * type : "get", data : { keyWord : keyWord, pageNum : pageNum, pageSize :
	 * pageSize }, success : function(result) { if (result.status == 200) {
	 * loadPage(result.object); } else { alert(result.message); } }, error :
	 * function(result) { alert(result.message); } })
	 */
}
// 修改用户模态框准备数据
function update(obj) {
	var gStockId = $(obj).parents("tr").find("#gStockId").text();
	var goodsName = $(obj).parents("tr").find("#goodsName").text();
	var purchaseNumber = $(obj).parents("tr").find("#purchaseNumber").text();
	var stockDate = $(obj).parents("tr").find("#stockDate").text();
	var provider = $(obj).parents("tr").find("#provider").text();

	$.ajax({
		url : "http://localhost:8080/ERP/goodsStock/getStockInfo",
		type : "get",
		data : {
			gStockId : gStockId
		},
		success : function(result) {
			if (result.status == 200) {
				var gStock = result.object;
				$("h4").text("修改产品入库单");
				$("[name=provider]").val(provider);
				$("[name=gStockId]").val(gStockId);
				$("[name=goodsName]").val(goodsName);
				$("[name=purchaseNumber]").val(purchaseNumber);
				$("[name=stockDate]").val(stockDate);

				$("[name=purchaseId]").val(gStock.purchaseId);
				$("[name=stockNumber]").val(gStock.stockNumber);
				$("[name=handlerName]").val(gStock.handlerName);
				$("[name=operator]").val(gStock.operator);
				$("[name=remark]").val(gStock.remark);
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
		html += "<td id='gStockId' >" + data[i].gStockId + "</td>";
		html += "<td id='goodsName' >" + data[i].goodsName + "</td>";
		html += "<td id='provider' >" + data[i].provider + "</td>";
		html += "<td id='purchaseNumber' >" + data[i].purchaseNumber + "</td>";
		html += "<td id='stockDate' >" + data[i].stockDate + "</td>";
		html += "<td id='handlerName' >" + data[i].handlerName + "</td>";
		html += "<td ><div><button type='button' gStockId='"
				+ data[i].gStockId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#modal' onclick='update(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='del(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#list").html(html);
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
	var gStockId = $(obj).parents("tr").find("#gStockId").text();
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "http://localhost:8080/ERP/goodsStock/delete",
			type : "get",
			data : {
				gStockId : gStockId
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
		url : "http://localhost:8080/ERP/goodsStock/getGStockPageInfo",
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
	// var t=$("[name=inbillName]").val();
	var obj = $(".check-error");
	if (obj.length != 0) {
		return;
	}
	var gStockId = $("[name=gStockId]").val();
	var purchaseId = $("[name=purchaseId]").val();
	var goodsName = $("[name=goodsName]").val();
	var operator = $("[name=operator]").val();
	var stockDate = $("[name=stockDate]").val();
	var remark = $("[name=remark]").val();
	var handlerName = $("[name=handlerName]").val();
	var purchaseNumber = $("[name=purchaseNumber]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/goodsStock/updateGStock",
		type : "post",
		data : {
			gStockId : gStockId,
			goodsName : goodsName,
			purchaseId : purchaseId,
			operator : operator,
			stockDate : stockDate,
			remark : remark,
			handlerName : handlerName,
			purchaseNumber : purchaseNumber
		},
		success : function(result) {
			if (result.status == 200) {
				$('#modal').modal('hide');
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
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "http://localhost:8080/ERP/goodsStock/deleteMany",
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

		$("h4").text("添加新入库单");

		$("[name=goodsName]").val("");
		$("[name=purchaseNumber]").val("");
		$("[name=stockDate]").val("");
		$("[name=provider]").val("");
		$("[name=purchaseId]").val("");
		$("[name=stockNumber]").val("");
		$("[name=handlerName]").val("");
		$("[name=remark]").val("");
		$.ajax({
			url : "http://localhost:8080/ERP/goodsStock/getGStockId",
			type : "get",
			success : function(result) {
				if (result.status == 200) {
					$("[name=gStockId]").val(result.object);
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
	$("#addOutput").on("hide.bs.modal", function() {
		$(".field").each(function() {
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
	$("[name=stockDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
	$("[name=purchaseId]").bind("change", function() {
		var purchaseId = $(this).val();
		if (purchaseId == "") {
			return;
		}
		$.ajax({
			url : "http://localhost:8080/ERP/goodsStock/getPurchaseInfo",
			type : "get",
			data : {
				purchaseId : purchaseId
			},
			success : function(result) {
				if (result.status == 200) {
					var purchase = result.object;
					$("[name=purchaseNumber]").val(purchase.purchaseNumber);
					$("[name=goodsName]").val(purchase.goodsName);
					$("[name=provider]").val(purchase.provider);
					$("[name=stockNumber]").val(purchase.stockNumber);
					$("[name=handlerName]").val(purchase.handlerName);
				} else {
					alert(result.message);
				}
			},
			error : function(result) {
				alert(result.message);
			}
		})
	});
	gotoPage(1, 8);

})