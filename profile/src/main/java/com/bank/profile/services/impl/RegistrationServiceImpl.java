package com.bank.profile.services.impl;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entities.ActualRegistration;
import com.bank.profile.entities.Registration;
import com.bank.profile.mappers.ActualRegistrationMapper;
import com.bank.profile.mappers.RegistrationMapper;
import com.bank.profile.repositories.ActualRegistrationRepository;
import com.bank.profile.repositories.RegistrationRepository;
import com.bank.profile.services.interfaces.ActualRegistrationService;
import com.bank.profile.services.interfaces.RegistrationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;

    @Override
    @Transactional
    public RegistrationDto create(RegistrationDto dto) {
        if (dto.getCountry() == null || dto.getIndex() == null) {
            throw new IllegalArgumentException("Country and index are required");
        }
        Registration registration = registrationMapper.toEntity(dto);
        Registration savedRegistration = registrationRepository.save(registration);
        return registrationMapper.toDto(savedRegistration);
    }

    @Override
    @Transactional
    public RegistrationDto update(Long id, RegistrationDto dto) {
        Registration existing = registrationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (dto.getCountry() != null) {
            existing.setCountry(dto.getCountry());
        }
        if (dto.getRegion() != null) {
            existing.setRegion(dto.getRegion());
        }
        if (dto.getCity() != null) {
            existing.setCity(dto.getCity());
        }
        if (dto.getDistrict() != null) {
            existing.setDistrict(dto.getDistrict());
        }
        if (dto.getLocality() != null) {
            existing.setLocality(dto.getLocality());
        }
        if (dto.getStreet() != null) {
            existing.setStreet(dto.getStreet());
        }
        if (dto.getHouseNumber() != null) {
            existing.setHouseNumber(dto.getHouseNumber());
        }
        if (dto.getHouseBlock() != null) {
            existing.setHouseBlock(dto.getHouseBlock());
        }
        if (dto.getFlatNumber() != null) {
            existing.setFlatNumber(dto.getFlatNumber());
        }
        if (dto.getIndex() != null) {
            existing.setIndex(dto.getIndex());
        }

        Registration updatedRegistration = registrationRepository.save(existing);
        return registrationMapper.toDto(updatedRegistration);
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrationDto getById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return registrationMapper.toDto(registration);
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