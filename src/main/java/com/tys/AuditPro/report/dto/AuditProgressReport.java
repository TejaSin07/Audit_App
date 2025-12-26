package com.tys.AuditPro.report.dto;

import com.tys.AuditPro.domain.audit.AuditStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuditProgressReport {

    private Long auditId;
    private String assignedTo;
    private AuditStatus status;
    private long totalFindings;
}
