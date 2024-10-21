package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	//declaring the connection globally bcz we will use this in connection closing statement
	Connection conn;
	Statement stat;
	public void getDbConnection(String url,String un,String pwd) throws SQLException {
	try {
	Driver driverRef=new Driver();
	DriverManager.registerDriver(driverRef);
	conn= DriverManager.getConnection(url,un,pwd);
	}
	catch(Exception e) {
	}
}
	public void getDbConnection() throws SQLException {
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		conn= DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		}
		catch(Exception e) {
		}
	}
	public void closeConnection() throws SQLException {
		try {
		conn.close();
		}
		catch(Exception e) {
		}
	}
	public ResultSet ExecuteSelectQuery(String Query) throws SQLException {
		ResultSet result = null;
		try {
		stat=conn.createStatement();
		result= stat.executeQuery(Query);
		}
		catch(Exception e) {
		}
		//this resultset object has complete table
		return result;
	}
	public int ExecuteNonSelectQuery(String query) throws SQLException {
		int result=0;
		try {
		stat= conn.createStatement();
		result=stat.executeUpdate(query);
		//it will return 0=query execution got fail or +1or-1=execution happened
		}
		catch(Exception e) {
		}
		return result;
		
	}
}
