package com.onlineshop.entities;

public class product
{
	private int pId;
	private String pName;
	private String pDescription;
	private String pPhoto;
	private int pPrice;
	private String pCategory;
	
	public product() {
	}
	public product(int pId, String pName, String pDescription, String pPhoto, int pPrice, String pCategory) {
		this.pId = pId;
		this.pName = pName;
		this.pDescription = pDescription;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pCategory = pCategory;
	}
	public product(String pName, String pDescription, String pPhoto, int pPrice, String pCategory) {
		this.pName = pName;
		this.pDescription = pDescription;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pCategory = pCategory;
	}
	
	public product(int pId, String pName, String pDescription, String pPhoto, int pPrice) {
		this.pId = pId;
		this.pName = pName;
		this.pDescription = pDescription;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDescription() {
		return pDescription;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}
	public String getpPhoto() {
		return pPhoto;
	}
	public void setpPhoto(String pPhoto) {
		this.pPhoto = pPhoto;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getpCategory() {
		return pCategory;
	}
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	
	
	
}
