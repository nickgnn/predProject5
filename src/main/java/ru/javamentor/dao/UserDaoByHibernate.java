package ru.javamentor.dao;

import org.springframework.stereotype.Component;
import ru.javamentor.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoByHibernate implements UserDao {
    private Session session;
    private Configuration configuration = getConfiguration();

    public UserDaoByHibernate() {
        this.session = createSessionFactory().openSession();
    }

    private Session createNewSession() {
        return createSessionFactory().openSession();
    }

    private SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?serverTimezone=Europe/Moscow");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

        return configuration;
    }

    @Override
    public void addUser(String name, int age) throws SQLException {
        this.session = createNewSession();

        User user = getUserByName(name);

        if (user == null) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, age));
            transaction.commit();
            session.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    public void addUser(String name, int age, String password, String role) throws SQLException {
        this.session = createNewSession();

        User user = getUserByName(name);

        if (user == null) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, age, password, role));
            transaction.commit();
            session.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        List<User> users = session.createQuery("FROM User").list();

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        this.session = createNewSession();

        Transaction transaction =  session.beginTransaction();

        String hql = "FROM User WHERE name = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", name);
        List<User> users = query.list();

        transaction.commit();

        if (users.size() == 0) {
            return null;
        }

        session.close();

        return users.get(0);
    }

    @Override
    public long getUserIdByName(String name) throws SQLException {
        this.session = createNewSession();

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
    public int updateUser(User user, String name) throws SQLException {
        this.session = createNewSession();

        User userCheck = getUserByName(name);
        int rows = 0;

        if (userCheck == null) {
            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE User SET name = :newName where id = :userID";
            Query query = session.createQuery(hql);
            query.setParameter("newName", name);
            query.setParameter("userID", user.getId());

            rows = query.executeUpdate();

            transaction.commit();
            session.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
            return rows;
        }

        return rows;
    }

    @Override
    public int updateUser(User user, int age) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE User SET age = :newAge where id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("newAge", age);
        query.setParameter("userID", user.getId());

        int res = query.executeUpdate();

        transaction.commit();
        session.close();

        return res;
    }

    @Override
    public int updateUser(User user, Long ID) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE User SET id = :newID where id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("newID", ID);
        query.setParameter("userID", user.getId());

        int res = query.executeUpdate();

        transaction.commit();
        session.close();

        return res;
    }

    @Override
    public int updateUser(User user, String name, int age, String password) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE User SET password = :newPassword where id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("newPassword", password);
        query.setParameter("userID", user.getId());

        int res = query.executeUpdate();

        transaction.commit();
        session.close();

        return res;
    }

    @Override
    public int updateUser(User user, String name, int age, String password, String role) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE User SET role = :newRole where id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("newRole", role);
        query.setParameter("userID", user.getId());

        int res = query.executeUpdate();

        transaction.commit();
        session.close();

        return res;
    }

    @Override
    public void deleteUserByName(String name) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "DELETE User WHERE name = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", name);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserById(Long id) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "DELETE User WHERE id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("userID", id);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void createTable() throws SQLException {
//        String sql = "CREATE TABLE IF NOT EXISTS `users` (\n" +
//                     " `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
//                     " `name` VARCHAR(45) NOT NULL,\n" +
//                     " `age` INT NOT NULL,\n" +
//                     "PRIMARY KEY (`id`))";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.execute();
//        preparedStatement.close();
    }

    @Override
    public void dropTable() throws SQLException {
//        String sql = "DROP TABLE IF EXISTS `users`";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.execute();
//        preparedStatement.close();
    }
}
