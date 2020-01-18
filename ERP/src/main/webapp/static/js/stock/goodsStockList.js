function refush() {
	var keyWord = $("[name=keyword]").val();
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	$.ajax({
		url : "http://localhost:8080/ERP/output/getOutputByKeyWord",
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
	var outputId = $(obj).parents("tr").find("#outputId").text();
	var productName = $(obj).parents("tr").find("#productName").text();
	var outputNumber = $(obj).parents("tr").find("#outputNumber").text();
	var outputDate = $(obj).parents("tr").find("#outputDate").text();
	var clientName = $(obj).parents("tr").find("#clientName").text();

	$.ajax({
		url : "http://localhost:8080/ERP/output/getOutputInfo",
		type : "get",
		data : {
			outputId : outputId
		},
		success : function(result) {
			if (result.status == 200) {
				var output = result.object;
				$("h4").text("修改出库单");
				$("[name=clientName]").val(clientName);
				$("[name=outputId]").val(outputId);
				$("[name=productName]").val(productName);
				$("[name=outputNumber]").val(outputNumber);
				$("[name=outputDate]").val(outputDate);

				$("[name=sellId]").val(output.sellId);
				$("[name=orderId]").val(output.orderId);
				$("[name=stockNumber]").val(output.stockNumber);
				$("[name=handlerName]").val(output.handerName);
				$("[name=operator]").val(output.operator);
				$("[name=remark]").val(output.remark);
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
		html += "<td id='goodsName' >" + data[i].goodsName + "</td>";
		html += "<td id='price' >" + data[i].price + "</td>";
		html += "<td id='stockNumber' >" + data[i].stockNumber + "</td>";
		html += "<td id='handlerName' >" + data[i].handlerName + "</td>";
		html += "<td id='category' >" + data[i].category + "</td>";
		html += "<td ><div><button type='button' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#modal'onclick='update(this)' >修改</button>";
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
	var outputId = $(obj).parents("tr").find("#outputId").text();
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "http://localhost:8080/ERP/output/delete",
			type : "get",
			data : {
				outputId : outputId
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
		url : "http://localhost:8080/ERP/goodsStock/getPageInfo",
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
	// var t=$("[name=outputName]").val();
	var obj = $(".check-error");
	if (obj.length != 0) {
		return;
	}
	var outputId = $("[name=outputId]").val();
	var sellId = $("[name=sellId]").val();
	var handlerName = $("[name=handlerName]").val();
	var outputNumber = $("[name=outputNumber]").val();
	var operator = $("[name=operator]").val();
	var outputDate = $("[name=outputDate]").val();
	var remark = $("[name=remark]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/output/update",
		type : "post",
		data : {
			outputId : outputId,
			sellId : sellId,
			handerName : handlerName,
			outputNumber : outputNumber,
			operator : operator,
			outputDate : outputDate,
			remark : remark
		},
		success : function(result) {
			if (result.status == 200) {
				$('#addOutput').modal('hide');
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
			url : "http://localhost:8080/ERP/output/deleteMany",
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

		$("h4").text("添加新出库单");

		$("[name=productName]").val("");
		$("[name=outputNumber]").val("");
		$("[name=outputDate]").val("");
		$("[name=orderId]").val("");
		$("[name=clientName]").val("");
		$("[name=sellId]").val("");
		$("[name=stockNumber]").val("");
		$("[name=handlerName]").val("");
		$("[name=remark]").val("");
		$.ajax({
			url : "http://localhost:8080/ERP/output/generateOutputId",
			type : "get",
			success : function(result) {
				if (result.status == 200) {
					$("[name=outputId]").val(result.object);
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
	$("[name=outputDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
	$("[name=sellId]").bind("change", function() {
		var sellId = $(this).val();
		if (sellId == "") {
			return;
		}
		$.ajax({
			url : "http://localhost:8080/ERP/output/getSellInfo",
			type : "get",
			data : {
				sellId : sellId
			},
			success : function(result) {
				if (result.status == 200) {
					var output = result.object;
					$("[name=orderId]").val(output.orderId);
					$("[name=productName]").val(output.productName);
					$("[name=clientName]").val(output.customerName);
					$("[name=stockNumber]").val(output.stockNumber);
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