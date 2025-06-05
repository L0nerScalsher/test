package com.bank.profile.dto;

import lombok.Data;

@Data
public class AccountCreatedMessage {
    private Long accountId;
    private Long passportId;
}