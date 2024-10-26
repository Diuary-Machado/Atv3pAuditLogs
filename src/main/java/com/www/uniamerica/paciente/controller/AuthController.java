package com.www.uniamerica.paciente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.www.uniamerica.paciente.entity.Usuario;
import com.www.uniamerica.paciente.repository.UsuarioRepository;
import com.www.uniamerica.paciente.service.UsuarioDetailsServiceImpl;
import com.www.uniamerica.paciente.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Usuario usuario) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getNomeUsuario(), usuario.getSenha())
            );
            
            final Usuario userDetails = (Usuario) userDetailsService.loadUserByUsername(usuario.getNomeUsuario());
            
            final String jwt = jwtUtil.generateToken(userDetails, userDetails.getId());
            
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe.");
        }

        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encodedPassword);
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}
