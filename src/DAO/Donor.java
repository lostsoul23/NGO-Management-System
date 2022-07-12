/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Controller.DB;
import static java.lang.Integer.max;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ayush
 */
public class Donor extends User{
    private int userid;
    private String username;
    private String email;
    private String contact;
    private String residence;
    
    
    // getters and Setters
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public boolean validateUsername(String username){       // checks if the username given exists or not in the database
		boolean status=false;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from user where username=?");
                        ps.setString(1,username);
                        ResultSet rs=ps.executeQuery();
                        status=rs.next();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
	}
    
    public boolean validateEmail(String emailid){           // checks if the Email given exists or not in the database
		boolean status=false;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from user where email=?");
                        ps.setString(1,emailid);
                        ResultSet rs=ps.executeQuery();
                        status=rs.next();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
	}
    
    // save into database
    public int save(){         
        int status=0;
        try{
            try (Connection con = DB.getConnection()) {
                PreparedStatement pst1;
                pst1 = con.prepareStatement("SELECT * FROM user");
                ResultSet rs1 = pst1.executeQuery();
                int count = 0;
                while(rs1.next()){
                    count = max(rs1.getInt("user_id"),count);
                }
                userid = count + 1; // it calculates the first user id which is not assigned to any user yet
                PreparedStatement ps;
                ps = con.prepareStatement("insert into user(user_id,name,username,password,email,phoneno,residence) values(?,?,?,?,?,?,?)");
                ps.setInt(1,userid);
                ps.setString(2,Name);
                ps.setString(3,username);
                ps.setString(4,Password);
                ps.setString(5,email);
                ps.setString(6,contact);
                ps.setString(7,residence);
                status=ps.executeUpdate();
            }
        }catch(SQLException e){
                System.out.println(e);
        }
        return status;
    }
    
    // Delete the user with the particular ID
	public int delete(int id){
		int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("delete from user where user_id=?");
                        ps.setInt(1,id);
                        status=ps.executeUpdate();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
	}

	// Check if user and password match
	public static boolean validate(String id_no,String password){
		boolean status=false;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from User where id_no=? and password=?");
                        ps.setString(1,id_no);
                        ps.setString(2,password);
                        ResultSet rs=ps.executeQuery();
                        status=rs.next();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
	}

	// Call to create the table
	public static void main(String[] args) {
	}
        // retriev password
    public String validate() {
        String newpassword;
        newpassword = "";
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from user where username=? and email=? and phoneno = ?");
                        ps.setString(1,this.username);
                        ps.setString(2,this.email);
                        ps.setString(3,this.contact);
                        ResultSet rs=ps.executeQuery();
                        if(rs.next()!=false){
                            return rs.getString("password");
                        }
                    }
		}catch(SQLException e){System.out.println(e);}
		return newpassword;
    }
    
    // getuser if from username and password
    public int get_id(String username, String password){
         int newid  = 0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from user where username=? and password = ?");
                        ps.setString(1,username);
                        ps.setString(2,password);
                        ResultSet rs=ps.executeQuery();
                        if(rs.next()!=false){
                            return rs.getInt("user_id");
                        }
                    }
		}catch(SQLException e){System.out.println(e);}
		return newid;
    }
    
    
    // get username from user id
    public static String findusername(int id) {
        String newname = "";
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from user where user_id=?");
                        ps.setInt(1,id);
                        ResultSet rs=ps.executeQuery();
                        if(rs.next()!=false){
                            return rs.getString("name");
                        }
                    }
		}catch(SQLException e){System.out.println(e);}
		return newname;
    }
}