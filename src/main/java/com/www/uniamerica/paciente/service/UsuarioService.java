package com.www.uniamerica.paciente.service;

import java.util.List;

import com.www.uniamerica.paciente.entity.Usuario;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Long id);
    Usuario createUsuario(Usuario usuario);
    Usuario updateUsuario(Long id, Usuario usuario);
    void deleteUsuario(Long id);
}
