//关键字查询
function changeSearch() {
	var keyWord = $("[name=keyword]").val();
	var category = $("[name=type]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/goods/getGoodsByKeyWord",
		type : "get",
		data : {
			keyWord : keyWord,
			category: category
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
function remove(obj){
	var flag=$(obj).prop("checked");
	if(flag){
		return;
	}
	$("#ckA").attr("checked",false);
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
			url : "http://localhost:8080/ERP/goods/deleteMany",
			type : "get",
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
		url : "http://localhost:8080/ERP/goods/getPageInfo",
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
// 加载页面
function loadPage(result) {
	var data = result.list;
	var html = ""
	for (var i = 0; i < data.length; i++) {
		html += "<tr><td  width=''><input type='checkbox' onchange='remove(this)' name='id[]'/>"
				+ (i+1+result.pageSize*(result.pageNum-1)) + "</td>";
		html += "<td id='goodsId'style='display:none'>"
			+ data[i].goodsId + "</td>";
		html += "<td id='goodsName' width=''>" + data[i].goodsName
				+ "</td>";
		html += "<td id='productionPlace' width=''>" + data[i].productionPlace
				+ "</td>";
		html += "<td id='unit' width=''>" + data[i].unit + "</td>";
		html += "<td id='size' width=''>" + data[i].size + "</td>";
		html += "<td id='batchNumber' width=''>" + data[i].batchNumber
				+ "</td>";
		html += "<td ><div><button type='button' id='"
				+ data[i].goodsId
				+ "' class='button border-main border-little icon-plus-square-o' data-toggle='modal' data-target='#addGoods'onclick='getData(this)' >修改</button>";
		html += "<button type='button' class='button border-red button-little' onclick='deleteOne(this)'> <span class='icon-trash-o'></span> 删除 </button> </div></td> </tr>";
	}
	$("#goodsList").html(html);
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
		var goodsId = $(obj).parents("tr").find("#goodsId").text();
		$.ajax({
			url : "http://localhost:8080/ERP/goods/deleteGoods",
			type : "get",
			data : {
				goodsId : goodsId
			},
			success : function(result) {
				if(result.status==200){
					refush();
				}else{
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
	$(".modal-title").html("修改商品信息");
	var goodsId = $(obj).parents("tr").find("#goodsId").text();
	var goodsName = $(obj).parents("tr").find("#goodsName").text();
	var productionPlace = $(obj).parents("tr").find("#productionPlace").text();
	var unit = $(obj).parents("tr").find("#unit").text();
	var size = $(obj).parents("tr").find("#size").text();
	var batchNumber = $(obj).parents("tr").find("#batchNumber").text();
	$.ajax({
		url : "http://localhost:8080/ERP/goods/getGoodsInfo",
		type : "get",
		data : {
			goodsId : goodsId
		},
		success : function(result) {
			if(result.status==200){
				var goods=result.object;
				$("[name=goodsId]").val(goodsId);
				$("[name=goodsName]").val(goodsName);
				$("[name=goodsNickname]").val(goods.goodsNickname);
				$("[name=productionPlace]").val(productionPlace);
				$("[name=unit]").val(unit);
				$("[name=size]").val(size);
				$("[name=pack]").val(goods.pack);
				$("[name=batchNumber]").val(batchNumber);
				$("[name=approvalNumber]").val(goods.approvalNumber);
				$("[name=provider]").val(goods.provider);
				$("[name=category]").val(goods.category);
				$("[name=price]").val(goods.price);
				$("[name=remark]").val(goods.remark);
			}else{
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
	$(".modal-title").html("添加新的商品资料");
	$("[name=goodsId]").val("");
	$("[name=goodsName]").val("");
	$("[name=goodsNickname]").val("");
	$("[name=productionPlace]").val("");
	$("[name=unit]").val("");
	$("[name=size]").val("");
	$("[name=pack]").val("");
	$("[name=batchNumber]").val("");
	$("[name=approvalNumber]").val("");
	$("[name=provider]").val("");
	$("[name=category]").val("");
	$("[name=price]").val("");
	$("[name=remark]").val("");
}
// 将新增或修改的数据发送到后台
function pushData() {
	var obj=$(".check-error");
	if(obj.length!=0){
		return;
	}
	var goodsId = $("[name=goodsId]").val();
	var goodsName = $("[name=goodsName]").val();
	var goodsNickname = $("[name=goodsNickname]").val();
	var productionPlace = $("[name=productionPlace]").val();
	var unit = $("[name=unit]").val();
	var size = $("[name=size]").val();
	var pack = $("[name=pack]").val();
	var batchNumber = $("[name=batchNumber]").val();
	var approvalNumber = $("[name=approvalNumber]").val();
	var provider = $("[name=provider]").val();
	var category = $("[name=category]").val();
	var price = $("[name=price]").val();
	var remark = $("[name=remark]").val();

	$.ajax({
		url : "http://localhost:8080/ERP/goods/updateGoods",
		type : "get",
		data : {
			goodsId : goodsId,
			goodsName : goodsName,
			goodsNickname : goodsNickname,
			productionPlace : productionPlace,
			unit : unit,
			size : size,
			pack : pack,
			batchNumber : batchNumber,
			approvalNumber : approvalNumber,
			provider : provider,
			category : category,
			price : price,
			remark : remark
		},
		success : function(result) {
			if(result.status==200){
				$('#addGoods').modal('hide');
				refush();
			}else{
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
	$("#addGoods").on("hide.bs.modal",function(){
		$(".field").each(function(){
			$(this).removeClass("check-error");
			$(this).removeClass("check-success");
			$(this).find(".input-help").remove();
		})
	})
})
