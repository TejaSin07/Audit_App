package com.tys.AuditPro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAuditRequest {

    @NotBlank(message = "Audit name is required")
    private String name;
}

