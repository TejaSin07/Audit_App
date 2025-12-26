package com.tys.AuditPro.rules;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Rule {

    private String name;
    private RuleEvent event;
    private RuleCondition condition;
    private RuleAction action;
}
