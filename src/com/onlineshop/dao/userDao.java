package com.onlineshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.onlineshop.entities.user;

public class userDao 
{
	private Connection con;

	public userDao(Connection con) {
		this.con = con;
	}
	
	public boolean registerUser(user user)
	{
		boolean f= false;
		try {
			String q="insert into user(name,email,password,mobile) values(?,?,?,?)";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getEmail());
			psmt.setString(3, user.getPassword());
			psmt.setInt(4, user.getMobile());
			psmt.executeUpdate();
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public user loginUser(String email,String password)
	{
		user user=null;
		try {
			String q="select * from user where email=? and password=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setString(1, email);
			psmt.setString(2, password);
			
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				user=new user();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setMobile(rs.getInt(5));
				user.setProfile(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setCity(rs.getString(8));
				user.setState(rs.getString(9));
				user.setCountry(rs.getString(10));
				user.setPincode(rs.getInt(11));
				user.setUserType(rs.getString(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public boolean editProfile(user user)
	{
		boolean f=false;
		try {
			String q="update user set name=?,password=?,mobile=?,profile=?,address=?,city=?,state=?,country=?,pincode=? where id=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getPassword());
			psmt.setInt(3, user.getMobile());
			psmt.setString(4, user.getProfile());
			psmt.setString(5, user.getAddress());
			psmt.setString(6, user.getCity());
			psmt.setString(7, user.getState());
			psmt.setString(8, user.getCountry());
			psmt.setInt(9, user.getPincode());
			psmt.setInt(10, user.getId());
			
			psmt.executeUpdate();
			
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
	}
	
	public user getUserDetailsById(int id)
	{
		user user=null;
		try {
			String q="select * from user where id=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, id);
			
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
				user=new user();
				
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setMobile(rs.getInt(5));
				user.setProfile(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setCity(rs.getString(8));
				user.setState(rs.getString(9));
				user.setCountry(rs.getString(10));
				user.setPincode(rs.getInt(11));
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	
	public boolean updatePersonalDetails(user user)
	{
		boolean f=false;
		try {
			String q="update user set name=?, mobile=?, profile=? where id=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setString(1, user.getName());
			psmt.setInt(2, user.getMobile());
			psmt.setString(3, user.getProfile());
			psmt.setInt(4, user.getId());
			
			psmt.executeUpdate();
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	public boolean updatePassword(user user)
	{
		boolean f=false;
		try {
			String q="update user set password=? where id=? and email=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			
			psmt.setString(1, user.getPassword());
			psmt.setInt(2, user.getId());
			psmt.setString(3, user.getEmail());
			
			psmt.executeUpdate();
			
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	
	
	public boolean updateAddress(user user)
	{
		boolean f=false;
		try {
			String q="update user set address=?,city=?,state=?,country=?,pincode=? where email=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			
			psmt.setString(1, user.getAddress());
			psmt.setString(2, user.getCity());
			psmt.setString(3, user.getState());
			psmt.setString(4, user.getCountry());
			psmt.setInt(5, user.getPincode());
			
//			psmt.setInt(6, user.getId());
			psmt.setString(6, user.getEmail());
			psmt.executeUpdate();
			
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
