package com.bank.profile.mappers;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entities.Profile;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {PassportMapper.class, ActualRegistrationMapper.class})
public interface ProfileMapper {
    @Mapping(target = "accountDetails",
            expression = "java(profile.getAccountDetails() == null ? " +
                    "null : accountDetailsMapper.toDtoList(profile.getAccountDetails(), this))")
    ProfileDto toDto(Profile profile, @Context AccountDetailsMapper accountDetailsMapper);

    @Mapping(target = "accountDetails", ignore = true)
    Profile toEntity(ProfileDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountDetails", ignore = true)
    void updateEntity(@MappingTarget Profile profile, ProfileDto dto);
}
