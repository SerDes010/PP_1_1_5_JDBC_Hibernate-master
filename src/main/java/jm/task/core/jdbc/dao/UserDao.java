package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws SQLException;

    void dropUsersTable() throws SQLException;

    void saveUser(String var1, String var2, byte var3) throws SQLException;

    void removeUserById(long var1) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable() throws SQLException;
}
