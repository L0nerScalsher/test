package com.bank.profile.repositories;

import com.bank.profile.entities.Profile;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @NotNull
    @EntityGraph(attributePaths = {"passport", "actualRegistration", "accountDetails"})
    Optional<Profile> findById(@NotNull Long id);

    Optional<Profile> findByPassportId(Long passportId);
}
