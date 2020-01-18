package com.hqyj.crm.pStock.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pickout {
    private String pickoutId;
    
    private String goodsName;
    
    private String provider;

    private Integer pickNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
   	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pickoutDate;

    private String plId;

//    private String orderId;
    
    private Integer stockNumber;

    private String handlerName;

    private String operator;

    private String remark;

    public String getPickoutId() {
        return pickoutId;
    }

    public void setPickoutId(String pickoutId) {
        this.pickoutId = pickoutId;
    }

    public Integer getPickNumber() {
        return pickNumber;
    }

    public void setPickNumber(Integer pickNumber) {
        this.pickNumber = pickNumber;
    }

    public Date getPickoutDate() {
        return pickoutDate;
    }

    public void setPickoutDate(Date pickoutDate) {
        this.pickoutDate = pickoutDate;
    }

    public String getPlId() {
        return plId;
    }

    public void setPlId(String plId) {
        this.plId = plId;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}



	public Integer getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
    
}