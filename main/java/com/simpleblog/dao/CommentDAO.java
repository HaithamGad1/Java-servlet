package com.simpleblog.dao;

import com.simpleblog.model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDAO {
    private Connection connection;

    public CommentDAO(Connection connection) {
        this.connection = connection;
    }

    public void createComment(Comment comment) {
        String sql = "INSERT INTO comments (article_id, user_id, comment_text) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, comment.getArticleId());
            pstmt.setInt(2, comment.getUserId());
            pstmt.setString(3, comment.getCommentText());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}