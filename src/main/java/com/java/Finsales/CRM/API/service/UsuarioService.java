package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.domain.repository.UsuarioRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.PerfilUsuario;
import com.java.Finsales.CRM.API.domain.utils.exceptions.Usuarios.UsuarioExistenteException;
import com.java.Finsales.CRM.API.dto.request.Usuario.CreateUsuarioRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(CreateUsuarioRequest request){
        boolean usuarioExiste = usuarioRepository.existsByEmail(request.getEmail());

        if(usuarioExiste){
            throw  new UsuarioExistenteException("O usuário já existe no sistema");
        }

        String senhaCriptografada = passwordEncoder.encode(request.getSenha());

        Usuario usuario = new Usuario(
                request.getNome(),
                request.getEmail(),
                senhaCriptografada
        );

        return usuarioRepository.save(usuario);

    }

}
