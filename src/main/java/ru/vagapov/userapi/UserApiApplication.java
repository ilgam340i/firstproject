package ru.vagapov.userapi;

import ru.vagapov.userapi.dao.Impl.UserDaoJDBCImpl;
import ru.vagapov.userapi.dao.UserDao;
import ru.vagapov.userapi.entity.UserEntity;
import ru.vagapov.userapi.service.UserService;
import ru.vagapov.userapi.service.impl.UserServiceImpl;
import ru.vagapov.userapi.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class UserApiApplication {

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJDBCImpl() ;
        UserServiceImpl userService = new UserServiceImpl(userDao);
        userService.createUsersTable();
        userService.saveUser("Makaken", "Makak", LocalDate.parse("1995-09-10"), "Uchaly", (byte) 29);
        System.out.println("User with name Makaken has been added to database");
        userService.saveUser("Ilgam", "Isyangulov", LocalDate.parse("1995-09-10"), "Uchaly", (byte) 29);
        System.out.println("User with name Ilgam has been added to database");
        userService.saveUser("Dayan", "Isyangulov", LocalDate.parse("1995-09-10"), "Uchaly", (byte) 29);
        System.out.println("User with name Dayan has been added to database");
        userService.saveUser("Zulfia", "Isyangulova", LocalDate.parse("1997-03-31"), "Mezhgore", (byte) 27);
        System.out.println("User with name Zulfia has been added to database");
        List<UserEntity> userList = userService.getAllUsers();
        for (UserEntity user : userList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
