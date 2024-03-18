package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.services.additem.AddItemParseResult;
import step.learning.services.additem.AddItemParseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AddItemServlet extends HttpServlet {
    private final AddItemParseService addItemParseService;
    @Inject
    public AddItemServlet(AddItemParseService addItemParseService) {
        this.addItemParseService = addItemParseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("page-body", "additem");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddItemParseResult parseResult = addItemParseService.parse(req);
        String json = String.format(
                "{\"fields\": %d, \"files\": %d}",
                parseResult.getFields().size(),
                parseResult.getFiles().size()
        );
        resp.getWriter().print(json);
    }
}
