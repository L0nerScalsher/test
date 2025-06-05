package com.bank.profile.repositories;

import com.bank.profile.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
}
