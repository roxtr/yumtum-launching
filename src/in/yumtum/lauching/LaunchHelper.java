package in.yumtum.lauching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LaunchHelper {
	
    public String insertData(String email){
    	
    	String result = null;
    	try{		
    	
    		Connection conn = getDBConnection();
    		PreparedStatement pst = null;
    		conn.setAutoCommit(true);
    		 ResultSet rs = null;
    		 System.out.println("after connetion formation");
    		 
    		java.util.Date dt = new java.util.Date();

    		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    		String currentTime = sdf.format(dt);

    		  String sql = "insert into betausers (email,regDate) values(?,?)";
    		  System.out.println("before pst");
    		  pst = conn.prepareStatement(sql);
    		  System.out.println("after pst");
    		  pst.setString(1, email);
    		  pst.setString(2, currentTime);
    		  System.out.println("before excute");
    		  pst.executeUpdate();
    		  pst.close();
    		  System.out.println("after wxecute");
    		  sql = "select * from betausers";
    		  pst = conn.prepareStatement(sql);
    		  System.out.println("before exec query");
    		  rs = pst.executeQuery();
    		  System.out.println("after wxec");
    		  while (rs.next()) {
    		  System.out.print(rs.getString(1) + "\t");
    		  System.out.print(rs.getString(2) + "\t");
    		  System.out.println(rs.getString(3));
    		  }
    		  rs.close();
    		  pst.close();
    		  
    		  conn.close();
    		  System.out.println("before set resut");
    		  result = "success";
    	
    	}
    	catch (Exception e) {
    		  System.out.println(e.getMessage());
    		result = "failure";
    	  }
    	
    	return result;
    	
    }
	
	
	private Connection getDBConnection(){
		
		  /** Uses JNDI and Datasource (preferred style).   */
		
		    String DATASOURCE_CONTEXT = "java:comp/env/jdbc/YUMTUM_DS";
		    
		    Connection result = null;
		    try {
		    	System.out.println("before getting intial context");
		      Context initialContext = new InitialContext();
		      System.out.println("after getting intial context");
		      System.out.println("intial context value"+initialContext);
		      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
		      System.out.println("after getting data source - object"+datasource);
		      if (datasource != null) {
		        result = datasource.getConnection();
		      }
		    }
		    catch ( NamingException e ) {
		    	System.out.println(e.getMessage());
		    }
		    catch(SQLException e){
		    	System.out.println(e.getMessage());
		    }
		    System.out.println("before returning result");
		    return result;
		  	
	}

}
