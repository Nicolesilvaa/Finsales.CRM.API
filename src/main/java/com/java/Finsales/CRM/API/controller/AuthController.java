package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.dto.request.Usuario.Login;
import com.java.Finsales.CRM.API.dto.response.security.AuthResponse;
import com.java.Finsales.CRM.API.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> autenticar(@Valid @RequestBody Login login) {
        String token = authService.autenticar(login);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}