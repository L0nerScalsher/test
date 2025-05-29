package com.bank.profile.services.impl;

import com.bank.profile.entities.Registration;
import com.bank.profile.repositories.RegistrationRepository;
import com.bank.profile.services.interfaces.RegistrationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Override
    @Transactional
    public Registration create(Registration registration) {
        if (registration.getCountry() == null || registration.getIndex() == null) {
            throw new IllegalArgumentException();
        }
        return registrationRepository.save(registration);
    }

    @Override
    @Transactional
    public Registration update(Long id, Registration registration) {
        Registration existing = registrationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (registration.getCountry() != null) {
            existing.setCountry(registration.getCountry());
        }

        existing.setCountry(registration.getCountry());
        existing.setRegion(registration.getRegion());
        existing.setCity(registration.getCity());
        existing.setDistrict(registration.getDistrict());
        existing.setLocality(registration.getLocality());
        existing.setStreet(registration.getStreet());
        existing.setHouseNumber(registration.getHouseNumber());
        existing.setHouseBlock(registration.getHouseBlock());
        existing.setFlatNumber(registration.getFlatNumber());

        if (registration.getIndex() != null) {
            existing.setIndex(registration.getIndex());
        }

        return registrationRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Registration getById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        registrationRepository.deleteById(id);
    }
}
