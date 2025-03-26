package com.simpleblog.service;

import com.simpleblog.dao.UserDAO;
import com.simpleblog.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    // إنشاء مستخدم جديد
    public void createUser(User user) throws SQLException {
        userDAO.createUser(user);
    }

    // الحصول على مستخدم بواسطة اسم المستخدم
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    // الحصول على جميع المستخدمين
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    // تحديث معلومات المستخدم
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    // حذف مستخدم بواسطة الـ ID
    public void deleteUser(int id) throws SQLException {
        userDAO.deleteUser(id);
    }

    // الحصول على مستخدم بواسطة الـ ID
    public User getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
    }
}