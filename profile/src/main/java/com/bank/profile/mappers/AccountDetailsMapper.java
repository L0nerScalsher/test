package com.bank.profile.mappers;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.AccountDetails;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {
    @Mapping(target = "profile", expression = "java(accountDetails.getProfile() == null ?" +
            " null : profileMapper.toDto(accountDetails.getProfile(), this))")
    AccountDetailsDto toDto(AccountDetails accountDetails, @Context ProfileMapper profileMapper);

    default List<AccountDetailsDto> toDtoList(List<AccountDetails> accountDetails,
                                              @Context ProfileMapper profileMapper) {
        return accountDetails == null ? null : accountDetails.stream()
                .map(ad -> toDto(ad, profileMapper))
                .collect(Collectors.toList());
    }

    @Mapping(target = "profile", ignore = true)
    AccountDetails toEntity(AccountDetailsDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profile", ignore = true)
    void updateEntity(@MappingTarget AccountDetails accountDetails, AccountDetailsDto dto);
}
