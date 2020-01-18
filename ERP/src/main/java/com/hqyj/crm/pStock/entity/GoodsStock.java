package com.hqyj.crm.pStock.entity;

import java.math.BigDecimal;

public class GoodsStock {
 private String goodsName;
 
 private BigDecimal price;
 
 private int stockNumber;
 
 private String provider;
 
 private String handlerName;
 
 private String category;

public String getGoodsName() {
	return goodsName;
}

public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}

public BigDecimal getPrice() {
	return price;
}

public void setPrice(BigDecimal price) {
	this.price = price;
}

public int getStockNumber() {
	return stockNumber;
}

public void setStockNumber(int stockNumber) {
	this.stockNumber = stockNumber;
}

public String getProvider() {
	return provider;
}

public void setProvider(String provider) {
	this.provider = provider;
}

public String getHandlerName() {
	return handlerName;
}

public void setHandlerName(String handlerName) {
	this.handlerName = handlerName;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}
 
 
}
