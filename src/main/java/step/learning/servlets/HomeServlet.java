package step.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // додаємо до атрибутів запиту додатковий - щодо тіла у шаблоні
        req.setAttribute("page-body", "home");
        // Імітуємо наче запит є "/WEB-INF/_layout.jsp" і передаємо у нього
        // req із доданим атрибутом
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req,resp);
    }
}
/*
    Сервлети - спеціалізовані класи Java для мережевої роботи.
    Можна вважати їх аналогами контролерів.
    Для роботи з сервлетами необхідно підключити javax servlet API
*/