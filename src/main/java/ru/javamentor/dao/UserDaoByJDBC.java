package ru.javamentor.dao;

import ru.javamentor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoByJDBC implements UserDao {
    private Connection connection;

    public UserDaoByJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (\n" +
                     " `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                     " `name` VARCHAR(45) NOT NULL,\n" +
                     " `age` INT NOT NULL,\n" +
                     "PRIMARY KEY (`id`))";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void dropTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS `users`";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM `users`";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<User> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("password"),
                    resultSet.getString("role")));
        }

        resultSet.close();
        preparedStatement.close();

        return list;
    }

    @Override
    public void addUser(String name, int age) throws SQLException {
        User user = getUserByName(name);

        if (user == null) {
            String sql = "INSERT INTO `users` (`name`, `age`) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    public void addUser(String name, int age, String password, String role) throws SQLException {
        User user = getUserByName(name);

        if (user == null) {
            String sql = "INSERT INTO `users` (`name`, `age`, `password`, `role`) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        String sql = "SELECT * FROM `users` WHERE (`name` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);

        User user;

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user = new User(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("password"),
                    resultSet.getString("role"));
        } else {
            return null;
        }

        resultSet.close();
        preparedStatement.close();

        return user;
    }

    @Override
    public int updateUser(User user, String name) throws SQLException {
        User userCheck = getUserByName(name);
        int rows = 0;

        if (userCheck == null) {
            String sql = "UPDATE `users` SET `name` = ? WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, user.getId());

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
            return rows;
        }

        return rows;
    }

    @Override
    public int updateUser(User user, int age) throws SQLException {
        String sql = "UPDATE `users` SET `age` = ? WHERE (`id` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, age);
        preparedStatement.setLong(2, user.getId());

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        return rows;
    }

    @Override
    public int updateUser(User user, Long ID) throws SQLException {
        String sql = "UPDATE `users` SET `id` = ? WHERE (`id` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, ID);
        preparedStatement.setLong(2, user.getId());

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        return rows;
    }

    @Override
    public int updateUser(User user, String name, int age, String password) throws SQLException {
        String sql = "UPDATE `users` SET `password` = ? WHERE (`id` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, password);
        preparedStatement.setLong(2, user.getId());

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        return rows;
    }

    @Override
    public int updateUser(User user, String name, int age, String password, String role) throws SQLException {
        String sql = "UPDATE `users` SET `role` = ? WHERE (`id` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, role);
        preparedStatement.setLong(2, user.getId());

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        return rows;
    }

    @Override
    public long getUserIdByName(String name) throws SQLException {
        long id = 0;
        User user = getUserByName(name);

        if (user == null) {
            return id;
        } else {
            id = user.getId();
        }

        return id;
    }

    @Override
    public boolean isExistsUser(String name) throws SQLException {
        if (getUserByName(name) == null) {
            return false;
        }

        return true;
    }

    @Override
    public void deleteUserByName(String name) throws SQLException {
        String sql = "DELETE FROM `users` WHERE (`name` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteUserById(Long id) throws SQLException {
        String sql = "DELETE FROM `users` WHERE (`id` = ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);

        preparedStatement.execute();
        preparedStatement.close();
    }
}
