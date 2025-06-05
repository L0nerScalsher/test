package com.bank.profile.services.interfaces;

import com.bank.profile.dto.ActualRegistrationDto;


public interface ActualRegistrationService {
    ActualRegistrationDto create(ActualRegistrationDto dto);
    ActualRegistrationDto update(Long id, ActualRegistrationDto dto);
    ActualRegistrationDto getById(Long id);
    void delete(Long id);
}
