package com.tys.AuditPro.rules;

import com.tys.AuditPro.rules.actions.EscalateAuditAction;
import com.tys.AuditPro.rules.conditions.HighSeverityFindingCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfig {

    @Bean
    public Rule highSeverityFindingRule(
            HighSeverityFindingCondition condition,
            EscalateAuditAction action) {

        return new Rule(
                "High Severity Finding Escalation",
                RuleEvent.FINDING_CREATED,
                condition,
                action
        );
    }
}
