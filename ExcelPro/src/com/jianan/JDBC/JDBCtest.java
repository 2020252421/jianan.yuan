package com.jianan.JDBC;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import com.jianan.dao.StudentDao;
import com.jianan.entity.Student;



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
				System.out.println("id:"+rs.getString("id"));
				System.out.println("name"+rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("数据库连接失败！原因是"+e.getMessage());
		}finally {
			JDBCUtil.closeConnection(rs, st, conn);
		}
	}
	private static void add(String id,String name,String cardNo,String password,String prefession,String sex) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs  = null;
		try {
			//建立连接
			conn = JDBCUtil.getConnection();
			//获取prepareStatement
			String sql = "insert into t_student (id,cardNo,name,password,prefession,sex) values(?,?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2,cardNo );
			st.setString(3, name);
			st.setString(4, password);
			st.setString(5, prefession);
			st.setString(6, sex);
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
	private static void update(String id,String name,String cardNo,String password,String prefession,String sex) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//建立连接
			conn = JDBCUtil.getConnection();
			//获取prepareStatement
			String sql = "update t_student set cardNo = ?,name = ?,password = ?,prefession = ?,sex = ? where id = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, cardNo);
			st.setString(3, name);
			st.setString(4, password);
			st.setString(5, prefession);
			st.setString(6, sex);
			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("数据库连接失败，原因是："+e.getMessage());
		}finally {
			JDBCUtil.closeConnection(rs, st, conn);
		}
	}
	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		Student student = new Student();
		//student = dao.addStudent("1714010729", "12345", "苑佳楠", "10000", "软件工程", "女");
		student = dao.getStudentById("1714010729");
		System.out.println(student);
		
	}
}
