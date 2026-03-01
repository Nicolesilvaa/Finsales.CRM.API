package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.domain.repository.UsuarioRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.PerfilUsuario;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusUsuario;
import com.java.Finsales.CRM.API.domain.utils.exceptions.Usuarios.UsuarioExistenteException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.Usuarios.UsuarioNaoExistenteException;
import com.java.Finsales.CRM.API.dto.request.Usuario.CreateUsuarioRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criar(CreateUsuarioRequest request) {

        Usuario usuario = new Usuario();

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        usuario.setPerfil(PerfilUsuario.VENDEDOR);
        usuario.setStatus(StatusUsuario.ATIVO);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario inativarUsuario(Long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNaoExistenteException("Usuário não encontrado"));

        usuario.setStatus(StatusUsuario.INATIVO);
        usuario.setUltimaAtualizacao(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

}
