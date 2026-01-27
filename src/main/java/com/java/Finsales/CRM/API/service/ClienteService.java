package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.dto.request.CreateClienteRequest;
import com.java.Finsales.CRM.API.domain.repository.ClienteRepository;
import com.java.Finsales.CRM.API.dto.request.UpdateClienteRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(CreateClienteRequest request){

        boolean clienteExiste = clienteRepository.existsByDocumento(request.getDocumento());

        if(clienteExiste){
            throw new IllegalArgumentException("Cliente já cadastrado com este documento");
        }

        Cliente cliente = new Cliente(
                request.getNome(),
                request.getDocumento(),
                request.getEmail(),
                request.getTelefone(),
                request.getTipoCliente()
        );

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente atualizar(Long id, UpdateClienteRequest request){

        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setStatus(request.getStatus());
        cliente.setUltimaAtualizacao(LocalDateTime.now());

        return clienteRepository.save(cliente);

    }

}
