package com.tys.AuditPro.repository;

import com.tys.AuditPro.domain.finding.Finding;
import com.tys.AuditPro.domain.finding.FindingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FindingRepository extends JpaRepository<Finding, Long> {

    List<Finding> findByAuditId(Long auditId);

    List<Finding> findByStatus(FindingStatus status);
}
