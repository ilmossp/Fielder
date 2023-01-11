package com.xadmin.usermanagement.dao;

import java.sql.*;
import com.xadmin.usermanagement.bean.User;
import java.util.List;
import java.util.ArrayList;

public class UserDao {

	private String jdbcURL="jdbc:mariadb://localhost:3306/FielderDev";
	private String jdbcUsername = "root";
	private String jdbcPassword = "as";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO Users" + "  (email, password,type ) VALUES "
            + " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select * from Users where id =?";
    private static final String SELECT_ALL_USERS = "select * from Users";
    private static final String DELETE_USERS_SQL = "delete from Users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update Users set email= ?, password =?, type =? where id = ?;";
	
    public UserDao() {
    }
    
    protected Connection getConnection() {
    	Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
	
    }
    
    public User selectUser(String id) {
		User user = new User();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			System.out.println(statement);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setType(resultSet.getString("type"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	

	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String type = rs.getString("type");
				users.add(new User(id,email,password,type));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(String CIN) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, CIN);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
   
}
