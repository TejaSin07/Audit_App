package com.tys.AuditPro.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiError {

    private String error;
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
