package com.bank.profile.aop;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.services.interfaces.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
@Slf4j
public class AuditAspect {

    private final AuditService auditService;
    private final ObjectMapper objectMapper;

    @Pointcut("execution(* com.bank.profile.services.impl.*ServiceImpl.create(..)) &&" +
            " !execution(* com.bank.profile.services.impl.AuditServiceImpl.create(..)) && args(dto)")
    public void anyCreateMethod(Object dto) {}

    @AfterReturning(pointcut = "anyCreateMethod(dto)", returning = "result")
    public void auditCreate(JoinPoint joinPoint, Object dto, Object result) {
        try {
            AuditDto audit = new AuditDto();
            String entityType = determineEntityType(dto.getClass().getSimpleName());
            audit.setEntityType(entityType);
            audit.setOperationType("CREATE");
            audit.setCreatedBy(getCurrentUser());
            audit.setCreatedAt(LocalDateTime.now());
            audit.setEntityJson(objectMapper.writeValueAsString(dto));
            audit.setNewEntityJson(objectMapper.writeValueAsString(result));
            auditService.create(audit);
        } catch (Exception e) {
            log.error("Audit update failed: {}", e.getMessage(), e);
        }
    }

    @Pointcut("execution(* com.bank.profile.services.impl.*ServiceImpl.update(..)) && args(dto)")
    public void anyUpdateMethod(Object dto) {}

    @AfterReturning(pointcut = "anyUpdateMethod(dto)", returning = "result")
    public void auditUpdate(JoinPoint joinPoint, Object dto, Object result) {
        try {
            AuditDto audit = new AuditDto();
            String entityType = determineEntityType(dto.getClass().getSimpleName());
            audit.setEntityType(entityType);
            audit.setOperationType("UPDATE");
            audit.setCreatedBy(getCurrentUser());
            audit.setCreatedAt(LocalDateTime.now());
            audit.setEntityJson(objectMapper.writeValueAsString(dto));
            audit.setNewEntityJson(objectMapper.writeValueAsString(result));
            auditService.create(audit);
        } catch (Exception e) {
            log.error("Audit update failed: {}", e.getMessage(), e);
        }
    }

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