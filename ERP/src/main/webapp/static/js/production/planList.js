//关键字查询,刷新页面
function refush() {
	var keyWord = $("[name=keyword]").val();
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	$.ajax({
		url : "http://localhost:8080/ERP/plan/getPlanByKeyWord",
		type : "get",
		data : {
			keyWord : keyWord,
			pageSize:pageSize,
			pageNum:pageNum
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
			url : "http://localhost:8080/ERP/plan/deleteMany",
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
		url : "http://localhost:8080/ERP/plan/getPageInfo",
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
		html += "<td id='planId'>" + data[i].planId + "</td>";
		html += "<td id='productName' width=''>" + data[i].productName
				+ "</td>";
		html += "<td id='planNumber' width=''>" + data[i].planNumber + "</td>";
		if(data[i].planFinishDate==null){
			html += "<td id='planFinishDate' width=''> </td>";
		}else{
			html += "<td id='planFinishDate' width=''>" + data[i].planFinishDate + "</td>";
		}
		if(data[i].realNumber==null){
			html += "<td id='realNumber' width=''> </td>";
		}else{
			html += "<td id='realNumber' width=''>" + data[i].realNumber + "</td>";
		}
		html += "<td ><div><button type='button' id='"
				+ data[i].planId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addPlan'onclick='getData(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='deleteOne(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#planList").html(html);
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
		var planId = $(obj).parents("tr").find("#planId").text();
		$.ajax({
			url : "http://localhost:8080/ERP/plan/deletePlan",
			type : "get",
			data : {
				planId : planId
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
	var planId = $(obj).parents("tr").find("#planId").text();
	var productName = $(obj).parents("tr").find("#productName").text();
	var planNumber = $(obj).parents("tr").find("#planNumber").text();
	var planFinishDate = $(obj).parents("tr").find("#planFinishDate").text();
	var realNumber = $(obj).parents("tr").find("#realNumber").text();

	$.ajax({
		url : "http://localhost:8080/ERP/plan/getPlanInfo",
		type : "get",
		data : {
			planId : planId
		},
		success : function(result) {
			if (result.status == 200) {
				var plan = result.object;
				$("[name=planId]").val(planId);
				$("[name=productName]").val(productName);
				$("[name=planNumber]").val(planNumber);
				$("[name=planDate]").val(plan.planDate);
				$("[name=planFinishDate]").val(planFinishDate);
				$("[name=handlerName]").val(plan.handlerName);
				$("[name=realNumber]").val(realNumber);
				$("[name=remark]").val(plan.remark);
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
	$(".modal-title").html("添加新计划");

	$("[name=productName]").val("");
	$("[name=planNumber]").val("");
	$("[name=planDate]").val("");
	$("[name=planFinishDate]").val("");
	$("[name=handlerName]").val("");
	$("[name=realNumber]").val("");
	$("[name=remark]").val("");
	$.ajax({
		url : "http://localhost:8080/ERP/plan/generatePlanId",
		type : "get",
		success : function(result) {
			if (result.status == 200) {
				$("[name=planId]").val(result.object);
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
	var productName = $("[name=productName]").val();
	var planNumber = $("[name=planNumber]").val();
	var planDate = $("[name=planDate]").val();
	var planFinishDate = $("[name=planFinishDate]").val();
	var handlerName = $("[name=handlerName]").val();
	var realNumber = $("[name=realNumber]").val();
	var remark = $("[name=remark]").val();
	var operator = $("[name=operator]").val();
	var planId = $("[name=planId]").val();

	$.ajax({
		url : "http://localhost:8080/ERP/plan/updatePlan",
		type : "get",
		data : {
			planId : planId,
			productName : productName,
			planNumber : planNumber,
			planDate : planDate,
			planFinishDate : planFinishDate,
			handlerName : handlerName,
			realNumber : realNumber,
			operator : operator,
			remark : remark
		},
		success : function(result) {
			if (result.status == 200) {
				$('#addPlan').modal('hide');
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
			url : "http://localhost:8080/ERP/plan/getProduct",
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
	$("#addPlan").on("hide.bs.modal", function() {
		$(".field").each(function() {
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
	// 加载日历控件
	$("[name=planDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
	$("[name=planFinishDate]").datetimepicker({
		format : 'YYYY-MM-DD hh:mm:ss',
		locale : moment.locale('zh-cn')
	});
})
