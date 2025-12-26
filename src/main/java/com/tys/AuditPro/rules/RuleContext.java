package com.tys.AuditPro.rules;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class RuleContext {

    private RuleEvent event;
    private Map<String, Object> data;

}
