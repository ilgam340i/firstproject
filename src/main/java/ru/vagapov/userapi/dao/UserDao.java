package ru.vagapov.userapi.dao;

import ru.vagapov.userapi.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String firstName,
                  String lastName,
                  LocalDate birthDate,
                  String birthPlace,
                  Byte age);

    void removeUserById(Long id);

    List<UserEntity> getAllUsers();

    void cleanUsersTable();
}
