package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        try (connection){
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT)";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            Assert.fail("При создании таблицы произошло исключение\n" + e);
            connection.rollback();
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        try (connection){
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS user";
            statement.execute(sql);
            connection.commit();
        } catch (Exception e) {
            Assert.fail("При удалении таблицы произошло исключение\n" + e);
            connection.rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO user (name, lastName, age) VALUES(?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection = Util.getConnection();
        try (connection) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " успешно добавлен в таблицу");
            connection.commit();
        } catch (Exception e) {
            Assert.fail("При добавлении в таблицу User произошло исключение\n" + e);
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        Statement statement;
        Connection connection = Util.getConnection();
        try (connection) {
            statement = connection.createStatement();
            String sql = "DELETE FROM user WHERE id = id";
            statement.executeUpdate(sql);
            System.out.println("User под номером " + id + " успешно удалён");
            connection.commit();
        } catch (Exception e) {
            Assert.fail("При удалении из таблицы User произошло исключение\n" + e);
            connection.rollback();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        Statement statement;
        Connection connection = Util.getConnection();
        try (connection) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Таблица user:");
            while (resultSet.next()) {
                User classUser = new User();
                classUser.setName(resultSet.getString(2));
                classUser.setLastName(resultSet.getString(3));
                classUser.setAge(resultSet.getByte(4));
                users.add(classUser);
            }
            System.out.println(users);
            connection.commit();
        } catch (Exception e) {
            Assert.fail("При запросе таблицы User произошло исключение\n" + e);
            connection.rollback();
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement;
        Connection connection = Util.getConnection();
        try (connection) {
            statement = connection.createStatement();
            String sql = "TRUNCATE table user";
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно очищена");
            connection.commit();
        } catch (Exception e) {
            Assert.fail("При очистки таблицы произошло исключение\n" + e);
            connection.rollback();
        }
    }
}
