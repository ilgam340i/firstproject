package ru.vagapov.userapi.service;

import ru.vagapov.userapi.entity.UserEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void createUsersTable() throws SQLException;

    void dropUsersTable() throws SQLException;

    void saveUser(String firstName,
                  String lastName,
                  LocalDate birthDate,
                  String birthPlace,
                  Byte age) throws SQLException;

    void removeUserById(Long id) throws SQLException;

    List<UserEntity> getAllUsers() throws SQLException;

    void cleanUsersTable();
}
