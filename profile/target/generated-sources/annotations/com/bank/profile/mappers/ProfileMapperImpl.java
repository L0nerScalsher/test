package com.bank.profile.mappers;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entities.ActualRegistration;
import com.bank.profile.entities.Passport;
import com.bank.profile.entities.Profile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-05T23:53:38+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDto toDto(Profile profile, AccountDetailsMapper accountDetailsMapper) {
        if ( profile == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        profileDto.setPassportId( profilePassportId( profile ) );
        profileDto.setActualRegistrationId( profileActualRegistrationId( profile ) );
        profileDto.setId( profile.getId() );
        profileDto.setPhoneNumber( profile.getPhoneNumber() );
        profileDto.setEmail( profile.getEmail() );
        profileDto.setNameOnCard( profile.getNameOnCard() );
        profileDto.setInn( profile.getInn() );
        profileDto.setSnils( profile.getSnils() );

        profileDto.setAccountDetails( profile.getAccountDetails() == null ? null : accountDetailsMapper.toDtoList(profile.getAccountDetails()) );

        return profileDto;
    }

    @Override
    public Profile toEntity(ProfileDto dto) {
        if ( dto == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( dto.getId() );
        profile.setPhoneNumber( dto.getPhoneNumber() );
        profile.setEmail( dto.getEmail() );
        profile.setNameOnCard( dto.getNameOnCard() );
        profile.setInn( dto.getInn() );
        profile.setSnils( dto.getSnils() );

        return profile;
    }

    @Override
    public void updateEntity(Profile profile, ProfileDto dto) {
        if ( dto == null ) {
            return;
        }

        profile.setPhoneNumber( dto.getPhoneNumber() );
        profile.setEmail( dto.getEmail() );
        profile.setNameOnCard( dto.getNameOnCard() );
        profile.setInn( dto.getInn() );
        profile.setSnils( dto.getSnils() );
    }

    private Long profilePassportId(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        Passport passport = profile.getPassport();
        if ( passport == null ) {
            return null;
        }
        Long id = passport.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long profileActualRegistrationId(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        ActualRegistration actualRegistration = profile.getActualRegistration();
        if ( actualRegistration == null ) {
            return null;
        }
        Long id = actualRegistration.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
