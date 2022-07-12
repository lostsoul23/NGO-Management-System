/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Controller.DB;
//import com.mysql.cj.xdevapi.Statement;
import static java.lang.Integer.max;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author ayush
 */

public class Student {
    int studentid;
    String name;
    int age;
    int Class;
    String gender;
    String school;
    int percent;
    int income;
    int money = 0;   
    int dress = 0;
    int books = 0;
    int shoes = 0;
    int stationery = 0;
    int bag = 0;
    int donated_money = 0;
    String donated_dress = "NO";
    String donated_books = "NO";
    String donated_shoes = "NO";
    String donated_stationery = "NO";
    String donated_bag = "NO";
    
    // here parameters like money, books, etc denote the quantity of items required by the student
    // paramters starting with donated_ store a NO if the given item has been donated to the student and YES in the opposite case
    
    // getters and setters
    public int getItem()
    {
        return studentid;
    }
    
    public void setItem(int id)
    {
        this.studentid = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String nam)
    {
        this.name = nam;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int ag)
    {
        this.age = ag;
    }
    
    public int getclass()
    {
        return Class;
    }
    
    public void setClass(int cl)
    {
        this.Class = cl;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public void setGender(String g)
    {
        this.gender = g;
    }
    
    public String getSchool()
    {
        return school;
    }
    
    public void setSchool(String sc)
    {
        this.school = sc;
    }
    
    public int getPercent()
    {
        return percent;
    }
    
    public void setPercent(int per)
    {
        this.percent = per;
    }
    
    public int getIncome()
    {
        return income;
    }
    
    public void setIncome(int inc)
    {
        this.income = inc;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public void setMoney(int mon)
    {
        this.money = mon;
    }
    
    public int getDress()
    {
        return dress;
    }
    
    public void setDress(int dre)
    {
        this.dress = dre;
    }
    public int getBooks()
    {
        return books;
    }
    
    public void setBooks(int book)
    {
        this.books = book;
    }
    public int getShoes()
    {
        return shoes;
    }
    
    public void setShoes(int sho)
    {
        this.shoes = sho;
    }
    
    public int getStationery()
    {
        return stationery;
    }
    
    public void setStationery(int sta)
    {
        this.stationery = sta;
    }
    
    public int getBag()
    {
        return bag;
    }
    
    public void setBag(int ba)
    {
        this.bag = ba;
    }
    
    public boolean validateData(String name, int age, int Class, String gender, String school, int percent, int income){ // checks if the record with given parameters is already present
		boolean status=false;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select * from student where name=? and age=? and Class=? and Gender=? and School=? and percentage=? and parental_income=?");
                        ps.setString(1,name);
                        ps.setInt(2,age);
                        ps.setInt(3,Class);
                        ps.setString(4,gender);
                        ps.setString(5,school);
                        ps.setInt(6,percent);
                        ps.setInt(7,income);
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
                pst1 = con.prepareStatement("SELECT * FROM student");
                ResultSet rs1 = pst1.executeQuery();
                int count = 0;
                while(rs1.next()){
                    count = max(rs1.getInt("student_id"),count);
                }
                studentid = count + 1;
                PreparedStatement ps;
                ps = con.prepareStatement("insert into student(student_id, name, age, Class, Gender, School, percentage, parental_income, Money, Dress, Books, Shoes, Stationery, Bag, donated_Money, donated_Dress, donated_Books, donated_Shoes, donated_Stationery, donated_Bag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1,studentid);
                ps.setString(2,name);
                ps.setInt(3,age);
                ps.setInt(4,Class);
                ps.setString(5,gender);
                ps.setString(6,school);
                ps.setInt(7,percent);
                ps.setInt(8,income);
                ps.setInt(9,money);
                ps.setInt(10,dress);
                ps.setInt(11,books);
                ps.setInt(12,shoes);
                ps.setInt(13,stationery);
                ps.setInt(14,bag);
                ps.setInt(15,donated_money);
                ps.setString(16,donated_dress);
                ps.setString(17,donated_books);
                ps.setString(18,donated_shoes);
                ps.setString(19,donated_stationery);
                ps.setString(20,donated_bag);
                status=ps.executeUpdate();
            }
        }catch(SQLException e){
                System.out.println(e);
        }
        return status;
    }
    
    // Delete the student with the particular ID
	public int delete(int id){
		int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("delete from student where student_id=?");
                        ps.setInt(1,id);
                        status=ps.executeUpdate();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
	}
        
        public static void main(String[] args) {
	}
        
        public int organise() throws SQLException // used for sorting the students in the database table student_sorted
        {
            int status = 0;
            try{
                try(Connection con = DB.getConnection()){
                    PreparedStatement ps = con.prepareStatement("create table student_sorted as select * from student order by percentage desc, Gender asc, parental_income asc");
                    status = droptable();
                    ps.executeUpdate();
                }
            }catch(SQLException e){System.out.println(e);}
            return status;
        }
        
        public void distributeItems()     // this function distributes the items which are available as items for donation
        {
            int status = 0;
            try{
                try(Connection con = DB.getConnection()){
                    String query1 = "select student_id from student_sorted where ";
                    String query2 = " > 0";
                    String arr[] = {"Dress", "Books", "Shoes", "Stationery", "Bag"};
                    PreparedStatement ps1 = con.prepareStatement("select quantity from item");
                    int values[] = new int[5];
                    int c = 0;
                    ResultSet rs1 = ps1.executeQuery();
                    while(rs1.next())
                    {
                        if(c == 0)
                        {
                            c++;
                            continue;
                        }
                        values[c-1] = rs1.getInt("quantity");
                        c++;
                    }
                    for(int i = 0; i<5; i++)
                    {
                        String query = query1 + arr[i] + query2;
                        PreparedStatement ps = con.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();
                        ArrayList<Integer> temp = new ArrayList<>();
                        while(rs.next())
                        {
                            temp.add(rs.getInt("student_id"));
                        }
                        int k = 0;
                        String query3 = "update student_sorted set ";   
                        String query4 = " = ? where student_id = ?";
                        while(values[i]>0&&k<temp.size())
                        {
                            String query5 = query3 + "donated_" + arr[i] + query4;
                            PreparedStatement ps2 = con.prepareStatement(query5);
                            ps2.setString(1,"YES");
                            ps2.setInt(2,temp.get(k));
                            int flag = ps2.executeUpdate();
                            k++;
                            values[i]--;
                        }
                    }
                    String query3 = "update item set quantity = ? where name = ?";     // update quantity of all items after distribution
                    for(int i = 0; i<5; i++)
                    {
                        PreparedStatement ps2 = con.prepareStatement(query3);
                        ps2.setInt(1,values[i]);
                        ps2.setString(2,arr[i]);
                        int flag = ps2.executeUpdate();
                    }
                }
            }catch(SQLException e){System.out.println(e);}
        }
        
        public int money_distributions()        // this function donates the money after the items already present have been distributed optimally
        {
            int status = 0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select quantity from item where name = ?");
                        ps.setString(1,"Money");
                        int balance = 0;
                        ResultSet rs = ps.executeQuery();
                        while(rs.next())
                        {
                            balance = rs.getInt("quantity");
                        }
                        PreparedStatement ps1 = con.prepareStatement("select rate from item");
                        int rates[] = new int[5];
                        int c = 0;
                        String arr[] = {"Dress", "Books", "Shoes", "Stationery", "Bag"};
                        ResultSet rs1 = ps1.executeQuery();
                        while(rs1.next())
                        {
                            if(c == 0)
                            {
                                c++;
                                continue;
                            }
                            rates[c-1] = rs1.getInt("rate");
                            c++;
                        }
                        ArrayList<Integer> temp = new ArrayList<>();
                        String query1 = "select student_id, Dress, Books, Shoes, Stationery, Bag, Money, donated_Dress, donated_Books, donated_Shoes, donated_Stationery, donated_Bag from student_sorted";
                        PreparedStatement ps2 = con.prepareStatement(query1);
                        ResultSet rs2 = ps2.executeQuery();
                        //int r = 1;
                        while(rs2.next())
                        {
                            int curr_amount = 0;
                            curr_amount += rs2.getInt("Money");
                            if(rs2.getInt("Dress")==1&&rs2.getString("donated_Dress").equals("NO"))
                            {
                                curr_amount += rates[0];
                                //System.out.println(r);
                            }
                            if(rs2.getInt("Books")==1&&rs2.getString("donated_Books").equals("NO"))
                            {
                                curr_amount += rates[1];
                                //System.out.println(r);
                            }
                            if(rs2.getInt("Shoes")==1&&rs2.getString("donated_Shoes").equals("NO"))
                            {
                                curr_amount += rates[2];
                                //System.out.println(r);
                            }
                            if(rs2.getInt("Stationery")==1&&rs2.getString("donated_Stationery").equals("NO"))
                            {
                                curr_amount += rates[3];
                                //System.out.println(r);
                            }
                            if(rs2.getInt("Bag")==1&&rs2.getString("donated_Bag").equals("NO"))
                            {
                                curr_amount += rates[4];
                                //System.out.println(r);
                            }
                            int to_be_donated = 0;
                            if(curr_amount<balance)
                            {
                                to_be_donated = curr_amount;
                                balance -= curr_amount;
                                //System.out.println(balance);
                            }
                            else
                            {
                                to_be_donated = balance;
                                balance = 0;
                            }
                            //System.out.println(to_be_donated);
                            PreparedStatement ps4 = con.prepareStatement("update student_sorted set donated_money = ? where student_id = ?");
                            ps4.setInt(1,to_be_donated);
                            ps4.setInt(2,rs2.getInt("student_id"));
                            status = ps4.executeUpdate();
                            if(balance <= 0)
                            {
                                break;
                            }
                            //r++;
                        }
                        PreparedStatement ps5 = con.prepareStatement("update item set quantity = ? where name = ?");
                        ps5.setInt(1,balance);
                        ps5.setString(2,"Money");
                        status = ps5.executeUpdate();
                        return balance;
                    }
		}catch(SQLException e){System.out.println(e);}
                return 0;
        }
        
        public int original_balance()      // this function returns the original balance of money donated
        {
            int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select quantity from item where name = ?");
                        ps.setString(1,"Money");
                        ResultSet rs = ps.executeQuery();
                        int ans = 0;
                        while(rs.next())
                        {
                            ans = rs.getInt("quantity");
                        }
                        return ans;
                    }
		}catch(SQLException e){System.out.println(e);}
            return status;
        }
        
        public int expenditure()              // this function returns the total expenditure made in the current year
        {
            int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("select donated_money from student_sorted");
                        int expenditure = 0;
                        ResultSet rs = ps.executeQuery();
                        while(rs.next())
                        {
                            expenditure += rs.getInt("donated_money");
                        }
                        return expenditure;
                    }
		}catch(SQLException e){System.out.println(e);}
            return status;
        }
        
        public int deleteTable()         // it is used to delete contents of a table
        {
            int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("truncate table student_sorted");
                        status=ps.executeUpdate();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
        }
        
        public int droptable()              // it completely removes the table from the database
        {
            int status=0;
		try{
                    try (Connection con = DB.getConnection()) {
                        PreparedStatement ps=con.prepareStatement("drop table student_sorted");
                        status=ps.executeUpdate();
                    }
		}catch(SQLException e){System.out.println(e);}
		return status;
        }
}
