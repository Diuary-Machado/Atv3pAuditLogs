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

import com.www.uniamerica.paciente.entity.Consulta;
import com.www.uniamerica.paciente.service.ConsultaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/findall")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> findAll() {
        List<Consulta> consultas = consultaService.findAll();
        
        if (consultas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há consultas disponíveis.");
        }
        
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/findby/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Consulta consulta = consultaService.findById(id);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada com o ID: " + id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> save(@RequestBody Consulta consulta) {
        consultaService.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Consulta criada com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            consultaService.delete(id);
            return ResponseEntity.ok("Consulta deletada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Consulta consulta) {
        try {
            consultaService.update(id, consulta);
            return ResponseEntity.ok("Consulta atualizada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }
    }
}
