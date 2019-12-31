function refush() {
	var pageNum = $("#ck").text();
	var pageSize = $(".form-control").val();
	gotoPage(pageNum, pageSize);
}
function del(obj) {
	if (confirm("您确定要删除吗?")) {
		var userId = $(obj).prev().attr("id");
		$.ajax({
			url : "http://localhost:8080/ERP/system/userDelete",
			type : "get",
			data : {
				userId : userId
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
function gotoPage(pageNum, pageSize) {
	$.ajax({
		url : "http://localhost:8080/ERP/system/getUserPageInfo",
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
function emptyModel() {
	$("[name=userId").val("");
	$("[name=username").val("");
	$("[name=account").val("");
	$("[name=roleId").val("");
	$("h4").html("新增用户");
}

function update(obj) {
	var userId = $(obj).parents("tr").find("#userId").text();
	var username = $(obj).parents("tr").find("#username").text();
	var account = $(obj).parents("tr").find("#account").text();
	$("[name=userId").val(userId);
	$("[name=username").val(username);
	$("[name=account").val(account);
	$("h4").html("修改用户");
	$.ajax({
		url : "http://localhost:8080/ERP/system/getRoleName",
		type : "get",
		data : {
			userId : userId
		},
		success : function(result) {
			if (result.status == 200) {
				$("[name=roleId").val(result.object.roleId);
			} else {
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
		html += "<tr><td ><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i + 1 + result.pageSize * (result.pageNum - 1)) + "</td>";
		html += "<td id='userId' style='display:none'>" + data[i].userId
				+ "</td>";
		html += "<td id='username' >" + data[i].username + "</td>";
		html += "<td id='account' >" + data[i].account + "</td>";
		html += "<td id='createTime' >" + data[i].createTime + "</td>";
		html += "<td ><div><button type='button' id='"
				+ data[i].userId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addUser'onclick='update(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='del(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#userList").html(html);
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

function pushData() {
	var userId = $("input[name=userId]").val();
	var username = $("input[name=username]").val();
	var account = $("input[name=account]").val();
	var roleId = $("[name=roleId]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/system/userEidt",
		type : "post",
		data : {
			userId : userId,
			username : username,
			account : account,
			roleId : roleId
		},
		success : function(result) {
			if (result.status == 200) {
				$('#addUser').modal('hide');
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
			url : "http://localhost:8080/ERP/system/userDeleBatch",
			type:"post",
			data : {
				userArry : id_arr
			},
			traditional : true,
			success : function(result) {
				if (result.status == 200) {
					refush();
				} else {
					alert(result.message);
				}
			},
			error : function(reuslt) {
				alert(reuslt.message);
			}

		})
	}
}
function remove(obj) {
	var flag = $(obj).prop("checked");
	if (flag) {
		return;
	}
	$("#ckA").attr("checked", false);
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

$(function() {
	gotoPage(1, 8);

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
})
