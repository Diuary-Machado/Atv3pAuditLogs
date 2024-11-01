package com.www.uniamerica.paciente.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.www.uniamerica.paciente.entity.Consulta;
import com.www.uniamerica.paciente.entity.Usuario;
import com.www.uniamerica.paciente.service.AuditService;

@Aspect
@Component
public class ConsultaAuditAspect {

    @Autowired
    private AuditService auditService;

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.ConsultaService.save(..)) && args(consulta)", returning = "result")
    public void logSave(JoinPoint joinPoint, Object result, Consulta consulta) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "insert", ((Consulta) result).getId());
    }

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.ConsultaService.delete(..)) && args(id)")
    public void logDelete(JoinPoint joinPoint, Long id) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "delete", id);
    }

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.ConsultaService.update(..)) && args(id, consulta)", returning = "result")
    public void logUpdate(JoinPoint joinPoint, Object result, Long id, Consulta consulta) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "update", ((Consulta) result).getId());
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
