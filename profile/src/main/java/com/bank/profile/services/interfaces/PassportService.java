package com.bank.profile.services.interfaces;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entities.Passport;
import com.bank.profile.entities.Profile;

public interface PassportService {
    PassportDto create(PassportDto dto);
    PassportDto update(Long id, PassportDto dto);
    PassportDto getById(Long id);
    void delete(Long id);
}
