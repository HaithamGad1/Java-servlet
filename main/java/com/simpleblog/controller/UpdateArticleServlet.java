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

@WebServlet("/update-article")
public class UpdateArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");

        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setCategory(category);

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);
        articleService.updateArticle(article);

        response.sendRedirect("dashboard.jsp");
    }
}
