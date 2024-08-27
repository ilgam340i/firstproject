package ru.vagapov.userapi;

import org.junit.Assert;
import org.junit.Test;
import ru.vagapov.userapi.entity.UserEntity;
import ru.vagapov.userapi.service.UserService;
import ru.vagapov.userapi.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class UserApiApplicationTests {

    private final UserService userService = new UserServiceImpl();

    private final String testFirstName = "Ivan";
    private final String testLastName = "Ivanov";
    private final LocalDate testBirthDate = LocalDate.now();
    private final String testBirthPlace = "Moscow";
    private final Byte testAge = 5;


    @Test
    public void dropUsersTable() {
        try {
            userService.dropUsersTable();
            userService.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testFirstName, testLastName, testBirthDate, testBirthPlace, testAge);

            UserEntity user = userService.getAllUsers().get(0);

            if (!testFirstName.equals(user.getFirstName())
                    || !testLastName.equals(user.getLastName())
                    || !testBirthPlace.equals(user.getBirthPlace())
                    || !testAge.equals(user.getAge())
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testFirstName, testLastName, testBirthDate, testBirthPlace, testAge);
            userService.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testFirstName, testLastName, testBirthDate, testBirthPlace, testAge);
            List<UserEntity> userList = userService.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testFirstName, testLastName, testBirthDate, testBirthPlace, testAge);
            userService.cleanUsersTable();

            if (!userService.getAllUsers().isEmpty()) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }

}
