package com.www.uniamerica.paciente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.www.uniamerica.paciente.entity.Audit;
import com.www.uniamerica.paciente.service.AuditService;

@RestController
@RequestMapping("/admin/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Audit>> getAllLogs() {
        List<Audit> audits = auditService.getAllAuditLogs();
        return ResponseEntity.ok(audits);
    }
}
