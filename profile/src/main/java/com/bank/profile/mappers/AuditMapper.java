package com.bank.profile.mappers;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entities.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditDto toDto(Audit audit);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Audit toEntity(AuditDto dto);
}
