package com.tys.AuditPro.report.controller;

import com.tys.AuditPro.report.repository.AuditReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports/audits")
@RequiredArgsConstructor
public class AuditReportController {

    private final AuditReportRepository auditReportRepository;

    // STEP 2.3 — Audit status summary
    @GetMapping("/status-summary")
    @PreAuthorize("hasRole('MANAGER')")
    public Object auditStatusSummary() {
        return auditReportRepository.countAuditsByStatus();
    }

    // STEP 4.3 — Audit progress report  ✅ ADD THIS HERE
    @GetMapping("/progress")
    @PreAuthorize("hasRole('MANAGER')")
    public Object auditProgress() {
        return auditReportRepository.auditProgress();
    }
}
