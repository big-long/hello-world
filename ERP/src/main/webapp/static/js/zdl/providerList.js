function changeSearch() {
	var keyWord = $("[name=keyword]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/provider/getProviderByKeyWord",
		type : "get",
		data : {
			keyword : keyWord
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
// 修改供应商模态框准备数据
function update(obj) {
	var providerId = $(obj).parents("tr").find("#providerId").text();
	var providerForShort = $(obj).parents("tr").find("#providerForShort")
			.text();
	var phone = $(obj).parents("tr").find("#phone").text();
	var linkman = $(obj).parents("tr").find("#linkman").text();
	var email = $(obj).parents("tr").find("#email").text();
	var depositBank = $(obj).parents("tr").find("#depositBank").text();
	$.ajax({
		url : "http://localhost:8080/ERP/provider/getProviderInfo",
		type : "get",
		data : {
			providerId : providerId
		},
		success : function(result) {
			if (result.status == 200) {
				var provider = result.object;
				$("h4").text("修改客户资料");

				$("[name=providerId]").val(providerId);
				$("[name=providerName]").val(provider.providerName);
				$("[name=providerForShort]").val(providerForShort);
				$("[name=address]").val(provider.address);
				$("[name=postalcode]").val(provider.postalcode);
				$("[name=phone]").val(phone);
				$("[name=facsimile]").val(provider.facsimile);
				$("[name=linkman]").val(linkman);
				$("[name=linkmanPhone]").val(provider.linkmanPhone);
				$("[name=email]").val(email);
				$("[name=depositBank]").val(depositBank);
				$("[name=account]").val(provider.account);
				$("[name=credibility]").val(provider.credibility);
				$("[name=remark]").val(provider.remark);
			} else {
				alert(result.message);
			}

		},
		error : function(result) {
			alert(result.message);
		}

	})

}
// 加载供应商数据与分页信息
function loadPage(result) {
	var data = result.list;
	var html = ""
	for (var i = 0; i < data.length; i++) {
		html += "<tr><td ><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i + 1 + result.pageSize * (result.pageNum - 1)) + "</td>";
		html += "<td id='providerId' style='display:none'>"
				+ data[i].providerId + "</td>";
		html += "<td id='providerForShort' >" + data[i].providerForShort
				+ "</td>";
		html += "<td id='phone' >" + data[i].phone + "</td>";
		html += "<td id='linkman' >" + data[i].linkman + "</td>";
		html += "<td id='email' >" + data[i].email + "</td>";
		html += "<td id='depositBank'>" + data[i].depositBank + "</td>";
		html += "<td ><div><button type='button' providerId='"
				+ data[i].providerId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addProvider'onclick='update(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='del(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#providerList").html(html);
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
// 删除指定客户
function del(obj) {
	var providerId = $(obj).parents("tr").find("#providerId").text();
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "http://localhost:8080/ERP/provider/delete",
			type : "get",
			data : {
				providerId : providerId
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
		url : "http://localhost:8080/ERP/provider/getProviderByKeyWord",
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
	var obj = $(".check-error");
	if (obj.length != 0) {
		return;
	}
	var providerId = $("[name=providerId]").val();
	var providerName = $("[name=providerName]").val();
	var providerForShort = $("[name=providerForShort]").val();
	var address = $("[name=address]").val();
	var postalcode = $("[name=postalcode]").val();
	var phone = $("[name=phone]").val();
	var facsimile = $("[name=facsimile]").val();
	var email = $("[name=email]").val();
	var linkman = $("[name=linkman]").val();
	var linkmanPhone = $("[name=linkmanPhone]").val();
	var depositBank = $("[name=depositBank]").val();
	var account = $("[name=account]").val();
	var credibility = $("[name=credibility]").val();
	var remark = $("[name=remark]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/provider/update",
		type : "post",
		data : {
			providerId : providerId,
			providerName : providerName,
			providerForShort : providerForShort,
			address : address,
			postalcode : postalcode,
			phone : phone,
			facsimile : facsimile,
			email : email,
			linkman : linkman,
			linkmanPhone : linkmanPhone,
			depositBank : depositBank,
			account : account,
			credibility : credibility,
			remark : remark
		},
		success : function(data) {
			if (result.status == 200) {
				$('#addProvider').modal('hide');
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
// 批量删除
function delSelect() {
	if (confirm("您确定要删除吗?")) {
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
			url : "http://localhost:8080/ERP/provider/deleteMany",
			data : {
				id_arr : id_arr
			},
			traditional : true,
			success : function(data) {
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
		$("[name=providerId]").val("");
		$("[name=providerName]").val("");
		$("[name=providerForShort]").val("");

		$("[name=address]").val("");
		$("[name=postalcode]").val("");
		$("[name=phone]").val("");
		$("[name=facsimile]").val("");
		$("[name=linkman]").val("");
		$("[name=linkmanPhone]").val("");
		$("[name=email]").val("");
		$("[name=depositBank]").val("");
		$("[name=account]").val("");
		$("[name=credibility]").val("");
		$("[name=remark]").val("");

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
	$("#addProvider").on("hide.bs.modal", function() {
		$(".field").each(function() {
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
})