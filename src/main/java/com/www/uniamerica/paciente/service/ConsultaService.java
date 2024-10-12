package com.www.uniamerica.paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.www.uniamerica.paciente.entity.Consulta;
import com.www.uniamerica.paciente.repository.ConsultaRepository;

@Service
public class ConsultaService {


	@Autowired
	private ConsultaRepository consultaRepository;

	public List<Consulta> findAll() {
		return consultaRepository.findAll();
	}

	public Consulta findById(Long id) {
		return consultaRepository.findById(id).orElse(null);
	}

	public Consulta save(Consulta consulta) {
		return consultaRepository.save(consulta);
	}

	public void delete(Long id) {
		consultaRepository.deleteById(id);
	}

	public Consulta update(Long id, Consulta consulta) {
		consulta.setId(id);
		return consultaRepository.save(consulta);
	}

}
