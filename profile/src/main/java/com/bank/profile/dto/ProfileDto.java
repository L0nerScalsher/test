package com.bank.profile.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ProfileDto {
    private Long id;

    @NotNull(message = "Phone number must not be null")
    private Long phoneNumber;

    @Size(max = 264, message = "Email must not exceed 264 characters")
    private String email;

    @Size(max = 370, message = "Name on card must not exceed 370 characters")
    private String nameOnCard;

    private Long inn;

    private Long snils;

    @NotNull(message = "Passport ID must not be null")
    private Long passportId;

    @NotNull(message = "Actual registration ID must not be null")
    private Long actualRegistrationId;

    @JsonManagedReference
    private List<AccountDetailsDto> accountDetails = new ArrayList<>();
}