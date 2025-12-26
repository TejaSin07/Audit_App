package com.tys.AuditPro.repository;

import com.tys.AuditPro.domain.audit.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Long> {

    // Used for auditor dashboard
    List<Audit> findByAssignedTo(String username);
}
