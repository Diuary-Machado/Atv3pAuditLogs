package com.www.uniamerica.paciente.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.www.uniamerica.paciente.entity.Paciente;
import com.www.uniamerica.paciente.entity.Usuario;
import com.www.uniamerica.paciente.service.AuditService;

@Aspect
@Component
public class AuditAspect {


    @Autowired
    private AuditService auditService;

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.PacienteService.save(..)) && args(paciente)", returning = "result")
    public void logSave(JoinPoint joinPoint, Object result, Paciente paciente) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "insert", ((Paciente) result).getId());
    }

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.PacienteService.delete(..)) && args(id)")
    public void logDelete(JoinPoint joinPoint, Long id) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "delete", id);
    }

    @AfterReturning(pointcut = "execution(* com.www.uniamerica.paciente.service.PacienteService.update(..)) && args(id, paciente)", returning = "result")
    public void logUpdate(JoinPoint joinPoint, Object result, Long id, Paciente paciente) {
        Long userId = getCurrentUserId();
        auditService.logAction(userId, "update", ((Paciente) result).getId());
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