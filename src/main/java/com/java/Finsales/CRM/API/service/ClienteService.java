package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.dto.request.CreateClienteRequest;
import com.java.Finsales.CRM.API.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void criarCliente(CreateClienteRequest request){

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

        clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

}
