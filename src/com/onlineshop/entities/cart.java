package com.onlineshop.entities;

public class cart 
{
	private int ItemNo;
	private int userId;
	private int productId;
	private String productName;
	private String productPhoto;
	private int productQuantity;
	private int productPrice;
	private int totalPrice;
	public cart(int productId, String productName, String productPhoto, int productQuantity, int productPrice,
			int totalPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productPhoto = productPhoto;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.totalPrice = totalPrice;
	}
	public cart(int productId, String productName, String productPhoto, int productQuantity, int productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productPhoto = productPhoto;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
	}
	
	public cart(int userId, int productId, String productName, String productPhoto, int productQuantity,
			int productPrice, int totalPrice) {
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.productPhoto = productPhoto;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.totalPrice = totalPrice;
	}
	
	
	
	public cart(int userId, int productId, int productQuantity, int productPrice, int totalPrice) {
		this.userId = userId;
		this.productId = productId;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.totalPrice = totalPrice;
	}
	public cart() {
	}
	
	
	
	public int getItemNo() {
		return ItemNo;
	}
	public void setItemNo(int itemNo) {
		ItemNo = itemNo;
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
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
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
	
	
	
	
	
	
}
