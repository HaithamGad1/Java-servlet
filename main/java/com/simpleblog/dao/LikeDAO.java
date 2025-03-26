package com.simpleblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDAO {
    private Connection connection;

    public LikeDAO(Connection connection) {
        this.connection = connection;
    }

    public void likeArticle(int articleId, int userId) throws SQLException {
        String sql = "INSERT INTO likes (article_id, user_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
    }

    public int getLikesCount(int articleId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM likes WHERE article_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }
}