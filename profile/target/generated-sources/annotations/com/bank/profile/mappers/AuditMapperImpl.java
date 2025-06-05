package com.bank.profile.mappers;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entities.Audit;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-05T23:53:38+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AuditMapperImpl implements AuditMapper {

    @Override
    public Audit toEntity(AuditDto dto) {
        if ( dto == null ) {
            return null;
        }

        Audit audit = new Audit();

        audit.setEntityType( dto.getEntityType() );
        audit.setOperationType( dto.getOperationType() );
        audit.setCreatedBy( dto.getCreatedBy() );
        audit.setModifiedBy( dto.getModifiedBy() );
        audit.setCreatedAt( dto.getCreatedAt() );
        audit.setModifiedAt( dto.getModifiedAt() );
        audit.setNewEntityJson( dto.getNewEntityJson() );
        audit.setEntityJson( dto.getEntityJson() );

        return audit;
    }

    @Override
    public AuditDto toDto(Audit entity) {
        if ( entity == null ) {
            return null;
        }

        AuditDto auditDto = new AuditDto();

        auditDto.setId( entity.getId() );
        auditDto.setEntityType( entity.getEntityType() );
        auditDto.setOperationType( entity.getOperationType() );
        auditDto.setCreatedBy( entity.getCreatedBy() );
        auditDto.setModifiedBy( entity.getModifiedBy() );
        auditDto.setCreatedAt( entity.getCreatedAt() );
        auditDto.setModifiedAt( entity.getModifiedAt() );
        auditDto.setNewEntityJson( entity.getNewEntityJson() );
        auditDto.setEntityJson( entity.getEntityJson() );

        return auditDto;
    }
}
