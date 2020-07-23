package feedbackdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class feedback extends teacher {
	int s_enroll;
	//char s_sec;
	String s_name;
	int q1,q2,q3,q4,q5;
	double rating;
	feedback(int t_id,String t_name,String t_dept,int s_enroll,String s_name,int q1,int q2,int q3,int q4,int q5)
	{
		super(t_id,"","");
		this.s_name=s_name;
		this.s_enroll=s_enroll;
		//this.s_sec=s_sec;
		this.q1=q1;
		this.q2=q2;
		this.q3=q3;
		this.q4=q4;
		this.q5=q5;
		this.rating=(this.q1+this.q2+this.q3+this.q4+this.q5)/5.0;
	}
	public static void create_f_Table() throws Exception
	{
		try {
			Connection con=get_t_connection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS feedback(f_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,t_id int NOT NULL,s_id int NOT NULL,s_name varchar(50),q1 int NOT NULL,q2 int NOT NULL,q3 int NOT NULL,q4 int NOT NULL,q5 int NOT NULL,rating FLOAT);");
			create.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		//finally {System.out.println("TABLE CREATED");}
	}
	public void insert_f() throws Exception{
		try {
			Connection con=get_t_connection();
			PreparedStatement check = con.prepareStatement("SELECT COUNT(*) AS COUNT FROM TEACHER WHERE t_id='"+this.t_id+"';");
			ResultSet result2 = check.executeQuery();
			result2.next();
			if(result2.getInt("COUNT")==0)
			{
				System.out.println("\n Entered Teacher id not in records.");
			}
			else
			{		
			PreparedStatement name = con.prepareStatement("SELECT t_name,t_dept FROM TEACHER WHERE t_id='"+this.t_id+"';");
			ResultSet result1 = name.executeQuery();
			result1.next();
			System.out.println("TEACHER FOUND");
			System.out.println("TEACHER NAME: "+result1.getString("t_name"));
			System.out.println("TEACHER DEPT: "+result1.getString("t_dept"));
			PreparedStatement insert = con.prepareStatement("INSERT INTO feedback(t_id,s_id,s_name,q1,q2,q3,q4,q5,rating) VALUES ('"+this.t_id+"','"+this.s_enroll+"','"+this.s_name+"','"+this.q1+"','"+this.q2+"','"+this.q3+"','"+this.q4+"','"+this.q5+"','"+this.rating+"');");
			insert.executeUpdate();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		//finally {System.out.println("ROW INSERTED");}
	}
	public static void delete_f(int s_id) throws Exception{
		try {
			Connection con =get_t_connection();
			char c;
			int counter,counter1;
			PreparedStatement checker =con.prepareStatement("SELECT COUNT(*) AS COUNT FROM feedback WHERE s_id ='"+s_id+"';");
			ResultSet result3 = checker.executeQuery();
			result3.next();
			if(result3.getInt("COUNT")==0)
			{
				System.out.println("\n Record Doesn't exist");
			}
			else
			{
			counter1=display_f_specific(s_id);
			System.out.println("\n Do you want to delete the feedback record(y/n)");
			Scanner sc = new Scanner(System.in);
			c=sc.next().charAt(0);
			sc.close();
			if(c=='y')
			{
			PreparedStatement delete = con.prepareStatement("DELETE FROM feedback WHERE s_id='"+s_id+"';");
			counter=delete.executeUpdate();
			System.out.println(counter +" records deleted......");
			}
			}
			}catch(Exception e) {
			System.out.println(e);
		}
	
		//finally {System.out.println("ROW DELETED");}
	}
	public static void edit_f(int t_id,int s_enroll,String s_name,int q1,int q2,int q3,int q4,int q5) throws Exception{
		try {
		Connection con =get_t_connection();
		int counter;
		float ratings;
		ratings=(q1+q2+q3+q4+q5)/5;
		PreparedStatement checker =con.prepareStatement("SELECT COUNT(*) AS COUNT FROM feedback WHERE s_id ='"+s_enroll+"';");
		ResultSet result=checker.executeQuery();
		result.next();
		if(result.getInt("COUNT")==0)
		{
			System.out.println("\n Record Doesn't exist");
		}
		else
		{
		PreparedStatement edit = con.prepareStatement("UPDATE feedback SET t_id ='"+t_id+"',s_name = '"+s_name+"',q1='"+q1+"',q2='"+q2+"',q3='"+q3+"',q4='"+q4+"',q5='"+q5+"',rating='"+ratings+"' WHERE s_id = '"+s_enroll+"';");
		counter=edit.executeUpdate();
		System.out.println(counter +" records edited......");
		}
	}catch(Exception e) {
		System.out.println(e);
	}
	//finally {System.out.println("ROW EDITED");}

	}
	public static void display_f() throws Exception{
		try {
			Connection con = get_t_connection();
			int counter=0;
			PreparedStatement display = con.prepareStatement("SELECT * FROM feedback;");
			ResultSet result = display.executeQuery();
			while(result.next()) {
				System.out.print(result.getInt("f_id"));
				System.out.print("  ");
				System.out.print(result.getInt("t_id"));
				System.out.print("  ");
				System.out.print(result.getInt("s_id"));
				System.out.print("  ");
				System.out.print(result.getString("s_name"));
				System.out.print("  ");
				System.out.print(result.getInt("q1"));
				System.out.print("  ");
				System.out.print(result.getInt("q2"));
				System.out.print("  ");
				System.out.print(result.getInt("q3"));
				System.out.print("  ");
				System.out.print(result.getInt("q4"));
				System.out.print("  ");
				System.out.print(result.getInt("q5"));
				System.out.print("  ");
				System.out.println(result.getFloat("rating"));
				counter++;
			};
			System.out.println(counter +" records displayed......");
		}catch(Exception e){System.out.println(e);}
		//finally {System.out.println("TABLE DISPLAYED");}
	}
	public static int display_f_specific(int s_id) throws Exception{
		try {
			Connection con = get_t_connection();
			int counter=0;
			PreparedStatement display = con.prepareStatement("SELECT * FROM feedback WHERE s_id='"+s_id+"';");
			ResultSet result = display.executeQuery();
			System.out.println(" f_id | t_id | s_id | sname | q1 | q2 | q3 | q4 | q5 | rating ");
			while(result.next()) {
				System.out.print(result.getInt("f_id"));
				System.out.print("        ");
				System.out.print(result.getInt("t_id"));
				System.out.print("    ");
				System.out.print(result.getInt("s_id"));
				System.out.print("    ");
				System.out.print(result.getString("s_name"));
				System.out.print("    ");
				System.out.print(result.getInt("q1"));
				System.out.print("    ");
				System.out.print(result.getInt("q2"));
				System.out.print("    ");
				System.out.print(result.getInt("q3"));
				System.out.print("    ");
				System.out.print(result.getInt("q4"));
				System.out.print("    ");
				System.out.print(result.getInt("q5"));
				System.out.print("    ");
				System.out.println(result.getFloat("rating"));
				//System.out.print("        ");
				counter++;
			};
			System.out.println(counter +" records displayed......");
			return counter;
		}catch(Exception e){System.out.println(e);}
		//finally {System.out.println("TABLE DISPLAYED");}
		return 0;
	}
	public static void display_best() throws Exception{
		try {
			Connection con=get_t_connection();
			PreparedStatement best = con.prepareStatement("SELECT t_name,rating FROM teacher,feedback WHERE teacher.t_id=feedback.t_id ORDER BY rating DESC LIMIT 1;");
			ResultSet result = best.executeQuery();
			result.next();
			System.out.println("BEST TEACHER ACCORDING TO FEEDBACK IS ---");
			System.out.print(result.getString("t_name"));
			System.out.print("   "+result.getFloat("rating"));
		}catch(Exception e) {System.out.println(e);}
	}
	public static void display_worst() throws Exception{
		try {
			Connection con=get_t_connection();
			PreparedStatement worst = con.prepareStatement("SELECT t_name,rating FROM teacher,feedback WHERE teacher.t_id=feedback.t_id ORDER BY rating ASC LIMIT 1;");
			ResultSet result = worst.executeQuery();
			result.next();
			System.out.println("WORST TEACHER ACCORDING TO FEEDBACK IS ---");
			System.out.print(result.getString("t_name"));
			System.out.print("  "+result.getFloat("rating"));
		}catch(Exception e) {System.out.println(e);}
	}	
	}