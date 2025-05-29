package com.bank.profile.services.impl;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entities.Profile;
import com.bank.profile.mappers.AccountDetailsMapper;
import com.bank.profile.mappers.ProfileMapper;
import com.bank.profile.repositories.ProfileRepository;
import com.bank.profile.services.interfaces.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final AccountDetailsMapper accountDetailsMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public ProfileDto create(ProfileDto dto) {
        Profile profile = profileMapper.toEntity(dto);
        profile = profileRepository.save(profile);
        ProfileDto result = profileMapper.toDto(profile, accountDetailsMapper);
        kafkaTemplate.send("profile.create", result);
        return result;
    }

    @Override
    @Transactional
    public ProfileDto update(Long id, ProfileDto dto) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
        profileMapper.updateEntity(existingProfile, dto);
        profileRepository.save(existingProfile);
        ProfileDto result = profileMapper.toDto(existingProfile, accountDetailsMapper);
        kafkaTemplate.send("profile.update", result);
        return result;
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
        ProfileDto deleteDto = new ProfileDto();
        deleteDto.setId(id);
        kafkaTemplate.send("profile.delete", deleteDto);
    }
}