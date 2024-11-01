package com.www.uniamerica.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.www.uniamerica.paciente.entity.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
	
 
}