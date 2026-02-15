package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Oportunidade;
import com.java.Finsales.CRM.API.dto.request.Oportunidade.CreateOportunidadeRequest;
import com.java.Finsales.CRM.API.dto.request.Oportunidade.UpdateStatusOportunidadeRequest;
import com.java.Finsales.CRM.API.service.OportunidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

    private final OportunidadeService oportunidadeService;

    public OportunidadeController(OportunidadeService oportunidadeService) {
        this.oportunidadeService = oportunidadeService;
    }

    @PostMapping
    public ResponseEntity<Oportunidade> criarOportunidade(@Valid @RequestBody CreateOportunidadeRequest request){

        Oportunidade oportunidade = oportunidadeService.criarOportunidade(
                request.getClienteId(),
                request.getProdutoId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(oportunidade);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusOportunidadeRequest request){

        oportunidadeService.mudarStatusOportunidade(id, request);
        return ResponseEntity.noContent().build();
    }


}
