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

/**
 *
 * @author ayush
 */
public class Item {
    int itemid;
    String name;
    int price;
    int quantity;
    
    
    // getters and setters
    public int getItemId()
    {
        return itemid;
    }
    
    public void setItemId(int id)
    {
        this.itemid = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String nam)
    {
        this.name = nam;
    }
    
    public int getPrice()
    {
        return price;
    }
    
    public void setPrice(int pric)
    {
        this.price = pric;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(int quan)
    {
        this.quantity = quan;
    }
    
    public int updateQuantity(String type, int excess)         // updates the quantity of items
    {
        int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("update item set quantity=quantity+? where name=?");
                        ps.setInt(1,excess);
                        ps.setString(2,type);
                        status=ps.executeUpdate();
                        return status;
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
    }
    
    public int updatePrice(String type, int new_price)     // updates the price of items
    {
        int status = 0;
        try{
            try (Connection con = DB.getConnection()) {
                PreparedStatement ps=con.prepareStatement("update item set rate=? where name=?");
                ps.setInt(1,new_price);
                ps.setString(2,type);
                status=ps.executeUpdate();
                return status;
            }
		}catch(SQLException e){System.out.println(e);}
		return status;
    }
    
    public static void main(String args[])
    {}
}
