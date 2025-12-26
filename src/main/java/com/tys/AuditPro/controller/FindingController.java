package com.tys.AuditPro.controller;

import com.tys.AuditPro.domain.finding.*;
import com.tys.AuditPro.domain.history.AuditHistoryType;
import com.tys.AuditPro.repository.FindingRepository;
import com.tys.AuditPro.rules.RuleContext;
import com.tys.AuditPro.rules.RuleEngine;
import com.tys.AuditPro.rules.RuleEvent;
import com.tys.AuditPro.service.AuditHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/findings")
@RequiredArgsConstructor
public class FindingController {

    private final FindingRepository findingRepository;
    private final AuditHistoryService auditHistoryService;
    private final RuleEngine ruleEngine;   // ✅ NEW

    // 1️⃣ Create finding (USER)
    @PostMapping("/audit/{auditId}")
    @PreAuthorize("hasRole('USER')")
    public Finding createFinding(
            @PathVariable Long auditId,
            @RequestBody Finding finding) {

        String auditor =
                SecurityContextHolder.getContext()
                        .getAuthentication().getName();

        finding.setAuditId(auditId);
        finding.setStatus(FindingStatus.OPEN);
        finding.setCreatedBy(auditor);
        finding.setCreatedAt(LocalDateTime.now());

        // ✅ Save finding first
        Finding saved = findingRepository.save(finding);

        // ✅ Audit history (business action)
        auditHistoryService.log(
                saved.getId(),
                "FINDING",
                AuditHistoryType.FINDING_CREATED,
                "Finding created"
        );

        // ✅ RULE ENGINE TRIGGER (THIS WAS MISSING EARLIER)
        ruleEngine.fire(
                RuleContext.builder()
                        .event(RuleEvent.FINDING_CREATED)
                        .data(Map.of(
                                "findingId", saved.getId(),
                                "auditId", saved.getAuditId(),
                                "severity", saved.getSeverity().name()
                        ))
                        .build()
        );

        return saved;
    }

    // 2️⃣ Start finding (USER)
    @PutMapping("/{id}/start")
    @PreAuthorize("hasRole('USER')")
    public Finding startFinding(@PathVariable Long id) {

        Finding finding = findingRepository.findById(id).orElseThrow();

        finding.setStatus(FindingStatus.IN_PROGRESS);
        finding.setUpdatedAt(LocalDateTime.now());

        Finding saved = findingRepository.save(finding);

        auditHistoryService.log(
                saved.getId(),
                "FINDING",
                AuditHistoryType.FINDING_UPDATED,
                "Finding started"
        );

        return saved;
    }

    // 3️⃣ Resolve finding (USER)
    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasRole('USER')")
    public Finding resolveFinding(@PathVariable Long id) {

        Finding finding = findingRepository.findById(id).orElseThrow();

        finding.setStatus(FindingStatus.RESOLVED);
        finding.setUpdatedAt(LocalDateTime.now());

        Finding saved = findingRepository.save(finding);

        auditHistoryService.log(
                saved.getId(),
                "FINDING",
                AuditHistoryType.FINDING_UPDATED,
                "Finding resolved"
        );

        return saved;
    }

    // 4️⃣ Approve finding (MANAGER)
    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('MANAGER')")
    public Finding approveFinding(@PathVariable Long id) {

        Finding finding = findingRepository.findById(id).orElseThrow();

        String manager =
                SecurityContextHolder.getContext()
                        .getAuthentication().getName();

        finding.setStatus(FindingStatus.ACCEPTED);
        finding.setReviewedBy(manager);
        finding.setUpdatedAt(LocalDateTime.now());

        Finding saved = findingRepository.save(finding);

        auditHistoryService.log(
                saved.getId(),
                "FINDING",
                AuditHistoryType.FINDING_APPROVED,
                "Finding approved"
        );

        return saved;
    }

    // 5️⃣ Reject finding (MANAGER)
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('MANAGER')")
    public Finding rejectFinding(@PathVariable Long id) {

        Finding finding = findingRepository.findById(id).orElseThrow();

        String manager =
                SecurityContextHolder.getContext()
                        .getAuthentication().getName();

        finding.setStatus(FindingStatus.REJECTED);
        finding.setReviewedBy(manager);
        finding.setUpdatedAt(LocalDateTime.now());

        Finding saved = findingRepository.save(finding);

        auditHistoryService.log(
                saved.getId(),
                "FINDING",
                AuditHistoryType.FINDING_REJECTED,
                "Finding rejected"
        );

        return saved;
    }

    // 6️⃣ View findings (USER / MANAGER)
    @GetMapping("/audit/{auditId}")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    public List<Finding> getFindings(@PathVariable Long auditId) {
        return findingRepository.findByAuditId(auditId);
    }
}
