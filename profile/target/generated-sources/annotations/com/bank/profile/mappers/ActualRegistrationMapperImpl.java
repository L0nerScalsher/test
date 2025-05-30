package com.bank.profile.mappers;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entities.ActualRegistration;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T02:09:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class ActualRegistrationMapperImpl implements ActualRegistrationMapper {

    @Override
    public ActualRegistrationDto toDto(ActualRegistration actualRegistration) {
        if ( actualRegistration == null ) {
            return null;
        }

        ActualRegistrationDto actualRegistrationDto = new ActualRegistrationDto();

        actualRegistrationDto.setId( actualRegistration.getId() );
        actualRegistrationDto.setCountry( actualRegistration.getCountry() );
        actualRegistrationDto.setRegion( actualRegistration.getRegion() );
        actualRegistrationDto.setCity( actualRegistration.getCity() );
        actualRegistrationDto.setDistrict( actualRegistration.getDistrict() );
        actualRegistrationDto.setLocality( actualRegistration.getLocality() );
        actualRegistrationDto.setStreet( actualRegistration.getStreet() );
        actualRegistrationDto.setHouseNumber( actualRegistration.getHouseNumber() );
        actualRegistrationDto.setHouseBlock( actualRegistration.getHouseBlock() );
        actualRegistrationDto.setFlatNumber( actualRegistration.getFlatNumber() );
        actualRegistrationDto.setIndex( actualRegistration.getIndex() );

        return actualRegistrationDto;
    }

    @Override
    public ActualRegistration toEntity(ActualRegistrationDto dto) {
        if ( dto == null ) {
            return null;
        }

        ActualRegistration actualRegistration = new ActualRegistration();

        actualRegistration.setId( dto.getId() );
        actualRegistration.setCountry( dto.getCountry() );
        actualRegistration.setRegion( dto.getRegion() );
        actualRegistration.setCity( dto.getCity() );
        actualRegistration.setDistrict( dto.getDistrict() );
        actualRegistration.setLocality( dto.getLocality() );
        actualRegistration.setStreet( dto.getStreet() );
        actualRegistration.setHouseNumber( dto.getHouseNumber() );
        actualRegistration.setHouseBlock( dto.getHouseBlock() );
        actualRegistration.setFlatNumber( dto.getFlatNumber() );
        actualRegistration.setIndex( dto.getIndex() );

        return actualRegistration;
    }

    @Override
    public void updateEntity(ActualRegistration existing, ActualRegistrationDto dto) {
        if ( dto == null ) {
            return;
        }

        existing.setId( dto.getId() );
        existing.setCountry( dto.getCountry() );
        existing.setRegion( dto.getRegion() );
        existing.setCity( dto.getCity() );
        existing.setDistrict( dto.getDistrict() );
        existing.setLocality( dto.getLocality() );
        existing.setStreet( dto.getStreet() );
        existing.setHouseNumber( dto.getHouseNumber() );
        existing.setHouseBlock( dto.getHouseBlock() );
        existing.setFlatNumber( dto.getFlatNumber() );
        existing.setIndex( dto.getIndex() );
    }
}
