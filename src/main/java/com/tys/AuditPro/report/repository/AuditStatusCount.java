package com.tys.AuditPro.report.repository;

import com.tys.AuditPro.domain.audit.AuditStatus;

public interface AuditStatusCount {

    AuditStatus getStatus();
    long getCount();
}
