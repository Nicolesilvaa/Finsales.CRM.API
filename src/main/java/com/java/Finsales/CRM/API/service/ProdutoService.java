package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.domain.model.Produto;
import com.java.Finsales.CRM.API.domain.repository.ProdutoRepository;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ProdutoExistenteException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ProdutoNaoEncontradoException;
import com.java.Finsales.CRM.API.dto.request.CreateProdutoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto(CreateProdutoRequest request){

        boolean produtoExiste = produtoRepository.existsByNome(request.getNome());

        if(produtoExiste){
            throw new ProdutoExistenteException("O produto ja existe no sistema");
        }

        Produto produto = new Produto(request.getNome(), request.getDescricao());

        return  produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }


    public Produto buscarById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

    }





}
