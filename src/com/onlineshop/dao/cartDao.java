package com.onlineshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.onlineshop.entities.cart;

public class cartDao
{
	private Connection con;

	public cartDao(Connection con) {
		this.con = con;
	}
	
	public boolean addItemToCart(cart cart,int id)
	{
		boolean f=false;
		try {
			String q="insert into cart(userId,productId,productName,productPhoto,productQuantity,productPrice,totalPrice) values(?,?,?,?,?,?,?)";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, id);
			psmt.setInt(2, cart.getProductId());
			psmt.setString(3, cart.getProductName());
			psmt.setString(4, cart.getProductPhoto());
			psmt.setInt(5, cart.getProductQuantity());
			psmt.setInt(6, cart.getProductPrice());
			psmt.setInt(7, cart.getTotalPrice());
			
			psmt.executeUpdate();
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public ArrayList<cart> getAllProductByUserIdFromCart(int userId)
	{
		ArrayList<cart> clist=new ArrayList<cart>();
		try {
			String q="select * from cart where userId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, userId);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				cart cart=new cart();
				cart.setItemNo(rs.getInt(1));
				cart.setUserId(rs.getInt(2));
				cart.setProductId(rs.getInt(3));
				cart.setProductName(rs.getString(4));
				cart.setProductPhoto(rs.getString(5));
				cart.setProductQuantity(rs.getInt(6));
				cart.setProductPrice(rs.getInt(7));
				cart.setTotalPrice(rs.getInt(8));
				
				clist.add(cart);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	
	public boolean removeItemFormCart(int itemNo)
	{
		boolean f=false;
		try {
			String q="delete from cart where itemNo=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, itemNo);
			
			psmt.executeUpdate();
			
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
	}
	
	
	
	public int getProductQuantity(int userId,int productId)
	{
		int quantity=0;
		try {
			String q="select count(productQuantity) as countQuantity from cart where userId=? and productId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, userId);
			psmt.setInt(2, productId);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				quantity=rs.getInt("countQuantity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quantity;
	}
	
	
	public int getAllProductFormCartByUserId(int userId)
	{
		int quantity=0;
		try {
			String q="SELECT COUNT(*) AS quantity FROM cart WHERE userId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, userId);
			
			ResultSet rs=psmt.executeQuery();
			
			if(rs.next())
			{
				quantity=rs.getInt("quantity");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quantity;
	}
	
	
	public boolean incDecQuantity(cart cart)
	{
		boolean f=false;
		try {
			String q="update cart set productQuantity=?,productPrice=?,totalPrice=? where userId=? and productId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, cart.getProductQuantity());
			psmt.setInt(2, cart.getProductPrice());
			psmt.setInt(3, cart.getTotalPrice());
			psmt.setInt(4, cart.getUserId());
			psmt.setInt(5, cart.getProductId());
			
			psmt.executeUpdate();
			
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	public cart getProductDetailsByUserIdAndProductId(int userId,int productId)
	{
		cart cart=null;
		try {
			String q="select * from cart where userId=? and productId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, userId);
			psmt.setInt(2, productId);
			ResultSet rs=psmt.executeQuery();
			
			if(rs.next())
			{
				cart=new cart();
				cart.setItemNo(rs.getInt(1));
				cart.setUserId(rs.getInt(2));
				cart.setProductId(rs.getInt(3));
				cart.setProductName(rs.getString(4));
				cart.setProductPhoto(rs.getString(5));
				cart.setProductQuantity(rs.getInt(6));
				cart.setProductPrice(rs.getInt(7));
				cart.setTotalPrice(rs.getInt(8));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	
	
	public int totalPaybleAmount(int userId)
	{
		int totalAmount=0;
		try {
			String q="select sum(totalPrice) from cart where userId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			
			psmt.setInt(1, userId);
			
			ResultSet rs=psmt.executeQuery();
			
			if(rs.next())
			{
				totalAmount=rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmount;
	}
	
	
	public boolean removeFromCartByUserId(int userId)
	{
		boolean f=false;
		try {
			String q="delete from cart where userId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, userId);
			
			psmt.executeUpdate();
			
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
}
