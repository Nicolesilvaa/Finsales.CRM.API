package com.java.Finsales.CRM.API.controller;


import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.dto.request.CreateClienteRequest;
import com.java.Finsales.CRM.API.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Void> criarCliente(
            @Valid @RequestBody CreateClienteRequest request
            ){
        clienteService.criarCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //201
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listar());
    }

}
