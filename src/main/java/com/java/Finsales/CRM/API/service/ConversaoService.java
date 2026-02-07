package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.domain.model.Lead;
import com.java.Finsales.CRM.API.domain.repository.ClienteRepository;
import com.java.Finsales.CRM.API.domain.repository.LeadRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusLead;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ConversaoErrorException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.LeadNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversaoService {

    private final LeadRepository leadRepository;
    private final ClienteRepository clienteRepository;


    public ConversaoService(LeadRepository leadRepository,ClienteRepository clienteRepository) {
        this.leadRepository = leadRepository;
        this.clienteRepository = clienteRepository;
    }

    public Cliente converterLead(Long leadId){

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new LeadNaoEncontradoException("Lead não encontrado"));

        if(lead.getStatus() == StatusLead.CONVERTIDO || lead.getStatus() == StatusLead.DESCARTADO){
            throw  new ConversaoErrorException(  "O lead não pode ser convertido pois seu status atual é " + lead.getStatus());
        }

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(lead.getNome());
        novoCliente.setEmail(lead.getEmail());
        novoCliente.setTelefone(lead.getTelefone());
        novoCliente.setDataCriacao(LocalDateTime.now());
        novoCliente.setUltimaAtualizacao(LocalDateTime.now());

        Cliente clienteSalvo = clienteRepository.save(novoCliente);

        lead.setStatus(StatusLead.CONVERTIDO);
        lead.setUltimaAtualizacao(LocalDateTime.now());

        leadRepository.save(lead);

        return clienteSalvo;

    }
}
