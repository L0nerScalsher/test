package com.bank.profile.mappers;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entities.Passport;
import com.bank.profile.entities.Registration;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-05T23:53:38+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PassportMapperImpl implements PassportMapper {

    @Override
    public PassportDto toDto(Passport passport) {
        if ( passport == null ) {
            return null;
        }

        PassportDto passportDto = new PassportDto();

        passportDto.setRegistrationId( passportRegistrationId( passport ) );
        passportDto.setId( passport.getId() );
        passportDto.setSeries( passport.getSeries() );
        passportDto.setNumber( passport.getNumber() );
        passportDto.setLastName( passport.getLastName() );
        passportDto.setFirstName( passport.getFirstName() );
        passportDto.setMiddleName( passport.getMiddleName() );
        passportDto.setGender( passport.getGender() );
        passportDto.setBirthDate( passport.getBirthDate() );
        passportDto.setBirthPlace( passport.getBirthPlace() );
        passportDto.setIssuedBy( passport.getIssuedBy() );
        passportDto.setDateOfIssue( passport.getDateOfIssue() );
        passportDto.setDivisionCode( passport.getDivisionCode() );
        passportDto.setExpirationDate( passport.getExpirationDate() );

        return passportDto;
    }

    @Override
    public Passport toEntity(PassportDto dto) {
        if ( dto == null ) {
            return null;
        }

        Passport passport = new Passport();

        passport.setId( dto.getId() );
        passport.setSeries( dto.getSeries() );
        passport.setNumber( dto.getNumber() );
        passport.setLastName( dto.getLastName() );
        passport.setFirstName( dto.getFirstName() );
        passport.setMiddleName( dto.getMiddleName() );
        passport.setGender( dto.getGender() );
        passport.setBirthDate( dto.getBirthDate() );
        passport.setBirthPlace( dto.getBirthPlace() );
        passport.setIssuedBy( dto.getIssuedBy() );
        passport.setDateOfIssue( dto.getDateOfIssue() );
        passport.setDivisionCode( dto.getDivisionCode() );
        passport.setExpirationDate( dto.getExpirationDate() );

        return passport;
    }

    @Override
    public void updateEntity(Passport existing, PassportDto dto) {
        if ( dto == null ) {
            return;
        }

        existing.setSeries( dto.getSeries() );
        existing.setNumber( dto.getNumber() );
        existing.setLastName( dto.getLastName() );
        existing.setFirstName( dto.getFirstName() );
        existing.setMiddleName( dto.getMiddleName() );
        existing.setGender( dto.getGender() );
        existing.setBirthDate( dto.getBirthDate() );
        existing.setBirthPlace( dto.getBirthPlace() );
        existing.setIssuedBy( dto.getIssuedBy() );
        existing.setDateOfIssue( dto.getDateOfIssue() );
        existing.setDivisionCode( dto.getDivisionCode() );
        existing.setExpirationDate( dto.getExpirationDate() );
    }

    private Long passportRegistrationId(Passport passport) {
        if ( passport == null ) {
            return null;
        }
        Registration registration = passport.getRegistration();
        if ( registration == null ) {
            return null;
        }
        Long id = registration.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
