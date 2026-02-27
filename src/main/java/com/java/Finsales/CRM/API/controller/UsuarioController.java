package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.dto.request.Usuario.CreateUsuarioRequest;
import com.java.Finsales.CRM.API.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody CreateUsuarioRequest request) {

        Usuario usuario = usuarioService.criarUsuario(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){

        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Usuario> inativar(@PathVariable Long id){

        Usuario usuario = usuarioService.inativarUsuario(id);
        return ResponseEntity.ok(usuario);
    }

}
