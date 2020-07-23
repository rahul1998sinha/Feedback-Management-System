package feedbackdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class teacher {
	int t_id;
	String t_name;
	String t_dept;
	teacher(int t_id,String t_name,String t_dept)
	{
		this.t_id=t_id;
		this.t_name=t_name;
		this.t_dept=t_dept;
	}
	teacher(int t_id)
	{
		this.t_id=t_id;
		this.t_name="";
		this.t_dept="";
	}
	public static Connection get_t_connection() throws Exception
	{
		try {
			String driver = "com.mysql.jdbc.Driver";
			   String url = "jdbc:mysql://localhost:3306/javaproject";
			   String username = "admin";
			   String password = "raulgimenez";
			   Class.forName(driver);
			   
			   Connection conn = DriverManager.getConnection(url,username,password);
			   //System.out.println("Connected");
			   return conn;
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public void create_t() throws Exception
	{
		try {
			Connection con=get_t_connection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS teacher(t_id int NOT NULL,t_name varchar(100),t_dept varchar(50),PRIMARY KEY(t_id));");
			create.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		//finally {System.out.println("TABLE CREATED");}
	}
	public void insert_t() throws Exception{
		try {
			Connection con=get_t_connection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO teacher(t_id,t_name,t_dept) VALUES ('"+this.t_id+"','"+this.t_name+"','"+this.t_dept+"');");
			insert.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		//finally {System.out.println("ROW INSERTED");}
	}
	public static void delete_t(int id) throws Exception{
		try {
			Connection con =get_t_connection();
			int counter,x;
			x=display_t_specific(id);
			char c;
			Scanner sc = new Scanner(System.in);
			if(x!=0)
			{
				System.out.println("Do you want to delete this record");
			c=sc.next().charAt(0);
			sc.close();
			if(c=='Y'||c=='y')
			{
			PreparedStatement delete = con.prepareStatement("DELETE FROM teacher WHERE t_id='"+id+"';");
			counter=delete.executeUpdate();
			PreparedStatement deletef = con.prepareStatement("DELETE FROM feedback WHERE t_id='"+id+"';");
			deletef.executeUpdate();
			System.out.println(counter +" records deleted......");
			}
			}
			else
				System.out.println("No RECORDS FOUND");
		}catch(Exception e) {
			System.out.println(e);
		}
		//finally {System.out.println("ROW DELETED");}
	}
	public static void edit_t(int id,String name,String dept) throws Exception{
		try {
		Connection con =get_t_connection();
		int counter;
		PreparedStatement edit = con.prepareStatement("UPDATE teacher SET t_name ='"+name+"',t_dept = '"+dept+"' WHERE t_id = '"+id+"';");
		counter=edit.executeUpdate();
		System.out.println(counter +" records edited......");
	}catch(Exception e) {
		System.out.println(e);
	}
	//finally {System.out.println("ROW EDITED");}

	}
	public static void display_t() throws Exception{
		try {
			Connection con = get_t_connection();
			int counter=0;
			PreparedStatement display = con.prepareStatement("SELECT * FROM teacher;");
			ResultSet result = display.executeQuery();
			System.out.println("   ID  | Teacher Name | Department");
			while(result.next()) {
				System.out.print(result.getInt("t_id"));
				System.out.print("        ");
				System.out.print(result.getString("t_name"));
				System.out.print("                 ");
				System.out.println(result.getString("t_dept"));
				counter++;
			};
			System.out.println(counter +" records displayed......");
		}catch(Exception e){System.out.println(e);}
		//finally {System.out.println("TABLE DISPLAYED");}
	}
	public static int display_t_specific(int t_id) throws Exception{
		try {
			Connection con = get_t_connection();
			int counter=0;
			PreparedStatement count = con.prepareStatement("SELECT COUNT(*) AS COUNT FROM teacher WHERE t_id='"+t_id+"'");
			ResultSet result1 = count.executeQuery();
			result1.next();
			if(result1.getInt("COUNT")==0)
				System.out.println("\n No records found");
			else
			{
			PreparedStatement display = con.prepareStatement("SELECT * FROM teacher WHERE t_id='"+t_id+"';");
			ResultSet result = display.executeQuery();
			while(result.next()) {
				System.out.print(result.getInt("t_id"));
				System.out.print("        ");
				System.out.print(result.getString("t_name"));
				System.out.print("                ");
				System.out.println(result.getString("t_dept"));
				counter++;
			};
			System.out.println(counter +" records displayed......");
			}
			return counter;
		}catch(Exception e){System.out.println(e);}
		//finally {System.out.println("TABLE DISPLAYED");}
		return 0;
	}
}
