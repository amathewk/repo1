package net.soadna.foresee;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseStatProcessor implements StatProcessor {
	
    private static String user = "Anil";
    private static String pwd = "dumpass";
    private static String url = "jdbc:db2:FORESEE";
	
	public void collectStats() {
		
		Connection conn = getConn();
		
		

	}

	public String generateChart() {
		// TODO Auto-generated method stub
		return null;
	}

	public String generateReport() {
		// TODO Auto-generated method stub
		return null;
	}

	// this method gets a database connection 	
	  public static Connection getConn(){
	    Connection conn=null;
			
	    //  load the appropriate DB2 driver and 
	    //  get a connection to the “test” database  
	    try {
	       Class.forName("com.ibm.db2.jcc.DB2Driver");
	       conn = DriverManager.getConnection(url, user, pwd);
//	       . . . 	
	    }
	    catch (Exception e) { e.printStackTrace();	}
	    return conn;
			
	  }   // end getConn();
	
}
