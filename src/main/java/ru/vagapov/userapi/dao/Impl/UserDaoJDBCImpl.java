package ru.vagapov.userapi.dao.Impl;

import ru.vagapov.userapi.dao.UserDao;
import ru.vagapov.userapi.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String firstName,
                         String lastName,
                         LocalDate birthDate,
                         String birthPlace,
                         Byte age) {

    }

    public void removeUserById(Long id) {

    }

    public List<UserEntity> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
