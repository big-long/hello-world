package com.hqyj.crm.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.crm.order.dao.OrderMapper;
import com.hqyj.crm.order.entity.Order;
import com.hqyj.crm.order.service.OrderService;
import com.hqyj.crm.production.dao.ClientMapper;
import com.hqyj.crm.production.dao.ProductMapper;
import com.hqyj.crm.production.entity.Client;
import com.hqyj.crm.production.entity.Product;

/**
 * @author zhaoPeng
 * @createDate 2019年12月21日 下午1:54:48
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	// 客户信息
	@Autowired
	private ClientMapper clientMapper;
	// 商品信息
	@Autowired
	private ProductMapper productMapper;

	/**
	 * 查询所以订单
	 */
	@Override
	public List<Order> queryAllOrders() {
		return orderMapper.queryAllOrders();
	}

	/** 初始化添加订单页面的数据 */
	@Override
	public Map<String, Object> queryDateOfOrder() {
		// 客户信息
		List<Client> clientsList = clientMapper.queryAllClients();
		// 产品信息
		List<Product> productsList = productMapper.selectProducts();
		// 货运方式
		List<String> deliveryWayList = orderMapper.queryDeliveryWay();
		// 将这三种数据放入map中
		HashMap<String, Object> orderDate = new HashMap<String, Object>();
		orderDate.put("clients", clientsList);
		orderDate.put("products", productsList);
		orderDate.put("deliveryWays", deliveryWayList);
		return orderDate;
	}

	/**
	 * 添加订单表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrder(Order order) {
		int flag = orderMapper.addOrder(order);
		if (flag != 0) {
			// 添加订单详情表
			int res = orderMapper.addOrderInfo(order);
			if (res != 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 批量删除选中的订单记录
	 */
	@Override
	public int deleteOrders(String[] ids) {
		// 得到已完成的订单id
		String[] finnishedId = orderMapper.queryIdOfFinished();
		// 声明
		ArrayList<String> idArray = new ArrayList<String>();
		// 传过来的订单编号如果是未发货的订单则不能删除
		for (int i = 0; i < finnishedId.length; i++) {
			for (int j = 0; j < ids.length; j++) {
				if (ids[j].equals(finnishedId[i])) {
					idArray.add(ids[j]);
				}
			}
		}

		if (ids.length != idArray.size()) {
			return -1;
		}
		int res = orderMapper.deleteOrderInfo(ids);
		if (res == 0) {
			return 0;
		}
		int flag = orderMapper.deleteOrders(ids);
		if (flag == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * 得到已完成的订单id
	 */
	@Override
	public String[] queryIdOfFinished() {

		return orderMapper.queryIdOfFinished();
	}

	/**
	 * 批量审核订单
	 */
	@Override
	public Boolean revieWOrdersBatch(String[] ids) {
		// 将穿送过来的订单id状态改变
		int flag = orderMapper.updataStatusById(ids);
		if (flag == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 查询对应订单的详情
	 */
	@Override
	public Order queryOrderInfoById(String orderId) {
		Order orderInfo = orderMapper.queryOrderInfoById(orderId);
		if (orderInfo != null) {
			return orderInfo;
		}
		return null;
	}
}
