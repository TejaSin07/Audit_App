package com.tys.AuditPro.controller;

import com.tys.AuditPro.domain.history.AuditHistory;
import com.tys.AuditPro.repository.AuditHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class AuditHistoryController {

    private final AuditHistoryRepository repository;

    /**
     * View history for a specific reference
     * referenceType: AUDIT | FINDING | RULE
     * referenceId  : auditId / findingId
     */
    @GetMapping("/{referenceType}/{referenceId}")
    @PreAuthorize("hasRole('MANAGER')")   // 🔒 tightened
    public List<AuditHistory> getHistory(
            @PathVariable String referenceType,
            @PathVariable Long referenceId) {

        return repository
                .findByReferenceIdAndReferenceTypeOrderByPerformedAtAsc(
                        referenceId,
                        referenceType.toUpperCase()
                );
    }
}
