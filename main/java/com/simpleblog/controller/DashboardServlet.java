package com.simpleblog.controller;

import com.simpleblog.model.Article;
import com.simpleblog.model.User;
import com.simpleblog.service.ArticleService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");

        ArticleService articleService = new ArticleService(connection);
        List<Article> articles = articleService.getAllArticlesByUserId(user.getId());

        request.setAttribute("articles", articles);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}