package com.bank.profile.mappers;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.entities.AccountDetails;
import com.bank.profile.entities.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-05T23:53:38+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AccountDetailsMapperImpl implements AccountDetailsMapper {

    @Override
    public AccountDetailsDto toDto(AccountDetails entity) {
        if ( entity == null ) {
            return null;
        }

        AccountDetailsDto accountDetailsDto = new AccountDetailsDto();

        accountDetailsDto.setProfileId( entityProfileId( entity ) );
        accountDetailsDto.setId( entity.getId() );
        accountDetailsDto.setAccountId( entity.getAccountId() );

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
    public List<AccountDetailsDto> toDtoList(List<AccountDetails> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AccountDetailsDto> list = new ArrayList<AccountDetailsDto>( entities.size() );
        for ( AccountDetails accountDetails : entities ) {
            list.add( toDto( accountDetails ) );
        }

        return list;
    }

    private Long entityProfileId(AccountDetails accountDetails) {
        if ( accountDetails == null ) {
            return null;
        }
        Profile profile = accountDetails.getProfile();
        if ( profile == null ) {
            return null;
        }
        Long id = profile.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
