package com.tys.AuditPro.controller;

import com.tys.AuditPro.domain.audit.*;
import com.tys.AuditPro.dto.*;
import com.tys.AuditPro.service.AuditFormService;
import com.tys.AuditPro.service.AuditHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/forms")
@RequiredArgsConstructor
public class AuditFormController {

    private final AuditFormService service;

    @PostMapping
    public AuditForm createForm(@RequestBody AuditFormRequest request) {
        return service.createForm(request);
    }

    @PostMapping("/{formId}/fields")
    public AuditField addField(
            @PathVariable Long formId,
            @RequestBody AuditFieldRequest request) {
        return service.addField(formId, request);
    }

    @PostMapping("/{formId}/activate")
    public AuditForm activateForm(@PathVariable Long formId) {
        return service.activateForm(formId);
    }
}
