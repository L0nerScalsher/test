package com.bank.profile.services.interfaces;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.dto.ProfileDto;

public interface AuditService {
    void create(AuditDto auditDto);
}
