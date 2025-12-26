package com.tys.AuditPro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

    @GetMapping("/manager/test")
    public String managerTest() {
        return "Manager access OK";
    }
}
