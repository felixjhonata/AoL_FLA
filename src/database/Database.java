package database;

import model.user.Admin;
import model.user.Customer;
import model.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Database {
	String jdbcURL = "jdbc:mysql://localhost:3306/aol_fla";
	String user = "root";
	String password = "";
	Connection connection;
	PreparedStatement ps;
	
	Database() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean addUser(User user) {
		
		String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?);";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPhoneNo());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			
			if(user instanceof Admin) {
				ps.setString(6, "admin");
			} else {
				ps.setString(6, "customer");
			}
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
		
		return false;
	}
	
	public User getUserByCredentials(String email, String password) {
		
		String query = "SELECT * FROM users WHERE email = ? AND password = ?;";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String phoneNo = rs.getString(3);
				String role = rs.getString(6);
				
				if(role.equals("customer")) {
					return new Customer(id, name, phoneNo, email, password);
				} else {
					return new Admin(id, name, phoneNo, email, password);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}