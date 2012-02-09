package in.yumtum.lauching;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class saveInfo
 */
public class saveInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String email;
		
		email = request.getParameter("email");
		LaunchHelper helper = new LaunchHelper();
		
		response.setContentType("text/html");
   	    PrintWriter out = response.getWriter();
   	    out.write(helper.insertData(email));
		
	}

}
