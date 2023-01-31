package com.onlineshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.onlineshop.entities.category;

public class categoryDao 
{
	private Connection con;

	public categoryDao(Connection con) {
		this.con = con;
	}
	
	public ArrayList<category> getAllCategory()
	{
		ArrayList<category> clist=new ArrayList<category>();
		try {
			String q="select * from category";
			PreparedStatement psmt=this.con.prepareStatement(q);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				category category=new category();
				category.setcId(rs.getInt(1));
				category.setcName(rs.getString(2));
				category.setcDesc(rs.getString(3));
				
				clist.add(category);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	
}
