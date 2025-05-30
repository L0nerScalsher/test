package com.bank.profile.mappers;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.AccountDetails;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T02:09:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class AccountDetailsMapperImpl implements AccountDetailsMapper {

    @Override
    public AccountDetailsDto toDto(AccountDetails accountDetails, ProfileMapper profileMapper) {
        if ( accountDetails == null ) {
            return null;
        }

        AccountDetailsDto accountDetailsDto = new AccountDetailsDto();

        accountDetailsDto.setId( accountDetails.getId() );
        accountDetailsDto.setAccountId( accountDetails.getAccountId() );

        accountDetailsDto.setProfile( accountDetails.getProfile() == null ? null : profileMapper.toDto(accountDetails.getProfile(), this) );

        return accountDetailsDto;
    }

    @Override
    public AccountDetails toEntity(AccountDetailsDto dto) {
        if ( dto == null ) {
            return null;
        }

        AccountDetails accountDetails = new AccountDetails();

        accountDetails.setId( dto.getId() );
        accountDetails.setAccountId( dto.getAccountId() );

        return accountDetails;
    }

    @Override
    public void updateEntity(AccountDetails accountDetails, AccountDetailsDto dto) {
        if ( dto == null ) {
            return;
        }

        accountDetails.setAccountId( dto.getAccountId() );
    }
}
