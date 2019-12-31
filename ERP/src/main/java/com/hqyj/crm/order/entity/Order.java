package com.hqyj.crm.order.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {
	/* 订单编号 */
	private String orderId;
	/* 下单时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderDate;
	/* 订单金额 */
	private Long orderMoney;
	/* 交货方式 */
	private String deliveryWay;
	/* 交货日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deliveryDate;
	/* 经手人 */
	private String handlerName;
	/* 操作员 */
	private String operator;
	/* 备注 */
	private String remark;
	/* 下单客户 */
	private String orderCustomer;
	/* private String */

	private String status;

	private String productName;

	private int orderNumber;

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(String orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getDeliveryWay() {
		return deliveryWay;
	}

	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Order(String orderId, Date orderDate, Long orderMoney, String deliveryWay, Date deliveryDate,
			String handlerName, String operator, String remark, String orderCustomer, String status, String productName,
			int orderNumber) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderMoney = orderMoney;
		this.deliveryWay = deliveryWay;
		this.deliveryDate = deliveryDate;
		this.handlerName = handlerName;
		this.operator = operator;
		this.remark = remark;
		this.orderCustomer = orderCustomer;
		this.status = status;
		this.productName = productName;
		this.orderNumber = orderNumber;
	}

	public Order() {
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderMoney=" + orderMoney
				+ ", deliveryWay=" + deliveryWay + ", deliveryDate=" + deliveryDate + ", handlerName=" + handlerName
				+ ", operator=" + operator + ", remark=" + remark + ", orderCustomer=" + orderCustomer + ", status="
				+ status + ", productName=" + productName + ", orderNumber=" + orderNumber + "]";
	}

}