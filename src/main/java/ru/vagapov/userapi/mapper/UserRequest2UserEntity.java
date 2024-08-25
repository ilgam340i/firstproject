package ru.vagapov.userapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.vagapov.userapi.entity.UserEntity;
import ru.vagapov.user.models.UserRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRequest2UserEntity {

    void map(@MappingTarget UserEntity target, UserRequest source);

    default OffsetDateTime map(LocalDate time) {
        if (time == null) return null;
        return time.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
    }
}
