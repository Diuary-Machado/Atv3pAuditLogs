package com.www.uniamerica.paciente.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.www.uniamerica.paciente.entity.Audit;
import com.www.uniamerica.paciente.repository.AuditRepository;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public void logAction(Long userId, String actionType, Long entityId) {
        Audit audit = new Audit();
        audit.setUserId(userId);
        audit.setActionType(actionType);
        audit.setEntityId(entityId);
        audit.setTimestamp(LocalDateTime.now());
        auditRepository.save(audit);
    }
    
    public List<Audit> getAllAuditLogs() {
        return auditRepository.findAll();
    }
}	