package com.www.uniamerica.paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.www.uniamerica.paciente.entity.Doutor;
import com.www.uniamerica.paciente.repository.DoutorRepository;

@Service
public class DoutorService {

    @Autowired
    private DoutorRepository doutorRepository;

    public List<Doutor> findAll() {
        return doutorRepository.findAll();
    }

    public Doutor findById(Long id) {
        return doutorRepository.findById(id).orElse(null);
    }

    public Doutor save(Doutor doutor) {
        return doutorRepository.save(doutor);
    }

    public void delete(Long id) {
        doutorRepository.deleteById(id);
    }

    public Doutor update(Long id, Doutor doutor) {
        doutor.setId(id); 
        return doutorRepository.save(doutor);
    }
}
