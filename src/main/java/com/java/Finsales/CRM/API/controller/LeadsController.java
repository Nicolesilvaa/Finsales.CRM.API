package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Lead;
import com.java.Finsales.CRM.API.dto.request.CreateLeadRequest;
import com.java.Finsales.CRM.API.dto.request.UpdateEmailLeadRequest;
import com.java.Finsales.CRM.API.dto.request.UpdateStatusLeadRequest;
import com.java.Finsales.CRM.API.service.ConversaoService;
import com.java.Finsales.CRM.API.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/leads")
public class LeadsController {

    private final LeadService leadService;
    private final ConversaoService conversaoService;

    public LeadsController(LeadService leadService, ConversaoService conversaoService) {
        this.leadService = leadService;
        this.conversaoService = conversaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criarLead(@Valid @RequestBody CreateLeadRequest request){
        leadService.criarLead(request);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //201
    }

    @GetMapping()
    public ResponseEntity<List<Lead>>listarLead() {
        return ResponseEntity.ok(leadService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lead> buscarLeadById(@PathVariable Long id) {
        return ResponseEntity.ok(leadService.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public  ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusLeadRequest request){
        leadService.atualizarStatus( id, request.getStatus());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/email")
    public  ResponseEntity<Void> atualizarEmail(@PathVariable Long id, @Valid @RequestBody UpdateEmailLeadRequest request){
        leadService.atualizarEmail(id, request.getEmail());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/converter")
    public ResponseEntity<Void> converterLeadEmCliente(@PathVariable Long id) {
        conversaoService.converterLead(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
