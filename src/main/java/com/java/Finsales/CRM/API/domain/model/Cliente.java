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


}
