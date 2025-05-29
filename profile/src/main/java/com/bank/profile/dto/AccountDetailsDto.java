package com.bank.profile.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDetailsDto {
    private Long id;

    @NotNull(message = "Account ID must not be null")
    private Long accountId;

    @JsonBackReference
    @NotNull(message = "Profile must not be null")
    private ProfileDto profile;
}
