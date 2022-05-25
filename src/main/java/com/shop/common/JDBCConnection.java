package com.shop.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection conn = DriverManager.getConnection(url, "system", "1234");
		return conn;	
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch(SQLException e) { 
				e.getStackTrace();
			}
		}
		
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.getStackTrace();
			}
		}
		
		if(conn!=null) { 
			try {
				conn.close();
			} catch(SQLException e) {
				e.getStackTrace();
			}
		} 
	}
	
}