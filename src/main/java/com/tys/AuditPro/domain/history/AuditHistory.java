package com.tys.AuditPro.domain.history;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long referenceId;        // auditId OR findingId
    private String referenceType;    // AUDIT / FINDING / RULE

    @Enumerated(EnumType.STRING)
    private AuditHistoryType action;

    private String performedBy;
    private String remarks;

    private LocalDateTime performedAt;
}
