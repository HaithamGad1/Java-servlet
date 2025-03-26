package com.simpleblog.controller;

import com.simpleblog.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        UserService userService = new UserService(connection);
        try {
            userService.deleteUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("admin.jsp");
    }
}
