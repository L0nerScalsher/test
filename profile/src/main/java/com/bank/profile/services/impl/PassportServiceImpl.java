package com.bank.profile.services.impl;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entities.Passport;
import com.bank.profile.entities.Registration;
import com.bank.profile.mappers.PassportMapper;
import com.bank.profile.repositories.PassportRepository;
import com.bank.profile.repositories.RegistrationRepository;
import com.bank.profile.services.interfaces.PassportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final RegistrationRepository registrationRepository;

    @Override
    @Transactional
    public PassportDto create(PassportDto dto) {
        Registration registration = registrationRepository.findById(dto.getRegistrationId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Registration not found with id: " + dto.getRegistrationId()));

        Passport passport = passportMapper.toEntity(dto);
        passport.setRegistration(registration);
        passport = passportRepository.save(passport);
        return passportMapper.toDto(passport);
    }

    @Override
    @Transactional
    public PassportDto update(Long id, PassportDto dto) {
        Passport existing = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found with id: " + id));

        Registration registration = registrationRepository.findById(dto.getRegistrationId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Registration not found with id: " + dto.getRegistrationId()));

        passportMapper.updateEntity(existing, dto);
        existing.setRegistration(registration);
        passportRepository.save(existing);
        return passportMapper.toDto(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public PassportDto getById(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport not found with id: " + id));
        return passportMapper.toDto(passport);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        passportRepository.deleteById(id);
    }

}
