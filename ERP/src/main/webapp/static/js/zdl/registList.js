//关键字查询
function changeSearch() {
	var keyWord = $("[name=keyword]").val();
	var category = $("[name=type]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/regist/getregistByKeyWord",
		type : "get",
		data : {
			keyWord : keyWord,
			category : category
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
function remove(obj) {
	var flag = $(obj).prop("checked");
	if (flag) {
		return;
	}
	$("#ckA").attr("checked", false);
}
// 刷新页面
function refush() {
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	gotoPage(pageNum, pageSize);
}
// 批量删除
function deleteMany() {
	if (confirm("您确定要删除选择的吗?")) {
		var id_arr = [];
		$("input[name='id[]']").each(function() {
			if ($(this).prop("checked")) {
				id_arr.push($(this).parent().next().text());
			}
		});
		$.ajax({
			url : "http://localhost:8080/ERP/regist/deleteMany",
			type : "get",
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
			error : function(result) {
				alert(result.message);
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
// 跳转页面
function gotoPage(pageNum, pageSize) {
	$.ajax({
		url : "http://localhost:8080/ERP/regist/getPageInfo",
		type : "get",
		data : {
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
// 加载页面
function loadPage(result) {
	var data = result.list;
	var html = ""
	for (var i = 0; i < data.length; i++) {
		html += "<tr><td  width=''><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i + 1 + result.pageSize * (result.pageNum - 1)) + "</td>";
		html += "<td id='productionId'>" + data[i].productionId + "</td>";
		html += "<td id='productName' width=''>" + data[i].productName
				+ "</td>";
		html += "<td id='produceNumber' width=''>" + data[i].produceNumber
				+ "</td>";
		html += "<td id='produceDate' width=''>" + data[i].produceDate
				+ "</td>";
		if (data[i].status == 0) {
			html += "<td id='status' width=''>未入库</td>";

		} else if (data[i].status == 1) {
			html += "<td id='status' width=''>已入库</td>";
		}
		html += "<td ><div><button type='button' id='"
				+ data[i].productionId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addRegist'onclick='getData(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='deleteOne(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#registList").html(html);
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
// 删除单个
function deleteOne(obj) {
	if (confirm("您确定要删除吗?")) {
		var productionId = $(obj).parents("tr").find("#productionId").text();
		$.ajax({
			url : "http://localhost:8080/ERP/regist/deleteRegist",
			type : "get",
			data : {
				productionId : productionId
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
// 修改按钮为模态框准备数据
function getData(obj) {
	/* var productId=$(obj).id; */
	$(".modal-title").html("修改登记单");
	var productionId = $(obj).parents("tr").find("#productionId").text();
	var productName = $(obj).parents("tr").find("#productName").text();
	var produceNumber = $(obj).parents("tr").find("#produceNumber").text();
	var produceDate = $(obj).parents("tr").find("#produceDate").text();
	var status = $(obj).parents("tr").find("#status").text();

	$.ajax({
		url : "http://localhost:8080/ERP/regist/getRegistInfo",
		type : "get",
		data : {
			productionId : productionId
		},
		success : function(result) {
			if (result.status == 200) {
				var regist = result.object;
				$("[name=productionId]").val(productionId);
				$("[name=productName]").val(productName);
				$("[name=produceNumber]").val(produceNumber);
				$("[name=produceDate]").val(produceDate);
				$("[name=status]").val(status);
				$("[name=size]").val(regist.size);
				$("[name=producer]").val(regist.producer);
				$("[name=price]").val(regist.price);
				$("[name=storageDate]").val(regist.storageDate);
				$("[name=handerName]").val(regist.handerName);
				$("[name=operator]").val(regist.operator);
				$("[name=remark]").val(regist.remark);
			} else {
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		}
	})

}
// 新增按钮置空模态框
function emptyModel() {
	$(".modal-title").html("添加新的登记单");

	$("[name=productName]").val("");
	$("[name=produceNumber]").val("");
	$("[name=produceDate]").val("");
	$("[name=status]").val("");
	$("[name=size]").val("");
	$("[name=producer]").val("");
	$("[name=price]").val("");
	$("[name=storageDate]").val("");
	$("[name=handerName]").val("");
	// $("[name=operator]").val("");
	$("[name=remark]").val("");
	$.ajax({
		url : "http://localhost:8080/ERP/regist/generateRegistId",
		type : "get",
		success : function(result) {
			if (result.status == 200) {
				$("[name=productionId]").val(result.object);
			} else {
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		}
	})
}
// 将新增或修改的数据发送到后台
function pushData() {
	var obj = $(".check-error");
	if (obj.length != 0) {
		return;
	}
	var productionId = $("[name=productionId]").val();
	var productName = $("[name=productName]").val();
	var produceNumber = $("[name=produceNumber]").val();
	var produceDate = $("[name=produceDate]").val();
	var status = $("[name=status]").val();
	if (status == '未入库') {
		status = 0;
	} else if (status == "已入库") {
		status = 1;
	}
	;
	var size = $("[name=size]").val();
	var producer = $("[name=producer]").val();
	var price = $("[name=price]").val();
	var storageDate = $("[name=storageDate]").val();
	var handerName = $("[name=handerName]").val();
	var operator = $("[name=operator]").val();
	var remark = $("[name=remark]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/regist/updateRegist",
		type : "get",
		data : {
			productionId : productionId,
			productName : productName,
			produceNumber : produceNumber,
			produceDate : produceDate,
			status : status,
			size : size,
			price : price,
			producer : producer,
			storageDate : storageDate,
			handerName : handerName,
			operator : operator,
			remark : remark
		},
		success : function(result) {
			if (result.status == 200) {
				$('#addRegist').modal('hide');
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
$(function() {
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
	$("select[name=productName]").bind("change", function() {
		var productName = $(this).val();
		$.ajax({
			url : "http://localhost:8080/ERP/regist/getProduct",
			type : "get",
			data : {
				productName : productName
			},
			success : function(result) {
				if (result.status == 200) {
					$("[name=size]").val(result.object.size);
					$("[name=price]").val(result.object.price);
				}
			},
			error : function(result) {

			}
		})
	})
	$("#addRegist").on("hide.bs.modal", function() {
		$(".field").each(function() {
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
	// 加载日历控件
	$("[name=produceDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
	$("[name=storageDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
})
