package ru.vagapov.userapi.service;

import ru.vagapov.userapi.model.UserDto;
import ru.vagapov.user.models.ListUserRequest;
import ru.vagapov.user.models.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getUser(UUID userGuid);

    UserDto createUser(UserRequest userRequest);

    UserDto updateUser(UUID userGuid, UserRequest user);

    UserDto deleteUser(UUID userGuid);

    List<UserDto> getListUser(ListUserRequest listUserRequest);
}
