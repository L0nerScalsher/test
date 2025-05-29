package com.bank.profile.services.interfaces;

import com.bank.profile.dto.ProfileDto;

public interface AuditService {
    void logChange(String entityType, String operation, Long entityId, String details);
}
