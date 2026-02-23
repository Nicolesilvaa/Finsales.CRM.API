package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.dto.request.Usuario.CreateUsuarioRequest;
import com.java.Finsales.CRM.API.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody CreateUsuarioRequest createUsuarioRequest) {

            Usuario usuario = new Usuario();

            return  ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


}
