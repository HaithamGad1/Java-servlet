package com.simpleblog.controller;

import com.simpleblog.dao.CommentDAO;
import com.simpleblog.model.Comment;
import com.simpleblog.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleIdStr = request.getParameter("articleId");
        if (articleIdStr == null || articleIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Article ID is missing");
            return;
        }

        try {
            int articleId = Integer.parseInt(articleIdStr);
            int userId = ((User) request.getSession().getAttribute("user")).getId();
            String commentText = request.getParameter("commentText");

            Comment comment = new Comment(articleId, userId, commentText);

            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            CommentDAO commentDAO = new CommentDAO(connection);
            commentDAO.createComment(comment);

            response.sendRedirect("view-article.jsp?id=" + articleId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Article ID format");
        }
    }
}