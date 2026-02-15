package com.java.Finsales.CRM.API.domain.model;


import com.java.Finsales.CRM.API.domain.utils.enums.StatusOportunidade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "oportunidade")
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOportunidade status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;


    public Oportunidade(Cliente cliente, Produto produto) {

        this.cliente = cliente;
        this.produto = produto;
        this.status = StatusOportunidade.ABERTA;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void mudarStatus(StatusOportunidade novoStatus){

        if(!podeTransicionarPara(novoStatus)){
            throw new RuntimeException(
                    "Transição inválida: " + this.status + " -> " + novoStatus
            );
        }

        this.status = novoStatus;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    private void validarFluxoStatus(StatusOportunidade atual, StatusOportunidade novo){

        if(atual == StatusOportunidade.GANHA || atual == StatusOportunidade.PERDIDA){
            throw new RuntimeException("Oportunidade já finalizada");
        }

        if(atual == StatusOportunidade.ABERTA && novo == StatusOportunidade.GANHA){
            throw new RuntimeException("Não pode pular etapas do funil");
        }

        if((atual == StatusOportunidade.ABERTA|| atual == StatusOportunidade.ABERTA) || (atual == StatusOportunidade.NEGOCIANDO|| atual == StatusOportunidade.NEGOCIANDO ) ) {
            throw new RuntimeException("A oportunidade não pode ser duplicada" );
        }

        if(atual == StatusOportunidade.NEGOCIANDO|| atual == StatusOportunidade.ABERTA){
            throw new RuntimeException("A oportunidade não pode sofreer regressão");
        }

        if(atual == StatusOportunidade.PERDIDA || atual == StatusOportunidade.GANHA){
            throw new RuntimeException("A oportunidade ja está perdida");
        }
    }

    private boolean podeTransicionarPara(StatusOportunidade novo){

        switch (this.status){

            case ABERTA:
                return novo == StatusOportunidade.NEGOCIANDO;

            case NEGOCIANDO:
                return novo == StatusOportunidade.GANHA
                        || novo == StatusOportunidade.PERDIDA;

            case GANHA:
            case PERDIDA:
                return false;
        }

        return false;
    }



}
