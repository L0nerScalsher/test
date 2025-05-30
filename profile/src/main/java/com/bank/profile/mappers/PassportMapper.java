package com.bank.profile.mappers;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entities.Passport;
import com.bank.profile.repositories.RegistrationRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {RegistrationMapper.class})
public interface PassportMapper {
    @Mapping(target = "registrationId", source = "registration.id")
    PassportDto toDto(Passport passport);

    @Mapping(target = "registration", ignore = true) // Сервис установит вручную
    Passport toEntity(PassportDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registration", ignore = true) // Сервис обновит вручную
    void updateEntity(@MappingTarget Passport existing, PassportDto dto);
}
