package ru.vagapov.userapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.vagapov.userapi.entity.UserEntity;
import ru.vagapov.userapi.model.UserDto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntity2UserDto {

    UserDto map(UserEntity entity);

    List<UserDto> map(List<UserEntity> entity);

    default LocalDate map(OffsetDateTime time) {
        if (time == null) return null;
        return time.toLocalDateTime().toLocalDate();
    }
}
