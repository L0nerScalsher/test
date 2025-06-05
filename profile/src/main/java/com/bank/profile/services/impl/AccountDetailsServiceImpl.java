package com.bank.profile.services.impl;


import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.AccountDetails;
import com.bank.profile.entities.Profile;
import com.bank.profile.mappers.AccountDetailsMapper;
import com.bank.profile.repositories.AccountDetailsRepository;
import com.bank.profile.repositories.ProfileRepository;
import com.bank.profile.services.interfaces.AccountDetailsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final ProfileRepository profileRepository;
    private final AccountDetailsMapper accountDetailsMapper;

    @Override
    @Transactional
    public AccountDetailsDto create(AccountDetailsDto dto) {
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + dto.getProfileId()));

        AccountDetails accountDetails = accountDetailsMapper.toEntity(dto);
        accountDetails.setProfile(profile);
        accountDetails = accountDetailsRepository.save(accountDetails);
        return accountDetailsMapper.toDto(accountDetails);
    }
}