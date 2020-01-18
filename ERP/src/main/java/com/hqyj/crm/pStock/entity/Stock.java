package com.hqyj.crm.pStock.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Stock {
    private String inbillId;

    private Integer stockNumber;

    private String operator;

    private Integer inbillNumber;

    private String productionId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
   	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inbillDate;

    private String handlerName;

    private String productName;
    
    private String provider;
    
    private String remark;
    
    

    public String getInbillId() {
        return inbillId;
    }

    public void setInbillId(String inbillId) {
        this.inbillId = inbillId;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getInbillNumber() {
        return inbillNumber;
    }

    public void setInbillNumber(Integer inbillNumber) {
        this.inbillNumber = inbillNumber;
    }

    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }

    public Date getInbillDate() {
        return inbillDate;
    }

    public void setInbillDate(Date inbillDate) {
        this.inbillDate = inbillDate;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
    
    
}