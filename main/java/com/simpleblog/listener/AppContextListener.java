package com.simpleblog.listener;

import com.simpleblog.util.DatabaseConnection;
import jakarta.servlet.annotation.WebListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // إنشاء الاتصال بقاعدة البيانات
            Connection connection = DatabaseConnection.getConnection();
            // وضع الاتصال في ServletContext
            sce.getServletContext().setAttribute("DBConnection", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // إغلاق الاتصال عند إيقاف التطبيق
        Connection connection = (Connection) sce.getServletContext().getAttribute("DBConnection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
