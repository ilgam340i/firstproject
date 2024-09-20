package ru.vagapov.userapi.service.impl;

import ru.vagapov.userapi.dao.Impl.UserDaoHibernateImpl;
import ru.vagapov.userapi.dao.Impl.UserDaoJDBCImpl;
import ru.vagapov.userapi.dao.UserDao;
import ru.vagapov.userapi.entity.UserEntity;
import ru.vagapov.userapi.service.UserService;
import ru.vagapov.userapi.util.ConnectionUtil;
import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao=userDao;

    }


    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String birthPlace,
            Byte age) {
       userDao.saveUser(firstName,lastName,birthDate,birthPlace,age);

    }

    public void removeUserById(Long id) {
        userDao.removeUserById(id);

    }

    public List<UserEntity> getAllUsers() {

        return  userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();

    }
}
