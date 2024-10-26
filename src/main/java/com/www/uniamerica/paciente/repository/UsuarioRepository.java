package com.www.uniamerica.paciente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.www.uniamerica.paciente.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	 Optional<Usuario> findByNomeUsuario(String nomeUsuario);
    
}
