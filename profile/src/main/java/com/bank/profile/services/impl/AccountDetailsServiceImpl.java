package com.bank.profile.services.impl;


import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.AccountDetails;
import com.bank.profile.entities.Profile;
import com.bank.profile.mappers.AccountDetailsMapper;
import com.bank.profile.mappers.ProfileMapper;
import com.bank.profile.repositories.AccountDetailsRepository;
import com.bank.profile.repositories.ProfileRepository;
import com.bank.profile.services.interfaces.AccountDetailsService;
import jakarta.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Slf4j
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountDetailsMapper accountDetailsMapper;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    @Transactional
    public AccountDetailsDto create(AccountDetailsDto dto) {
        Profile profile = profileRepository.findById(dto.getProfile().getId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + dto.getProfile().getId()));
        AccountDetails accountDetails = accountDetailsMapper.toEntity(dto);
        accountDetails.setProfile(profile);
        accountDetails = accountDetailsRepository.save(accountDetails);
        log.info("Created AccountDetails with id: {}", accountDetails.getId());
        return accountDetailsMapper.toDto(accountDetails, profileMapper);
    }

    @Override
    @Transactional
    public AccountDetailsDto update(Long id, AccountDetailsDto dto) {
        AccountDetails existing = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountDetails not found with id: " + id));
        accountDetailsMapper.updateEntity(existing, dto);
        existing.setProfile(profileRepository.findById(dto.getProfile().getId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + dto.getProfile().getId())));
        accountDetailsRepository.save(existing);
        log.info("Updated AccountDetails with id: {}", id);
        return accountDetailsMapper.toDto(existing, profileMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDetailsDto getById(Long id) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountDetails not found with id: " + id));
        return accountDetailsMapper.toDto(accountDetails, profileMapper);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!accountDetailsRepository.existsById(id)) {
            throw new EntityNotFoundException("AccountDetails not found with id: " + id);
        }
        accountDetailsRepository.deleteById(id);
        log.info("Deleted AccountDetails with id: {}", id);
    }

    @Transactional
    public void createFromKafka(Long accountId, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + profileId));
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(System.currentTimeMillis()); // Замени на свой ID
        accountDetails.setAccountId(accountId);
        accountDetails.setProfile(profile);
        accountDetailsRepository.save(accountDetails);
        log.info("Saved from Kafka: accountId={}, profileId={}", accountId, profileId);
    }
}