package com.cg.DAOImpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.cg.UserDTO;

public class UserDAOImpl implements UserDAO{

	@Autowired
	private DataSource datasource = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.datasource = datasource;
//		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
	}
	
	/*public void create(UserDTO user){
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
		}*/
		
		
		//Using NamedJDBCServlet
	
	public void create(UserDTO user){
		BeanPropertySqlParameterSource sqlParameterSource;
		sqlParameterSource = new BeanPropertySqlParameterSource(user);
		
		String insertQuery = "insert into user (username,password,email,birthDate,profession) values (:username,:password,:email,:birthDate,:profession)";
		
		namedParameterJdbcTemplate.update(insertQuery, sqlParameterSource);
	}
	
}
