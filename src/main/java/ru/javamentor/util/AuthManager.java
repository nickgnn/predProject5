package ru.javamentor.util;

import ru.javamentor.exception.DBException;
import ru.javamentor.model.User;
import org.apache.commons.lang3.StringUtils;
import ru.javamentor.service.Service;
import ru.javamentor.service.UserService;

public class AuthManager {
    private static Service service = UserService.getInstance();

    private boolean isLogged;
    private String name;
    private String password;

    private static AuthManager authManager;

    public static AuthManager getInstance() {
        if (authManager == null) {
            authManager = new AuthManager();
        }

        return authManager;
    }

    private AuthManager() {
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isLogin(String name, String password) throws DBException {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }

        User user = service.getUserByName(name);

        if (user == null) {
            return false;
        }

        return password.equals(user.getPassword());
    }
}