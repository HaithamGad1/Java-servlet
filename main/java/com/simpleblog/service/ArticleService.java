package com.simpleblog.service;

import com.simpleblog.dao.ArticleDAO;
import com.simpleblog.model.Article;
import java.sql.Connection;
import java.util.List;

public class ArticleService {
    private final ArticleDAO articleDAO;

    public ArticleService(Connection connection) {
        this.articleDAO = new ArticleDAO(connection);
    }

    // إنشاء مقال جديد
    public boolean createArticle(Article article) {
        return articleDAO.createArticle(article);
    }

    // الحصول على جميع مقالات مستخدم معين
    public List<Article> getAllArticlesByUserId(int userId) {
        return articleDAO.getAllArticlesByUserId(userId);
    }

    // الحصول على مقال بواسطة ID
    public Article getArticleById(int id) {
        return articleDAO.getArticleById(id);
    }

    // تحديث مقال موجود
    public boolean updateArticle(Article article) {
        return articleDAO.updateArticle(article);
    }

    // حذف مقال
    public boolean deleteArticle(int id) {
        return articleDAO.deleteArticle(id);
    }

    // الحصول على جميع المقالات
    public List<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }

    // زيادة عدد مشاهدات المقال
    public boolean incrementViews(int articleId) {
        return articleDAO.incrementViews(articleId);
    }

    // البحث في المقالات
    public List<Article> searchArticles(String query) {
        return articleDAO.searchArticles(query);
    }

    // الحصول على مقالات حسب الفئة
    public List<Article> getArticlesByCategory(String category) {
        return articleDAO.getAllArticles().stream()
                .filter(article -> article.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}