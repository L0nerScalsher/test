package com.bank.profile.repositories;

import com.bank.profile.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
