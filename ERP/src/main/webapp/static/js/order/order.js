//加载订单表格
$(document).ready(function() {
		$("#jqGrid").jqGrid({
			url : 'http://localhost:8080/ERP/order/orderList',
			mtype : "GET",
			styleUI : 'Bootstrap',
			caption : '订单列表',
			datatype : "json",
			colModel : [ {
				label : '订单编号id',
				name : 'orderId',
				key : true,
				width : 150
			}, {
				label : '下单客户',
				name : 'orderCustomer',
				width : 200,
				editable : true
			}, {
				label : '产品名称',
				name : 'productName',
				width : 150,
				editable : true
			}, {
				label : '订单数量',
				name : 'orderNumber',
				width : 150
			}, {
				label : '订单状态',
				name : 'status',
				width : 100,
				formatter: statusFormatter
			} ],
			sortname : 'orderId',
			/*  cellEdit:true, */
			//定义对form编辑时的url
			editurl : 'clientArray',
			viewrecords : true,
			loadonce : true,
			reloadAfterSubmit: true,
			width : 1000,
			height : 300,
			//每行前面添加选择框
			multiselect : true,
			rowNum : 10,
			rownumbers : true,
			rownumWidth : 35,
			pager : "#jqGridPager",
//			++++++++++++++++++以下为新添加的+++++++++++++++++++
			subGrid: true, // set the subGrid property to true to show expand buttons for each row
			subgridtype: 'json', // set the subgrid type to json
			 // (2)子表格容器的id和需要展开子表格的行id，将传入此事件函数 
			subGridRowExpanded: function(subgrid_id, row_id) {
				// (3)根据subgrid_id定义对应的子表格的table的id  
				var subgrid_table_id;  
	            subgrid_table_id = subgrid_id + "_t";
	            //(4)根据subgrid_id定义对应的子表格的pager的id  
	            var subgrid_pager_id;  
	            subgrid_pager_id = subgrid_id + "_pgr";
	         // (5)动态添加子报表的table和pager  
	            $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+subgrid_pager_id+"' class='scroll'></div>");
				
	         // (6)创建jqGrid对象   
	            $("#" + subgrid_table_id).jqGrid({ 
	            	 // (7)子表格数据对应的url，注意传入的对应行的id参数  
	            	 url: 'http://localhost:8080/ERP/order/queryOrderInfoById?orderId='+row_id, 
	            	 mtype : 'GET',
	            	 styleUI : 'Bootstrap',
	                 caption : '订单详情列表',
		       		 datatype : "json",
	                 colModel: [  
	                	 {
	         				label : '订单编号',
	         				name : 'orderId',
	         				key : true,
	         				width : 100
	         			},
	         			{
	         				label : '下单时间',
	         				name : 'orderDate',
	         				width : 120
	         			},
	         			{
	         				label : '订单金额',
	         				name : 'orderMoney',
	         				width : 100
	         			},
	         			{
	         				label : '运输方式',
	         				name : 'deliveryWay',
	         				width : 80
	         			},
	         			{
	         				label : '交货时间',
	         				name : 'deliveryDate',
	         				width : 80
	         			},
	         			{
	         				label : '经手人',
	         				name : 'handlerName',
	         				width : 80
	         			},
	         			{
	         				label : '操作员',
	         				name : 'operator',
	         				width : 80
	         			},
	         			{
	         				label : '备注',
	         				name : 'remark',
	         				width : 80
	         			}
	                   ],
                    jsonReader: {   // (8)针对子表格的jsonReader设置  
                       root:"gridModel",  
                       records: "record",  
                       repeatitems : false  
                   },  
                   loadonce : true,
                   multiselect : true,
                   viewrecords: true,  
                   height: "100%",  
                   pager:"#"+subgrid_pager_id, 
	            });
			}
			
		});

		$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
			"overflow-x" : "scroll"
		});
		$('#jqGrid').navGrid("#jqGridPager", {
			edit : true,
			add : false,
			del : true,
			refresh : false,
			view : false
		});
		$('#jqGrid').inlineNav('#jqGridPager',
		// the buttons to appear on the toolbar of the grid
		{
			edit : false,
			add : false,
			del : false,
			cancel : false,
			editParams : {
				keys : true,
			},
			addParams : {
				keys : true
			}
		});
		
		//加载日历控件
		 $('#orderDate').datetimepicker({
	        format: 'YYYY-MM-DD hh:mm:ss',
	        locale: moment.locale('zh-cn')
	    });
		$("#deliveryDate").datetimepicker({
			format: 'YYYY-MM-DD hh:mm:ss',
	        locale: moment.locale('zh-cn')
		});

	});
	//显示状态按钮
	function statusFormatter(cellvalue) {
	    if (cellvalue == 0) {
	        return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 50%;\">未发货</button>";
	    }
	    else if (cellvalue == 1) {
	        return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 50%;\">已发货</button>";
	    }
	}
	
	function addOrderPage(){
		$("#myModalLabel").text("添加新订单");
		$("#updateBtn").addClass("hide"); 
		$("#addBtn").removeClass("hide");
		$.ajax({
			url:"http://localhost:8080/ERP/order/loadDateOfOrder",
			type:"GET",
			dataType:"json",
			success:function(data){
				if(data!=null){
					//下单客户
					var html1="<option value=''>--请选择下单客户--</option>";
					$.each(data.clients,function(i,val){
						html1+="<option value="+val.clientName+">"+val.clientName+"</option>";
					});
					$("#orderCustomer").html(html1);
					//产品名称
					var html2="<option value=''>--请选择产品名称--</option>";
					$.each(data.products,function(i,val){
						html2+="<option value="+val.productName+">"+val.productName+"</option>";
					});
					$("#productName").html(html2);
					
					//货运方式
					var html3="<option value=''>--请选择货运方式--</option>";
					$.each(data.deliveryWays,function(i,val){
						html3+="<option value="+val+">"+val+"</option>";
					});
					$("#deliveryWay").html(html3);
				}
				//再次发送ajax请求得到订单编号
				$.ajax({
					url:"http://localhost:8080/ERP/order/generateOrderId",
					type:"GET",
					dataType:"text",
					success:function(data){
						//将订单编号放入输入框中
						$("#orderId").val(data);
					},
					err:function(data){
						swal("请求失败，稍后再试", {
		                    icon: "error",
		                });
					}
				});
			},
			err:function(data){
				swal("请求失败，稍后再试", {
                    icon: "error",
                });
			}
		});
	}
	//添加订单
	function addOrder(){
		var productName=$("#productName").val();
		var orderCustomer=$("#orderCustomer").val();
		var orderId=$("#orderId").val();
		var orderDate=$("#orderDate").val();
		var orderNumber=$("#orderNumber").val();
		var orderMoney=$("#orderMoney").val();
		var deliveryWay=$("#deliveryWay").val();
		var deliveryDate=$("#deliveryDate").val();
		var handlerName=$("#handlerName").val();
		var operator=$("#operator").val();
		var remark=$("#remark").val();
		if (productName!=""
			&&orderCustomer!=""
				&&orderId!="" 
					&&orderDate!=""
						&&orderNumber!=""
							&&orderMoney!=""
								&&deliveryWay!=""
									&&deliveryDate!=""
										&&handlerName!=""
											&&operator!=""){
			//发送ajax请求，添加数据
			//再次发送ajax请求得到订单编号
			$.ajax({
				url:"http://localhost:8080/ERP/order/addOrder",
				type:"POST",
				data:{
					"productName":productName,
					"orderCustomer":orderCustomer,
					"orderId":orderId,
					"orderDate":orderDate,
					"orderNumber":orderNumber,
					"orderMoney":orderMoney,
					"deliveryWay":deliveryWay,
					"deliveryDate":deliveryDate,
					"handlerName":handlerName,
					"deliveryDate":deliveryDate,
					"operator":operator,
					"remark":remark
				},
				success:function(data){
					if (data==true) {
					  swal("提交成功", {
                          icon: "success",
                      });
						$(".form-control").val("");
						addOrderPage();
						 $("#jqGrid").setGridParam({datatype:'json'}).trigger('reloadGrid');
					}else{
						swal("提交失败", {
                            icon: "error",
                        });
					}
				},
				err:function(data){
					swal("请求失败，稍后再试", {
                        icon: "error",
                    });
				}
			});
		} else {
			swal("请将信息填写完整！", {
                icon: "warning",
            });
		}
	}
	
	
	
	/**
	 * 获取jqGrid选中的多条记录
	 * @returns {*}
	 */
	function getSelectedRows() {
	    var grid = $("#jqGrid");
	    var rowKey = grid.getGridParam("selrow");
	    if (!rowKey) {
	        swal("请选择一条记录", {
	            icon: "warning",
	        });
	        return;
	    }
	    return grid.getGridParam("selarrrow");
	}
	
	/**
	 * jqGrid重新加载
	 */
	function reload() {
	    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
	    $("#jqGrid").jqGrid('setGridParam', {page: page}).trigger("reloadGrid");
	}
	
	
	//刪除记录
	function deleteOrders() {
		//获取选中的行
	    var ids = getSelectedRows();
	    if (ids == null) {
	        return;
	    }
	    swal({
	        title: "确认弹框",
	        text: "数据尤珍贵，且删且珍惜?",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/ERP/order/deleteOrders",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    datatype:"text",
                    success: function (data) {
                        if (data == 1) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").setGridParam({datatype:'json'}).trigger('reloadGrid');
                          /*  $("#jqGrid").trigger("reloadGrid");*/
                        } else if(data==-1) {
                            swal("还未发货的订单不能删除！", {
                                icon: "warning",
                            });
                        }else{
                        	 swal("删除失败", {
                                 icon: "error",
                             });
                        }
                    }
                });
            }
	        }
	    );
	}
	
	//审核订单
	function revieWOrders(){
		//获取选中的行
		var ids=getSelectedRows();
		if (ids == null) {
	        return;
	    }
		swal({
	        title: "确认弹框",
	        text: "确认这些订单已发货吗?",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/ERP/order/revieWOrders",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (data) {
                        if (data == true) {
                            swal("审核成功", {
                                icon: "success",
                            });
                            /*$("#jqGrid").trigger("reloadGrid");*/
                            //重新加载数据
                            $("#jqGrid").setGridParam({datatype:'json'}).trigger('reloadGrid');
                        } else {
                            swal("审核失败", {
                                icon: "error",
                            });
                        }
                    }
                });
            }
	     });
	 }
