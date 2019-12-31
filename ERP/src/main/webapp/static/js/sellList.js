/**
 * 
 */
/**
 * 
 */
function pushData() {
	var sellId = $("input[name=sellId]").val();
	var sellDate = $("input[name=sellDate]").val();
	var productName = $("input[name=productName]").val();
	var customerName = $("input[name=customerName]").val();
	var sellNumber = $("input[name=sellNumber]").val();
	var totalMoney = $("input[name=totalMoney]").val();
	var orderId = $("input[name=orderId]").val();
	var settlementWay = $("[name=settlementWay]").val();
	var label = document.getElementById("sellStatus");
	var sellStatus = label.innerText.trim();
	var handlerName = $("input[name=handlerName]").val();
	var Operator = $("input[name=Operator]").val();
	$.ajax({
		url : "http://localhost:8080/ERP/PSell/sellAdd",
		type : "get",
		data : {
			sellId : sellId,
			sellDate : sellDate,
			productName : productName,
			customerName : customerName,
			sellNumber : sellNumber,
			orderId : orderId,
			totalMoney : totalMoney,
			settlementWay : settlementWay,
			handlerName : handlerName,
			Operator : Operator,
			sellStatus : sellStatus
		},
		success : function(data) {
			window.location.href = "http://localhost:8080/ERP/PSell/sellList";
		},
		error : function(data) {

		}
	})
}

function DelSelect() {
	var Checkbox = false;
	$("input[name=sellId3]").each(function() {
		if (this.checked == true) {
			Checkbox = true;
		}
	});
	if (Checkbox) {
		var t = confirm("您确认要删除选中的内容吗？");
		if (t == false){ 
			return false;
		}
		//发送ajax请求，批量删除
		//取得选中的id
		//定义一个数组，用于接收选中行的id
		var sellIdArray=new Array();
		
		$("input[name=sellId3]").each(function(){
			
			if (this.checked) {
				//alert($(this).val());
				sellIdArray.push($(this).val());
			} 
		});
	
		//发送ajax请求
		$.ajax({
		url:"http://localhost:8080/ERP/PSell/sellDeleBatch",
		type:"POST",
		traditional : true,
		data:{"sellArray":sellIdArray},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("删除成功！");
				$("input[name=sellId3]").each(function(){
					if (this.checked) {
						$(this).parent().parent().remove();
					} 
				});
			}else{
				alert("删除失败！");
			} 
		},
		err:function(data){
			alert("请求失败，稍后再试");
		}
		});
		/* $("#listform").submit(); */
	} else {
		alert("请选择您要删除的内容!");
		return false;
	}
}

$(function(){
	// $("[name=add]").bind("click", function() {
	//
	// /* $("h4").text("修改客户信息"); */
	// $("[name=userId]").val("");
	// $("[name=username]").val("");
	// $("[name=account]").val("");
	// $("[name=password]").val("");
	// $("[name=roleId]").val("");
	//			
	// })

	$("[name=update]").bind("click", function() {
		// 爬取所需要的数据
		var sellId = $(this).parents("tr").find("#sellId").text();
		var productName = $(this).parents("tr").find("#productName").text();
		var customerName = $(this).parents("tr").find("#customerName").text();
		var sellNumber = $(this).parents("tr").find("#sellNumber").text();
		var sellDate = $(this).parents("tr").find("#sellDate").text();
		var sellStatus = $(this).parents("tr").find("#sellStatus").text();
		var status = $(this).parents("tr").find("#status").text();
//		

		$("h4").text("修改客户信息");
		$("input[name=sellId2]").val(sellId);
		$("input[name=productName2]").val(productName);
		$("input[name=customerName2]").val(customerName);
		$("input[name=sellNumber2]").val(sellNumber);
		$("input[name=sellDate2]").val(sellDate);
		$("input[name=sellStatus2]").val(sellStatus);
		$("input[name=status2]").val(status);
	})

})


