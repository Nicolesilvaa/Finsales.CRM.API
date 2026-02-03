package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Lead;
import com.java.Finsales.CRM.API.domain.repository.LeadRepository;
import com.java.Finsales.CRM.API.dto.request.CreateLeadRequest;
import org.springframework.stereotype.Service;

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

        return  leadRepository.findAll();

    }
}
