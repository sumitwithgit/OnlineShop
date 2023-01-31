package com.onlineshop.entities;

public class user
{
	private int id;
	private String name;
	private String email;
	private String password;
	private int mobile;
	private String profile;
	private String address="";
	private String city="";
	private String state="";
	private String country="";
	private int pincode;
	private String userType;
	public user(int id, String name, String email, String password, int mobile,String profile, String address, String city,
			String state, String country, int pincode,String userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.profile=profile;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.userType=userType;
	}
	
	
	
	
	
	





	public user(String email, String address, String city, String state, String country, int pincode) {		this.id = id;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}











	public user(int id, String name, String password, int mobile, String profile, String address, String city,
			String state, String country, int pincode) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.profile = profile;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	
	
	public user(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public user() {
	}

	public user(String name, String email, String password, int mobile) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}
	
	

	public user(int id, String name, int mobile, String profile) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.profile = profile;
	}

	public user(String name, String email, String password, int mobile,String profile,String userType) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.profile=profile;
		this.userType=userType;
	}
	public user(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public user(int id, String name, String email, String password, int mobile,String profile,String userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.profile=profile;
		this.userType=userType;
	}
	
	public user(int id, String name, String password, int mobile, String profile) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.profile = profile;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
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
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
}
