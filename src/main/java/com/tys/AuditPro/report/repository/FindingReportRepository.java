package com.tys.AuditPro.report.repository;

import com.tys.AuditPro.domain.finding.Finding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FindingReportRepository extends JpaRepository<Finding, Long> {

    @Query("""
        select f.severity as severity, count(f) as count
        from Finding f
        group by f.severity
    """)
    List<FindingSeverityCount> countBySeverity();
}
