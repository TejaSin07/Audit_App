package com.tys.AuditPro.report.repository;

import com.tys.AuditPro.domain.finding.FindingSeverity;

public interface FindingSeverityCount {

    FindingSeverity getSeverity();
    long getCount();
}
