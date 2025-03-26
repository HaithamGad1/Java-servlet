package com.simpleblog.controller;

import com.simpleblog.model.Article;
import com.simpleblog.model.User;
import com.simpleblog.service.ArticleService;
import com.simpleblog.dao.ArticleDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/create-article")
public class CreateArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // جلب بيانات المقال من الفورم
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");

        // الحصول على معرّف المستخدم من الجلسة
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();

        // إنشاء كائن المقال
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCategory(category);
        article.setUserId(userId);

        // الحصول على الاتصال بقاعدة البيانات من الـ ServletContext
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");

        // إنشاء كائن خدمة المقالات
        ArticleService articleService = new ArticleService(connection);

        // استدعاء الطريقة createArticle لإدخال المقال في قاعدة البيانات
        boolean isCreated = articleService.createArticle(article);

        // التحقق مما إذا تم إدخال المقال بنجاح
        if (isCreated) {
            response.sendRedirect("dashboard");  // إعادة التوجيه إلى صفحة لوحة التحكم إذا تم إدخال المقال بنجاح
        } else {
            response.getWriter().write("Error creating article!");  // رسالة في حالة حدوث خطأ
        }
    }
}
