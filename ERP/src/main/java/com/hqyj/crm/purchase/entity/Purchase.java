package com.hqyj.crm.purchase.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Purchase {
	private String purchaseId;

	private String goodsName;

	private Integer purchaseNumber;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
   	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date purchaseDate;

	private String status;
	
	private String category;
	
	private String goodsId;
	
	private String goodsNickname;
	
	private String price;

	private String providerName;

	private Long purchaseAmount;

	private Long amountPayable;

	private Long amountPaid;

	private Long unpaidAmount;

	private String settlementWay;

	private String handlerName;

	private String operator;

	private String remark;

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getStatus() {
		return status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNickname() {
		return goodsNickname;
	}

	public void setGoodsNickname(String goodsNickname) {
		this.goodsNickname = goodsNickname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public Long getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Long purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Long getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(Long amountPayable) {
		this.amountPayable = amountPayable;
	}

	public Long getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Long amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Long getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(Long unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}

	public String getSettlementWay() {
		return settlementWay;
	}

	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
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
}