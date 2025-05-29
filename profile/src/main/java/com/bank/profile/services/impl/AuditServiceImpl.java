package com.bank.profile.services.impl;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entities.Audit;
import com.bank.profile.mappers.AuditMapper;
import com.bank.profile.repositories.AuditRepository;
import com.bank.profile.services.interfaces.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Transactional
    public void logChange(String entityType, String operation, Long entityId, String details) {
        Audit audit = new Audit();
        audit.setEntityType(entityType);
        audit.setOperationType(operation);
        audit.setCreatedBy("system");
        audit.setCreatedAt(LocalDateTime.now());
        audit.setEntityJson("{\"id\":" + entityId + ", \"details\":\"" + details + "\"}");
        auditRepository.save(audit);
    }
}
