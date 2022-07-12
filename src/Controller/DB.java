/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author ayush
 */
public class DB {
    public static Connection getConnection(){
                Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NGOMS", "akd", "helloakd");
		 } catch ( ClassNotFoundException | SQLException e ) {
			System.err.println(e.getClass().getName()+ "Failed here" + ": " + e.getMessage() );
		 }
		 return con;
	}
}
