package com.tys.AuditPro.service;

import com.tys.AuditPro.domain.history.AuditHistory;
import com.tys.AuditPro.domain.history.AuditHistoryType;
import com.tys.AuditPro.repository.AuditHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class AuditHistoryService {

    private final AuditHistoryRepository repository;

    public void log(
            Long refId,
            String refType,
            AuditHistoryType action,
            String remarks) {

        String performedBy = getActor();

        AuditHistory history = AuditHistory.builder()
                .referenceId(refId)
                .referenceType(refType)
                .action(action)
                .performedBy(performedBy)
                .remarks(remarks)
                .performedAt(LocalDateTime.now())
                .build();

        repository.save(history);
    }

    private String getActor() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return "SYSTEM";
        }
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}
