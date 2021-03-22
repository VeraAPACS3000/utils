import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {

	//Переопределяем
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		
		HttpSession session = request.getSession();
		Integer counter = (Integer)session.getAttribute("visitCounter");
		if(counter==null) {
			counter=1;
		} else {
			counter++;
		}
		session.setAttribute("visitCounter", counter);
		
		String username = request.getParameter("username");
				
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		if(username == null) {
			printWriter.write("my Hello word");
		} else {
			printWriter.write(username + ", my Hello word");
		}
		
		printWriter.close();
		
		
	}

	
}
