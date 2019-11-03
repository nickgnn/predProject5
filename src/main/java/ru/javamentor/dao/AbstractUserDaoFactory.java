package ru.javamentor.dao;

import org.springframework.stereotype.Component;

@Component("userDaoFactory")
public interface AbstractUserDaoFactory {
    UserDao getTypeOfConnection();
}
