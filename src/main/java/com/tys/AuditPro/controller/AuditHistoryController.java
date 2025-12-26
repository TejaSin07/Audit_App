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

    @GetMapping("/{type}/{id}")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    public List<AuditHistory> getHistory(
            @PathVariable String type,
            @PathVariable Long id) {

        return repository
                .findByReferenceIdAndReferenceTypeOrderByPerformedAtAsc(
                        id, type.toUpperCase());
    }
}
