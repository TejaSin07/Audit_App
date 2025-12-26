package com.tys.AuditPro.dto;

import com.tys.AuditPro.domain.finding.FindingSeverity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFindingRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Severity is required")
    private FindingSeverity severity;

    @NotBlank(message = "Description is required")
    private String description;
}
