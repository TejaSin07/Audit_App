package com.tys.AuditPro.dto;

import lombok.Data;

@Data
public class AuditFormRequest {
    private String formName;
    private String formCode;
    private String description;
}
