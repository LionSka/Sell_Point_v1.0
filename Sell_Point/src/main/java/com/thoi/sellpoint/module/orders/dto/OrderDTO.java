package com.thoi.sellpoint.module.orders.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int orderID;
	double subTotal;
	double discount;
	double total;
	String transactionNumber;
	double cash;
	char status;
	char type;
	
	/** Código de estado para una orden que se encuentra pendiente de entrega*/
	public static char ORDER_STATUS_PENDING='P';
	/** Código de estado para una orden que ya a sido entregada*/
	public static char ORDER_STATUS_CLOSED='C';	
	/** Código de tipo para una orden, cuando es para consumir en el local */
	public static char ORDER_TYPE_INSITE='S';
	/** Código de tipo para una orden, cuando es para llevar express */
	public static char ORDER_TYPE_XPRESS='E';
	/** Código de tipo para una orden, cuando es para empacado llevar */
	public static char ORDER_TYPE_TOCARRY='C';
	
	
	public OrderDTO() {
		super();
	}
	
	public OrderDTO(int orderID, double subTotal, double discount,
			double total, String transactionNumber, double cash, char status,
			char type) {
		super();
		this.orderID = orderID;
		this.subTotal = subTotal;
		this.discount = discount;
		this.total = total;
		this.transactionNumber = transactionNumber;
		this.cash = cash;
		this.status = status;
		this.type = type;
	}
	
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
}
