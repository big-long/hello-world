package com.hqyj.crm.pStock.entity;

public class Stock {
	private String inbillId;
	private String productName;
	private String productionPlace;
	private Integer repertoryNumber;
	private String operator;
	public String getInbillId() {
		return inbillId;
	}
	public void setInbillId(String inbillId) {
		this.inbillId = inbillId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductionPlace() {
		return productionPlace;
	}
	public void setProductionPlace(String productionPlace) {
		this.productionPlace = productionPlace;
	}
	
	public Integer getRepertoryNumber() {
		return repertoryNumber;
	}
	public void setRepertoryNumber(Integer repertoryNumber) {
		this.repertoryNumber = repertoryNumber;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	

}
