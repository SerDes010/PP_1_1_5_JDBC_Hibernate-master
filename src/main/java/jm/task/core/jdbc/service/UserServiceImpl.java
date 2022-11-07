package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

   // UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
    UserDao dao = new UserDaoHibernateImpl();
    public UserServiceImpl(){
    }

    public void createUsersTable() throws SQLException {
    dao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        dao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return this.dao.getAllUsers();
    }
    public void cleanUsersTable() throws SQLException {
        this.dao.cleanUsersTable();
    }

}
