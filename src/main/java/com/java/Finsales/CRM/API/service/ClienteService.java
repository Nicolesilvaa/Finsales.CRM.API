package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteExistenteException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteNaoEncontradoException;
import com.java.Finsales.CRM.API.dto.request.CreateClienteRequest;
import com.java.Finsales.CRM.API.domain.repository.ClienteRepository;
import com.java.Finsales.CRM.API.dto.request.UpdateClienteRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(CreateClienteRequest request){

        boolean clienteExiste = clienteRepository.existsByDocumento(request.getDocumento());

        if(clienteExiste){
            throw new ClienteExistenteException("O cliente ja existe no sistema");
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

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setStatus(request.getStatus());
        cliente.setUltimaAtualizacao(LocalDateTime.now());

        return clienteRepository.save(cliente);

    }

}
