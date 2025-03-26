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

@WebServlet("/view-article")
public class ViewArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);
        Article article = articleService.getArticleById(id);

        request.setAttribute("article", article);
        request.getRequestDispatcher("view-article.jsp").forward(request, response);
    }
}