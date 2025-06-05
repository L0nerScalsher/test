package com.bank.profile.services.impl;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entities.ActualRegistration;
import com.bank.profile.entities.Passport;
import com.bank.profile.entities.Profile;
import com.bank.profile.mappers.AccountDetailsMapper;
import com.bank.profile.mappers.ProfileMapper;
import com.bank.profile.repositories.ActualRegistrationRepository;
import com.bank.profile.repositories.PassportRepository;
import com.bank.profile.repositories.ProfileRepository;
import com.bank.profile.services.interfaces.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final PassportRepository passportRepository;
    private final ActualRegistrationRepository actualRegistrationRepository;
    private final ProfileMapper profileMapper;
    private final AccountDetailsMapper accountDetailsMapper;

    @Override
    @Transactional
    public ProfileDto create(ProfileDto dto) {
        Passport passport = passportRepository.findById(dto.getPassportId())
                .orElseThrow(() -> new EntityNotFoundException("Passport not found with id: " + dto.getPassportId()));

        ActualRegistration actualRegistration = null;
        if (dto.getActualRegistrationId() != null) {
            actualRegistration = actualRegistrationRepository.findById(dto.getActualRegistrationId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("ActualRegistration not found with id: " + dto.getActualRegistrationId()));
        }

        Profile profile = profileMapper.toEntity(dto);
        profile.setPassport(passport);
        profile.setActualRegistration(actualRegistration);
        profile = profileRepository.save(profile);
        return profileMapper.toDto(profile, accountDetailsMapper);
    }

    @Override
    @Transactional
    public ProfileDto update(Long id, ProfileDto dto) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));

        Passport passport = passportRepository.findById(dto.getPassportId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Passport not found with id: " + dto.getPassportId()));

        ActualRegistration actualRegistration = null;
        if (dto.getActualRegistrationId() != null) {
            actualRegistration = actualRegistrationRepository.findById(dto.getActualRegistrationId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("ActualRegistration not found with id: " +
                                                        dto.getActualRegistrationId()));
        }

        profileMapper.updateEntity(existingProfile, dto);
        existingProfile.setPassport(passport);
        existingProfile.setActualRegistration(actualRegistration);
        profileRepository.save(existingProfile);
        return profileMapper.toDto(existingProfile, accountDetailsMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto getById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
        return profileMapper.toDto(profile, accountDetailsMapper);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
        profileRepository.delete(profile);
    }
}
