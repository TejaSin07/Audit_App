package com.tys.AuditPro.domain.audit;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "audit_forms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String formName;

    @Column(nullable = false, unique = true)
    private String formCode;

    private String description;

    @Column(nullable = false)
    private Integer version;

//    @Column(nullable = false)
//    private String status; // DRAFT, ACTIVE, INACTIVE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormStatus status;


    @OneToMany(
            mappedBy = "auditForm",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AuditField> fields;

    private LocalDateTime createdAt;
}

