package com.onlineshop.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionProvider 
{
	private static Connection con;
	public static Connection getConnection()
	{
		if(con==null)
		{
			try {
				Class.forName(database.driver);
				con=DriverManager.getConnection(database.url,database.username,database.password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
