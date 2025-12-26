package com.tys.AuditPro.controller;

import com.tys.AuditPro.domain.audit.Audit;
import com.tys.AuditPro.domain.audit.AuditStatus;
import com.tys.AuditPro.domain.history.AuditHistoryType;
import com.tys.AuditPro.dto.CreateAuditRequest;
import com.tys.AuditPro.repository.AuditRepository;
import com.tys.AuditPro.service.AuditAuthorizationService;
import com.tys.AuditPro.service.AuditHistoryService;
import com.tys.AuditPro.service.AuditValidationService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditRepository auditRepository;
    private final AuditValidationService auditValidationService;
    private final AuditHistoryService auditHistoryService;
    private final AuditAuthorizationService auditAuthorizationService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public Audit createAudit(
            @Valid @RequestBody CreateAuditRequest request) {

        String manager =
                SecurityContextHolder.getContext()
                        .getAuthentication().getName();

        Audit audit = new Audit();
        audit.setAuditName(request.getName());
        audit.setStatus(AuditStatus.CREATED);
        audit.setCreatedBy(manager);
        audit.setCreatedAt(LocalDateTime.now());

        Audit saved = auditRepository.save(audit);

        auditHistoryService.log(
                saved.getId(),
                "AUDIT",
                AuditHistoryType.AUDIT_CREATED,
                "Audit created"
        );

        return saved;
    }
    @PutMapping("/{id}/assign/{username}")
    @PreAuthorize("hasRole('MANAGER')")
    public Audit assignAudit(@PathVariable Long id, @PathVariable String username) {

        Audit audit = auditRepository.findById(id).orElseThrow();

        auditValidationService.validateTransition(
                audit.getStatus(),
                AuditStatus.ASSIGNED
        );

        audit.setAssignedTo(username);
        audit.setStatus(AuditStatus.ASSIGNED);
        audit.setUpdatedAt(LocalDateTime.now());

        Audit saved = auditRepository.save(audit);

        auditHistoryService.log(
                saved.getId(),
                "AUDIT",
                AuditHistoryType.AUDIT_ASSIGNED,
                "Assigned to " + username
        );

        return saved;
    }

    @PutMapping("/{id}/start")
    @PreAuthorize("hasRole('USER')")
    public Audit startAudit(@PathVariable Long id) {

        Audit audit = auditRepository.findById(id).orElseThrow();

        auditAuthorizationService.checkUserAccess(audit);

        auditValidationService.validateTransition(
                audit.getStatus(),
                AuditStatus.IN_PROGRESS
        );

        audit.setStatus(AuditStatus.IN_PROGRESS);
        audit.setUpdatedAt(LocalDateTime.now());

        return auditRepository.save(audit);
    }


    @PutMapping("/{id}/submit")
    @PreAuthorize("hasRole('USER')")
    public Audit submitAudit(@PathVariable Long id) {

        Audit audit = auditRepository.findById(id).orElseThrow();

        auditAuthorizationService.checkUserAccess(audit);

        auditValidationService.validateTransition(
                audit.getStatus(),
                AuditStatus.UNDER_REVIEW
        );

        audit.setStatus(AuditStatus.UNDER_REVIEW);
        audit.setUpdatedAt(LocalDateTime.now());

        return auditRepository.save(audit);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('MANAGER')")
    public Audit approveAudit(@PathVariable Long id) {

        Audit audit = auditRepository.findById(id).orElseThrow();

        auditValidationService.validateTransition(
                audit.getStatus(),
                AuditStatus.APPROVED
        );

        audit.setStatus(AuditStatus.APPROVED);
        audit.setUpdatedAt(LocalDateTime.now());

        Audit saved = auditRepository.save(audit);

        auditHistoryService.log(
                saved.getId(),
                "AUDIT",
                AuditHistoryType.AUDIT_APPROVED,
                "Audit approved"
        );

        return saved;
    }

    @PutMapping("/{id}/close")
    @PreAuthorize("hasRole('MANAGER')")
    public Audit closeAudit(@PathVariable Long id) {

        Audit audit = auditRepository.findById(id).orElseThrow();

        auditValidationService.validateTransition(
                audit.getStatus(),
                AuditStatus.CLOSED
        );

        audit.setStatus(AuditStatus.CLOSED);
        audit.setUpdatedAt(LocalDateTime.now());

        Audit saved = auditRepository.save(audit);

        auditHistoryService.log(
                saved.getId(),
                "AUDIT",
                AuditHistoryType.AUDIT_CLOSED,
                "Audit closed"
        );

        return saved;
    }
    // ❗ Remaining methods stay SAME (no request body)
}
