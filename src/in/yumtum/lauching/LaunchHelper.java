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
    		String sql = null;
    		ResultSet rs = null;
    		String count = null;
    		conn.setAutoCommit(true);
    		 
    		sql = "select count(*) from betausers where email in(?)";
    		  pst = conn.prepareStatement(sql);
    		  pst.setString(1, email);
    		  rs = pst.executeQuery();
    		  
    		  while (rs.next()) {
    		        count = rs.getString(1);
    		       }
    		  
       		  pst.close();
       		  rs.close();
    		  
       		if(count.equals("0")){
        		java.util.Date dt = new java.util.Date();
        		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           		String currentTime = sdf.format(dt);
           		
	       	  	sql = "insert into betausers (email,regDate) values(?,?)";
	    		pst = conn.prepareStatement(sql);
	    		pst.setString(1, email);
	    		pst.setString(2, currentTime);
	    		pst.executeUpdate();
	    		  pst.close();
	    		  conn.close();
	    		  result = "success";
       		}else {
       			result = "exists";
       		}
    	}
    	catch (Exception e) {
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
		      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
		      if (datasource != null) {
		        result = datasource.getConnection();
		      }
		    }
		    catch ( NamingException e ) {
		    }
		    catch(SQLException e){
		    }
		    return result;
		  	
	}

}
