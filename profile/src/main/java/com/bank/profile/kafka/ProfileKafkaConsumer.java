package com.bank.profile.kafka;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entities.Profile;
import com.bank.profile.mappers.ProfileMapper;
import com.bank.profile.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileKafkaConsumer {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;


    @KafkaListener(topics = "profile.create.request")
    public void profileCreate(ProfileDto dto) {
        profileService.create(dto);
    }

    @KafkaListener(topics = "profile.update.request")
    public void profileUpdate(ProfileDto dto) {
        Profile profile = profileMapper.toEntity(dto);
        profileService.update(profile.getId(), dto);
    }

    @KafkaListener(topics = "profile.get.request")
    public void consumeProfileUpdate(ProfileDto profileDto) {
        profileService.update(profileDto.getId(), profileDto);
    }

    @KafkaListener(topics = "profile.delete.request")
    public void consumeProfileDelete(ProfileDto profileDto) {
        profileService.delete(profileDto.getId());
    }

}

