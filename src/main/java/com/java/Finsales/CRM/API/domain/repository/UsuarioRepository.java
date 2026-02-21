package com.java.Finsales.CRM.API.domain.repository;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByEmail(String email);
}
