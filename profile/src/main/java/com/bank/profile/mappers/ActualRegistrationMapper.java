package com.bank.profile.mappers;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entities.ActualRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper {
    ActualRegistrationDto toDto(ActualRegistration actualRegistration);

    ActualRegistration toEntity(ActualRegistrationDto dto);

    void updateEntity(@MappingTarget ActualRegistration existing, ActualRegistrationDto dto);
}

