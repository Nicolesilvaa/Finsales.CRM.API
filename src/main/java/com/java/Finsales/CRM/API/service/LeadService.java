package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Lead;
import com.java.Finsales.CRM.API.domain.repository.LeadRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusLead;
import com.java.Finsales.CRM.API.domain.utils.exceptions.LeadNaoEncontradoException;
import com.java.Finsales.CRM.API.dto.request.CreateLeadRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadService {
    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead criarLead(CreateLeadRequest request){

        boolean leadExiste = leadRepository.existsByEmail(request.getEmail());

        if(leadExiste){
            throw new IllegalArgumentException("Já existe um lead cadastrado com este e-mail");
        }

        Lead lead = new Lead(
                request.getNome(),
                request.getEmail(),
                request.getTelefone(),
                request.getOrigemLead()
        );

        return leadRepository.save(lead);
    }

    public List<Lead> listarTodos() {
        return leadRepository.findAll();
    }

    public Lead atualizarStatus(Long id, StatusLead novoStatus) {

        Lead lead = buscarPorId(id);

        lead.setStatus(novoStatus);
        lead.setUltimaAtualizacao(LocalDateTime.now());

        return leadRepository.save(lead);
    }

    public Lead buscarPorId(Long id) {
        return leadRepository.findById(id)
                .orElseThrow(() -> new LeadNaoEncontradoException("Lead não encontrado"));
    }


}
