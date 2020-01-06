package com.hqyj.crm.order.dao;

import java.util.List;

import com.hqyj.crm.order.entity.Order;

public interface OrderMapper {

    int addOrderInfo(Order record);

    /**查询所订单*/
	List<Order> queryAllOrders();

	/**货运方式*/
	List<String> queryDeliveryWay();

	/**插入订单表*/
	int addOrder(Order order);

	/**批量删除订单表*/
	int deleteOrders(String[] ids);
	
	/**批量删除订单详情表*/
	int deleteOrderInfo(String[] ids);

	/**得到已完成的订单*/
	String[] queryIdOfFinished();

	/*更改指定id的订单状态*/
	int updataStatusById(String[] ids);

	/* 查询对应订单的详情 */
	Order queryOrderInfoById(String orderId);

	Order queryOrderByOrderId(String orderId);
	
	
}