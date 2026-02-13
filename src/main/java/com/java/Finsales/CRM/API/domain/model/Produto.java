package com.java.Finsales.CRM.API.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = LocalDateTime.now();
    }
}
