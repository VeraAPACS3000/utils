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

/** Сейчас сервлет работает с сессией, увеличивая счетчик visitCounter при каждом посещении страницы. Если атрибут visitCounter еще не создан (при первом посещении страницы), метод getAttribute() вернет null, поэтому нужно проводить проверку на null. То же касается и параметров запроса. Если пользователь не передал параметр username, его значение будет null. В таком случае поприветствуем пользователя как анонимного.

Чтобы передать параметр в GET-запросе, используются path-variables, то есть нужно обратиться по ссылке http://localhost:8080/hello?username=Pavel. Подробней об http-запросах можно почитать в предыдущей статье цикла.

Теперь у нашего приложения есть минимальная логика, но немного раздражает 404-я ошибка в root-пути. Чтобы исправить ее, создадим еще один сервлет и замапим его на начальную страницу @WebServlet("/"). Задача этого сервлета — перенаправлять запросы на путь “/hello”. Сделать это можно двумя способами: с помощью forward или redirect. Пожалуй, стоит разобраться, в чем между ними разница.

forward — делегирует обработку запроса другому сервлету на сервере, клиент при этом не задействуется. Для этого в метод doGet() нового сервлета нужно добавить такой код: 

getServletContext().getRequestDispatcher("/hello").forward(req, resp);

В этом коде мы обращаемся к контексту сервлетов, из него достаем диспетчер запросов нужного сервлета и просим его обработать конкретный запрос с указанными параметрами (req, resp).

redirect — возвращает клиенту адрес, по которому нужно обратиться для обработки его запроса. Большинство браузеров переходит на переданную ссылку автоматически. Для реализации редиректа нужно добавить этот код:

resp.sendRedirect(req.getContextPath() + "/hello");

Мы в HttpServletResponse вызываем метод redirect() и передаем ему адрес, на который клиенту нужно обратиться. Важная деталь: http-параметры нужно также добавить в конце полного пути редиректа, что не очень удобно.

В нашей ситуации предпочтительнее использовать forward, а бывает так, что лучше — redirect. Если будешь понимать разницу в их работе, не ошибешься с выбором.*/
