package com.onlineshop.entities;

public class category 
{
	private int cId;
	private String cName;
	private String cDesc;
	public category(int cId, String cName, String cDesc) {
		this.cId = cId;
		this.cName = cName;
		this.cDesc = cDesc;
	}
	public category() {
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcDesc() {
		return cDesc;
	}
	public void setcDesc(String cDesc) {
		this.cDesc = cDesc;
	}
	
	
}
