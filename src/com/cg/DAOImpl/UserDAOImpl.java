package com.cg.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.UserDTO;

public class UserDAOImpl {

	@Autowired
	DataSource datasource = null;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void setDataSource(DataSource dataSource){
		this.datasource = dataSource;
	}
	
	public void create(UserDTO user){
		Connection conn = null;
		//PreparedStatement insertStatement = null;
		int rows=0;
		
		String insertQuery = "insert into user (username,password,email,birthDate,profession) values (?,?,?,?,?)";
		
		try {
			conn =(Connection) datasource.getConnection();
			try(PreparedStatement insertStatement = conn.prepareStatement(insertQuery)){
				insertStatement.setString(1, user.getUsername());
				insertStatement.setString(2, user.getPassword());
				insertStatement.setString(3, user.getEmail());
				insertStatement.setString(4, user.getBirthDate().toString());
				insertStatement.setString(5, user.getProfession());
				
				rows = insertStatement.executeUpdate();
				
				System.out.println(rows+" rows added");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}
}
