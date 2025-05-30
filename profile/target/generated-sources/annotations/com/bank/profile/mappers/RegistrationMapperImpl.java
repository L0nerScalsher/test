package com.bank.profile.mappers;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entities.Registration;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T02:22:32+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public RegistrationDto toDto(Registration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationDto registrationDto = new RegistrationDto();

        registrationDto.setId( registration.getId() );
        registrationDto.setCountry( registration.getCountry() );
        registrationDto.setRegion( registration.getRegion() );
        registrationDto.setCity( registration.getCity() );
        registrationDto.setDistrict( registration.getDistrict() );
        registrationDto.setLocality( registration.getLocality() );
        registrationDto.setStreet( registration.getStreet() );
        registrationDto.setHouseNumber( registration.getHouseNumber() );
        registrationDto.setHouseBlock( registration.getHouseBlock() );
        registrationDto.setFlatNumber( registration.getFlatNumber() );
        registrationDto.setIndex( registration.getIndex() );

        return registrationDto;
    }

    @Override
    public Registration toEntity(RegistrationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Registration registration = new Registration();

        registration.setId( dto.getId() );
        registration.setCountry( dto.getCountry() );
        registration.setRegion( dto.getRegion() );
        registration.setCity( dto.getCity() );
        registration.setDistrict( dto.getDistrict() );
        registration.setLocality( dto.getLocality() );
        registration.setStreet( dto.getStreet() );
        registration.setHouseNumber( dto.getHouseNumber() );
        registration.setHouseBlock( dto.getHouseBlock() );
        registration.setFlatNumber( dto.getFlatNumber() );
        registration.setIndex( dto.getIndex() );

        return registration;
    }
}
