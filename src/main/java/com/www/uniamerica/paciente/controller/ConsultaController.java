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

import com.www.uniamerica.paciente.entity.Consulta;
import com.www.uniamerica.paciente.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@GetMapping("/findall")
	public List<Consulta> findAll(){
		return consultaService.findAll();
	}

	@GetMapping("/findby/{id}")
	public ResponseEntity<Consulta> findById(@PathVariable Long id) {
		Consulta consulta = consultaService.findById(id);
		if (consulta != null) {
			return ResponseEntity.ok(consulta);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/save")
	public Consulta save(@RequestBody Consulta consulta) {
		return consultaService.save(consulta);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		consultaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody Consulta consulta) {
		Consulta updatedConsulta = consultaService.update(id, consulta);
		return ResponseEntity.ok(updatedConsulta);
	}


}
