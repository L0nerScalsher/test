package com.bank.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditDto {
    private Long id;

    @NotBlank(message = "Entity type must not be blank")
    @Size(max = 40, message = "Entity type must not exceed 40 characters")
    private String entityType;

    @NotBlank(message = "Operation type must not be blank")
    @Size(max = 255, message = "Operation type must not exceed 255 characters")
    private String operationType;

    @NotBlank(message = "Created by must not be blank")
    @Size(max = 255, message = "Created by must not exceed 255 characters")
    private String createdBy;

    @Size(max = 255, message = "Modified by must not exceed 255 characters")
    private String modifiedBy;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String newEntityJson;

    @NotBlank(message = "Entity JSON must not be blank")
    private String entityJson;
}
