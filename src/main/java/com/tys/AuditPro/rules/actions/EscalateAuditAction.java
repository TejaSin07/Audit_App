package com.tys.AuditPro.rules.actions;

import com.tys.AuditPro.rules.RuleAction;
import com.tys.AuditPro.rules.RuleContext;
import org.springframework.stereotype.Component;

@Component
public class EscalateAuditAction implements RuleAction {

    @Override
    public void execute(RuleContext context) {
        Long auditId = (Long) context.getData().get("auditId");
        System.out.println("🚨 Escalation triggered for audit " + auditId);
    }
}
