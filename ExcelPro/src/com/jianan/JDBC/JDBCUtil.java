package com.jianan.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class JDBCUtil {
	private static Connection conn = null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(conn == null) {
				conn = DriverManager.getConnection("JDBC:mysql://localhost:3306/mysqllesson?useUnicode=true&characterEncoding=UTF-8", "root", "12345");
			}
		} catch (Exception e) {
			System.out.println("数据库连接失败！原因是"+e.getMessage());
		}
		return conn;
	}
	public static boolean closeConnection(ResultSet rs,PreparedStatement st,Connection conn ) {
		boolean flag = true;
		try {
			 if(rs!=null) {
				 rs.close();
			 }
			 if(st!=null) {
				 st.close();
			 }
			 if(conn!=null) {
				 conn.close();
			 }
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
