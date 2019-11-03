package ru.javamentor.service;

import ru.javamentor.exception.DBException;
import org.springframework.stereotype.Component;
import ru.javamentor.dao.AbstractUserDaoFactory;
import ru.javamentor.dao.UserDaoFactory;
import ru.javamentor.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserService implements Service {
    private static UserService userService;
    private AbstractUserDaoFactory factory;

    public UserService(AbstractUserDaoFactory factory) {
        this.factory = factory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(new UserDaoFactory());
        }

        return userService;
    }

    @Override
    public void addUser(String name, int age) throws DBException {
        try {
            factory.getTypeOfConnection().addUser(name, age);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(String name, int age, String password, String role) throws DBException {
        try {
            factory.getTypeOfConnection().addUser(name, age, password, role);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        try {
            return factory.getTypeOfConnection().getAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User getUserByName(String name) throws DBException {
        try {
            return factory.getTypeOfConnection().getUserByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public long getUserIdByName(String name) throws DBException {
        try {
            return factory.getTypeOfConnection().getUserIdByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user, String name) throws DBException {
        try {
            factory.getTypeOfConnection().updateUser(user, name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user, int age) throws DBException {
        try {
            factory.getTypeOfConnection().updateUser(user, age);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user, String name, int age, String password) throws DBException {
        try {
            factory.getTypeOfConnection().updateUser(user, name, age, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user, String name, int age, String password, String role) throws DBException {
        try {
            factory.getTypeOfConnection().updateUser(user, name, age, password, role);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user, Long id) throws DBException {
        try {
            factory.getTypeOfConnection().updateUser(user, id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean isExistsUser(String name) throws DBException {
        try {
            return factory.getTypeOfConnection().isExistsUser(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserByName(String name) throws DBException {
        try {
            factory.getTypeOfConnection().deleteUserByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserById(Long id) throws DBException {
        try {
            factory.getTypeOfConnection().deleteUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void createTable() throws DBException {
        try {
            factory.getTypeOfConnection().createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void cleanUp() throws DBException {
        try {
            factory.getTypeOfConnection().dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
