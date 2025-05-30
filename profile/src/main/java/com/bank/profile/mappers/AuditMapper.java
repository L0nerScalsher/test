package com.bank.profile.mappers;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entities.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    @Mapping(target = "id", ignore = true)
    Audit toEntity(AuditDto dto);

    AuditDto toDto(Audit entity);
}
