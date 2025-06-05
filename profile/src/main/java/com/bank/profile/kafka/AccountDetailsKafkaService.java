package com.bank.profile.kafka;

import com.bank.profile.dto.AccountCreatedMessage;
import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.Profile;
import com.bank.profile.repositories.ProfileRepository;
import com.bank.profile.services.interfaces.AccountDetailsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountDetailsKafkaService {
    private final AccountDetailsService accountDetailsService;
    private final ProfileRepository profileRepository;

    @KafkaListener(topics = "account.created", containerFactory = "accountCreatedListenerContainerFactory")
    public void handleAccountCreated(AccountCreatedMessage message) {
        try {
            log.info("Received AccountCreatedMessage: {}", message);

            Profile profile = profileRepository.findByPassportId(message.getPassportId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Profile not found for passportId: " + message.getPassportId()));

            AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
            accountDetailsDto.setAccountId(message.getAccountId());
            accountDetailsDto.setProfileId(profile.getId());

            AccountDetailsDto savedDto = accountDetailsService.create(accountDetailsDto);
            log.info("AccountDetails created: {}", savedDto);
        } catch (Exception e) {
            log.error("Error processing AccountDetails creation: {}", e.getMessage());
            throw e;
        }
    }
}