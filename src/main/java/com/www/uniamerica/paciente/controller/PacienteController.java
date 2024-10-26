package com.www.uniamerica.paciente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/findall")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> findAll() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há pacientes disponíveis.");
        }
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/findby/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Paciente paciente = pacienteService.findById(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado com o ID: " + id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> save(@RequestBody Paciente paciente) {
        pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Paciente criado com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            pacienteService.delete(id);
            return ResponseEntity.ok("Paciente deletado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            pacienteService.update(id, paciente);
            return ResponseEntity.ok("Paciente atualizado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
    }
}
