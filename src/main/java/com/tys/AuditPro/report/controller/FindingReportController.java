package com.tys.AuditPro.report.controller;

import com.tys.AuditPro.report.repository.FindingReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports/findings")
@RequiredArgsConstructor
public class FindingReportController {

    private final FindingReportRepository findingReportRepository;

    @GetMapping("/severity-summary")
    @PreAuthorize("hasRole('MANAGER')")
    public Object findingSeveritySummary() {
        return findingReportRepository.countBySeverity();
    }
}
