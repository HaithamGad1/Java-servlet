package com.simpleblog.controller;

import com.simpleblog.model.Article;
import com.simpleblog.model.User;
import com.simpleblog.service.ArticleService;
import com.simpleblog.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);
        UserService userService = new UserService(connection);

        List<Article> articles = articleService.getAllArticles();
        List<User> users = null;
        try {
            users = userService.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("articles", articles);
        request.setAttribute("users", users);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}
