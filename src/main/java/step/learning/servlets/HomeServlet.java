package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.services.db.DbService;
import step.learning.services.hash.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Singleton
public class HomeServlet extends HttpServlet {

    private final HashService hashService;
    private final DbService dbService;
    @Inject
    public HomeServlet(HashService hashService, DbService dbService) {
        this.hashService = hashService;
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("hash", hashService.digest("123"));
        req.setAttribute("db", dbService.getConnection() == null ? "Error" : "Success");

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