package ru.vagapov.userapi.service.dispatcher.Impl;

import org.springframework.stereotype.Service;
import ru.vagapov.userapi.model.UserDto;
import ru.vagapov.user.models.*;
import ru.vagapov.userapi.service.dispatcher.UserDispatcherService;
import ru.vagapov.userapi.validation.ValidationService;
import ru.vagapov.userapi.mapper.UserDto2UserResponse;
import ru.vagapov.userapi.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserDispatcherServiceImpl implements UserDispatcherService {

    private final UserService userService;
    private final ValidationService validationService;
    private final UserDto2UserResponse mapper;

    public UserDispatcherServiceImpl(UserService userService,
                                     ValidationService validationService,
                                     UserDto2UserResponse mapper) {
        this.userService = userService;
        this.validationService = validationService;
        this.mapper = mapper;
    }

    @Override
    public ExtendedUserResponse getUser(UUID userGuid) {
        UserDto user = userService.getUser(userGuid);
        return getExtendedUserResponse(user);
    }

    @Override
    public ExtendedUserResponse createUser(CreateUserRequest request) {
        CreateUser data = request.getData();
        if (data != null && data.getUser() != null) {
            validationService.validRequest(request);
            UserDto user = userService.createUser(data.getUser());
            return getExtendedUserResponse(user);
        } else {
            return getExtendedUserResponseError(request);
        }
    }

    @Override
    public ExtendedUserResponse updateUser(UpdateUserRequest request) {
        UpdateUser data = request.getData();
        if (data != null && data.getUser() != null) {
            validationService.validRequest(request);
            UserDto user = userService.updateUser(data.getUserGuid(), data.getUser());
            return getExtendedUserResponse(user);
        } else {
            return getExtendedUserResponseError(request);
        }
    }

    @Override
    public ExtendedUserResponse deleteUser(Integer version, UUID userGuid) {
        validationService.validVersion(version, userGuid);
        UserDto user = userService.deleteUser(userGuid);
        return getExtendedUserResponse(user);
    }

    @Override
    public ListUserResponse getListUser(ListUserRequest listUserRequest) {
        validationService.validRequest(listUserRequest);
        List<UserDto> userList = userService.getListUser(listUserRequest);
        ListUserResponse response = new ListUserResponse();
        response.setData(mapper.map(userList));
        return response;
    }

    private ExtendedUserResponse getExtendedUserResponse(UserDto user) {
        ExtendedUserResponse response = new ExtendedUserResponse();
        UserDataResponse dataResponse = new UserDataResponse();
        response.setData(dataResponse);
        dataResponse.setDeleted(user.getDeleted());
        dataResponse.setUserGuid(user.getUserGuid());
        dataResponse.setVersion(user.getVersion());
        dataResponse.setUser(mapper.map(user));
        return response;
    }

    private ExtendedUserResponse getExtendedUserResponseError(Object request) {
        ExtendedUserResponse response = new ExtendedUserResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("400");
        errorResponse.setMessage("Запрос пришел пустой: %s".formatted(request));
        response.setError(errorResponse);
        return response;
    }
}
