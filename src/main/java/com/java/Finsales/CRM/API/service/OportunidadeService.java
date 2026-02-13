package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import com.java.Finsales.CRM.API.domain.model.Oportunidade;
import com.java.Finsales.CRM.API.domain.model.Produto;
import com.java.Finsales.CRM.API.domain.repository.ClienteRepository;
import com.java.Finsales.CRM.API.domain.repository.OportunidadeRepository;
import com.java.Finsales.CRM.API.domain.repository.ProdutoRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.EstadoCliente;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteInativoException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteNaoEncontradoException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ProdutoNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class OportunidadeService {

    private final OportunidadeRepository oportunidadeRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;


    public OportunidadeService(
            OportunidadeRepository oportunidadeRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.oportunidadeRepository = oportunidadeRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Oportunidade criarOportunidade(Long clienteId, Long produtoId) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        if (cliente.getStatus() != EstadoCliente.ATIVO) {
            throw new ClienteInativoException("Cliente não está ativo");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));


        Oportunidade oportunidade = new Oportunidade(cliente, produto);

        return oportunidadeRepository.save(oportunidade);
    }
}
