package com.java.Finsales.CRM.API.domain.model;


import com.java.Finsales.CRM.API.domain.utils.enums.OrigemLead;
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
    private Long id;
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigemLead tipoCliente;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;

}
