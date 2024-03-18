package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dal.dao.CartDao;
import step.learning.services.form.FormParseResult;
import step.learning.services.form.FormParseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class SignupServlet extends HttpServlet {
    private final FormParseService formParseService;

    @Inject
    public SignupServlet(FormParseService formParseService) {
        this.formParseService = formParseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("page-body", "signup");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormParseResult parseResult = formParseService.parse(req);
        String json = String.format(
                "{\"fields\": %d, \"files\": %d}",
                parseResult.getFields().size(),
                parseResult.getFiles().size()
        );
        resp.getWriter().print(json);
    }
}
