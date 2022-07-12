/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controller.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ayush
 */
public class Admin extends User{
    public int validate(String username, String password) {   // Validate Login by checking if the details exist or not
        int newid  = 0;
               try{
                   try (Connection con = DB.getConnection()) {
                       PreparedStatement ps=con.prepareStatement("select * from admin where username=? and password = ?");
                       ps.setString(1,username);
                       ps.setString(2,password);
                       ResultSet rs=ps.executeQuery();
                       if(rs.next()!=false){
                           return 1;
                       }
                   }
               }catch(SQLException e){System.out.println(e);}
               return newid;
    }
}