package com.bank.profile.services.impl;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entities.Audit;
import com.bank.profile.mappers.AuditMapper;
import com.bank.profile.repositories.AuditRepository;
import com.bank.profile.services.interfaces.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    @Override
    @Transactional
    public void create(AuditDto auditDto) {
        Audit audit = auditMapper.toEntity(auditDto);
        auditRepository.save(audit);
    }
}
