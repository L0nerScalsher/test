package com.bank.profile.services.interfaces;

import com.bank.profile.dto.PassportDto;


public interface PassportService {
    PassportDto create(PassportDto dto);
    PassportDto update(Long id, PassportDto dto);
    PassportDto getById(Long id);
    void delete(Long id);
}
