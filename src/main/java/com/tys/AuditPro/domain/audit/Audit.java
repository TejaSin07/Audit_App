package com.tys.AuditPro.domain.audit;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audits")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String auditName;

    private String description;

    @Enumerated(EnumType.STRING)
    private AuditStatus status;

    // Who created the audit
    private String createdBy;

    // Assigned auditor username
    private String assignedTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
