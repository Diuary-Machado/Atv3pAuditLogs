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

import com.www.uniamerica.paciente.entity.Doutor;
import com.www.uniamerica.paciente.service.DoutorService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/doutores")
public class DoutorController {

    @Autowired
    private DoutorService doutorService;

    @GetMapping("/findall")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Object> findAll() {
        List<Doutor> doutores = doutorService.findAll();
        
        if (doutores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há doutores disponíveis.");
        }
        
        return ResponseEntity.ok(doutores);
    }

    @GetMapping("/findby/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Doutor doutor = doutorService.findById(id);
        if (doutor != null) {
            return ResponseEntity.ok(doutor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doutor não encontrado com o ID: " + id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> save(@RequestBody Doutor doutor) {
        doutorService.save(doutor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Doutor criado com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            doutorService.delete(id);
            return ResponseEntity.ok("Doutor deletado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doutor não encontrado.");
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Doutor doutor) {
        try {
            doutorService.update(id, doutor);
            return ResponseEntity.ok("Doutor atualizado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doutor não encontrado.");
        }
    }
}
