package com.bank.profile.mappers;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entities.Registration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationDto toDto(Registration registration);

    Registration toEntity(RegistrationDto dto);
}