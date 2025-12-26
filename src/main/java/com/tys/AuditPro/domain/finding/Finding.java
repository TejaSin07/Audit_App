package com.tys.AuditPro.domain.finding;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_findings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Finding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Parent audit
    private Long auditId;

    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private FindingSeverity severity;

    @Enumerated(EnumType.STRING)
    private FindingStatus status;

    // Auditor who created it
    private String createdBy;

    // Manager who approved/rejected
    private String reviewedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
