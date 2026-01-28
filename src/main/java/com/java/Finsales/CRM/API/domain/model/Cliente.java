package com.java.Finsales.CRM.API.domain.model;

import com.java.Finsales.CRM.API.domain.utils.enums.EstadoCliente;
import com.java.Finsales.CRM.API.domain.utils.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCliente tipoCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCliente status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;


    public Cliente(
            String nome,
            String documento,
            String email,
            String telefone,
            TipoCliente tipoCliente
    ) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.tipoCliente = tipoCliente;

        this.status = EstadoCliente.ATIVO;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
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
