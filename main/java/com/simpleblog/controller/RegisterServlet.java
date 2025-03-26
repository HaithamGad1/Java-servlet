package com.simpleblog.controller;

import com.simpleblog.dao.UserDAO;
import com.simpleblog.model.User;
import javax.servlet.annotation.WebServlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        // تهيئة الاتصال في الطريقة init()
        this.connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        UserDAO userDAO = new UserDAO(connection);
        try {
            userDAO.createUser(user);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
