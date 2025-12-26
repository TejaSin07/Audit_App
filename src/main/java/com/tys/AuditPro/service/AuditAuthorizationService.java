package com.tys.AuditPro.service;

import com.tys.AuditPro.domain.audit.Audit;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditAuthorizationService {

    public void checkUserAccess(Audit audit) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        boolean isManager =
                auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));

        // MANAGER → full access
        if (isManager) {
            return;
        }

        // USER → only assigned audit
        if (!username.equals(audit.getAssignedTo())) {
            throw new AccessDeniedException(
                    "You are not assigned to this audit");
        }
    }
}
