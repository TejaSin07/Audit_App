package com.tys.AuditPro.repository;

import com.tys.AuditPro.domain.audit.AuditField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditFieldRepository extends JpaRepository<AuditField, Long> {

    List<AuditField> findByAuditFormIdOrderByDisplayOrder(Long formId);
}
