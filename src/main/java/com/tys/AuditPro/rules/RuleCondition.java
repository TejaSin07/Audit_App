package com.tys.AuditPro.rules;

public interface RuleCondition {
    boolean evaluate(RuleContext context);
}
