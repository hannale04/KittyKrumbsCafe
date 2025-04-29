package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {
	private static final String URL =  "jdbc:mysql://127.0.0.1:3306/kitty_krumbs_cafe";
    private static final String USER = "root";  
    private static final String PASSWORD = "root"; 
    private Connection connection;
    
    public DatabaseConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection created successfully!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found: " + e.getMessage());
			
		} catch(SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}
	}
    
    public Connection getConnection() {
    	return connection;
    }
    
    public void close() throws SQLException{
    	if (connection != null) {
    		connection.close();
    		System.out.println("Connection closed successfully!");
    	}
    }
}
