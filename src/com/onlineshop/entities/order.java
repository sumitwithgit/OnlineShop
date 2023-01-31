package com.onlineshop.entities;

public class order
{
	private int orderId;
	private int userId;
	private int productId;
	private String productName;
	private int productQuantity;
	private int productPrice;
	private int totalPrice;
	private String address;
	private String city;
	private String state;
	private String country;
	private int mobile;
	private int pincode;
	private String paymentOption;
	private String Tid;
	private String orderStatus;
	
	
	
	
	

	public order(int userId, int productId, String productName, int productQuantity, int productPrice, int totalPrice,
			String address, String city, String state, String country, int mobile, int pincode, String paymentOption,
			String tid, String orderStatus) {
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.totalPrice = totalPrice;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.mobile = mobile;
		this.pincode = pincode;
		this.paymentOption = paymentOption;
		Tid = tid;
		this.orderStatus = orderStatus;
	}



	public order() {
	}



	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getTid() {
		return Tid;
	}

	public void setTid(String tid) {
		Tid = tid;
	}
	
	
}
