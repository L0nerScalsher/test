package com.bank.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassportDto {
    private Long id;

    @NotNull(message = "Series must not be null")
    private Integer series;

    @NotNull(message = "Number must not be null")
    private Long number;

    @NotBlank(message = "Last name must not be blank")
    @Size(max = 255, message = "Last name must not exceed 255 characters")
    private String lastName;

    @NotBlank(message = "First name must not be blank")
    @Size(max = 255, message = "First name must not exceed 255 characters")
    private String firstName;

    @Size(max = 255, message = "Middle name must not exceed 255 characters")
    private String middleName;

    @NotBlank(message = "Gender must not be blank")
    @Size(max = 3, message = "Gender must not exceed 3 characters")
    private String gender;

    @NotNull(message = "Birth date must not be null")
    private LocalDate birthDate;

    @NotBlank(message = "Birth place must not be blank")
    @Size(max = 480, message = "Birth place must not exceed 480 characters")
    private String birthPlace;

    @NotBlank(message = "Issued by must not be blank")
    private String issuedBy;

    @NotNull(message = "Date of issue must not be null")
    private LocalDate dateOfIssue;

    @NotNull(message = "Division code must not be null")
    private Integer divisionCode;

    private LocalDate expirationDate;

    @NotNull(message = "Registration ID must not be null")
    private Long registrationId;
}
