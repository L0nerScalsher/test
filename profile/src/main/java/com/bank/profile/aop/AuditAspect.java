package com.bank.profile.aop;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.services.interfaces.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService auditService;
    private final ObjectMapper objectMapper;

    // Pointcut для всех методов create в сервисах
    @Pointcut("execution(* com.bank.profile.services.impl.*ServiceImpl.create(..)) && args(dto)")
    public void anyCreateMethod(Object dto) {}

    @AfterReturning(pointcut = "anyCreateMethod(dto)", returning = "result", argNames = "dto,result")
    public void auditCreate(Object dto, Object result) {
        try {
            AuditDto audit = new AuditDto();
            // Определяем entityType на основе типа DTO
            String entityType = determineEntityType(dto.getClass().getSimpleName());
            audit.setEntityType(entityType);
            audit.setOperationType("CREATE");
            audit.setCreatedBy(getCurrentUser());
            audit.setCreatedAt(LocalDateTime.now());
            audit.setEntityJson(objectMapper.writeValueAsString(dto)); // Исходный DTO
            audit.setNewEntityJson(objectMapper.writeValueAsString(result)); // Результат после создания
            auditService.create(audit);
        } catch (Exception e) {
            System.err.println("Audit create failed: " + e.getMessage());
        }
    }

    // Метод для определения entityType по имени класса DTO
    private String determineEntityType(String dtoClassName) {
        return switch (dtoClassName) {
            case "AccountDetailsDto" -> "AccountDetails";
            case "ActualRegistrationDto" -> "ActualRegistration";
            case "PassportDto" -> "Passport";
            case "ProfileDto" -> "Profile";
            case "RegistrationDto" -> "Registration";
            default -> "Unknown";
        };
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() ? authentication.getName() : "anonymous";
    }
}