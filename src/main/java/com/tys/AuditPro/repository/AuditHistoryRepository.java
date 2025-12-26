package com.tys.AuditPro.repository;

import com.tys.AuditPro.domain.history.AuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditHistoryRepository
        extends JpaRepository<AuditHistory, Long> {

    List<AuditHistory> findByReferenceIdAndReferenceTypeOrderByPerformedAtAsc(
            Long referenceId, String referenceType);
}
