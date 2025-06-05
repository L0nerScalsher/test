package com.bank.profile.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String errorType;
    private String errorMessage;

    public ErrorResponseDto(String errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}