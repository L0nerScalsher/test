package com.bank.profile.mappers;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entities.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {RegistrationMapper.class})
public interface PassportMapper {
    @Mapping(target = "registrationId", source = "registration.id")
    PassportDto toDto(Passport passport);

    @Mapping(target = "registration", ignore = true)
    Passport toEntity(PassportDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registration", ignore = true)
    void updateEntity(@MappingTarget Passport existing, PassportDto dto);
}
