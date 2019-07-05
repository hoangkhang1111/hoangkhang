package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

		 
	    public static Connection getConnecttion() {
	        Connection cons = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            cons = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:/toritori", "root", "");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return cons;
	    }
	 
	    public static void main(String[] args) {
	        System.out.println(getConnecttion());
	    }
	    
}
