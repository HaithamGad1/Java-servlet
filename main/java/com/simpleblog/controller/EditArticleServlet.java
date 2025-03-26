package com.simpleblog.controller;

import com.simpleblog.model.Article;
import com.simpleblog.service.ArticleService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/edit-article")
public class EditArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int articleId = Integer.parseInt(request.getParameter("id"));
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);

        Article article = articleService.getArticleById(articleId);
        request.setAttribute("article", article);
        request.getRequestDispatcher("/WEB-INF/views/edit-article.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        ArticleService articleService = new ArticleService(connection);

        Article article = articleService.getArticleById(id);
        article.setTitle(title);
        article.setContent(content);
        article.setCategory(category);

        if (articleService.updateArticle(article)) {
            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("error", "Failed to update article");
            request.getRequestDispatcher("edit-article.jsp").forward(request, response);
        }
    }
}