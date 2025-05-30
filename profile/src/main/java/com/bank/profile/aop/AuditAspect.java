package com.bank.profile.aop;


import com.bank.profile.dto.AuditDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.services.interfaces.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @AfterReturning(pointcut = "execution(* com.bank.profile.services.impl.ProfileServiceImpl.create(..))",
            returning = "result")
    public void auditCreate(ProfileDto result) {
        try {
            AuditDto audit = new AuditDto();
            audit.setEntityType("Profile");
            audit.setOperationType("CREATE");
            audit.setCreatedBy(getCurrentUser());
            audit.setCreatedAt(LocalDateTime.now());
            audit.setEntityJson(objectMapper.writeValueAsString(result));
            audit.setNewEntityJson(objectMapper.writeValueAsString(result));
            auditService.create(audit);
        } catch (Exception e) {
            // Логируем ошибку, но не ломаем основной процесс
            System.err.println("Audit create failed: " + e.getMessage());
        }
    }

    @AfterReturning(pointcut = "execution(* com.bank.profile.services.impl.ProfileServiceImpl.update(..))",
            returning = "result")
    public void auditUpdate(ProfileDto result) {
        try {
            AuditDto audit = new AuditDto();
            audit.setEntityType("Profile");
            audit.setOperationType("UPDATE");
            audit.setCreatedBy(getCurrentUser());
            audit.setModifiedBy(getCurrentUser());
            audit.setCreatedAt(LocalDateTime.now());
            audit.setModifiedAt(LocalDateTime.now());
            audit.setEntityJson(objectMapper.writeValueAsString(result));
            audit.setNewEntityJson(objectMapper.writeValueAsString(result));
            auditService.create(audit);
        } catch (Exception e) {
            System.err.println("Audit update failed: " + e.getMessage());
        }
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() ? authentication.getName() : "anonymous";
    }
}