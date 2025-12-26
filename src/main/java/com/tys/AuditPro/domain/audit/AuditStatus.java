package com.tys.AuditPro.domain.audit;

import lombok.Getter;

@Getter
public enum AuditStatus {

    CREATED,
    ASSIGNED,
    IN_PROGRESS,
    UNDER_REVIEW,
    APPROVED,
    CLOSED;

    public boolean canMoveTo(AuditStatus next) {

        return switch (this) {
            case CREATED -> next == ASSIGNED;
            case ASSIGNED -> next == IN_PROGRESS;
            case IN_PROGRESS -> next == UNDER_REVIEW;
            case UNDER_REVIEW -> next == APPROVED;
            case APPROVED -> next == CLOSED;
            default -> false;
        };
    }
}
