package com.bank.profile.mappers;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entities.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RegistrationMapper.class})
public interface PassportMapper {
    PassportDto toDto(Passport passport);

    Passport toEntity(PassportDto dto);

    void updateEntity(@MappingTarget Passport existing, PassportDto dto);
}
