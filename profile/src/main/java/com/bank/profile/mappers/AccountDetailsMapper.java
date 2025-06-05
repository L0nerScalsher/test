package com.bank.profile.mappers;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.AccountDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {
    @Mapping(target = "profileId", source = "profile.id")
    AccountDetailsDto toDto(AccountDetails entity);

    @Mapping(target = "profile", ignore = true)
    AccountDetails toEntity(AccountDetailsDto dto);

    List<AccountDetailsDto> toDtoList(List<AccountDetails> entities);
}
