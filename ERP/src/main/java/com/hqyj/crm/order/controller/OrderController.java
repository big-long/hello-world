package com.hqyj.crm.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.order.entity.Order;
import com.hqyj.crm.order.service.OrderService;

/**
* @author zhaoPeng
* @createDate 2019年12月21日 下午1:53:23 
*/
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 
	 * @Title: toOrderList
	 * @Description: TODO 跳转到订单表列表
	 * @param @return 参数
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/toOrderList")
	public String toOrderList() {
		return "order/orderList";
	}
	
	/**
	 * 
	 * @Title: orderList
	 * @Description: TODO:查询所有的订单信息
	 * @param @return 参数
	 * @return List<Order> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/orderList",method = RequestMethod.GET)
	@ResponseBody
	public List<Order> orderList(){
		return orderService.queryAllOrders();
	}
	/**
	 * 
	 * @Title: loadDateOfOrder
	 * @Description: TODO:添加订单页面的数据加载
	 * @param @return 参数
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/loadDateOfOrder")
	@ResponseBody
	public Map<String,Object> loadDateOfOrder(){
		return orderService.queryDateOfOrder();
	}
	/**
	 * 
	 * @Title: generateOrderId
	 * @Description: TODO :通过时间戳生成订单表编号
	 * @param @return 参数
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/generateOrderId")
	@ResponseBody
	public String generateOrderId() {
		return "ORD"+System.currentTimeMillis();
	}
	/**
	 * 
	 * @Title: addRole
	 * @Description: TODO:添加订单表
	 * @param @return 参数
	 * @return boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/addOrder",method = RequestMethod.POST)
	@ResponseBody
	public boolean addOrder(Order order) {
		return orderService.addOrder(order);
	}
	
	/**
	 * 
	 * @Title: deleteOrders
	 * @Description: TODO：删除选中的id
	 * @param @param ids
	 * @param @return 参数
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value="/deleteOrders",method = RequestMethod.POST)
	@ResponseBody
	public int deleteOrders(@RequestBody String[] ids) {
		return orderService.deleteOrders(ids);	
	}
	
	/**
	 * 
	 * @Title: revieWOrdersBatch
	 * @Description: TODO 批量审核订单
	 * @param @param ids
	 * @param @return 参数
	 * @return Boolean 返回类型
	 * @throws
	 */
	@PostMapping(value = "/revieWOrders")
	@ResponseBody
	public Boolean revieWOrdersBatch(@RequestBody String[] ids) {
		return orderService.revieWOrdersBatch(ids);
	}
	
	/**
	 * 
	 * @Title: queryOrderInfoById
	 * @Description: TODO:查询对应订单的详情
	 * @param @param orderId
	 * @param @return 参数
	 * @return Order 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/queryOrderInfoById",method = RequestMethod.GET)
	@ResponseBody
	public Order queryOrderInfoById(String orderId){
		return orderService.queryOrderInfoById(orderId);
	}

}
