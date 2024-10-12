package com.www.uniamerica.paciente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.www.uniamerica.paciente.entity.Paciente;
import com.www.uniamerica.paciente.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {


	@Autowired
	private PacienteService pacienteService;

	@GetMapping("/findall") 
	public List<Paciente> findAll(){
		return pacienteService.findAll();

	}

	@GetMapping("/findby/{id}")
	public ResponseEntity<Paciente> findById(@PathVariable Long id) {
		Paciente paciente = pacienteService.findById(id);
		if (paciente != null) {
			return ResponseEntity.ok(paciente);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/save")
	public Paciente save(@RequestBody Paciente paciente) {
		return pacienteService.save(paciente);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pacienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente) {
		Paciente updatedPaciente = pacienteService.update(id, paciente);
		return ResponseEntity.ok(updatedPaciente);
	}



}


