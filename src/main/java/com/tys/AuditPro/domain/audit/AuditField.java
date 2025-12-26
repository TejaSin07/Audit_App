package com.tys.AuditPro.domain.audit;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audit_fields")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String fieldKey; // a, b, c (used in rules)

    @Column(nullable = false)
    private String dataType; // STRING, NUMBER, BOOLEAN, DATE

    @Column(nullable = false)
    private String inputType; // TEXT, DROPDOWN, etc.

    private Boolean required;

    private Boolean readOnly;

    private Boolean visible;

    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private AuditForm auditForm;
}

