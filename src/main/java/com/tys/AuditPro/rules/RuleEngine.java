package com.tys.AuditPro.rules;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleEngine {

    private final List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public void fire(RuleContext context) {

        rules.stream()
                .filter(rule -> rule.getEvent() == context.getEvent())
                .filter(rule -> rule.getCondition().evaluate(context))
                .forEach(rule -> rule.getAction().execute(context));
    }
}
