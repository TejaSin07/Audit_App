package com.tys.AuditPro.repository;

import com.tys.AuditPro.domain.audit.AuditForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuditFormRepository extends JpaRepository<AuditForm, Long> {

    Optional<AuditForm> findByFormCodeAndStatus(String formCode, String status);
}
