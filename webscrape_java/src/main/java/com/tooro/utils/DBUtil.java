package com.tooro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static Connection dbConn;
	
	static{
		String dbURL = "jdbc:mysql://localhost:3306/scraperdb";
		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password", "");
		dbConn = null;
		try{
			dbConn=DriverManager.getConnection(dbURL, connectionProps);
		}catch(SQLException sqlE){
			System.out.println("There was a problem connecting to the database");
			sqlE.printStackTrace();
		}
		
		PreparedStatement useStmt;
		try{
			useStmt=dbConn.prepareStatement("USE scraperdb");
			useStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void storeData(String title, String path){
		PreparedStatement useStmt;
		try{
			useStmt=dbConn.prepareStatement("INSERT INTO wikipedia_link (title, path) values (?,?)");
			useStmt.setString(1, title);
			useStmt.setString(2, path);
			useStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
