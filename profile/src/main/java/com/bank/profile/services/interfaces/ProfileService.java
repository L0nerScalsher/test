package com.bank.profile.services.interfaces;


import com.bank.profile.dto.ProfileDto;


public interface ProfileService {
    ProfileDto create(ProfileDto dto);
    ProfileDto update(Long id, ProfileDto dto);
    ProfileDto getById(Long id);
    void delete(Long id);
}
