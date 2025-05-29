package com.bank.profile.services.interfaces;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entities.ActualRegistration;
import com.bank.profile.entities.Registration;

public interface ActualRegistrationService {
    ActualRegistrationDto create(ActualRegistrationDto dto);
    ActualRegistrationDto update(Long id, ActualRegistrationDto dto);
    ActualRegistrationDto getById(Long id);
    void delete(Long id);
}
