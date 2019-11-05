package ru.javamentor.dao;

import org.springframework.stereotype.Component;
import ru.javamentor.util.DBHelper;

@Component
public class UserDaoFactory implements AbstractUserDaoFactory {
    @Override
    public UserDao getTypeOfConnection() {
        return new UserDaoByHibernate(DBHelper.getInstance().getConfiguration());
    }
}
