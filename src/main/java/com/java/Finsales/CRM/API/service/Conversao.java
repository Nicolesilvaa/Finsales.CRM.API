package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.domain.model.Lead;
import com.java.Finsales.CRM.API.domain.repository.ConversaoRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusLead;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ConversaoErrorException;
import org.springframework.stereotype.Service;

@Service
public class Conversao {

        private final ConversaoRepository conversaoRepository;


    public Conversao(ConversaoRepository conversaoRepository) {
        this.conversaoRepository = conversaoRepository;
    }

    public Cliente converterLead(Lead lead){

        Cliente novoCliente = null;

        if(lead.getStatus() == StatusLead.CONVERTIDO || lead.getStatus() == StatusLead.DESCARTADO){
            throw  new ConversaoErrorException(  "O lead não pode ser convertido pois seu status atual é " + lead.getStatus());
        }

        novoCliente.setNome(lead.getNome());
        novoCliente.setEmail(lead.getEmail());
        novoCliente.setTelefone(lead.getTelefone());
        novoCliente.setDataCriacao(lead.getDataCriacao());


    }
}
