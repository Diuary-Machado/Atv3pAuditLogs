package com.www.uniamerica.paciente.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.www.uniamerica.paciente.entity.Doutor;
import com.www.uniamerica.paciente.entity.Usuario;
import com.www.uniamerica.paciente.service.AuditService;

@Aspect
@Component
public class DoutorAuditAspect {

    @Autowired
    private AuditService auditService;

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.DoutorService.save(..)) && args(doutor)", returning = "result")
    public void logSave(JoinPoint joinPoint, Object result, Doutor doutor) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "insert", ((Doutor) result).getId());
    }

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.DoutorService.delete(..)) && args(id)")
    public void logDelete(JoinPoint joinPoint, Long id) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "delete", id);
    }

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.DoutorService.update(..)) && args(id, doutor)", returning = "result")
    public void logUpdate(JoinPoint joinPoint, Object result, Long id, Doutor doutor) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "update", ((Doutor) result).getId());
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            return usuario.getId();
        }
        return null;
    }
}
