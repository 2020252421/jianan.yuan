package com.jianan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianan.JDBC.JDBCUtil;
import com.jianan.entity.Student;

public class StudentDao {
	Student student = new Student();
	public Student getStudentById(String id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		//建立连接
		connection = JDBCUtil.getConnection();
		//准备sql
		String sql = "select * from t_student where id = ?";
		try {
			statement = connection.prepareStatement(sql);
			//设置参数
			statement.setString(1, id);
			//执行statement
			resultSet = statement.executeQuery();
			//处理结果
			while(resultSet.next()) {
				
				student.setId(resultSet.getString("id"));
				student.setCardNo(resultSet.getString("cardNo"));
				student.setName(resultSet.getString("name"));
				student.setPassword(resultSet.getString("password"));
				student.setPrefession(resultSet.getString("prefession"));
				student.setSex(resultSet.getString("sex"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(resultSet, statement, connection);
		}
		
			return student;
		}
	public Student addStudent(String id,String cardNo,String name,String password,String prefession,String sex) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = JDBCUtil.getConnection();
		String sql = "insert into t_student (id,cardNo,name,password,prefession,sex) values(?,?,?,?,?,?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, cardNo);
			statement.setString(3, name);
			statement.setString(4, password);
			statement.setString(5,prefession );
			statement.setString(6, sex);
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(resultSet, statement, connection);
		}
			return student;
	}
}

