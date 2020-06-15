package com.carsland;

import javax.xml.bind.annotation.XmlRootElement;

public class Receipt {

	private int orderid;
	private int receiptid;
	private int carid;
	private String modelid;
	private int quantity;
	
	public int getOrderId() {
		return orderid;
	}
	
	public int getReceiptId() {
		return receiptid;
	}
	
	public int getCarId() {
		return carid;
	}
	
	public String getModelId() {
		return modelid;
	}
	
	public int getQuanity() {
		return quantity;
	}
	
	public void setOrderId(int id) {
		this.orderid = id;
	}
	
	public void setReceiptId(int id) {
		this.receiptid = id;
	}
	
	public void setCarId(int id) {
		this.carid = id;
	}
	
	public void setModelId(String id) {
		this.modelid = id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	  
}
