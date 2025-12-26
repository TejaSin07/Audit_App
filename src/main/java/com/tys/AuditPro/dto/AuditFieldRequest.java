package com.tys.AuditPro.dto;

import lombok.Data;

@Data
public class AuditFieldRequest {
    private String label;
    private String fieldKey;
    private String dataType;
    private String inputType;
    private boolean required;
    private int displayOrder;
}
