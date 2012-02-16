package in.yumtum.lauching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class LaunchHelper {

	static Logger log = Logger.getLogger(LaunchHelper.class);
	
    public String insertData(String email,String srefId){
    	
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
           		
           		String urefId = genUniqueRandomStr();
           		
	       	  	sql = "insert into betausers (email,regDate,srefId,urefId) values(?,?,?,?)";
	    		pst = conn.prepareStatement(sql);
	    		pst.setString(1, email);
	    		pst.setString(2, currentTime);
	    		pst.setString(3, srefId);
	    		pst.setString(4, urefId);
	    		
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
	
    private String generateRandomStr(){
    	
    	String randomStr = null;
    	
    	randomStr = RandomStringUtils.randomAlphabetic(6);
    	
    	return randomStr;
    }
    
    private boolean checkRandomStr(String randomStr){
    	
    	boolean bool = false;
		Connection conn = getDBConnection();
		PreparedStatement pst = null;
		String sql = null;
		ResultSet rs = null;

		String count = null;
		sql = "select count(*) from betausers where urefId in(?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, randomStr);
		
			rs = pst.executeQuery();
		
		  log.debug("after execute query");
		  while (rs.next()) {
		        count = rs.getString(1);
		       }
		  
  		  pst.close();
  		  rs.close();
    	
  		  if(count.equals("0")){
  			  bool = true;
  		  }
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
			 log.error("exception occoured during sql execution. error is:"+e.getMessage());
			}
    	return bool;
    }
    
    public String genUniqueRandomStr(){
    	
    	String unqRandomStr = "";
    	
    	log.info("in getUniqueRandomStr");
    	
    	unqRandomStr = generateRandomStr();
    	
    	if(!checkRandomStr(unqRandomStr)){
    		unqRandomStr = genUniqueRandomStr();
    	}
    	
    	return unqRandomStr;
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
	
	public static void main(String args[]){
			LaunchHelper lh = new LaunchHelper();
			
			 BasicConfigurator.configure();

		     log.setLevel(Level.DEBUG); // optional if log4j.properties file not used
		     // Possible levels: TRACE, DEBUG, INFO, WARN, ERROR, and FATAL

			System.out.println(lh.genUniqueRandomStr());
	}

}
