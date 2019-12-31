package com.hqyj.crm.order.service;

import java.util.List;
import java.util.Map;

import com.hqyj.crm.order.entity.Order;

/**
* @author zhaoPeng
* @createDate 2019年12月21日 下午1:54:13 
*/
public interface OrderService {

	/**查询所有的订单*/
	List<Order> queryAllOrders();

	/**加载订单表的原始数据*/
	Map<String, Object> queryDateOfOrder();

	/**添加订单表*/
	boolean addOrder(Order order);

	/**删除订单表中选中的数据*/
	int deleteOrders(String[] ids);
	
	/**得到已完成的订单id*/
	String[] queryIdOfFinished();

	/**批量审核订单*/
	Boolean revieWOrdersBatch(String[] ids);

	/* 查询对应订单的详情 */
	Order queryOrderInfoById(String orderId);

}
