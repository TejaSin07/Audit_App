package com.tys.AuditPro.service;

import com.tys.AuditPro.domain.audit.*;
import com.tys.AuditPro.dto.*;
import com.tys.AuditPro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditFormService {

    private final AuditFormRepository formRepository;
    private final AuditFieldRepository fieldRepository;

    public AuditForm createForm(AuditFormRequest request) {

        AuditForm form = AuditForm.builder()
                .formName(request.getFormName())
                .formCode(request.getFormCode())
                .description(request.getDescription())
                .version(1)
                .status(FormStatus.DRAFT)
                .createdAt(LocalDateTime.now())
                .build();

        return formRepository.save(form);
    }

    public AuditField addField(Long formId, AuditFieldRequest request) {

        AuditForm form = formRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        if (form.getStatus() != FormStatus.DRAFT) {
            throw new RuntimeException("Cannot modify active form");
        }

        AuditField field = AuditField.builder()
                .label(request.getLabel())
                .fieldKey(request.getFieldKey())
                .dataType(request.getDataType())
                .inputType(request.getInputType())
                .required(request.isRequired())
                .displayOrder(request.getDisplayOrder())
                .auditForm(form)
                .build();

        return fieldRepository.save(field);
    }

    public AuditForm activateForm(Long formId) {
        AuditForm form = formRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        form.setStatus(FormStatus.ACTIVE);
        return formRepository.save(form);
    }
}
