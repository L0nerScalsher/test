package com.bank.profile.services.interfaces;


import com.bank.profile.dto.RegistrationDto;

public interface RegistrationService {

    RegistrationDto create(RegistrationDto registration);
    RegistrationDto update(Long id, RegistrationDto dto);
    RegistrationDto getById(Long id);
    void delete(Long id);

}

