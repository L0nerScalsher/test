package com.bank.profile.kafka;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.services.impl.AccountDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountKafkaConsumer {

    private final AccountDetailsServiceImpl accountDetailsService;

    @KafkaListener(topics = "account.created", groupId = "profile-consumer-group", containerFactory = "accountFactory")
    public void consumeAccount(AccountDetailsDto dto) {
        try {
            accountDetailsService.create(dto);
            System.out.println("Saved: accountId=" + dto.getAccountId() + ", profileId=" + dto.getProfile().getId());
        } catch (Exception e) {
            System.out.println("Error processing: " + e.getMessage());
        }
    }
}
