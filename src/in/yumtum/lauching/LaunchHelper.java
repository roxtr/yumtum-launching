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
import org.json.simple.JSONObject;

public class LaunchHelper {

	static Logger log = Logger.getLogger(LaunchHelper.class);
	
    @SuppressWarnings("unchecked")
	public String insertData(String email,String srefId){
    	
    	log.info("In LaunchHelper.insertData");
    	
    	String result = null;
    	int dbRegId = 0;
		String dbEmail = null;
		String dbUrefId = null;
		
		String urefId = null;
		
    	try{		
    		log.debug("In try block before getting connection");
    		Connection conn = getDBConnection();
    		PreparedStatement pst = null;
    		String sql = null;
    		ResultSet rs = null;
    		
    		conn.setAutoCommit(true);
    		
    		//log.debug("after getting connection and initializing variables"+conn);
    		
    		sql = "select regID,urefId from betausers where email in(?)";
    		  
    		  log.debug("before setting prepared statement");
    		  pst = conn.prepareStatement(sql);
    		  pst.setString(1, email);
    		  log.debug("before execute query");
    		  rs = pst.executeQuery();
    		  log.debug("after execute query");
    		  while (rs.next()) {
    		        dbRegId = rs.getInt(1);
    		        dbUrefId = rs.getString(2);
    		       }
    		  
       		  pst.close();
       		  rs.close();
       		  log.debug("dbRegId should be non zero if email exists: count:"+dbRegId);
       		if(dbRegId == 0){
       			log.debug("in If count is 0");
        		java.util.Date dt = new java.util.Date();
        		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           		String currentTime = sdf.format(dt);
           		
           		urefId = genUniqueRandomStr();
           		
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
	    		  
	    		  result = "success";
       		}else {
	    		  
       			log.debug("in else. We will be here if email exists in db already");
       			result = "exists";
       		}
       		conn.close();
    	}
    	catch (Exception e) {
    		log.error("exception occoured during sql execution. error is:"+e.getMessage());
    		 e.printStackTrace();
    		result = "failure";
    	  }
    	
    	JSONObject jObj = new JSONObject();
    	
    	String subj;
    	String message;
    	
    	if (result.equals("success")){
    		
    		jObj.put("result", result);
    		jObj.put("refId", urefId);
    		
    		subj = "Thanks for signing Up";
    		message = "<div>Hi "+email+", <p>Thanks for joining the Yumtum beta list. We'll send you an invite soon.</p><p>In the meantime, you can follow us on <span><a href='www.twitter.com/yumtumindia'>Twitter</a></span>  or on <span><a href='www.facebook.com/yumtumindia'>Facebook</a></span> to get the inside scoop .</p></div>" +
    				"<p>Go ahead and share the URL ( http://www.yumtum.in/?refId="+urefId+" )with your friends and spread the joy. </p><p>\n <b>More you share, sooner you get the beta access.</b> :-) </p><p>\n \n \n \n - Team Yumtum</p>";

    		SendMail sendMail = new SendMail("hello@yumtum.in", email, subj, message);
    		sendMail.send();
    		
    		
    	} else if(result.equals("exists")){
    		
    		jObj.put("result", result);
    		jObj.put("refId", dbUrefId);

    		
    		subj = "Thanks for stopping by again";
    		message = "<div>Hi "+email+", <p>Thanks for coming back. We will contact you shortly with a beta invite.</p><p>In the meantime, you can follow us on <span><a href='www.twitter.com/yumtumindia'>Twitter</a></span>  or on <span><a href='www.facebook.com/yumtumindia'>Facebook</a></span> to get the inside scoop .</p></div>" +
    				"<p>Go ahead and share the URL ( http://www.yumtum.in/?refId="+dbUrefId+" )with your friends and spread the joy.</p><p> \n <b>More you share, sooner you get the beta access.</b> :-) </p><p>\n \n \n \n - Team Yumtum</p>";

    		SendMail sendMail = new SendMail("hello@yumtum.in", email, subj, message);
    		sendMail.send();

    		
    	}else{
    		
    		jObj.put("result", result);
    		jObj.put("refId", "");

    		
    		subj = "Thanks for stopping by ..";
    		message = "<div>Hi, <p>Thanks for stopping by.There seems to be a problem with our servers right now. :-( </p>\n\n<p> Please send a reply to this mail, we would then add you " +
    				"to the beta invites list.</p></div><div><p>\n \n \n \n - Team Yumtum</p></div>";

    		SendMail sendMail = new SendMail("hello@yumtum.in", email, subj, message);
    		sendMail.send();
    	}
    	
    	return jObj.toJSONString();
    	
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
  		  conn.close();
  		  if(count.equals("0")){
  			  bool = true;
  		  }
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
			 log.error("exception occoured during sql execution. error is:"+e.getMessage());
			 e.printStackTrace();
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
			
	/*		 BasicConfigurator.configure();

		     log.setLevel(Level.DEBUG); // optional if log4j.properties file not used
		     // Possible levels: TRACE, DEBUG, INFO, WARN, ERROR, and FATAL

			System.out.println(lh.genUniqueRandomStr());
*/	}

}
