package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.domain.repository.UsuarioRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusUsuario;
import com.java.Finsales.CRM.API.domain.utils.exceptions.Usuarios.EmailInexistenteException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.Usuarios.UsuarioInativoException;
import com.java.Finsales.CRM.API.dto.request.Usuario.Login;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String autenticar(Login login) {

        Usuario usuario = usuarioRepository.findByEmail(login.getEmail()).orElseThrow(() ->  new EmailInexistenteException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            throw new EmailInexistenteException("Email ou senha inválidos");
        }

        if (!usuario.getStatus().equals(StatusUsuario.ATIVO)) {
            throw new UsuarioInativoException("Usuário inativo");
        }

        return jwtService.gerarToken(usuario);
    }
}

