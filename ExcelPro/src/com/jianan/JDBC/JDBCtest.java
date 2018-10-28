package com.jianan.JDBC;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;



public class JDBCtest {
	private static void  get() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//建立连接
			conn = JDBCUtil.getConnection();
			//获取ststement
			String sql = "selet * from t_student";
			st = conn.prepareStatement(sql);
			//执行sql
			rs = st.executeQuery();
			//处理结果
			while(rs.next()) {
				System.out.println("id:"+rs.getString(1));
				System.out.println("name"+rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("数据库连接失败！原因是"+e.getMessage());
		}finally {
			JDBCUtil.closeConnection(rs, st, conn);
		}
	}
	private static void add(String id,String name,String cardNo,String password) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs  = null;
		try {
			//建立连接
			conn = JDBCUtil.getConnection();
			//获取prepareStatement
			String sql = "insert into t_student (id,cardNo,name,password) values(?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2,cardNo );
			st.setString(3, name);
			st.setString(4, password);
			//执行sql
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("数据库连接失败！原因是"+e.getMessage());
		}finally {
			JDBCUtil.closeConnection(rs, st, conn);
		}
	}
	private static void delete(String id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//建立连接
			conn = JDBCUtil.getConnection();
			//获取prepareStatement
			String sql = "delete from t_student where id = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			//执行sql
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("数据库连接失败，原因是："+e.getMessage());
		}finally {
			JDBCUtil.closeConnection(rs, st, conn);
		}
	}
	private static void update(String id,String name,String cardNo,String password) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//建立连接
			conn = JDBCUtil.getConnection();
			//获取prepareStatement
			String sql = "update t_student set cardNo = ?,name = ?,password = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, cardNo);
			st.setString(3, name);
			st.setString(4, password);
			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("数据库连接失败，原因是："+e.getMessage());
		}finally {
			JDBCUtil.closeConnection(rs, st, conn);
		}
	}
	public static void main(String[] args) {
		delete("123");
	}
}
