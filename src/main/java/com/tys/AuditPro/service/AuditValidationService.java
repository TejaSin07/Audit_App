package com.tys.AuditPro.service;

import com.tys.AuditPro.domain.audit.AuditStatus;
import com.tys.AuditPro.exception.InvalidAuditStateException;
import org.springframework.stereotype.Service;

@Service
public class AuditValidationService {

    public void validateTransition(AuditStatus current, AuditStatus next) {

        if (!current.canMoveTo(next)) {
            throw new InvalidAuditStateException(
                    "Cannot move audit from " + current + " to " + next
            );
        }
    }
}
