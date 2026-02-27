package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.model.Produto;
import com.java.Finsales.CRM.API.dto.request.Produto.CreateProdutoRequest;
import com.java.Finsales.CRM.API.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody CreateProdutoRequest request){
        Produto produtoCriado = produtoService.criarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }


    @PreAuthorize("hasAnyRole('ADMIN','VENDEDOR')")
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @PreAuthorize("hasAnyRole('ADMIN','VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutosById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarById(id));
    }
}
