package com.onlineshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.onlineshop.entities.order;

public class orderDao 
{
	private Connection con;

	public orderDao(Connection con) {
		this.con = con;
	}
	
	public boolean orderInProcess(order order)
	{
		boolean f=false;
		try {
			String q="insert into ordertable(userId,productId,productName,productQuantity,productPrice,totalPrice,address,city,state,country,mobile,pincode,paymentOption,Tid,orderStatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, order.getUserId());
			psmt.setInt(2, order.getProductId());
			psmt.setString(3, order.getProductName());
			psmt.setInt(4, order.getProductQuantity());
			psmt.setInt(5, order.getProductPrice());
			psmt.setInt(6, order.getTotalPrice());
			psmt.setString(7, order.getAddress());
			psmt.setString(8, order.getCity());
			psmt.setString(9, order.getState());
			psmt.setString(10, order.getCountry());
			psmt.setInt(11, order.getMobile());
			psmt.setInt(12, order.getPincode());
			psmt.setString(13, order.getPaymentOption());
			psmt.setString(14, order.getTid());
			psmt.setString(15, order.getOrderStatus());
			psmt.executeUpdate();
			
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	
	public ArrayList<order> getAllOrderDetailsByUserId(int userId)
	{
		ArrayList<order> olist=new ArrayList<order>();
		try {
			String q="select * from ordertable where userId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, userId);
			ResultSet rs=psmt.executeQuery();
			order order=null;
			while(rs.next())
			{
				order=new order();
				order.setOrderId(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setProductId(rs.getInt(3));
				order.setProductName(rs.getString(4));
				order.setProductQuantity(rs.getInt(5));
				order.setProductPrice(rs.getInt(6));
				order.setTotalPrice(rs.getInt(7));
				order.setAddress(rs.getString(8));
				order.setCity(rs.getString(9));
				order.setState(rs.getString(10));
				order.setCountry(rs.getString(11));
				order.setMobile(rs.getInt(12));
				order.setPincode(rs.getInt(13));
				order.setPaymentOption(rs.getString(14));
				order.setTid(rs.getString(15));
				order.setOrderStatus(rs.getString(16));
				
				
				olist.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return olist;
	}
	
	
	public ArrayList<order> getAllOrders()
	{
		ArrayList<order> olist=new ArrayList<order>();
		try {
			String q="select * from ordertable where orderStatus='processing'";
			PreparedStatement psmt=this.con.prepareStatement(q);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				order order=new order();
				order.setOrderId(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setProductId(rs.getInt(3));
				order.setProductName(rs.getString(4));
				order.setProductQuantity(rs.getInt(5));
				order.setProductPrice(rs.getInt(6));
				order.setTotalPrice(rs.getInt(7));
				order.setAddress(rs.getString(8));
				order.setCity(rs.getString(9));
				order.setState(rs.getString(10));
				order.setCountry(rs.getString(11));
				order.setMobile(rs.getInt(12));
				order.setPincode(rs.getInt(13));
				order.setPaymentOption(rs.getString(14));
				order.setTid(rs.getString(15));
				order.setOrderStatus(rs.getString(16));
				
				olist.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist;
	}
	
	
	public boolean updateAcceptOrdersByOrderIdAndOrderStatus(int orderId)
	{
		boolean f=false;
		try {
			String q="update ordertable set orderStatus='Delivered' where orderId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			
			psmt.setInt(1, orderId);
			
			psmt.executeUpdate();
			
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	
	public boolean updateRejectOrdersByOrderIdAndOrderStatus(int orderId)
	{
		boolean f=false;
		try {
			String q="update ordertable set orderStatus='Cancle' where orderId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			
			psmt.setInt(1, orderId);
			
			psmt.executeUpdate();
			
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public ArrayList<order> getAllAcceptedOrders()
	{
		ArrayList<order> olist=new ArrayList<order>();
		try {
			String q="select * from ordertable where orderStatus='Delivered'";
			PreparedStatement psmt=this.con.prepareStatement(q);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				order order=new order();
				order.setOrderId(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setProductId(rs.getInt(3));
				order.setProductName(rs.getString(4));
				order.setProductQuantity(rs.getInt(5));
				order.setProductPrice(rs.getInt(6));
				order.setTotalPrice(rs.getInt(7));
				order.setAddress(rs.getString(8));
				order.setCity(rs.getString(9));
				order.setState(rs.getString(10));
				order.setCountry(rs.getString(11));
				order.setMobile(rs.getInt(12));
				order.setPincode(rs.getInt(13));
				order.setPaymentOption(rs.getString(14));
				order.setTid(rs.getString(15));
				order.setOrderStatus(rs.getString(16));
				
				olist.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist;
	}
	
	
	public ArrayList<order> getAllRejectedOrders()
	{
		ArrayList<order> olist=new ArrayList<order>();
		try {
			String q="select * from ordertable where orderStatus='Cancle'";
			PreparedStatement psmt=this.con.prepareStatement(q);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				order order=new order();
				order.setOrderId(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setProductId(rs.getInt(3));
				order.setProductName(rs.getString(4));
				order.setProductQuantity(rs.getInt(5));
				order.setProductPrice(rs.getInt(6));
				order.setTotalPrice(rs.getInt(7));
				order.setAddress(rs.getString(8));
				order.setCity(rs.getString(9));
				order.setState(rs.getString(10));
				order.setCountry(rs.getString(11));
				order.setMobile(rs.getInt(12));
				order.setPincode(rs.getInt(13));
				order.setPaymentOption(rs.getString(14));
				order.setTid(rs.getString(15));
				order.setOrderStatus(rs.getString(16));
				
				olist.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist;
	}
	
	
	
}
