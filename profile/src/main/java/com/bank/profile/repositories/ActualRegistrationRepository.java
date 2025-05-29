package com.bank.profile.repositories;

import com.bank.profile.entities.ActualRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualRegistrationRepository extends JpaRepository<ActualRegistration, Long> {
}
