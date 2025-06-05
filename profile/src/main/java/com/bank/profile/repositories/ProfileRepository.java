package com.bank.profile.repositories;

import com.bank.profile.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.passport.id = :passportId")
    Optional<Profile> findByPassportId(@Param("passportId") Long passportId);
}
