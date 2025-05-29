package com.bank.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;

    @NotBlank(message = "Country must not be blank")
    @Size(max = 166, message = "Country must not exceed 166 characters")
    private String country;

    @Size(max = 160, message = "Region must not exceed 160 characters")
    private String region;

    @Size(max = 160, message = "City must not exceed 160 characters")
    private String city;

    @Size(max = 160, message = "District must not exceed 160 characters")
    private String district;

    @Size(max = 230, message = "Locality must not exceed 230 characters")
    private String locality;

    @Size(max = 230, message = "Street must not exceed 230 characters")
    private String street;

    @Size(max = 20, message = "House number must not exceed 20 characters")
    private String houseNumber;

    @Size(max = 20, message = "House block must not exceed 20 characters")
    private String houseBlock;

    @Size(max = 40, message = "Flat number must not exceed 40 characters")
    private String flatNumber;

    @NotNull(message = "Index must not be null")
    private Long index;
}