package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Lead;
import com.java.Finsales.CRM.API.dto.request.CreateLeadRequest;
import com.java.Finsales.CRM.API.dto.request.UpdateStatusLeadRequest;
import com.java.Finsales.CRM.API.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/leads")
public class LeadsController {

    private final LeadService leadService;

    public LeadsController(LeadService leadService) {
        this.leadService = leadService;
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

}
