package step.learning.servlets;

import step.learning.dal.dao.PromotionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/promotion")
public class PromotionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PromotionDao promotionDao = new PromotionDao();
        req.setAttribute("promotion", promotionDao.getPromotion());
        req.setAttribute("page-body", "promotion");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req,resp);
    }
}
