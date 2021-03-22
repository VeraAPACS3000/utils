import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Home extends HttpServlet {

	//В этом коде мы обращаемся к контексту сервлетов, из него достаем диспетчер запросов нужного сервлета 
	//и просим его обработать конкретный запрос с указанными параметрами (req, resp).
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getServletContext().getRequestDispatcher("/hello").forward(request, response);
		
		//Мы в HttpServletResponse вызываем метод redirect() и передаем ему адрес, на который клиенту нужно обратиться.
		//Важная деталь: http-параметры нужно также добавить в конце полного пути редиректа, что не очень удобно.
		 response.sendRedirect(request.getContextPath()+"/hello");
	}
	
}
