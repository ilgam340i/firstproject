package ru.vagapov.userapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.vagapov.userapi.model.UserDto;
import ru.vagapov.user.models.ListUserDataResponse;
import ru.vagapov.user.models.UserResponse;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDto2UserResponse {

    UserResponse map(UserDto user);

    List<ListUserDataResponse> map(List<UserDto> user);
}
