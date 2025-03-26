package com.simpleblog.util;

import com.simpleblog.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}