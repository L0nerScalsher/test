package com.bank.profile.services.interfaces;

import com.bank.profile.dto.AccountDetailsDto;


public interface AccountDetailsService {
    AccountDetailsDto create(AccountDetailsDto dto);
    AccountDetailsDto update(Long id, AccountDetailsDto dto);
    AccountDetailsDto getById(Long id);
    void delete(Long id);

}
