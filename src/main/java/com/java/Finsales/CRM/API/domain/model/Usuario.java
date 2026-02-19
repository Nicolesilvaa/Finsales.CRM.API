package com.java.Finsales.CRM.API.domain.model;

import com.java.Finsales.CRM.API.domain.utils.enums.PerfilUsuario;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "usuario_interno")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusUsuario status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;

    @Column(nullable = true)
    private LocalDateTime ultimoLogin;

    public Usuario(Long id, String nome, String email, String senha, PerfilUsuario perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.status = StatusUsuario.ATIVO;
    }

    @PrePersist
    public void aoCriar() {
        LocalDateTime agora = LocalDateTime.now();
        this.dataCriacao = agora;
        this.ultimaAtualizacao = agora;
    }

    @PreUpdate
    public void aoAtualizar() {
        this.ultimaAtualizacao = LocalDateTime.now();
    }


    public void atualizarStatus(StatusUsuario novoStatus) {
        this.status = novoStatus;
    }

    public void registrarLogin() {
        this.ultimoLogin = LocalDateTime.now();
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }
}
