package com.simpleblog.controller;

import com.simpleblog.service.ArticleService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/delete-article")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);
        articleService.deleteArticle(id);

        response.sendRedirect("dashboard.jsp");
    }
}
