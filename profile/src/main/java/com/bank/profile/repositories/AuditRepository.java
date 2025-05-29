package com.bank.profile.repositories;


import com.bank.profile.entities.Audit;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}