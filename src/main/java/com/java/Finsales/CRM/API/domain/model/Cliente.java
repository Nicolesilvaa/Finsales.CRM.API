package com.java.Finsales.CRM.API.domain.model;

import com.java.Finsales.CRM.API.domain.utils.EstadoCliente;
import com.java.Finsales.CRM.API.domain.utils.TipoCliente;

import java.time.LocalDateTime;

public class Cliente {

    private Long id;

    private String nome;
    private String documento;
    private String email;
    private String telefone;

    private TipoCliente tipoCliente;
    private EstadoCliente status;

    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;

    public Cliente(Long id, String nome, String documento, String email, String telefone, TipoCliente tipoCliente, EstadoCliente status, LocalDateTime dataCriacao, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.tipoCliente = tipoCliente;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public void atualizarContato(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void inativar() {
        this.status = EstadoCliente.INATIVO;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

}
