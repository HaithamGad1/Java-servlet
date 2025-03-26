package com.simpleblog.dao;

import com.simpleblog.model.Article;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    private final Connection connection;

    public ArticleDAO(Connection connection) {
        this.connection = connection;
    }

    // Create Article
    public boolean createArticle(Article article) {
        String sql = "INSERT INTO articles (title, content, category, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setString(3, article.getCategory());
            pstmt.setInt(4, article.getUserId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    article.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating article: " + e.getMessage());
            return false;
        }
    }

    // Get All Articles by User ID
    public List<Article> getAllArticlesByUserId(int userId) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE user_id = ? ORDER BY created_at DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Article article = mapResultSetToArticle(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching articles for user " + userId + ": " + e.getMessage());
        }
        return articles;
    }

    // Get Article by ID
    public Article getArticleById(int id) {
        String sql = "SELECT * FROM articles WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToArticle(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching article with ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    // Update Article
    public boolean updateArticle(Article article) {
        String sql = "UPDATE articles SET title = ?, content = ?, category = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setString(3, article.getCategory());
            pstmt.setInt(4, article.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating article: " + e.getMessage());
            return false;
        }
    }

    // Delete Article
    public boolean deleteArticle(int id) {
        String sql = "DELETE FROM articles WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting article: " + e.getMessage());
            return false;
        }
    }

    // Get All Articles
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles ORDER BY created_at DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Article article = mapResultSetToArticle(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all articles: " + e.getMessage());
        }
        return articles;
    }

    // Increment Article Views
    public boolean incrementViews(int articleId) {
        String sql = "UPDATE articles SET views = views + 1 WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error incrementing views for article " + articleId + ": " + e.getMessage());
            return false;
        }
    }

    // Search Articles
    public List<Article> searchArticles(String query) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE title LIKE ? OR content LIKE ? ORDER BY created_at DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String searchParam = "%" + query + "%";
            pstmt.setString(1, searchParam);
            pstmt.setString(2, searchParam);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Article article = mapResultSetToArticle(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            System.err.println("Error searching articles: " + e.getMessage());
        }
        return articles;
    }

    // Helper method to map ResultSet to Article object
    private Article mapResultSetToArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setCategory(rs.getString("category"));
        article.setUserId(rs.getInt("user_id"));
        article.setCreatedAt(rs.getTimestamp("created_at"));
        article.setViews(rs.getInt("views"));
        return article;
    }
}