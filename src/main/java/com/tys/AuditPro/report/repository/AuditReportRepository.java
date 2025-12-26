package com.tys.AuditPro.report.repository;

import com.tys.AuditPro.domain.audit.Audit;
import com.tys.AuditPro.report.dto.AuditProgressReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditReportRepository extends JpaRepository<Audit, Long> {

    @Query("""
        select a.status as status, count(a) as count
        from Audit a
        group by a.status
    """)
    List<AuditStatusCount> countAuditsByStatus();

    @Query("""
    select new com.tys.AuditPro.report.dto.AuditProgressReport(
        a.id,
        a.assignedTo,
        a.status,
        count(f)
    )
    from Audit a
    left join Finding f on f.auditId = a.id
    group by a.id, a.assignedTo, a.status
""")
    List<AuditProgressReport> auditProgress();

}
