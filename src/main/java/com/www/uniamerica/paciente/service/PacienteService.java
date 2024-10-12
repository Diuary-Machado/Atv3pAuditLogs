package com.www.uniamerica.paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.www.uniamerica.paciente.entity.Paciente;
import com.www.uniamerica.paciente.repository.PacienteRepository;

@Service
public class PacienteService {


	@Autowired
	private PacienteRepository pacienteRepository;

	public List<Paciente> findAll(){
		return pacienteRepository.findAll();
	}

	public Paciente findById(Long id) {
		return pacienteRepository.findById(id).orElse(null);
	}

	public Paciente save(Paciente paciente) {
		return pacienteRepository.save(paciente);	

	}

	public void delete(Long id) {	
		pacienteRepository.deleteById(id);
	}

	public Paciente update(Long id, Paciente paciente) {
		paciente.setId(id);
		return pacienteRepository.save(paciente);
	}


}
