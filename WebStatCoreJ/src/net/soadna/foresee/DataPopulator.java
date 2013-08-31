package net.soadna.foresee;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class DataPopulator {

   private static String user = "Anil";
   private static String pwd = "dumpass";
   private static String url = "jdbc:db2:FORESEE";

	
	public static void main(String[] s) {
		
		insertFiles();
	    
			
	    
		
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

	  public static void insertFiles(){
		  try {
		    File dataDir = new File("C:\\temp\\xmlData\\new");
		    
		    List<File> files = (List<File>)Arrays.asList(dataDir.listFiles());
		    
		    for (File file : files) {
				
		    	// get a connection 
		    	Connection conn = DataPopulator.getConn();
		    	
		    	//   define string that will insert file without validation
		    	String query = "insert into STATS (ID, EVENT) values (?,?)";
		    	
		    	// prepare the statement
		    	PreparedStatement insertStmt = conn.prepareStatement(query);
		    	insertStmt.setString(1, UUID.randomUUID().toString());
		    	
		    	System.out.println("processing .. " + file.getName());
		    	
		    	insertStmt.setBinaryStream(2, new FileInputStream(file), (int)file.length());
		    	
		    	// execute the statement 
		    	if (insertStmt.executeUpdate() != 1) {
		    		System.out.println("No record inserted.");
		    	}
		    	//		    . . . 
		    	conn.close();
		    }
		  }
		  
		  catch (Exception e) {  
		    	//TODO: log?
		  }
			}
		    

//	
//	
//	public static void storeXMLData(ForeSeeEvent event)
//	{
//		String xmlFilePath = "";
//		
//		try
//		{
//			Document xmldoc= new DocumentImpl();
//			Element xmlTransaction = xmldoc.createElement("TRANSACTION");
//			
//			Element xmlEvent = xmldoc.createElement("EVENT");
//			xmlEvent.setAttributeNS(null, "ID", event.getEventId());
//			xmlEvent.setAttributeNS(null, "TYPE", event.getEventType());			
//			xmlEvent.setAttributeNS(null, "TIMESTAMP", event.getEventTimeStamp().toString());
//			xmlEvent.setAttributeNS(null, "SOURCE", event.getEventSource());
//			
//			xmlTransaction.appendChild(xmlEvent);
//			
//			Element xmlClient = xmldoc.createElement("CLIENT");
//			xmlClient.setAttributeNS(null, "ID", event.getClientId());
//			xmlClient.setAttributeNS(null, "HOSTNAME", event.getClientHostName());
//			xmlClient.setAttributeNS(null, "IPADDRESS", event.getClientIPAddress());
//			xmlClient.setAttributeNS(null, "SESSIONID", event.getClientSessionId());
//			
//			xmlTransaction.appendChild(xmlClient);
//			
//			OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
//			of.setIndent(1);
//			of.setIndenting(true);
//			
//			String tmpDir = System.getenv("java.io.tmpdir");
//			File xmlFile = File.createTempFile(" " + System.currentTimeMillis(), ".xml", new File(tmpDir));
//						
//			FileOutputStream fos = new FileOutputStream(xmlFile);
//						
//			XMLSerializer serializer = new XMLSerializer(fos,of);
//			
//			serializer.asDOMSerializer();
//			serializer.serialize( xmldoc.getDocumentElement() );
//			fos.close();
//			
//			Context ctx = new InitialContext();
//			
//			DataSource ds = (DataSource)ctx.lookup("jdbc/Foresee");
//			
//			Connection con = ds.getConnection();
//			
//			con.setAutoCommit(true);
//			
//			PreparedStatement pstmt = con.prepareStatement(
//			                            "INSERT INTO STATS(ID, XMLDATA) VALUES (?,?)");
//			
//			pstmt.setString(1, UUID.randomUUID().toString());
//			pstmt.setBinaryStream(1, new FileInputStream(xmlFile), (int)xmlFile.length());
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (pstmt.executeUpdate() != 1) {
//		        System.out.println("No record inserted.");
//		    }
//
//			rs.close();
//			
//			pstmt.close();
//			
//			con.close();						
//		}	
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}	
//	}
	
	
	
	

}
	

