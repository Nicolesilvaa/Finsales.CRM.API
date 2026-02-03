package com.java.Finsales.CRM.API.domain.model;


import com.java.Finsales.CRM.API.domain.utils.enums.OrigemLead;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusLead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "lead")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column()
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigemLead origem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLead status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;

    public Lead(Long id, String nome, String email, String telefone, OrigemLead origem, StatusLead status, LocalDateTime dataCriacao, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.origem = origem;
        this.status = StatusLead.NOVO;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void atualizarStatus(StatusLead novoStatus) {
        this.status = novoStatus;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

}
