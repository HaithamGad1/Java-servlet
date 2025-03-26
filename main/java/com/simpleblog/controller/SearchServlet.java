package com.simpleblog.controller;

import com.simpleblog.model.Article;
import com.simpleblog.service.ArticleService;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);
        List<Article> articles = articleService.searchArticles(query);

        request.setAttribute("articles", articles);
        request.getRequestDispatcher("search-results.jsp").forward(request, response);
    }
}