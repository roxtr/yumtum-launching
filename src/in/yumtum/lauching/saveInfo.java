package in.yumtum.lauching;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class saveInfo
 */
public class saveInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(saveInfo.class);
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
	
		log.info("In do post of saveInfo servlet");
		
		String email;
		String refId;
		String resp;
		
		email = request.getParameter("email");
		refId = request.getParameter("refId");
		
		
		log.debug("email:"+email);
		log.debug("email:"+refId);
		
		if(refId.equals("") || refId.isEmpty()){
			refId = "Default";
		}
		
		LaunchHelper helper = new LaunchHelper();
		resp = helper.insertData(email,refId);
		response.setContentType("text/html");
   	    PrintWriter out = response.getWriter();
   	    out.write(resp);
		
   	    
	}

}
