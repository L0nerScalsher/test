package com.bank.profile.services.interfaces;


import com.bank.profile.entities.Profile;
import com.bank.profile.entities.Registration;

public interface RegistrationService {

    Registration create(Registration registration);
    Registration update(Long id, Registration registration);
    Registration getById(Long id);
    void delete(Long id);
}
