package feedbackdata;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//feedback.create_f_Table();
		char c;
		do
		{
		System.out.println("\n ---FEEDBACK MANAGEMENT SYSTEM---");
		System.out.println(" 1. TEACHER RECORDS");
		System.out.println(" 2. FEEDBACK FORM");
		System.out.println(" 3. FEEDBACK RESULTS");
		System.out.println(" 4. EXIT");
		System.out.println(" Enter your choice");
		int choice,choice1;
		int t_id,s_id,q1,q2,q3,q4,q5;
		choice=sc.nextInt();
		switch(choice)
		{
		case 1: do
				{
				System.out.println("\n ---TEACHER---");
				System.out.println(" 1. ADD RECORD");
				System.out.println(" 2. DISPLAY ALL RECORDS");
				System.out.println(" 3. DISPLAY SPECIFIC RECORD");
				System.out.println(" 4. DELETE SPECIFIC RECORD");
				System.out.println(" 5. EDIT SPECIFIC RECORD");
				System.out.println(" 6. BACK TO MAIN MENU");
				System.out.println(" Enter your choice");
				choice1=sc.nextInt();
				switch(choice1)
				{
				case 1:System.out.println("\n Enter the teacher id");
					   t_id=sc.nextInt();
					   System.out.println("\n Enter the teacher name");
				       sc.nextLine();
				       String t_name=sc.nextLine();
				       System.out.println("\n Enter the teacher dept");
				       String t_dept=sc.nextLine();
				       teacher T = new teacher(t_id,t_name,t_dept);
				       teacher.get_t_connection();    //function to load the jdbc driver and make the connection between mysql and the program
				       T.insert_t();				  //function to insert values in the teacher table
				       break;
				case 2:teacher.display_t();			//function to display all the values
						break;
				case 3:System.out.println("\n Enter the teacher id");
				       t_id=sc.nextInt();
				       //System.out.println(" ID | Teacher Name | Department");
				       System.out.println(" ID | Teacher Name | Department");
				       teacher.display_t_specific(t_id);  //function to display specific record of the teacher table
				       break;
				case 4:System.out.println("\n Enter the teacher id");
			       	   t_id=sc.nextInt();
			       	   teacher.delete_t(t_id);			// function to delete the specific record of the teacher table
			       	   teacher.display_t();
			       	   break;
				case 5:
						System.out.println("\n Enter the teacher id");
		       	       t_id=sc.nextInt();
		       	       int x;
		       	       x=teacher.display_t_specific(t_id);
		       	       if(x!=0)
		       	       {
		    		   System.out.println("Do you want to edit this record");
		    		   c=sc.next().charAt(0);
		    		   if(c=='Y'||c=='y')
		    		   {
		       	       System.out.println("\n Enter the teacher name");
				       sc.nextLine();
				       String tt_name=sc.nextLine();
				       System.out.println("\n Enter the teacher dept");
				       String tt_dept=sc.nextLine();
		       	       teacher.edit_t(t_id,tt_name,tt_dept);	//function to edit  the specific record from the teacher table
		       	       teacher.display_t();
		    		   }
		       	       }
		       	       else
		       	       {
		       	    	   System.out.println("No Records Found");
		       	       }
		       	       break;
		    
				}
				System.out.println("Do you want to continue... in Teacher Menu");
				c=sc.next().charAt(0);
				}while(c=='y');
				break;
		case 2:do
				{
				System.out.println("\n ---FEEDBACK---");
				System.out.println(" 1. ADD FEEDBACK");
				System.out.println(" 2. DISPLAY ALL FEEDBACK RECORDS");
				System.out.println(" 3. DISPLAY SPECIFIC FEEDBACK RECORD");
				System.out.println(" 4. DELETE SPECIFIC  FEEDBACK RECORD");
				System.out.println(" 5. EDIT SPECIFIC FEEDBACK RECORD");
				System.out.println(" 6. BACK TO MAIN MENU");
				System.out.println(" Enter your choice");
			    choice1=sc.nextInt();
			    switch(choice1)
			    {
			    case 1:System.out.println("Enter the teacher id");
				   t_id=sc.nextInt();
				   teacher.display_t_specific(t_id);
				   System.out.println("Enter the student id");
				   s_id=sc.nextInt();
				   System.out.println("Enter the student name");
			       sc.nextLine();
			       String s_name=sc.nextLine();
			       //System.out.println("\n Enter the teacher dept");
			       //String t_dept=sc.nextLine();
			       System.out.println(" Answer the following Question MAX-10 Min 5");
			       System.out.println("1.SUBJECT KNOWLEDGE");
				   q1=sc.nextInt();
				   System.out.println("2.COMMUNICATION SKILLS");
				   q2=sc.nextInt();
				   System.out.println("3.INTERACTIVENESS");
				   q3=sc.nextInt();
				   System.out.println("4.CLASS CONTROL");
				   q4=sc.nextInt();
				   System.out.println("5.TEACHING SKILLS");
				   q5=sc.nextInt();
			       feedback F = new feedback(t_id,"","",s_id,s_name,q1,q2,q3,q4,q5);
			       feedback.get_t_connection();
			       F.insert_f();
			       break;
			    case 2:System.out.println(" f_id | t_id | s_id | sname | q1 | q2 | q3 | q4 | q5 | rating ");
			    		feedback.display_f();
			    	   break;
			    case 3:System.out.println("Enter the Student Id");
			    		s_id=sc.nextInt();
			    		feedback.display_f_specific(s_id);
			    		break;
			    case 4:System.out.println("Enter the Student Id");
	    		       s_id=sc.nextInt();
	    		       feedback.delete_f(s_id);
	    		       break;
			    case 5:System.out.println("Enter the Student Id");
	    			   s_id=sc.nextInt();
	    		       feedback.display_f_specific(s_id);
	    		       System.out.println("\n Do you want to delete the data");
	    		       c=sc.next().charAt(0);
	    		       if(c=='y')
	    		       {
	    		    	   System.out.println("Enter the teacher id");
	    				   t_id=sc.nextInt();
	    				   System.out.println("Enter the student name");
	    			       sc.nextLine();
	    			       String s1_name=sc.nextLine();
	    			       //System.out.println("\n Enter the teacher dept");
	    			       //String t_dept=sc.nextLine();
	    			       System.out.println(" Answer the following Question MAX-10 Min 5");
	    			       System.out.println("1.SUBJECT KNOWLEDGE");
	    				   q1=sc.nextInt();
	    				   System.out.println("2.COMMUNICATION SKILLS");
	    				   q2=sc.nextInt();
	    				   System.out.println("3.INTERACTIVENESS");
	    				   q3=sc.nextInt();
	    				   System.out.println("4.CLASS CONTROL");
	    				   q4=sc.nextInt();
	    				   System.out.println("5.TEACHING SKILLS");
	    				   q5=sc.nextInt();
	    			       feedback.edit_f(t_id, s_id, s1_name, q1, q2, q3, q4, q5);
	    		       }
	    		       break;
	    		    	   
			    }
			    System.out.println("\n Do you want to continue in FeedBack Menu");
				c=sc.next().charAt(0);
				}while(c=='y');
				break;
		case 3:do
				{
				System.out.println("\n ---FEEDBACK RECORDS---");
			   System.out.println(" 1. BEST TEACHER");
			   System.out.println(" 2. WORST TEACHER");
			   System.out.println(" 3. BACK TO MAIN MENU");
		       System.out.println(" Enter your choice");
		       choice1=sc.nextInt();
				switch(choice1)
				{
				case 1:feedback.display_best();
						break;
				case 2:feedback.display_worst();
						break;
				}
				System.out.println("\n Do you want to continue in Feedback Records");
				c=sc.next().charAt(0);
				}while(c=='y');
		}
		System.out.println("\n Do you want to continue");
		c=sc.next().charAt(0);
	}while(c=='y');
	System.out.println("\n THANK YOU");
	sc.close();
	}
}
