package exercises;

/*
 *JdbcEx1.java
 *Created on Apr 12, 2015
 *Purpose: Jdbc.java - connect to a database using Oracle Thin Driver
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Rui
 */
  public class Project{
	  
	  static Connection conn = null;
	  static Statement stmt = null;
	  static String dbURL="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	  static String user = "projects"; /**your login here*/
	  static String password = "projects";  /*use your password here*/
	  static String custID=" ";
	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	  //new stuff
// static String sqlVQ1 = "SELECT productid AS \"Code\", RPAD(productname, 20, ' ') AS \"Product\"， "
	//	 +"To_Char(unitprice, '$9,999.99') AS \"Price\" FROM VQ1"; 
	 
// static String sqlVQ2 = "SELECT Orderid AS \"Order\", To_char(Orderdate,'yyyy-mm-dd') AS \"Order Date\"， "
		// +"To_Char(shippeddate, 'yyyy-mm-dd') AS \"ship date\", companyname FROM VQ2"; 
	 static String sqlQ1="select companyname as \"Companyname\",to_char(sysdate,'dd/mm/yyyy')as \"Current Date\" from customers";
	 static String sqlQ2="SELECT ORDERID, ORDERDATE, \"Status\", TO_CHAR(\"Total Amount\",'$9,999.99')  as \"Total Amount\" from VQ3";		

	 public Project() {}{
			
		}

	  
	  public static void main(String argus[]) throws SQLException, IOException{
		 
		 // Calendar currenttime = Calendar.getInstance();
		   // Date sqldate = new Date((currenttime.getTime()).getTime());
		 
		    		try{
     			  Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch(ClassNotFoundException e){
			  System.err.println(e.getMessage());
		  }
		
		  // while(!custID.equalsIgnoreCase("X")){
		//  System.out.print("Enter the Customer  ID:  ");
		//  custID = br.readLine();
		 // System.out.println("");
	   
		  try{
			  System.out.print("Enter the Customer  ID:  ");
	         	custID = br.readLine();
	          	
	         }catch(IOException ioe){
	        System.out.println("IO error trying to read custID!");
	         System.exit(1);
	          }
	      		
		  
		
		 //  }
		   
		   try{
			   
			  conn=DriverManager.getConnection(dbURL, user, password);
			  conn.clearWarnings();
			  System.out.println("Connection opened!for driver ==>Oracle 11XE!");
			   stmt= conn.createStatement();
			   ResultSet rs1 = stmt.executeQuery(sqlQ1);
			   ResultSet rs2 = stmt.executeQuery(sqlQ2);
			   
			   ResultSetMetaData rsm = rs2.getMetaData();
			  // custID=rs1.getNString(custID);
			   
               
			  
			   System.out.println("Statistics for "+ rs1 + " By Rui");
	           
			   System.out.println("Orders");


			   System.out.println("===================================================");

			   
			System.out.println();
       		System.out.println(rsm.getColumnName(1) + "\t\t" + rsm.getColumnName(2) + "\t\t"+
       	                    	rsm.getColumnName(3) + "\t\t" + rsm.getColumnName(4)); 


			   
       		while (rs2.next()) {
       			
       			
       	   			System.out.println(rs2.getInt(1) + "\t\t" + rs2.getDate(2)+"\t\t"
    					+ rs2.getString(3)+ "\t\t" + rs2.getString(4));
    		}
       }catch (Exception e) {
      	 System.err.println(e.getMessage());
 }

	   
	  
	  }
	  }
  