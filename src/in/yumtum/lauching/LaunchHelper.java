package in.yumtum.lauching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class LaunchHelper {

	static Logger log = Logger.getLogger(LaunchHelper.class);
	
    public String insertData(String email){
    	
    	log.info("In LaunchHelper.insertData");
    	
    	String result = null;
    	try{		
    		log.debug("In try block before getting connection");
    		Connection conn = getDBConnection();
    		PreparedStatement pst = null;
    		String sql = null;
    		ResultSet rs = null;
    		String count = null;
    		conn.setAutoCommit(true);
    		
    		//log.debug("after getting connection and initializing variables"+conn);
    		
    		sql = "select count(*) from betausers where email in(?)";
    		  
    		  log.debug("before setting prepared statement");
    		  pst = conn.prepareStatement(sql);
    		  pst.setString(1, email);
    		  log.debug("before execute query");
    		  rs = pst.executeQuery();
    		  log.debug("after execute query");
    		  while (rs.next()) {
    		        count = rs.getString(1);
    		       }
    		  
       		  pst.close();
       		  rs.close();
       		  log.debug("Count should be 1 if email exists: count:"+count);
       		if(count.equals("0")){
       			log.debug("in If count is 0");
        		java.util.Date dt = new java.util.Date();
        		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           		String currentTime = sdf.format(dt);
           		
	       	  	sql = "insert into betausers (email,regDate) values(?,?)";
	    		pst = conn.prepareStatement(sql);
	    		pst.setString(1, email);
	    		pst.setString(2, currentTime);
	    		log.debug("before insert statement execute query");
	    		pst.executeUpdate();
	    		log.debug("after insert statement execute query");
	    		  pst.close();
	    		  conn.close();
	    		  result = "success";
       		}else {
       			log.debug("in else. We will be here if email exists in db already");
       			result = "exists";
       		}
    	}
    	catch (Exception e) {
    		log.error("exception occoured during sql execution. error is:"+e.getMessage());
    		result = "failure";
    	  }
    	
    	return result;
    	
    }
	
	
	private Connection getDBConnection(){
		
		  /** Uses JNDI and Datasource (preferred style).   */
		
		    String DATASOURCE_CONTEXT = "java:comp/env/jdbc/YUMTUM_DS";
		    
		    log.info("In LaunchHelper.getDBConnection");
		    
		    Connection result = null;
		    try {
		    	  log.debug("before creating initial Context");
			      
		    	  Context initialContext = new InitialContext();
			      
			      log.debug("before creating datasource object");
			      
			      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
			      
			      log.debug("after creating datasource object");
			      
			      if (datasource != null) {
			    	  log.debug("before getting connection from DS");
			        
			    	  result = datasource.getConnection();
			          
			    	  log.debug("after getting connection from DS");
			    }
		    }
		    catch ( NamingException e ) {
		    	log.error("In NamingException. Message is:"+e.getMessage());
		    }
		    catch(SQLException e){
		    	log.error("In SQLException. Message is:"+e.getMessage());
		    }
		    catch(Exception e){
		    	log.error("In Generic Exception. Message is:"+e.getMessage());
		    }
		    return result;
		  	
	}

}
