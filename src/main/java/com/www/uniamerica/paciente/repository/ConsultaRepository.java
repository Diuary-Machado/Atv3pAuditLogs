package com.www.uniamerica.paciente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.www.uniamerica.paciente.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	List<Consulta> findByPacienteId(Long pacienteId);
}
