package ru.vagapov.userapi.validation.impl;

import org.springframework.stereotype.Service;
import ru.vagapov.userapi.validation.ValidationService;

import java.util.UUID;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validRequest(Object request) {

    }

    @Override
    public void validVersion(Integer version, UUID userGuid) {

    }
}
