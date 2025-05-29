package com.bank.profile.services.impl;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entities.ActualRegistration;
import com.bank.profile.mappers.ActualRegistrationMapper;
import com.bank.profile.repositories.ActualRegistrationRepository;
import com.bank.profile.services.interfaces.ActualRegistrationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ActualRegistrationServiceImpl implements ActualRegistrationService {
    private final ActualRegistrationRepository registrationRepository;
    private final ActualRegistrationMapper registrationMapper;

    @Override
    @Transactional
    public ActualRegistrationDto create(ActualRegistrationDto dto) {
        ActualRegistration registration = registrationMapper.toEntity(dto);
        registration = registrationRepository.save(registration);
        return registrationMapper.toDto(registration);
    }

    @Override
    @Transactional
    public ActualRegistrationDto update(Long id, ActualRegistrationDto dto) {
        ActualRegistration existing = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ActualRegistration not found with id: " + id));
        registrationMapper.updateEntity(existing, dto);
        registrationRepository.save(existing);
        return registrationMapper.toDto(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public ActualRegistrationDto getById(Long id) {
        ActualRegistration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ActualRegistration not found with id: " + id));
        return registrationMapper.toDto(registration);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new EntityNotFoundException("ActualRegistration not found with id: " + id);
        }
        registrationRepository.deleteById(id);
    }
}
