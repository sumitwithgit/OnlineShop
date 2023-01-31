package com.onlineshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.onlineshop.entities.product;

public class productDao {
	private Connection con;

	public productDao(Connection con) {
		this.con = con;
	}

	public boolean insertProduct(product product) {
		boolean f = false;
		try {
			String q = "insert into product(pName,pDescription,pPhoto,pPrice,pCategory) values(?,?,?,?,?)";
			PreparedStatement psmt = this.con.prepareStatement(q);
			psmt.setString(1, product.getpName());
			psmt.setString(2, product.getpDescription());
			psmt.setString(3, product.getpPhoto());
			psmt.setInt(4, product.getpPrice());
			psmt.setString(5, product.getpCategory());
			psmt.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
	
	
	public ArrayList<product> viewAllProduct()
	{
		ArrayList<product> plist=new ArrayList<product>();;
		try {
			String q="select * from product";
			PreparedStatement psmt=this.con.prepareStatement(q);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				product product=new product();
				product.setpId(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpDescription(rs.getString(3));
				product.setpPhoto(rs.getString(4));
				product.setpPrice(rs.getInt(5));
				product.setpCategory(rs.getString(6));
				plist.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return plist;
	}

	
	public boolean deleteProduct(int id)
	{
		boolean f=false;
		try {
			String q="delete from product where pId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, id);
			
			psmt.executeUpdate();
			
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public boolean editProduct(product product,int id)
	{
		boolean f=false;
		try {
			String q="update product set pName=?,pDescription=?,pPhoto=?,pPrice=? where pId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setString(1, product.getpName());
			psmt.setString(2, product.getpDescription());
			psmt.setString(3, product.getpPhoto());
			psmt.setInt(4, product.getpPrice());
			psmt.setInt(5, id);
			psmt.executeUpdate();
			
			f=true;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public ArrayList<product> getAllProductByCategory(String category)
	{
		ArrayList<product> plist=new ArrayList<product>();
		try {
			String q="select * from product where pCategory=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setString(1, category);
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
				product product=new product();
				product.setpId(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpDescription(rs.getString(3));
				product.setpPhoto(rs.getString(4));
				product.setpPrice(rs.getInt(5));
				product.setpCategory(category);
				
				plist.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return plist;
	}
	
	
	
	
	public product getProductDetailsById(int pid)
	{
		product product=null;
		try {
			String q="select * from product where pId=?";
			PreparedStatement psmt=this.con.prepareStatement(q);
			psmt.setInt(1, pid);
			
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
				product=new product();
				product.setpId(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpDescription(rs.getString(3));
				product.setpPhoto(rs.getString(4));
				product.setpPrice(rs.getInt(5));
				product.setpCategory(rs.getString(6));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	
	
	
	
	
	
	
	
	
}