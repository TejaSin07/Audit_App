package com.tys.AuditPro.rules.conditions;

import com.tys.AuditPro.rules.RuleCondition;
import com.tys.AuditPro.rules.RuleContext;
import org.springframework.stereotype.Component;

@Component
public class HighSeverityFindingCondition implements RuleCondition {

    @Override
    public boolean evaluate(RuleContext context) {
        Object severity = context.getData().get("severity");
        return severity != null && severity.equals("HIGH");
    }
}
