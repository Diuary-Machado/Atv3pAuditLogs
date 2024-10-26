package com.www.uniamerica.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.www.uniamerica.paciente.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	Paciente findByNome(String nome);

}
