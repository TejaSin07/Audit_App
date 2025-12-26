package com.tys.AuditPro.rules;

import com.tys.AuditPro.domain.history.AuditHistoryType;
import com.tys.AuditPro.service.AuditHistoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleEngine {

    private final List<Rule> rules;
    private final AuditHistoryService auditHistoryService;

    public RuleEngine(List<Rule> rules,
                      AuditHistoryService auditHistoryService) {
        this.rules = rules;
        this.auditHistoryService = auditHistoryService;
    }

    public void fire(RuleContext context) {

        rules.stream()
                .filter(rule -> rule.getEvent() == context.getEvent())
                .filter(rule -> rule.getCondition().evaluate(context))
                .forEach(rule -> {

                    // 1️⃣ Execute rule action
                    rule.getAction().execute(context);

                    // 2️⃣ Log rule execution (NEW)
                    Long auditId = (Long) context.getData().get("auditId");

                    auditHistoryService.log(
                            auditId,
                            "RULE",
                            AuditHistoryType.RULE_EXECUTED,
                            "Rule executed: " + rule.getName()
                    );
                });
    }
}
