package com.hqyj.crm.pStock.entity;





import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockInfo {
    private String inbillId;
    
    private String orderId;

	private String productionId;
   
	private Integer inbillNumber;
    
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date inbillDate;

    private String handlerName;

	private String remark;

	public String getInbillId() {
		return inbillId;
	}

	public void setInbillId(String inbillId) {
		this.inbillId = inbillId;
	}
	
    public Integer getInbillNumber() {
        return inbillNumber;
    }

    public void setInbillNumber(Integer inbillNumber) {
        this.inbillNumber = inbillNumber;
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
    
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductionId() {
		return productionId;
	}

	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}   
}